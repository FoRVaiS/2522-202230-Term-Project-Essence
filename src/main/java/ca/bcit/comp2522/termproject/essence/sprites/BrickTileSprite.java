package ca.bcit.comp2522.termproject.essence.sprites;

import ca.bcit.comp2522.termproject.essence.Sprite;
import ca.bcit.comp2522.termproject.essence.Vec2D;

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
   * @param position sprite position
   */
  public BrickTileSprite(final Vec2D position) {
    super(Sprite.TILE_BRICK, position);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   * @param scale    sprite scale factor
   */
  public BrickTileSprite(final Vec2D position, final double scale) {
    super(Sprite.TILE_BRICK, position, scale);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   * @param scale    sprite scale factor
   * @param radians  sprite rotation in radians
   */
  public BrickTileSprite(final Vec2D position, final double scale, final double radians) {
    super(Sprite.TILE_BRICK, position, scale, radians);
  }
}
