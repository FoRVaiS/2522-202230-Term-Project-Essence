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
   * Entity controller.
   */
  protected Controller controller;

  /**
   * Entity x coordinate.
   */
  protected double x;

  /**
   * Entity y coordinate.
   */
  protected double y;

  private final Sprite sprite;

  /**
   * Creates an instance to represent an object in the world.
   *
   * @param sprite sprite to represent the entity
   * @param x      x coordinate of the entity
   * @param y      y coordinate of the entity
   */
  public Entity(final Sprite sprite, final double x, final double y) {
    this.sprite = sprite;

    this.x = x;
    this.y = y;

    this.setStats();
  }

  /**
   * Returns the entity's x position.
   *
   * @return entity's x position
   */
  public double getX() {
    return this.x;
  }

  /**
   * Moves the entity on the x-axis.
   *
   * @param xDir the direction to move on the x-axis
   */
  public void moveX(final double xDir) {
    this.x += xDir * this.stats.get(Stats.SPEED);
  }

  /**
   * Returns the entity's y position.
   *
   * @return entity's y position
   */
  public double getY() {
    return this.y;
  }

  /**
   * Moves the entity on the y-axis.
   *
   * @param yDir the direction to move on the y-axis
   */
  public void moveY(final double yDir) {
    this.y += yDir * this.stats.get(Stats.SPEED);
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
    this.sprite.setPosition(this.getX(), this.getY());
    this.sprite.update();
  }

  /**
   * Renders the entity on screen.
   *
   * @param layer layer to render on
   */
  public void render(final Group layer) {
    if (!layer.getChildren().contains(this.sprite.getView())) {
      layer.getChildren().add(this.sprite.getView());
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
