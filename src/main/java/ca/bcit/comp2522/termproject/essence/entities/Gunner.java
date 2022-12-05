package ca.bcit.comp2522.termproject.essence.entities;

import ca.bcit.comp2522.termproject.essence.Entity;
import ca.bcit.comp2522.termproject.essence.Layers;
import ca.bcit.comp2522.termproject.essence.World;
import ca.bcit.comp2522.termproject.essence.sprites.PlayerSprite;

/**
 * The gunner entity.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public class Gunner extends Entity {
    private final double defaultGunnerHealth = 60.0;
    private final double defaultGunnerDamage = 15.0;
    private final double defaultGunnerSpeed = 5.0;

    /**
     * Creates a new gunner entity.
     *
     * @param world reference to the world
     */
    public Gunner(final World world) {
        super(world, new PlayerSprite(), Entity.Teams.ENEMY, Layers.FOREGROUND_LAYER);

        this.setStat(Entity.Stats.HEALTH, defaultGunnerHealth);
        this.setStat(Entity.Stats.DAMAGE, defaultGunnerDamage);
        this.setStat(Entity.Stats.SPEED, defaultGunnerSpeed);
    }
}
