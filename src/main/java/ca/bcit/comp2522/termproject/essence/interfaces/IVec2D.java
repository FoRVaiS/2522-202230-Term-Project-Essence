package ca.bcit.comp2522.termproject.essence.interfaces;

/**
 * An object that is in a 2-dimensional world.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public interface IVec2D {
  /**
   * Returns the x coordinate.
   *
   * @return x coordinate
   */
  double getX();

  /**
   * Sets the x coordinate.
   *
   * @param xPos new x position
   */
  void setX(double xPos);

  /**
   * Returns the y coordinate.
   *
   * @return y coordinate
   */
  double getY();

  /**
   * Sets the y coordinate.
   *
   * @param yPos new y position
   */
  void setY(double yPos);
}
