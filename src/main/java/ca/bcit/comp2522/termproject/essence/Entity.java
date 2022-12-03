package ca.bcit.comp2522.termproject.essence;

import java.util.HashMap;

import ca.bcit.comp2522.termproject.essence.entities.PelletProjectile;
import ca.bcit.comp2522.termproject.essence.interfaces.Controller;
import ca.bcit.comp2522.termproject.essence.interfaces.LogicComponent;
import ca.bcit.comp2522.termproject.essence.interfaces.Possessable;
import javafx.scene.Group;

/**
 * Represents an object in the world.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public abstract class Entity implements LogicComponent, Possessable {
  /**
   * Entity Stats.
   */
  public enum Stats {
    HEALTH,
    DAMAGE,
    SPEED
  }

  /**
   * Entity Stats.
   */
  protected final HashMap<Stats, Double> stats = new HashMap<>();

  /**
   * Entity position.
   */
  protected final Vec2D position;

  /**
   * Entity controller.
   */
  protected Controller controller;

  private final Group layer;

  private final Sprite sprite;

  private final World world;

  private double rotation = 0;

  /**
   * Creates an instance to represent an object in the world.
   *
   * @param world    reference to the world
   * @param sprite   sprite to represent the entity
   * @param layer    the layer the entity sprite should reside in
   * @param position the position of the entity
   */
  public Entity(final World world, final Sprite sprite, final Group layer, final Vec2D position) {
    this.world = world;
    this.sprite = sprite;
    this.layer = layer;
    this.position = position;

    this.sprite.setPosition(position);
    this.sprite.update();

    this.setStats();
  }

  /**
   * Returns the entity's x position.
   *
   * @return entity's x position
   */
  public double getX() {
    return this.position.getX();
  }

  /**
   * Moves the entity on the x-axis.
   *
   * @param xDir the direction to move on the x-axis
   */
  public void moveX(final double xDir) {
    this.position.setX(this.getX() + xDir * this.stats.get(Stats.SPEED));
  }

  /**
   * Returns the entity's y position.
   *
   * @return entity's y position
   */
  public double getY() {
    return this.position.getY();
  }

  /**
   * Moves the entity on the y-axis.
   *
   * @param yDir the direction to move on the y-axis
   */
  public void moveY(final double yDir) {
    this.position.setY(this.getY() + yDir * this.stats.get(Stats.SPEED));
  }

  /**
   * Returns the world the entity belongs to.
   *
   * @return the world the entity belongs to
   */
  protected World getWorld() {
    return this.world;
  }

  /**
   * Sets the entity's default stats.
   */
  protected abstract void setStats();

  /**
   * Spawns a projectile with a heading.
   *
   * @param projectile instance of a projectile to spawn
   * @param vector     projectile's movement vector
   */
  protected void spawnProjectile(final Projectile projectile, final Vec2D vector) {
    this.getWorld().spawn(projectile, new Vec2D(-this.getX(), -this.getY()));

    final double velocity = vector.getX();
    final double heading = vector.getY();

    projectile.setHeading(heading);
    projectile.setVelocity(velocity);
  }

  /**
   * Update's the entity's logic components.
   */
  @Override
  public void update() {
    final Vec2D newSpritePosition = new Vec2D(this.getX(), this.getY());
    this.sprite.setPosition(newSpritePosition);
    this.sprite.update();
  }

  /**
   * Sets the position of the entity.
   *
   * @param position the new position of the entity
   */
  public void setPosition(final Vec2D position) {
    this.position.setX(position.getX());
    this.position.setY(position.getY());
  }

  /**
   * Renders the entity on screen.
   */
  public void render() {
    if (!this.layer.getChildren().contains(this.sprite.getView())) {
      this.layer.getChildren().add(this.sprite.getView());
    }
  }

  /**
   * Shoots a projectile.
   *
   * @param flag a placeholder value for the consumer
   */
  public void shoot(final double flag) {
    final Vec2D projectilePos = new Vec2D(-this.getX(), -this.getY());
    final Projectile projectile = new PelletProjectile(this.getWorld(), this, projectilePos);

    final double velocity = 8.0;
    final double heading = -this.rotation;
    final Vec2D vector = new Vec2D(velocity, heading);

    this.spawnProjectile(projectile, vector);
  }

  /**
   * Sets the entity's controller.
   *
   * @param newController controller to possess the entity
   */
  @Override
  public void possess(final Controller newController) {
    this.controller = newController;

    this.controller.bindAxis(Controller.Events.MOVE_X, this::moveX);
    this.controller.bindAxis(Controller.Events.MOVE_Y, this::moveY);
    this.controller.bindAction(Controller.Events.ATTACK, this::shoot);

    this.controller.bindMouseMove(mousePosition -> {
      final double mouseX = mousePosition.getX();
      final double mouseY = mousePosition.getY();

      final double fullCircle = 2 * Math.PI;
      final double radians = (Math.atan2(mouseY, mouseX) + fullCircle) % fullCircle;

      this.rotation = radians;
    });
  }

  /**
   * Removes the entity's controller.
   */
  @Override
  public void unpossess() {
    this.controller = null;
  }
}
