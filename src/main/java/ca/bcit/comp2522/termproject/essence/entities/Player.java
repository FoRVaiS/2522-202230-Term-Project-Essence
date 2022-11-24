package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.Vec2D;
import ca.bcit.comp2522.termproject.essence.controllers.PlayerController;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;

/**
 * The player entity.
 *
 * @author Benjamin Chiang
 * @version 0.1.0
 */
public final class Player extends Entity {
  private static Player instance = null;

  private final double defaultHealth = 100.0;
  private final double defaultDamage = 100.0;
  private final double defaultSpeed = 24.0;

  /**
   * Creates a new player entity.
   */
  private Player() {
    super(new BrickTileSprite(), new Vec2D());

    this.render(Layers.PLAYER_LAYER);
    this.possess(new PlayerController());
  }

  /**
   * Returns a single instance of the player.
   *
   * @return player instance
   */
  public static Player getPlayer() {
    if (instance == null) {
      instance = new Player();
    }

    return instance;
  }

  /**
   * Sets the stats of the player.
   */
  @Override
  protected void setStats() {
    this.stats.put(Entity.Stats.HEALTH, this.defaultHealth);
    this.stats.put(Entity.Stats.DAMAGE, this.defaultDamage);
    this.stats.put(Entity.Stats.SPEED, this.defaultSpeed);
  }

  /**
   * Shifts everything but the player view layer in a direction on the x-axis.
   *
   * @param xDir the direction to move on the x-axis
   */
  @Override
  public void moveX(final double xDir) {
    super.moveX(xDir);

    final double currX = Layers.BACKGROUND_LAYER.getTranslateX();

    Layers.BACKGROUND_LAYER.setTranslateX(currX + xDir * this.stats.get(Entity.Stats.SPEED));
    Layers.FOREGROUND_LAYER.setTranslateX(currX + xDir * this.stats.get(Entity.Stats.SPEED));
  }

  /**
   * Shifts everything but the player view layer in a direction on the x-axis.
   *
   * @param yDir the direction to move on the x-axis
   */
  @Override
  public void moveY(final double yDir) {
    super.moveY(yDir);

    final double currY = Layers.BACKGROUND_LAYER.getTranslateY();

    Layers.BACKGROUND_LAYER.setTranslateY(currY + yDir * this.stats.get(Entity.Stats.SPEED));
    Layers.FOREGROUND_LAYER.setTranslateY(currY + yDir * this.stats.get(Entity.Stats.SPEED));
  }

  /**
   * Update player logic.
   */
  @Override
  public void update() {
  }
}
