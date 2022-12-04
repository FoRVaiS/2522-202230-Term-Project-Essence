package ca.bcit.comp2522.termproject.essence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ca.bcit.comp2522.termproject.essence.entities.Camera;
import ca.bcit.comp2522.termproject.essence.entities.Gunner;
import ca.bcit.comp2522.termproject.essence.entities.Hunter;
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
  private final double SPAWN_BOUNDARY = 0.02;
  private final HashMap<Integer, Chunk> chunks = new HashMap<>();
  private final ArrayList<LogicComponent> logicalChildren = new ArrayList<>();
  private final ArrayList<Entity> entities = new ArrayList<>();

  /**
   * Creates an instance of the world.
   */
  public World() {
    final Entity player = Player.getPlayer(this);
    final Camera camera = new Camera();
    player.setCamera(camera);

    this.spawn(player, new Vec2D());
    this.update(0);
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
          final Random random = new Random();
          final double spawnChance = random.nextDouble(0, 1);
          if (spawnChance <= SPAWN_BOUNDARY) {
            final int spawn = random.nextInt(0, 1);
            switch (spawn) {
              case 0 -> this.spawn(new Hunter(this), new Vec2D(chunkPosX, chunkPosY));
              case 1 -> this.spawn(new Gunner(this), new Vec2D(chunkPosX, chunkPosY));
            }
          }
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
   *
   * @param deltaTime time since last tick
   */
  @Override
  public void update(final long deltaTime) {
    final Entity player = Player.getPlayer(this);

    final Entity[] localEntities = this.entities.toArray(Entity[]::new);

    for (final Entity entity : localEntities) {
      entity.update(deltaTime);
    }

    Collision.intersect(entities);

    final int renderDistance = 2000;
    this.updateChunks(player.getX(), player.getY(), renderDistance);
  }
}
