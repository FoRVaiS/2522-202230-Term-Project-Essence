package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.Projectile;
import ca.bcit.comp2522.termproject.essence.Vec2D;
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
   * @param world    reference to the world
   * @param owner    entity who created this projectile
   * @param position where this projectile spawns
   */
  public PelletProjectile(final World world, final Entity owner, final Vec2D position) {
    super(world, owner, new PelletProjectileSprite(), Layers.FOREGROUND_LAYER, position);
  }
}
