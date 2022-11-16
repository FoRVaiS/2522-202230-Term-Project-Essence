package ca.bcit.comp2522.termproject.essence;

import javafx.animation.AnimationTimer;

/**
 * Manages and updates game components.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public class Game extends AnimationTimer {
  /* Number of milliseconds in a second */
  private final double msPerSecond = 1000.0;

  /* Number of nanoseconds per milliseconds */
  private final double nanoPerMs = 1_000_000;

  /* Number of milliseconds per update tick */
  private final double msPerTick;

  /* Time of when the last update was processed */
  private double lastMsTime = -1;

  /* Amount of time that has passed since the last update */
  private double msElapsed = 0;

  /**
   * Creates a new instance of the game.
   *
   * @param window   reference to the containing window
   * @param tickrate frequency of game updates
   */
  public Game(final Window window, final int tickrate) {
    this.msPerTick = this.msPerSecond / tickrate; // msPerSecond / ticksPerSecond
  }

  /**
   * Handler method that is invoked as fast as possible.
   *
   * @param now current time in nano seconds
   */
  @Override
  public void handle(final long now) {
    // The current time in milliseconds
    final double currentTime = now / this.nanoPerMs;

    // If this is the first call, set the last time to now (in ms)
    if (this.lastMsTime == -1) {
      this.lastMsTime = currentTime;
    }

    // The amount of ms that has passed since the last call/invocation
    final double deltaMsTime = currentTime - this.lastMsTime;

    // Add the delta to elapsed ms
    this.msElapsed += deltaMsTime;

    if (this.msElapsed >= this.msPerTick) {
      System.out.println("Tick!");

      // If the system lags and msElapsed is greater than the tick interval,
      // only remove enough time to allow for another update to happen
      this.msElapsed -= this.msPerTick;
    }

    this.lastMsTime = currentTime;
  }
}
