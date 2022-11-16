package ca.bcit.comp2522.termproject.essence;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * JavaFX window for Essence.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class Window extends Application {
    /**
     * The start of the application.
     *
     * @param stage a reference to the stage
     */
    @Override
    public void start(final Stage stage) {
        stage.setTitle("Essence");
        stage.setScene(new Scene(new Group()));
        stage.show();
        stage.getScene().setOnKeyPressed((final KeyEvent event) -> {
            if (event.getCode().equals(KeyCode.P)){
                Platform.exit();
            }
        });
        final int tickrate = 120;
        final EventHandler<ActionEvent> game = new Game(this, tickrate);
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
