package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.World;
import ca.bcit.comp2522.termproject.essence.controllers.PlayerController;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;

/**
 * The player entity.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public final class Player extends Entity {
  private static Player instance = null;

  private final double defaultHealth = 100.0;
  private final double defaultDamage = 100.0;
  private final double defaultSpeed = 24.0;

  /**
   * Creates a new player entity.
   *
   * @param world reference to the world
   */
  private Player(final World world) {
    super(world, new BrickTileSprite(), Layers.PLAYER_LAYER);

    this.setStat(Entity.Stats.HEALTH, this.defaultHealth);
    this.setStat(Entity.Stats.DAMAGE, this.defaultDamage);
    this.setStat(Entity.Stats.SPEED, this.defaultSpeed);

    this.possess(new PlayerController());
  }

  /**
   * Returns a single instance of the player.
   *
   * @param world reference to the world
   * @return player instance
   */
  public static Player getPlayer(final World world) {
    if (instance == null) {
      instance = new Player(world);
    }

    return instance;
  }
}
