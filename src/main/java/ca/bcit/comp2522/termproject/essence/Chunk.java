package ca.bcit.comp2522.termproject.essence;

import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.Objects;

/**
 * Grid of tiles.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class Chunk {
  /** Number of tiles in the chunk. */
  public static final int CHUNK_SIZE = 2;

  /** A list of tiles in the chunk. */
  private final ImageView[] tiles = new ImageView[Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE];

  /** Unique chunk id based on position. */
  private int id;

  /**
   * Creates a chunk, a grid of tiles.
   *
   * @param sprite sprite to use for the chunk
   * @param posX   chunk x coordinate
   * @param posY   chunk y coordinate
   */
  public Chunk(final Sprite sprite, final int posX, final int posY) {
    this.id = Chunk.generateChunkId(posX, posY);

    for (int tileY = 0; tileY < Chunk.CHUNK_SIZE; tileY++) {
      for (int tileX = 0; tileX < Chunk.CHUNK_SIZE; tileX++) {
        final int tileOriginX = posX + tileX * Sprite.TILE_SIZE;
        final int tileOriginY = posY + tileY * Sprite.TILE_SIZE;

        final Vec2D newTilePosition = new Vec2D(tileOriginX, tileOriginY);
        final Sprite tile = new BrickTileSprite();
        tile.setPosition(newTilePosition);

        tiles[tileX + tileY * Chunk.CHUNK_SIZE] = tile.getView();
      }
    }
  }

  /**
   * Returns the tiles in the chunk.
   *
   * @return tiles in the chunk
   */
  public ImageView[] getTiles() {
    return this.tiles;
  }

  /**
   * Returns the chunk's id.
   *
   * @return chunk id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Renders the chunk to the screen.
   */
  public void render() {
    for (final ImageView view : this.tiles) {
      if (!Layers.BACKGROUND_LAYER.getChildren().contains(view)) {
        Layers.BACKGROUND_LAYER.getChildren().add(view);
      }
    }
  }

  /**
   * Unloads the chunk from view.
   */
  public void unload() {
    for (final ImageView view : this.tiles) {
      Layers.BACKGROUND_LAYER.getChildren().remove(view);
    }
  }

  /**
   * Generates an id for chunks using position.
   *
   * @param posX x coordinate of the chunk
   * @param posY y coordinate of the chunk
   * @return unique id based on position
   */
  public static int generateChunkId(final int posX, final int posY) {
    final int tileSize = Sprite.TILE_SIZE;
    final int tilesInChunk = Chunk.CHUNK_SIZE;
    final int chunkLength = tileSize * tilesInChunk;

    return posX / chunkLength + posY;
  }

  /**
   * Determines if an obj is equal to this instance of this chunk.
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
    final Chunk chunk = (Chunk) obj;
    return getId() == chunk.getId() && Arrays.equals(getTiles(), chunk.getTiles());
  }

  /**
   * Returns the hashcode for this chunk instance.
   *
   * @return chunk instance hashcode
   */
  @Override
  public int hashCode() {
    final int hash = 31;
    int result = Objects.hash(getId());
    result = hash * result + Arrays.hashCode(getTiles());
    return result;
  }

  /**
   * Returns the string representation of the chunk.
   *
   * @return string representation of the chunk
   */
  @Override
  public String toString() {
    return "Chunk{"
        + "tiles=" + Arrays.toString(tiles)
        + ", id=" + id
        + '}';
  }
}
