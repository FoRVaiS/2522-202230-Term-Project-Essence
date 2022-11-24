package ca.bcit.comp2522.termproject.essence;

import javafx.scene.image.ImageView;

/**
 * Grid of tiles.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class Chunk {
  /** Number of tiles in the chunk. */
  public static final int CHUNK_SIZE = 2;

  /** A list of tiles in the chunk. */
  private final ImageView[] tiles = new ImageView[Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE];

  /** Unique chunk id based on position. */
  private int id;

  /** Should the chunk be rendered? */
  private boolean shouldRender = false;

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
        Sprite tile = Sprite.createBrickTile();
        tile.setPosition(newTilePosition);
        tile.update();

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
    this.shouldRender = true;
  }

  /**
   * Unloads the chunk from view.
   */
  public void unload() {
    this.shouldRender = false;
  }

  /**
   * Returns if the chunk should be rendered.
   *
   * @return should the chunk be rendered
   */
  public boolean shouldBeRendered() {
    return this.shouldRender;
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
}
