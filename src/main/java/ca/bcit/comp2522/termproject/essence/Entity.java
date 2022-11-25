package ca.bcit.comp2522.termproject.essence;

import java.util.HashMap;

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

  /**
   * Creates an instance to represent an object in the world.
   *
   * @param sprite   sprite to represent the entity
   * @param layer    the layer the entity sprite should reside in
   * @param position the position of the entity
   */
  public Entity(final Sprite sprite, final Group layer, final Vec2D position) {
    this.sprite = sprite;
    this.layer = layer;
    this.position = position;

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
   * Sets the entity's default stats.
   */
  protected abstract void setStats();

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
  }

  /**
   * Removes the entity's controller.
   */
  @Override
  public void unpossess() {
    this.controller = null;
  }
}
