package ca.bcit.comp2522.termproject.essence.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Vec2D;
import ca.bcit.comp2522.termproject.essence.entities.Player;
import ca.bcit.comp2522.termproject.essence.interfaces.Controller;

/**
 * An ai controller.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class AiController implements Controller {
  private final HashMap<Events, Consumer<Double>> eventFnMap = new HashMap<>();
  private final ArrayList<Consumer<Vec2D>> moveFnMap = new ArrayList<>();
  private final Entity target = Player.getPlayer(null);
  private final Entity self;
  private long freezeTime = 3_500;
  private long updateCooldown;

  /**
   * Creates an ai controller.
   *
   * @param self the entity being controlled by the ai controller
   */
  public AiController(final Entity self) {
    this.self = self;
  }

  /**
   * Maps the action event name to a number.
   *
   * @param eventName controller's KeyCode in keycode
   * @param handler   controller's Events as eventName
   */
  @Override
  public void bindAction(final Events eventName, final Consumer<Double> handler) {
    this.bindAxis(eventName, handler);
  }

  /**
   * Maps the action event name to a number.
   *
   * @param eventName controller's KeyCode in keycode
   * @param handler   controller's Events as eventName
   */
  @Override
  public void bindAxis(final Events eventName, final Consumer<Double> handler) {
    this.eventFnMap.put(eventName, handler);
  }

  /**
   * Invokes a handler with mouse coordinates.
   *
   * @param handler mouse handler
   */
  @Override
  public void bindMouseMove(final Consumer<Vec2D> handler) {
    this.moveFnMap.add(handler);
  }

  private void processMouseMove() {
    final Vec2D targetPos = this.target.getCentre();
    final Vec2D selfPos = this.self.getCentre();
    final Vec2D diffPos = Vec2D.subtract(targetPos, selfPos);

    for (final Consumer<Vec2D> handler : this.moveFnMap) {
      handler.accept(Vec2D.multiply(diffPos, new Vec2D(1, -1)));
    }
  }

  /**
   * Invokes the handler tied to an event name.
   *
   * @param eventName an event name
   * @param value     the value to pass to the handler
   */
  private void invokeHandler(final Events eventName, final double value) {
    this.eventFnMap.get(eventName).accept(value);
  }

  /**
   * Updates the AI controller logic.
   */
  @Override
  public void update(final long deltaTime) {
    if (freezeTime > 0) {
      freezeTime -= deltaTime;
      return;
    }

    final Vec2D targetPos = this.target.getCentre();
    final Vec2D selfPos = this.self.getCentre();
    final Vec2D diffPos = Vec2D.subtract(targetPos, selfPos);

    final double distance = Math.sqrt(Math.pow(diffPos.getX(), 2) + Math.pow(diffPos.getY(), 2));

    final long detectionRange = 850;
    final long timeUntilNextUpdate = 1250;
    this.processMouseMove();

    this.updateCooldown += deltaTime;

    if (this.updateCooldown >= timeUntilNextUpdate) {
      this.updateCooldown = 0;
      if (distance <= detectionRange) {
        this.invokeHandler(Events.ATTACK, 1);
      }
    }

    if (diffPos.getX() > 0) {
      this.invokeHandler(Events.MOVE_X, 1);
    } else if (diffPos.getX() < 0) {
      this.invokeHandler(Events.MOVE_X, -1);
    }

    if (diffPos.getY() > 0) {
      this.invokeHandler(Events.MOVE_Y, 1);
    } else if (diffPos.getY() < 0) {
      this.invokeHandler(Events.MOVE_Y, -1);
    }
  }

}
