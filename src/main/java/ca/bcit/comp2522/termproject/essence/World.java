package ca.bcit.comp2522.termproject.essence;

import java.util.ArrayList;
import java.util.HashMap;

import ca.bcit.comp2522.termproject.essence.entities.Player;
import ca.bcit.comp2522.termproject.essence.interfaces.LogicComponent;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;

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
    this.spawn(Player.getPlayer(), new Vec2D());
    this.update();
  }

  /**
   * Spawns an entity into the world at a given position.
   * 
   * @param ent      entity to spawn
   * @param position position to spawn entity at
   */
  public void spawn(final Entity ent, final Vec2D position) {
    this.entities.add(ent);

    ent.setPosition(position);
    ent.render();
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

    this.unloadChunks(renderedChunkIds);

    return renderedChunkIds;
  }

  /**
   * Unloads all unrendered chunks.
   *
   * @param renderedChunkIds Ids of chunks rendered in a given cycle
   */
  private void unloadChunks(final ArrayList<Integer> renderedChunkIds) {
    final ArrayList<Integer> unrenderedChunkIds = new ArrayList<>();

    for (final var chunkEntry : this.chunks.entrySet()) {
      final Chunk chunk = chunkEntry.getValue();

      if (!renderedChunkIds.contains(chunk.getId())) {
        chunk.unload();
        unrenderedChunkIds.add(chunkEntry.getKey());
      }
    }

    for (final Integer chunkId : unrenderedChunkIds) {
      this.chunks.remove(chunkId);
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
    this.updateChunks(-player.getX(), -player.getY(), renderDistance);
  }
}
