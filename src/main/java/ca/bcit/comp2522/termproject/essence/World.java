package ca.bcit.comp2522.termproject.essence;

import java.util.ArrayList;
import java.util.HashMap;

import ca.bcit.comp2522.termproject.essence.entities.Player;
import ca.bcit.comp2522.termproject.essence.interfaces.LogicComponent;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;
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
  private final ArrayList<Entity> entities = new ArrayList<>();

  /**
   * Creates an instance of the world.
   */
  public World() {
    this.entities.add(Player.getPlayer());
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
  private ArrayList<Integer> updateChunks(final double posX, final double posY, final int renderDistance) {
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
    for (final Chunk renderedChunk : this.chunks.values()) {
      if (!renderedChunkIds.contains(renderedChunk.getId()) && renderedChunk.shouldBeRendered()) {
        renderedChunk.unload();
        this.chunks.remove(renderedChunk.getId());
      }
    }
  }

  /**
   * Updates the visibility of chunks.
   */
  private void updateChunkView() {
    for (final Chunk chunk : this.chunks.values()) {
      for (final ImageView view : chunk.getTiles()) {
        if (!Layers.BACKGROUND_LAYER.getChildren().contains(view) && chunk.shouldBeRendered()) {
          Layers.BACKGROUND_LAYER.getChildren().add(view);
        } else if (!chunk.shouldBeRendered()) {
          Layers.BACKGROUND_LAYER.getChildren().remove(view);
        }
      }
    }
  }

  /**
   * Updates all logical components in the world.
   */
  @Override
  public void update() {
    final Entity player = Player.getPlayer();

    for (final LogicComponent child : this.logicalChildren) {
      child.update();
    }

    for (final Entity entity : this.entities) {
      entity.update();
    }

    final int renderDistance = 2000;
    System.out.println(this.chunks.size()); 
    final ArrayList<Integer> renderedChunkIds = this.updateChunks(-player.getX(), -player.getY(), renderDistance);
    this.unloadChunks(renderedChunkIds);
    this.updateChunkView();
  }
}
