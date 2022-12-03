package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.Vec2D;
import ca.bcit.comp2522.termproject.essence.World;
import ca.bcit.comp2522.termproject.essence.sprites.BrickTileSprite;

/**
 * The hunter entity.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class Hunter extends Entity {


    private final double defaultHunterHealth = 50.0;

    private final double defaultHunterDamage = 25.0;

    private final double defaultHunterSpeed = 12.0;

    /**
     * Creates a new gunner entity.
     *
     * @param world reference to the world
     */
    public Hunter(final World world) {
        super(world, new BrickTileSprite(), Layers.FOREGROUND_LAYER, new Vec2D());
    }

    /**
     * Sets the stats of the hunter mob.
     */
    @Override
    protected void setStats() {
        this.stats.put(Entity.Stats.HEALTH, this.defaultHunterHealth);
        this.stats.put(Entity.Stats.DAMAGE, this.defaultHunterDamage);
        this.stats.put(Entity.Stats.SPEED, this.defaultHunterSpeed);
    }

    /**
     * Returns the hunter's x position.
     *
     * @return hunter's x position
     */
    public double getX() {
        return this.position.getX();
    }

    /**
     * Returns the hunter's y position.
     *
     * @return hunter's y position
     */
    public double getY() {
        return this.position.getY();
    }
}
