package ca.bcit.comp2522.termproject.essence.interfaces;

import java.util.function.Consumer;

import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

/**
 * An interface for unrelated entities to use bindings.
 *
 * @author Felix Lieu & Benjamin Chiang
 * @version 0.1.0
 */

public interface Controller {
    /**
     * Binds Action type keycode to event name.
     * @param keyCode controller's KeyCode in keycode
     * @param eventName controller's Events as eventName
     */
    void bindActionKey(KeyCode keyCode, Events eventName);
    void bindAction(Events eventName, Consumer<Double> actionHandler);
    /**
     * Binds AxisKey type keycode and events to a scale.
     * @param keyCode controller's KeyCode in keycode
     * @param eventName controller's Events as eventName
     * @param scale controller's scale in integer
     */
    void bindAxisKey(KeyCode keyCode, Events eventName, Double scale);
    /**
     * Binds Axis type eventName to Double based handler.
     * @param eventName controller's KeyCode in keycode
     * @param handler controller's Events as eventName
     */
    void bindAxis(Events eventName, Consumer<Double> handler);
    /**
     * Updates controller logic.
     */
    void update();
    /**
     * Binding mouse clicks to specific events.
     * @param eventName controller's Events as eventName
     * @param mouseBtnName controller's MouseEvent as mouseBtnName
     */
    void bindMouse(Events eventName, EventType<MouseEvent> mouseBtnName);
    /**
     * Default action event names for entity manipulation.
     */
    enum Events {
        MOVE_X,
        MOVE_Y,
        CLICK_X,
        CLICK_Y,
        ATTACK,
        SKILL_SLOT1,
        SKILL_SLOT2,
        SKILL_SLOT3,
        SKILL_SLOT4
    }

}
