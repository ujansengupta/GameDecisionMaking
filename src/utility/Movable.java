package utility;

import objects.GameObject;
import processing.core.PVector;

/**
 * Created by ujansengupta on 4/7/17.
 */
public interface Movable
{
    void Seek(PVector target);

    void Arrive(PVector target);

    void Align(PVector target);

    void Wander();

    void Pursue(GameObject target);

    void stopMoving();

    boolean outOfBounds();

}
