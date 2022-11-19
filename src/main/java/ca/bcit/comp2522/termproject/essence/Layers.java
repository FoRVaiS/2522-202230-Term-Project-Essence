package ca.bcit.comp2522.termproject.essence;

import javafx.scene.Group;

/**
 * A container class for the different layers of the scene.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public final class Layers {
  /** The background layer of the scene. */
  public static final Group BACKGROUND_LAYER = new Group();

  /** The foreground layer of the scene. */
  public static final Group FOREGROUND_LAYER = new Group();

  /** The foreground layer containing only the player of the scene. */
  public static final Group PLAYER_LAYER = new Group();

  private Layers() { }
}
