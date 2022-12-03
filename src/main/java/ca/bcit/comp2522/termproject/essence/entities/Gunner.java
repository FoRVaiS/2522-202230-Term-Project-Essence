package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Vec2D;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;

/**
 * The gunner entity.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class Gunner extends Entity {

    private final double defaultGunnerHealth = 60.0;
    private final double defaultGunnerDamage = 15.0;
    private final double defaultGunnerSpeed = 12.0;

    /**
     * Creates a new gunner entity.
     */
    public Gunner() {
        super(new BrickTileSprite(), new Vec2D());
    }

    /**
     * Sets the stats of the gunner mob.
     */
    @Override
    protected void setStats() {
        this.stats.put(Entity.Stats.HEALTH, this.defaultGunnerHealth);
        this.stats.put(Entity.Stats.DAMAGE, this.defaultGunnerDamage);
        this.stats.put(Entity.Stats.SPEED, this.defaultGunnerSpeed);
    }

    /**
     * Returns the gunner's x position.
     *
     * @return gunner's x position
     */
    public double getX() {
        return this.position.getX();
    }
    /**
     * Returns the gunner's y position.
     *
     * @return gunner's y position
     */
    public double getY() {
        return this.position.getY();
    }
}
