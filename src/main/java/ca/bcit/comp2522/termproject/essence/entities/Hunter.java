package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.World;
import ca.bcit.comp2522.termproject.essence.sprites.PlayerSprite;

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
        super(world, new PlayerSprite(), Layers.FOREGROUND_LAYER);

        this.setStat(Entity.Stats.HEALTH, defaultHunterHealth);
        this.setStat(Entity.Stats.DAMAGE, defaultHunterDamage);
        this.setStat(Entity.Stats.SPEED, defaultHunterSpeed);
    }
}
