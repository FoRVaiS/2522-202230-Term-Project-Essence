package ca.bcit.comp2522.termproject.essence;

import java.util.HashMap;

import ca.bcit.comp2522.termproject.essence.entities.PelletProjectile;
import ca.bcit.comp2522.termproject.essence.interfaces.Collidable;
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
public abstract class Entity implements LogicComponent, Possessable, Collidable<Entity> {
  /**
   * Entity Stats.
   */
  public enum Stats {
    HEALTH,
    DAMAGE,
    SPEED
  }

  private final World world;
  private final Group layer;

  private final Sprite sprite;
  private final HashMap<Stats, Double> stats = new HashMap<>();
  private final Vec2D position = new Vec2D();

  private Camera camera;
  private Controller controller;

  private long cooldown = 0;
  private double rotation = 0;

  /**
   * Creates an instance to represent an object in the world.
   *
   * @param world  reference to the world
   * @param sprite sprite to represent the entity
   * @param layer  the layer the entity sprite should reside in
   */
  public Entity(final World world, final Sprite sprite, final Group layer) {
    this.world = world;
    this.sprite = sprite;
    this.layer = layer;

    final Vec2D dimensions = new Vec2D(this.getWidth(), this.getHeight());
    final Vec2D halfDimensions = Vec2D.divide(dimensions, new Vec2D(2, 2));
    final Vec2D spritePosition = Vec2D.subtract(this.getPosition(), halfDimensions);

    this.sprite.setPosition(spritePosition);
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
   * Returns the entity's sprite.
   *
   * @return the entity's sprite
   */
  protected Sprite getSprite() {
    return this.sprite;
  }

  /**
   * Returns the entity's stats.
   *
   * @return the entity's stats
   */
  protected HashMap<Stats, Double> getStats() {
    return this.stats;
  }

  /**
   * Sets an entity stat.
   *
   * @param key   name of the stat
   * @param value value of the stat
   */
  protected void setStat(final Stats key, final double value) {
    this.stats.put(key, value);
  }

  /**
   * Returns the entity's position.
   *
   * @return a copy of the entity's position
   */
  public Vec2D getPosition() {
    return Vec2D.copy(this.position);
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
   * Binds a camera to the entity.
   *
   * @param camera camera to track entity
   */
  public void setCamera(final Camera camera) {
    this.camera = camera;
  }

  /**
   * Returns the entity's width.
   *
   * @return the width of the entity's sprite
   */
  public double getWidth() {
    return this.sprite.getView().getImage().getWidth();
  }

  /**
   * Returns the entity's width.
   *
   * @return the height of the entity's sprite
   */
  public double getHeight() {
    return this.sprite.getView().getImage().getHeight();
  }

  /**
   * Shoots a projectile.
   *
   * @param flag a placeholder value for the consumer
   */
  public void shoot(final double flag) {
    final Projectile projectile = new PelletProjectile(this.getWorld());

    final double velocity = 8.0;
    final double heading = -this.rotation;
    final Vec2D vector = new Vec2D(velocity, heading);

    if (this.cooldown == 0) {
      final int duration = 150;
      this.spawnProjectile(projectile, vector);
      this.cooldown = duration;
    }
  }

  /**
   * Spawns a projectile with a heading.
   *
   * @param projectile instance of a projectile to spawn
   * @param vector     projectile's movement vector
   */
  protected void spawnProjectile(final Projectile projectile, final Vec2D vector) {
    projectile.setPosition(this.getPosition());
    this.getWorld().spawn(projectile);

    final double velocity = vector.getX();
    final double heading = vector.getY();

    projectile.setHeading(heading);
    projectile.setVelocity(velocity);
  }

  /**
   * Fires when this entity collides with another entity.
   *
   * @param otherEnt the colliding entity
   */
  @Override
  public void onCollision(final Entity otherEnt) {

  };

  /**
   * Update's the entity's logic components.
   *
   * @param deltaTime time since last tick
   */
  @Override
  public void update(final long deltaTime) {
    final Vec2D newSpritePosition = new Vec2D(
        this.position.getX() - this.getWidth() / 2,
        this.position.getY() - this.getHeight() / 2);

    if (this.camera != null) {
      this.camera.update(this.getPosition());
    }

    if (this.controller != null) {
      this.controller.update();
    }

    if (this.cooldown > 0) {
      this.cooldown = Math.max(0, this.cooldown - deltaTime);
    }

    this.sprite.setPosition(newSpritePosition);
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
