package ca.bcit.comp2522.termproject.essence.sprites;

import ca.bcit.comp2522.termproject.essence.Sprite;
import ca.bcit.comp2522.termproject.essence.Vec2D;

/**
 * A sprite for the player character.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class PlayerSprite extends Sprite {
  /**
   * Creates a sprite using a given image.
   */
  public PlayerSprite() {
    super(Sprite.SPRITE_PLAYER);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   */
  public PlayerSprite(final Vec2D position) {
    super(Sprite.SPRITE_PLAYER, position);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   * @param scale    sprite scale factor
   */
  public PlayerSprite(final Vec2D position, final double scale) {
    super(Sprite.SPRITE_PLAYER, position, scale);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   * @param scale    sprite scale factor
   * @param radians  sprite rotation in radians
   */
  public PlayerSprite(final Vec2D position, final double scale, final double radians) {
    super(Sprite.SPRITE_PLAYER, position, scale, radians);
  }
}
