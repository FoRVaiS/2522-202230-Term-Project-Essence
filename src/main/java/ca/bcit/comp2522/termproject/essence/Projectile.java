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
  private double velocity;

  /**
   * Creates an instance of a projectile spawned by an other entity.
   *
   * @param world    the world the projectile is in
   * @param owner    entity who spawned this projectile
   * @param sprite   projectile sprite
   * @param layer    layer to render on
   * @param position where the projectile should spawn
   */
  public Projectile(final World world, final Entity owner, final Sprite sprite, final Group layer,
      final Vec2D position) {
    super(world, sprite, Layers.FOREGROUND_LAYER, position);
  }

  /**
   * Sets the stats of the entity.
   */
  @Override
  protected void setStats() {
    this.stats.put(Stats.SPEED, this.velocity);
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
    this.velocity = velocity;
    this.setStats();
  }

  /**
   * Updates the projectile's logic.
   */
  @Override
  public void update() {
    super.update();

    final double deltaX = Math.cos(this.heading);
    final double deltaY = Math.sin(this.heading);

    this.moveX(deltaX);
    this.moveY(deltaY);
  }
}
