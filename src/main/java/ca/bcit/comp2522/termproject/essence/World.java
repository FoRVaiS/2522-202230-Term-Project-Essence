package ca.bcit.comp2522.termproject.essence;

import java.util.ArrayList;
import java.util.HashMap;

import ca.bcit.comp2522.termproject.essence.interfaces.LogicComponent;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * Manages objects in the world.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class World implements LogicComponent {
  private final HashMap<Integer, Chunk> chunks = new HashMap<>();
  private final ArrayList<LogicComponent> logicalChildren = new ArrayList<>();

  private final Group sceneGroup;

  /**
   * Creates an instance of the world.
   *
   * @param sceneGroup scene group for the world view
   */
  public World(final Group sceneGroup) {
    this.sceneGroup = sceneGroup;
    this.update();
  }

  /**
   * Updates chunks around a point as far as the render distance allows.
   *
   * @param posX           x coordinate of a point
   * @param posY           y coordinate of a point
   * @param renderDistance distance around point to render chunks
   * @return Arraylist of chunks rendered in this cycle
   */
  private ArrayList<Integer> updateChunks(final int posX, final int posY, final int renderDistance) {
    final int tileSize = Sprite.TILE_SIZE;
    final int tilesInChunk = Chunk.CHUNK_SIZE;
    final int chunkLength = tileSize * tilesInChunk;

    final int xStart = (int) Math.floor((posX - renderDistance) / chunkLength);
    final int xEnd = (int) Math.floor((posX + renderDistance) / chunkLength);

    final int yStart = (int) Math.floor((posY - renderDistance) / chunkLength);
    final int yEnd = (int) Math.floor((posY + renderDistance) / chunkLength);

    final ArrayList<Integer> renderedChunkIds = new ArrayList<>();

    for (int yOffset = yStart; yOffset <= yEnd; yOffset++) {
      for (int xOffset = xStart; xOffset <= xEnd; xOffset++) {
        final int chunkPosX = xOffset * chunkLength;
        final int chunkPosY = yOffset * chunkLength;

        final int chunkId = Chunk.generateChunkId(chunkPosX, chunkPosY);

        Chunk chunk;

        if (!chunks.containsKey(chunkId)) {
          chunk = new Chunk(new BrickTileSprite(), chunkPosX, chunkPosY);
          this.chunks.put(chunkId, chunk);
        } else {
          chunk = this.chunks.get(chunkId);
        }

        chunk.render();
        renderedChunkIds.add(chunkId);
      }
    }

    return renderedChunkIds;
  }

  /**
   * Unloads all unrendered chunks.
   *
   * @param renderedChunkIds Ids of chunks rendered in a given cycle
   */
  private void unloadChunks(final ArrayList<Integer> renderedChunkIds) {
    for (Chunk renderedChunk : this.chunks.values()) {
      if (!renderedChunkIds.contains(renderedChunk.getId()) && renderedChunk.shouldBeRendered()) {
        System.out.printf("Unloading %d\n", renderedChunk.getId());
        renderedChunk.unload();
      }
    }
  }

  /**
   * Updates the visibility of chunks.
   */
  private void updateChunkView() {
    for (Chunk chunk : this.chunks.values()) {
      for (ImageView view : chunk.getTiles()) {
        if (!this.sceneGroup.getChildren().contains(view) && chunk.shouldBeRendered()) {
          this.sceneGroup.getChildren().add(view);
        } else if (!chunk.shouldBeRendered()) {
          this.sceneGroup.getChildren().remove(view);
        }
      }
    }
  }

  /**
   * A demo sprite that "walks" in a circle to load and unload chunks in the world.
   *
   * @author Benjamin Chiang
   * @version 0.1.0
   */
  private class ChunkGeneratorDemo extends BrickTileSprite {
    private final double increment = 0.01;
    private final double radius = 700.0;

    private double time = 0;

    /**
     * Creates a sprite used to demo the chunk generator.
     */
    ChunkGeneratorDemo() {
      super();
      this.update();
    }

    /**
     * Renders the sprite if it has not already been added.
     */
    public void render() {
      if (!sceneGroup.getChildren().contains(this.getView())) {
        sceneGroup.getChildren().add(this.getView());
      }
    }

    /**
     * Updates the demo sprite's logic.
     */
    @Override
    public void update() {
      final int originX = (int) sceneGroup.getScene().getWidth() / 2;
      final int originY = (int) sceneGroup.getScene().getHeight() / 2;

      time += increment;

      final double newPosX = Math.sin(time) * radius + originX;
      final double newPosY = Math.cos(time) * radius + originY;

      this.setPosition(newPosX, newPosY);

      final double deltaX = newPosX - originX;
      final double deltaY = newPosY - originY;
      final double degrees = Math.atan(deltaY / deltaX) * 180 / Math.PI;

      this.setRotation(degrees);

      super.update();
      this.render();
    }
  }

  /**
   * Updates all logical components in the world.
   */
  @Override
  public void update() {
    for (LogicComponent child : this.logicalChildren) {
      child.update();
    }

    final int posX = (int) this.sceneGroup.getScene().getWidth() / 2;
    final int posY = (int) this.sceneGroup.getScene().getHeight() / 2;
    final int renderDistance = 256;

    ArrayList<Integer> renderedChunkIds = this.updateChunks(posX, posY, renderDistance);
    this.unloadChunks(renderedChunkIds);
    this.updateChunkView();
  }
}
