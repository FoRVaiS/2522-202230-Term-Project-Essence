package ca.bcit.comp2522.termproject.essence.sprites;

import ca.bcit.comp2522.termproject.essence.Sprite;

/**
 * A sprite for the brick tile.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class BrickTileSprite extends Sprite {
  /**
   * Creates a sprite using a given image.
   */
  public BrickTileSprite() {
    super(Sprite.TILE_BRICK);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param x sprite's x position
   * @param y sprite's y position
   */
  public BrickTileSprite(final double x, final double y) {
    super(Sprite.TILE_BRICK, x, y);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param x     sprite's x position
   * @param y     sprite's y position
   * @param scale sprite's scale factor
   */
  public BrickTileSprite(final double x, final double y, final double scale) {
    super(Sprite.TILE_BRICK, x, y, scale);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param x       sprite's x position
   * @param y       sprite's y position
   * @param scale   sprite's scale factor
   * @param radians sprite's rotation in radians
   */
  public BrickTileSprite(final double x, final double y, final double scale, final double radians) {
    super(Sprite.TILE_BRICK, x, y, scale, radians);
  }
}
