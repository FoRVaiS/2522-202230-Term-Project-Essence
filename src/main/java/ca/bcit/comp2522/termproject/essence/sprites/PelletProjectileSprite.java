package ca.bcit.comp2522.termproject.essence.sprites;

import ca.bcit.comp2522.termproject.essence.Sprite;
import ca.bcit.comp2522.termproject.essence.Vec2D;

/**
 * A sprite for the pellet projectile.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class PelletProjectileSprite extends Sprite {
  /**
   * Creates a sprite using a given image.
   */
  public PelletProjectileSprite() {
    super(Sprite.PROJECTILE_PELLET);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   */
  public PelletProjectileSprite(final Vec2D position) {
    super(Sprite.PROJECTILE_PELLET, position);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   * @param scale    sprite scale factor
   */
  public PelletProjectileSprite(final Vec2D position, final double scale) {
    super(Sprite.PROJECTILE_PELLET, position, scale);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param position sprite position
   * @param scale    sprite scale factor
   * @param radians  sprite rotation in radians
   */
  public PelletProjectileSprite(final Vec2D position, final double scale, final double radians) {
    super(Sprite.PROJECTILE_PELLET, position, scale, radians);
  }
}
