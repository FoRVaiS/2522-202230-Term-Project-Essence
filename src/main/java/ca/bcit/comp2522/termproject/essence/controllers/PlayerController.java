package ca.bcit.comp2522.termproject.essence.controllers;

import java.util.HashMap;
import java.util.function.Consumer;

import ca.bcit.comp2522.termproject.essence.interfaces.Controller;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * An implementation of Controller for the player to manipulate their character.
 *
 * @author Felix Lieu & Benjamin Chiang
 * @version 0.1.0
 */

public class PlayerController implements Controller {
    private final Scene scene = Layers.PLAYER_LAYER.getScene();
    private final HashMap<KeyCode, Events> keyMap;
    private final HashMap<Events, Consumer<Integer>> eventFnMap;
    private final HashMap<KeyCode, Integer> keyScaleMap;

    /**
     * Our dynamic constructor that takes in the scene.
     * @param scene PlayerController's method to detect user input
     */
    public PlayerController() {
        this.keyMap = new HashMap<>();
        this.eventFnMap = new HashMap<>();
        this.keyScaleMap = new HashMap<>();
        this.scene.setOnKeyPressed(this::processInput);

        this.bindAxisKey(KeyCode.D, Events.MOVE_X, 1);
        this.bindAxisKey(KeyCode.A, Events.MOVE_X, -1);
        this.bindAxisKey(KeyCode.W, Events.MOVE_Y, -1);
        this.bindAxisKey(KeyCode.S, Events.MOVE_Y, 1);

        this.bindAxisKey(KeyCode.RIGHT, Events.MOVE_X, 1);
        this.bindAxisKey(KeyCode.LEFT, Events.MOVE_X, -1);
        this.bindAxisKey(KeyCode.UP, Events.MOVE_Y, -1);
        this.bindAxisKey(KeyCode.DOWN, Events.MOVE_Y, 1);

    }

    @Override
    public void bindActionKey(final KeyCode keyCode, final Events eventName) {

    }

    @Override
    public void bindAction(final Events eventName, final Consumer<Integer> actionHandler) {

    }

    /**
     * Maps the keyCode to an action event name and to a scale.
     * @param keyCode controller's KeyCode in keycode
     * @param eventName controller's Events as eventName
     * @param scale controller's scale in integer
     */
    @Override
    public void bindAxisKey(final KeyCode keyCode, final Events eventName, final Integer scale) {
        this.keyMap.put(keyCode, eventName);
        this.keyScaleMap.put(keyCode, scale);

    }

    /**
     * Maps the action event name to a number.
     * @param eventName controller's KeyCode in keycode
     * @param handler controller's Events as eventName
     */

    @Override
    public void bindAxis(final Events eventName, final Consumer<Integer> handler) {
        this.eventFnMap.put(eventName, handler);
    }

    private void processInput(final KeyEvent event) {
        final KeyCode code = event.getCode();
        final Events eventName = this.keyMap.get(code);
        final Consumer<Integer> callback = this.eventFnMap.get(eventName);
        final Integer scale = this.keyScaleMap.get(code);
        callback.accept(scale);
    }
}



