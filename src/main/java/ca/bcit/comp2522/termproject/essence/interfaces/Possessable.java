package ca.bcit.comp2522.termproject.essence.interfaces;

/**
 * An object that is possessable by a controller.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public interface Possessable {
  /**
   * Sets the controller.
   *
   * @param controller controller to set
   */
  void possess(Controller controller);

  /**
   * Removes the current controller.
   */
  void unpossess();
}
