package ca.bcit.comp2522.termproject.essence;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Manages and updates game components.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class Game implements EventHandler<ActionEvent> {
  private final Timeline gameLoop;

  /**
   * Creates a new instance of the game.
   *
   * @param window   reference to the containing window
   * @param tickrate frequency of game updates
   */
  public Game(final Window window, final int tickrate) {
    this.gameLoop = new Timeline();

    this.startGameLoop(tickrate);
  }

  /**
   * Creates a game loop.
   *
   * @param tickrate frequency of game updates
   */
  private void startGameLoop(final int tickrate) {
    final int milliPerSecond = 1000; // ms / second

    // Update Frequency
    final double msPerTick = milliPerSecond / tickrate;

    // The amount of milliseconds per frame wrapped in a Duration object
    final Duration msPerFrame = Duration.millis(msPerTick);

    // Create a keyframe for the timeline
    final KeyFrame frame = new KeyFrame(msPerFrame, this);

    // Add the keyframe to the timeline
    this.gameLoop.getKeyFrames().add(frame);

    // Loop the timeline when it reaches the end
    this.gameLoop.setCycleCount(Timeline.INDEFINITE);

    // Start the timeline
    this.gameLoop.play();
  }

  /**
   * Handler method that is invoked based on the game update frequency.
   *
   * @param event a generic action event
   */
  @Override
  public void handle(final ActionEvent event) {
    System.out.println("Tick!");
  }
}
