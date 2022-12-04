package ca.bcit.comp2522.termproject.essence.interfaces;

/**
 * Interface for objects that are collidable with other objects.
 *
 * @param <T> type of collidable object
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public interface Collidable<T> {
  /**
   * Fires when this object collides with another object.
   *
   * @param obj the colliding object
   */
  void onCollision(T obj);
}
