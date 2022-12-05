package ca.bcit.comp2522.termproject.essence;

/**
 * An object that represents a 2D vector.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class Vec2D {
  private double x;
  private double y;

  /**
   * Creates an instance a 2D vector.
   */
  public Vec2D() {
    this(0.0, 0.0);
  }

  /**
   * Creates an instance a 2D vector.
   *
   * @param x x coordinate
   * @param y y coordinate
   */
  public Vec2D(final double x, final double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate.
   *
   * @return x coordinate
   */
  public double getX() {
    return this.x;
  }

  /**
   * Sets the x coordinate.
   *
   * @param xPos new x position
   */
  public void setX(final double xPos) {
    this.x = xPos;
  }

  /**
   * Returns the y coordinate.
   *
   * @return y coordinate
   */
  public double getY() {
    return this.y;
  }

  /**
   * Sets the y coordinate.
   *
   * @param yPos new y position
   */
  public void setY(final double yPos) {
    this.y = yPos;
  }

  /**
   * Calcuates the sum of two 2D vectors.
   *
   * @param vec      left-hand side vector
   * @param otherVec right-hand side vector
   * @return the sum of both vectors
   */
  public static Vec2D add(final Vec2D vec, final Vec2D otherVec) {
    return new Vec2D(vec.getX() + otherVec.getX(), vec.getY() + otherVec.getY());
  }

  /**
   * Calcuates the difference of two 2D vectors.
   *
   * @param vec      left-hand side vector
   * @param otherVec right-hand side vector
   * @return the difference of both vectors
   */
  public static Vec2D subtract(final Vec2D vec, final Vec2D otherVec) {
    return new Vec2D(vec.getX() - otherVec.getX(), vec.getY() - otherVec.getY());
  }

  /**
   * Calcuates the quotient of two 2D vectors.
   *
   * @param vec      left-hand side vector
   * @param otherVec right-hand side vector
   * @return the quotient of both vectors
   */
  public static Vec2D divide(final Vec2D vec, final Vec2D otherVec) {
    return new Vec2D(vec.getX() / otherVec.getX(), vec.getY() / otherVec.getY());
  }

  /**
   * Calcuates the product of two 2D vectors.
   *
   * @param vec      left-hand side vector
   * @param otherVec right-hand side vector
   * @return the product of both vectors
   */
  public static Vec2D multiply(final Vec2D vec, final Vec2D otherVec) {
    return new Vec2D(vec.getX() * otherVec.getX(), vec.getY() * otherVec.getY());
  }

  /**
   * Copys the 2D vector.
   *
   * @param vec the vector to copy
   * @return a shallow copy of a 2D vector
   */
  public static Vec2D copy(final Vec2D vec) {
    return new Vec2D(vec.getX(), vec.getY());
  }
}
