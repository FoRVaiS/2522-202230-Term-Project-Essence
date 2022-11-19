package ca.bcit.comp2522.termproject.essence;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * JavaFX window for Essence.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class Window extends Application {
    /** Default Width of Game Window. */
    public static final int WINDOW_WIDTH = 960;
    /** Default Height of Game Window. */
    public static final int WINDOW_HEIGHT = 540;
    /** Custom String Key Combination. */
    public static final String CUSTOM_STRING_CODE = "O";

    /**
     * The start of the application.
     *
     * @param stage a reference to the stage
     */
    @Override
    public void start(final Stage stage) {
        stage.setTitle("Essence");
        stage.setScene(new Scene(new Group(Layers.BACKGROUND_LAYER, Layers.FOREGROUND_LAYER, Layers.PLAYER_LAYER)));
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination(CUSTOM_STRING_CODE));
        stage.show();
        stage.getScene().setOnKeyPressed((final KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.P)) {
                Platform.exit();
            } else if (event.getCode().equals(KeyCode.O)) {
                stage.setHeight(WINDOW_HEIGHT);
                stage.setWidth(WINDOW_WIDTH);
                stage.centerOnScreen();
            }
        });
        final int tickrate = 120;
        final EventHandler<ActionEvent> game = new Game(tickrate);
    }

    /**
     * Application driver.
     *
     * @param args CLI arguments
     */
    public static void main(final String[] args) {
        Window.launch();
    }
}
