package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.Vec2D;

/**
 * A camera used to manipulate layers to simulate camera movement.
 *
 * @author Felix Lieu, Benjamin Chiang
 * @version 0.1.0
 */
public class Camera {
  /**
   * Moves all the layers a given distance.
   *
   * @param posX distance to move on the x-axis
   */
  public void moveX(final double posX) {
    Layers.BACKGROUND_LAYER.setTranslateX(posX);
    Layers.FOREGROUND_LAYER.setTranslateX(posX);
    Layers.PLAYER_LAYER.setTranslateX(posX);
  }

  /**
   * Moves all the layers a given distance.
   *
   * @param posY distance to move on the y-axis
   */
  public void moveY(final double posY) {
    Layers.BACKGROUND_LAYER.setTranslateY(posY);
    Layers.FOREGROUND_LAYER.setTranslateY(posY);
    Layers.PLAYER_LAYER.setTranslateY(posY);
  }

  /**
   * Updates the camera's position.
   *
   * @param position new camera position
   */
  public void update(final Vec2D position) {
    final double screenWidthHalf = Layers.PLAYER_LAYER.getScene().getWidth() / 2;
    final double screenHeightHalf = Layers.PLAYER_LAYER.getScene().getHeight() / 2;

    this.moveX(-position.getX() + screenWidthHalf);
    this.moveY(-position.getY() + screenHeightHalf);
  }
}
