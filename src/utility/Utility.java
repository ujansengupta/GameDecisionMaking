package utility;

import objects.GameObject;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * Created by ujansengupta on 3/30/17.
 */
public class Utility
{
    public static float mapToRange(float rotation) {
        float r = rotation % (2 * PConstants.PI);
        if (Math.abs(r) <= Math.PI)
            return r;
        else {
            if (r > Math.PI)
                return (r - 2 * PConstants.PI);
            else
                return (r + 2 * PConstants.PI);
        }
    }

    public static boolean checkTargetReached(GameObject obj, PVector target)
    {
        return((Math.abs(target.x - obj.getPosition().x) <= 20f) && (Math.abs(target.y - obj.getPosition().y) <= 20f));
    }

    public static Integer getGridIndex(PVector position)
    {
        return (int)(position.y/GameConstants.TILE_SIZE.y) * (int) GameConstants.NUM_TILES.x + (int) (position.x/GameConstants.TILE_SIZE.x);
    }

    public static PVector getGridLocation(PVector position)
    {
        return new PVector((int)(position.x/GameConstants.TILE_SIZE.x), (int) (position.y/GameConstants.TILE_SIZE.y));
    }

}
