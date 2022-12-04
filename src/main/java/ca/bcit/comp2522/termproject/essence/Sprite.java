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
  public static final Image SPRITE_PLAYER = new Image("SPRITE_PLAYER.png", 120 - 12, 220 - 22, true, true);

  /** Image reference for the PLAYER character. */
  public static final Image PROJECTILE_PELLET = new Image("SPRITE_PELLET_PROJECTILE.png");

  /** Image reference for the BRICK tile. */
  public static final Image TILE_BRICK = new Image("TILE_BRICK.png", TILE_SIZE, TILE_SIZE, false, false);

  /**
   * Sprite's position.
   */
  private final Vec2D position;

  private final ImageView view;

  private double scale;
  private double radians;

  /**
   * Creates a sprite using a given image.
   *
   * @param image sprite image
   */
  public Sprite(final Image image) {
    this(image, new Vec2D());
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param image    sprite image
   * @param position sprite position
   */
  public Sprite(final Image image, final Vec2D position) {
    this(image, position, 1);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param image    sprite image
   * @param position sprite position
   * @param scale    sprite scale factor
   */
  public Sprite(final Image image, final Vec2D position, final double scale) {
    this(image, position, scale, 0);
  }

  /**
   * Creates a sprite using a given image.
   *
   * @param image    sprite image
   * @param position sprite position
   * @param scale    sprite scale factor
   * @param radians  sprite rotation in radians
   */
  public Sprite(final Image image, final Vec2D position, final double scale, final double radians) {
    this.view = new ImageView(image);
    this.view.setSmooth(false);

    this.position = position;
    this.scale = scale;
    this.radians = radians;
    this.update(0);
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
   * @param newPos new position
   */
  public void setPosition(final Vec2D newPos) {
    this.position.setX(newPos.getX());
    this.position.setY(newPos.getY());
  };

  /**
   * Moves the sprite relative to it's current position.
   *
   * @param deltaVector 2D vector containing position deltas
   */
  public void translate(final Vec2D deltaVector) {
    this.position.setX(this.position.getX() + deltaVector.getX());
    this.position.setY(this.position.getY() + deltaVector.getY());
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
   *
   * @param deltaTime time since last tick
   */
  @Override
  public void update(final long deltaTime) {
    view.setX(this.position.getX());
    view.setY(this.position.getY());
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
