package ca.bcit.comp2522.termproject.essence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import ca.bcit.comp2522.termproject.essence.HUD.EssenceBar;
import ca.bcit.comp2522.termproject.essence.entities.Gunner;
import ca.bcit.comp2522.termproject.essence.entities.Hunter;
import ca.bcit.comp2522.termproject.essence.entities.Player;
import ca.bcit.comp2522.termproject.essence.interfaces.LogicComponent;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;


/**
 * Manages objects in the world.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public final class World implements LogicComponent {
  private final HashMap<Integer, Chunk> chunks = new HashMap<>();
  private final ArrayList<Entity> entities = new ArrayList<>();
  private final EssenceBar essenceBar = new EssenceBar();

  /**
   * Creates an instance of the world.
   */
  public World() {
    final Entity player = Player.getPlayer(this);
    final Camera camera = new Camera();
    player.setCamera(camera);

    this.spawn(player);
    this.update(0);

    this.essenceBar.render();
  }

  /**
   * Spawns an entity into the world at a given position.
   *
   * @param ent entity to spawn
   */
  public void spawn(final Entity ent) {
    this.entities.add(ent);

    ent.render();
  }

  /**
   * Despawns an entity.
   *
   * @param ent entity to despawn
   */
  public void despawn(final Entity ent) {
    this.entities.remove(ent);
  }

  /**
   * Spawns a random mob at a target location.
   *
   * @param position the position to spawn the mob at
   */
  private void spawnMob(final Vec2D position) {
    final Random random = new Random();

    final double spawnBoundary = 0.02;
    final double spawnChance = random.nextDouble(1);

    if (spawnChance <= spawnBoundary) {
      final int spawn = random.nextInt(2);
      Entity ent = null;

      switch (spawn) {
        case 0 -> {
          ent = new Gunner(this);
        }
        case 1 -> {
          ent = new Hunter(this);
        }
        default -> {
        }
      }

      ent.setPosition(position);
      this.spawn(ent);
    }
  }

  /**
   * Updates chunks around a point as far as the render distance allows.
   *
   * @param position       world position
   * @param renderDistance distance around point to render chunks
   */
  private void updateChunks(final Vec2D position, final int renderDistance) {
    final int tileSize = Sprite.TILE_SIZE;
    final int tilesInChunk = Chunk.CHUNK_SIZE;
    final int chunkLength = tileSize * tilesInChunk;

    final int xStart = (int) Math.floor((position.getX() - renderDistance) / chunkLength);
    final int xEnd = (int) Math.floor((position.getX() + renderDistance) / chunkLength);

    final int yStart = (int) Math.floor((position.getY() - renderDistance) / chunkLength);
    final int yEnd = (int) Math.floor((position.getY() + renderDistance) / chunkLength);

    final ArrayList<Integer> renderedChunkIds = new ArrayList<>();

    for (int yOffset = yStart; yOffset <= yEnd; yOffset++) {
      for (int xOffset = xStart; xOffset <= xEnd; xOffset++) {
        final int chunkPosX = xOffset * chunkLength;
        final int chunkPosY = yOffset * chunkLength;

        final int chunkId = Chunk.generateChunkId(chunkPosX, chunkPosY);

        Chunk chunk;

        if (!chunks.containsKey(chunkId)) {
          this.spawnMob(new Vec2D(chunkPosX, chunkPosY));
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
    final Entity[] localEntities = this.entities.toArray(Entity[]::new);

    for (final Entity entity : localEntities) {
      entity.update(deltaTime);
    }

    Collision.intersect(entities);

    final Entity player = Player.getPlayer(this);
    final int renderDistance = 2000;
    this.updateChunks(player.getPosition(), renderDistance);
  }

  /**
   * Determines if an obj is equal to this instance of the world.
   *
   * @param obj another object
   * @return true if the obj is equal to this instance
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final World world = (World) obj;
    return Objects.equals(chunks, world.chunks) && Objects.equals(entities, world.entities);
  }

  /**
   * Returns the hashcode for this world instance.
   *
   * @return world instance hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(chunks, entities);
  }

  /**
   * Returns the string representation of the world.
   *
   * @return string representation of the world
   */
  @Override
  public String toString() {
    return "World{"
        + "chunks=" + chunks
        + ", entities=" + entities
        + '}';
  }
}
