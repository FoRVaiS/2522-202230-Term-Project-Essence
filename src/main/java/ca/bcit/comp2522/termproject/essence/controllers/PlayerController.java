package ca.bcit.comp2522.termproject.essence.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.Vec2D;
import ca.bcit.comp2522.termproject.essence.interfaces.Controller;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * An implementation of Controller for the player to manipulate their character.
 *
 * @author Felix Lieu, Benjamin Chiang
 * @version 0.1.0
 */
public class PlayerController implements Controller {
    private final Scene scene = Layers.PLAYER_LAYER.getScene();
    private final HashMap<KeyCode, Events> keyMap;
    private final HashMap<Events, Consumer<Double>> eventFnMap;
    private final HashMap<KeyCode, Double> keyScaleMap;
    private final ArrayList<KeyCode> state;
    private final ArrayList<Consumer<Vec2D>> moveFn;

    /**
     * Creates a controller to process the player's keyboard input.
     */
    public PlayerController() {
        this.moveFn = new ArrayList<>();
        this.state = new ArrayList<>(); // Set ArrayList.
        this.keyMap = new HashMap<>();
        this.eventFnMap = new HashMap<>();
        this.keyScaleMap = new HashMap<>();

        // these are listeners.
        this.scene.setOnKeyPressed(this::pushKeyCodeToState);
        this.scene.setOnKeyReleased(this::resetKeyState);

        // mouse listener.
        this.scene.setOnMouseMoved(this::processMouseMove);

        this.bindActionKey(KeyCode.SPACE, Events.ATTACK);

        this.bindAxisKey(KeyCode.D, Events.MOVE_X, 1.0);
        this.bindAxisKey(KeyCode.A, Events.MOVE_X, -1.0);
        this.bindAxisKey(KeyCode.W, Events.MOVE_Y, -1.0);
        this.bindAxisKey(KeyCode.S, Events.MOVE_Y, 1.0);

        this.bindAxisKey(KeyCode.RIGHT, Events.MOVE_X, 1.0);
        this.bindAxisKey(KeyCode.LEFT, Events.MOVE_X, -1.0);
        this.bindAxisKey(KeyCode.UP, Events.MOVE_Y, -1.0);
        this.bindAxisKey(KeyCode.DOWN, Events.MOVE_Y, 1.0);
    }

    /**
     * Binds a key to an event.
     *
     * @param keyCode   the key code
     * @param eventName name of the event
     */
    public void bindActionKey(final KeyCode keyCode, final Events eventName) {
        this.bindAxisKey(keyCode, eventName, 1.0);
    }

    /**
     * Binds an event to a event handler.
     *
     * @param eventName name of the event
     * @param handler   function handler
     */
    @Override
    public void bindAction(final Events eventName, final Consumer<Double> handler) {
        this.eventFnMap.put(eventName, handler);
    }

    /**
     * Maps the keyCode to an action event name and to a scale.
     *
     * @param keyCode   controller's KeyCode in keycode
     * @param eventName controller's Events as eventName
     * @param scale     controller's scale in integer
     */
    public void bindAxisKey(final KeyCode keyCode, final Events eventName, final Double scale) {
        this.keyMap.put(keyCode, eventName);
        this.keyScaleMap.put(keyCode, scale);
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
     * Binds handler to mouse movement.
     *
     * @param handler controller's Events as eventName
     */
    @Override
    public void bindMouseMove(final Consumer<Vec2D> handler) {
        this.moveFn.add(handler);
    }

    private void processInput(final KeyCode code) {
        final Events eventName = this.keyMap.get(code);
        final Consumer<Double> callback = this.eventFnMap.get(eventName);
        final Double scale = this.keyScaleMap.get(code);
        callback.accept(scale);
    }

    private void processMouseMove(final MouseEvent event) {
        final double halfWidth = Layers.PLAYER_LAYER.getScene().getWidth() / 2;
        final double halfHeight = Layers.PLAYER_LAYER.getScene().getHeight() / 2;

        final double mouseX = event.getX() - halfWidth;
        final double mouseY = -(event.getY() - halfHeight);

        final Vec2D mousePosition = new Vec2D(mouseX, mouseY);

        for (final Consumer<Vec2D> handler : this.moveFn) {
            handler.accept(mousePosition);
        }
    }

    /**
     * Resets key state.
     *
     * @param event key event
     */
    private void resetKeyState(final KeyEvent event) {
        this.state.remove(event.getCode()); // mismatch in types for strictly event.
    }

    /**
     * Adds key to state.
     *
     * @param event key event
     */
    private void pushKeyCodeToState(final KeyEvent event) {
        if (!this.state.contains(event.getCode())) {
            this.state.add(event.getCode());
        }
    }

    /**
     * Updates player controller logic.
     *
     * @param deltaTime time since last tick
     */
    public void update(final long deltaTime) {
        for (KeyCode keyCode : state) {
            processInput(keyCode);
        }
    }

    /**
     * Determines if an obj is equal to this instance of this player controller.
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
        final PlayerController that = (PlayerController) obj;
        return Objects.equals(scene, that.scene)
                && Objects.equals(keyMap, that.keyMap)
                && Objects.equals(eventFnMap, that.eventFnMap)
                && Objects.equals(keyScaleMap, that.keyScaleMap)
                && Objects.equals(state, that.state)
                && Objects.equals(moveFn, that.moveFn);
    }

    /**
     * Returns the hashcode for this player controller instance.
     *
     * @return player controller instance hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(scene, keyMap, eventFnMap, keyScaleMap, state, moveFn);
    }

    /**
     * Returns the string representation of the player controller.
     *
     * @return string representation of the player controller
     */
    @Override
    public String toString() {
        return "PlayerController{"
                + "scene=" + scene
                + ", keyMap=" + keyMap
                + ", eventFnMap=" + eventFnMap
                + ", keyScaleMap=" + keyScaleMap
                + ", state=" + state
                + ", moveFn=" + moveFn
                + '}';
    }
}
