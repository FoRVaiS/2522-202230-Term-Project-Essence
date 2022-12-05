package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.Projectile;
import ca.bcit.comp2522.termproject.essence.World;
import ca.bcit.comp2522.termproject.essence.sprites.PelletProjectileSprite;

/**
 * A pellet projectile class.
 *
 * @author Felix Lieu, Benjamin Chiang
 * @version 0.1.0
 */
public class PelletProjectile extends Projectile {

  /**
   * Creates a pellet projectile.
   *
   * @param world reference to the world
   */
  public PelletProjectile(final World world) {
    super(world, new PelletProjectileSprite(), Layers.FOREGROUND_LAYER);
  }
}
