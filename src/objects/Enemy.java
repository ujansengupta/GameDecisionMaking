package objects;

import engine.Engine;
import environment.Environment;
import environment.PathFollower;
import processing.core.PApplet;
import processing.core.PVector;
import utility.GameConstants;

/**
 * Created by ujansengupta on 3/31/17.
 */
public abstract class Enemy extends GameObject
{

    public enum stateList
    {
        ATTACKPLAYER, SEEKTOOTH, WANDER, AVOID, SHOOTBULLETS
    }

    public int PURSUE_RADIUS;

    public GameObject finalTarget;
    public PathFollower pathFollower;

    public boolean isFollowingPath;

    public float contactDamage;

    public Enemy(PApplet app, PVector color, float size, float posX, float posY, float orientation, int life, int PursueRadius)
    {
        super (app, color, size, posX, posY, orientation, life);

        isFollowingPath = false;
        PURSUE_RADIUS = PursueRadius;
        pathFollower = new PathFollower(this, Environment.numTiles, Environment.tileSize);
        contactDamage = GameConstants.DEFAULT_CONTACT_DAMAGE;
    }

    public abstract void behaviour();

    public abstract void defaultBehaviour();

    public abstract void avoidObstacle();


    public GameObject getFinalTarget()
    {
        return finalTarget;
    }


}
