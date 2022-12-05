package ca.bcit.comp2522.termproject.essence;

import javafx.scene.Group;

/**
 * A entity class for a projectile.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class Projectile extends Entity {
  private double heading;

  /**
   * Creates an instance of a projectile spawned by an other entity.
   *
   * @param world  the world the projectile is in
   * @param sprite projectile sprite
   * @param layer  layer to render on
   */
  public Projectile(final World world, final Sprite sprite, final Group layer) {
    super(world, sprite, Layers.FOREGROUND_LAYER);
  }

  /**
   * Sets the heading of the projectile.
   *
   * @param heading the heading in radians
   */
  public void setHeading(final double heading) {
    this.heading = heading;
  }

  /**
   * Sets the velocity of the projectile.
   *
   * @param velocity the velocity of the projectiles
   */
  public void setVelocity(final double velocity) {
    this.setStat(Entity.Stats.SPEED, velocity);
  }

  /**
   * Updates the projectile's logic.
   *
   * @param deltaTime time since last tick
   */
  @Override
  public void update(final long deltaTime) {
    super.update(deltaTime);

    final double deltaX = Math.cos(this.heading);
    final double deltaY = Math.sin(this.heading);

    this.moveX(deltaX);
    this.moveY(deltaY);
  }
}
