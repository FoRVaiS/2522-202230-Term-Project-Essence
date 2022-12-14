package ca.bcit.comp2522.termproject.essence.interfaces;

import java.util.function.Consumer;

import ca.bcit.comp2522.termproject.essence.Vec2D;

/**
 * An interface for unrelated entities to use bindings.
 *
 * @author Felix Lieu, Benjamin Chiang
 * @version 0.1.0
 */

public interface Controller {
    /**
     * Binds an action to an event.
     *
     * @param eventName
     * @param actionHandler
     */
    void bindAction(Events eventName, Consumer<Double> actionHandler);

    /**
     * Binds Axis type eventName to Double based handler.
     *
     * @param eventName controller's KeyCode in keycode
     * @param handler   controller's Events as eventName
     */
    void bindAxis(Events eventName, Consumer<Double> handler);

    /**
     * Binds handler to mouse movement.
     *
     * @param handler controller's Events as eventName
     */
    void bindMouseMove(Consumer<Vec2D> handler);

    /**
     * Updates controller logic.
     *
     * @param deltaTime time since last tick
     */
    void update(long deltaTime);

    /**
     * Default action event names for entity manipulation.
     */
    enum Events {
        MOVE_X,
        MOVE_Y,
        ATTACK,
        SKILL_SLOT1,
        SKILL_SLOT2,
        SKILL_SLOT3,
        SKILL_SLOT4
    }

}
