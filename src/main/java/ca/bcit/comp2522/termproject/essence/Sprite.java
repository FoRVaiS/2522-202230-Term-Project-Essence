package ca.bcit.comp2522.termproject.essence;

import ca.bcit.comp2522.termproject.essence.interfaces.LogicComponent;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An object that can be displayed and manipulated.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public abstract class Sprite implements LogicComponent {
  /** Size of a tile. */
  public static final int TILE_SIZE = 128;

  /** Image reference for the PLAYER character. */
  public static final Image SPRITE_PLAYER = new Image("SPRITE_PLAYER.png");

  /** Image reference for the BRICK tile. */
  public static final Image TILE_BRICK = new Image("TILE_BRICK.png", TILE_SIZE, TILE_SIZE, false, false);

  private final ImageView view;

  private double x;
  private double y;
  private double scale;
  private double radians;

  /**
   * Creates a sprite using a given image.
   *
   * @param image sprite image
   */
  public Sprite(final Image image) {
    this(image, 0, 0, 1, 0);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param image sprite image
   * @param x     sprite's x position
   * @param y     sprite's y position
   */
  public Sprite(final Image image, final double x, final double y) {
    this(image, x, y, 1, 0);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param image sprite image
   * @param x     sprite's x position
   * @param y     sprite's y position
   * @param scale sprite's scale factor
   */
  public Sprite(final Image image, final double x, final double y, final double scale) {
    this(image, x, y, scale, 0);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param image   sprite image
   * @param x       sprite's x position
   * @param y       sprite's y position
   * @param scale   sprite's scale factor
   * @param radians sprite's rotation in radians
   */
  public Sprite(final Image image, final double x, final double y, final double scale, final double radians) {
    this.view = new ImageView(image);
    this.view.setSmooth(false);

    this.x = x;
    this.y = y;
    this.scale = scale;
    this.radians = radians;
    this.update();
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
   * Returns the sprite's x position.
   *
   * @return sprite's x position
   */
  protected double getX() {
    return this.x;
  }

  /**
   * Sets the sprite's x position.
   *
   * @param posX new x position
   */
  protected void setX(final double posX) {
    this.x = posX;
  }

  /**
   * Returns the sprite's y position.
   *
   * @return sprite's y position
   */
  protected double getY() {
    return this.y;
  }

  /**
   * Sets the sprite's y position.
   *
   * @param posY new y position
   */
  protected void setY(final double posY) {
    this.y = posY;
  }

  /**
   * Sets the sprite scale.
   *
   * @param factor the scale factor
   */
  public void setScale(final double factor) {
    this.scale = factor;
  };

  /**
   * Returns the sprite's rotation in radians.
   *
   * @return sprite's rotation in radians
   */
  public double getRotation() {
    return this.radians;
  }

  /**
   * Sets the sprite rotation.
   *
   * @param newRadians radians
   */
  public void setRotation(final double newRadians) {
    this.radians = newRadians;
  };

  /**
   * Sets the sprite position.
   *
   * @param posX origin x coordinate
   * @param posY origin y coordinate
   */
  public void setPosition(final double posX, final double posY) {
    this.setX(posX);
    this.setY(posY);
  };

  /**
   * Moves the sprite relative to it's current position.
   *
   * @param deltaX distance on the x axis
   * @param deltaY distance on the y axis
   */
  public void translate(final double deltaX, final double deltaY) {
    this.setX(this.getX() + deltaX);
    this.setY(this.getY() + deltaY);
  };

  /**
   * Rotates the sprite relative to it's current rotation.
   *
   * @param deltaRadians radians
   */
  public void rotate(final double deltaRadians) {
    this.radians += deltaRadians;
  };

  /**
   * Updates the sprite's properties.
   */
  @Override
  public void update() {
    view.setX(x);
    view.setY(y);
    view.setScaleX(scale);
    view.setScaleY(scale);
    view.setRotate(radians);
  }

  /**
   * Creates a brick tile sprite.
   *
   * @return an instance of the brick tile sprite
   */
  public static Sprite createBrickTile() {
    return new BrickTileSprite();
  }
}
