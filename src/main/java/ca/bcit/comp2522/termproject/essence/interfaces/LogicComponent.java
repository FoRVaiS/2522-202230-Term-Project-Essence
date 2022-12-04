package ca.bcit.comp2522.termproject.essence.interfaces;

/**
 * A component containing logic that is required to be updated.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public interface LogicComponent {
  /**
   * Updates the component.
   *
   * @param deltaTime time since last tick
   */
  void update(long deltaTime);
}
