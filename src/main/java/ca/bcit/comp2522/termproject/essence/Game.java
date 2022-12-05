package ca.bcit.comp2522.termproject.essence;

import javafx.animation.AnimationTimer;

import java.io.IOException;

/**
 * Manages and updates game components.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class Game {
  /**
   * Creates a new instance of the game.
   */
  public Game() {
    final World world = new World();

    // Conversion Factors
    final int msPerSecond = 1000;

    // Game Loop
    new AnimationTimer() {
      long lastMsTime = System.currentTimeMillis();
      long frameTime = 0;
      int frames;

      public void handle(final long currentNanoTime) {
        final long deltaTime = System.currentTimeMillis() - lastMsTime;

        world.update(deltaTime);
        lastMsTime += deltaTime;
        frameTime += deltaTime;

        if (frameTime >= msPerSecond) {
          System.out.printf("[Frames %d]\n", frames);
          frameTime -= msPerSecond;
          frames = 0;
        }

        frames++;
      }
    }.start();
  }
}
