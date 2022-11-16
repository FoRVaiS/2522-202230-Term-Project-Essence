package ca.bcit.comp2522.termproject.essence;

import javafx.application.Application;
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
        stage.show();

        final int tickrate = 120;
        final Game game = new Game(this, tickrate);
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
