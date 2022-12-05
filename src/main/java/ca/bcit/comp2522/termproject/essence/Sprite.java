package ca.bcit.comp2522.termproject.essence;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * An object that can be displayed and manipulated.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public abstract class Sprite {
  /** Size of a tile. */
  public static final int TILE_SIZE = 128;

  /** Image reference for the PLAYER character. */
  public static final Image SPRITE_PLAYER = new Image("SPRITE_PLAYER.png");

  /** Image reference for the PELLET projectile. */
  public static final Image PROJECTILE_PELLET = new Image("SPRITE_PELLET_PROJECTILE.png");

  /** Image reference for the BRICK tile. */
  public static final Image TILE_BRICK = new Image("TILE_BRICK.png", TILE_SIZE, TILE_SIZE, false, false);

  private final ImageView view;

  /**
   * Creates a sprite using a given image.
   *
   * @param image sprite image
   */
  public Sprite(final Image image) {
    this.view = new ImageView(image);
    this.view.setSmooth(false);
  }

  /**
   * Returns the sprite's ImageView component.
   *
   * @return sprite's ImageView component
   */
  public ImageView getView() {
    return this.view;
  }

  /**
   * Sets the sprite position.
   *
   * @param position new position
   */
  public void setPosition(final Vec2D position) {
    view.setX(position.getX());
    view.setY(position.getY());
  };

  /**
   * Determines if an obj is equal to this instance of this sprite.
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
    final Sprite sprite = (Sprite) obj;
    return Objects.equals(getView(), sprite.getView());
  }

  /**
   * Returns the hashcode for this sprite instance.
   *
   * @return sprite instance hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(getView());
  }

  /**
   * Returns the string representation of the sprite.
   *
   * @return string representation of the sprite
   */
  @Override
  public String toString() {
    return "Sprite{"
        + "view=" + view
        + '}';
  }
}
