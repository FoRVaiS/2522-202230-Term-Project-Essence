package ca.bcit.comp2522.termproject.essence.interfaces;

import ca.bcit.comp2522.termproject.essence.Skill;

/**
 * Our character interface that will contain their statistics and abilities.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */

public interface Character {

    /**
     * User's ability to use their desired input for a skill.
     * @param slotKey character's skill slot key
     */
    void useSkillSlot(int slotKey);

    /**
     * Bind a skill to a dedicated slot.
     * @param characterSkill character's skill or their ability
     * @param skillSlot character's skill slot as int
     */
    void bindSkillToSlot(Skill characterSkill, int skillSlot);

    /**
     * Change the character's stat based on skills or external events.
     * @param stat character's baseline stats
     * @param changeStatByScale character's stat as a double
     */
    void modifyStats(Stats stat, double changeStatByScale);
    /**
     * Getter for the character's statistics.
     */
    void getStats();

    /**
     * Baseline stats for our character.
     */
    enum Stats {
        HEALTH,
        BASE_DAMAGE,
        SPEED
    }
}
