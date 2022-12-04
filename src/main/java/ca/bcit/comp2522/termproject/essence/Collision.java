package ca.bcit.comp2522.termproject.essence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * A utility class to determine if objects are colliding.
 *
 * @author Benjamin Chiang, Felix Lieu
 * @version 0.1.0
 */
public final class Collision {
  private Collision() {
  }

  /**
   * Tests if an entity is intersecting another entity on the x axis.
   *
   * @param ent      an entity
   * @param otherEnt another entity to test against
   * @return true if the two entities collide on the x axis else false
   */
  private static boolean intersectX(final Entity ent, final Entity otherEnt) {
    final double entLeftEdge = ent.getX() - ent.getWidth() / 2;
    final double entRightEdge = ent.getX() + ent.getWidth() / 2;

    final double otherEntLeftEdge = otherEnt.getX() - otherEnt.getWidth() / 2;
    final double otherEntRightEdge = otherEnt.getX() + otherEnt.getWidth() / 2;

    final boolean hasRightIntersection = entRightEdge <= otherEntRightEdge && entRightEdge >= otherEntLeftEdge;
    final boolean hasLeftIntersection = entLeftEdge <= otherEntRightEdge && entLeftEdge >= otherEntLeftEdge;

    return hasRightIntersection || hasLeftIntersection;
  }

  /**
   * Tests if an entity is intersecting another entity on the y axis.
   *
   * @param ent      an entity
   * @param otherEnt another entity to test against
   * @return true if the two entities collide on the y axis else false
   */
  private static boolean intersectY(final Entity ent, final Entity otherEnt) {
    final double entTopEdge = ent.getY() - ent.getHeight() / 2;
    final double entBottomEdge = ent.getY() + ent.getHeight() / 2;

    final double otherEntTopEdge = otherEnt.getY() - otherEnt.getHeight() / 2;
    final double otherEntBottomEdge = otherEnt.getY() + otherEnt.getHeight() / 2;

    final boolean hasTopIntersection = entBottomEdge <= otherEntBottomEdge && entBottomEdge >= otherEntTopEdge;
    final boolean hasBottomIntersection = entTopEdge <= otherEntBottomEdge && entTopEdge >= otherEntTopEdge;

    return hasTopIntersection || hasBottomIntersection;
  }

  /**
   * Partitions entities colliding on the x axis into groups.
   *
   * @param entities a list of entities
   * @return a list of all possibly colliding entities partitioned into groups
   */
  private static ArrayList<Entity[]> partition(final List<Entity> entities) {
    final List<Entity> sortedEntities = Arrays.asList(entities.toArray(Entity[]::new));

    // Sort the entities based on their x coordinate.
    sortedEntities.sort((ent, otherEnt) -> {
      final double difference = ent.getX() - otherEnt.getX();

      if (difference > 0) {
        return 1;
      } else if (difference < 0) {
        return -1;
      } else {
        return 0;
      }
    });

    final ArrayList<Entity[]> possibleCollisions = new ArrayList<>();
    final Stack<Entity> active = new Stack<>();

    /*
     * Given a sorted list of entities, find all entities that are intersecting from
     * the perspective of only the x axis.
     */
    for (final Entity ent : sortedEntities) {
      // if the stack has at least one entity in it...
      if (!active.isEmpty()) {

        // ... get the most recent node ...
        final Entity lastEnt = active.peek();

        // ... and check if the current node and the last node intersect ...
        if (!Collision.intersectX(ent, lastEnt)) {
          // ... and if they don't, safely store away the last group of possibly colliding
          // entities.
          possibleCollisions.add(active.toArray(Entity[]::new));

          // Clear the stack for the next group of possibly colliding entities.
          active.clear();
        }
      }

      // Append the entity to the stack.
      active.add(ent);
    }

    return possibleCollisions;
  }

  /**
   * Determines if entities are colliding and triggers their onCollision methods.
   *
   * @param entities a list of entities
   */
  public static void intersect(final ArrayList<Entity> entities) {
    /*
     * Given a list of possibly colliding entity groups, we want to verify if a PAIR
     * of entities in a particular group are actually colliding by checking if they
     * are also vertically colliding.
     *
     * We will check all possible combinations of entities in a group and if a pair
     * is determined to be colliding then their onCollision method will be
     * triggered.
     *
     * Given a set of 4 elements: [0, 1, 2, 3], all possible combinations are:
     * - [0, 1]
     * - [0, 2]
     * - [0, 3]
     * - [1, 2]
     * - [1, 3]
     * - [2, 3]
     *
     * We will use two indices, a base index and a sub index.
     * The base index will always start from the beginning of the list but the sub
     * index will always begin one more element ahead of the base index.
     *
     * - The base index will only increment once the sub index has reached the end
     * of the list.
     * - The base index will not increment past the penultimate element.
     * - The sub index will iterate through the entire list and reseting to one
     * index ahead of the base index once the base index has incremented.
     */

    final ArrayList<Entity[]> possibleCollisions = Collision.partition(entities);

    for (final Entity[] xGroup : possibleCollisions) {
      for (int baseIndex = 0; baseIndex < (xGroup.length - 1); baseIndex++) {
        for (int subIndex = baseIndex + 1; subIndex < xGroup.length; subIndex++) {
          // Select a unique pair of entities.
          final Entity ent = xGroup[baseIndex];
          final Entity otherEnt = xGroup[subIndex];

          // Check if the pair of entities are colliding on the y axis ...
          if (Collision.intersectY(ent, otherEnt)) {
            // ... and if they are, trigger their onCollisions methods and pass the entity
            // they collidied into.
            ent.onCollision(otherEnt);
            otherEnt.onCollision(ent);
          }
        }
      }
    }
  }
}
