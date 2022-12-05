package ca.bcit.comp2522.termproject.essence;

import javafx.scene.Group;

import java.util.Objects;

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
   * @param team   the team the projectile belongs to
   * @param layer  layer to render on
   */
  public Projectile(final World world, final Sprite sprite, final Teams team, final Group layer) {
    super(world, sprite, team, Layers.FOREGROUND_LAYER);
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

  /**
   * Determines if an obj is equal to this instance of the projectile.
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

    final Projectile that = (Projectile) obj;
    return Double.compare(that.heading, heading) == 0;
  }

  /**
   * Returns the hashcode for this projectile instance.
   *
   * @return projectile instance hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(heading);
  }

  /**
   * Returns the string representation of the projectile.
   *
   * @return string representation of the projectile
   */
  @Override
  public String toString() {
    return "Projectile{"
        + "heading=" + heading
        + '}';
  }
}
