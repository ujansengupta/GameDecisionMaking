package objects;

import engine.Engine;
import environment.Environment;
import environment.PathFollower;
import processing.core.PApplet;
import processing.core.PVector;
import utility.GameConstants;
import utility.Utility;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ujansengupta on 3/31/17.
 */
public class Player extends GameObject
{
    public enum State
    {
        WANDER, SEEKCENTER, SEEKTARGET
    }

    private PApplet app;

    /* Player properties */

    public static PVector color = new PVector(109, 69, 1);
    public static float size = 20;

    private static float DEFAULT_X = GameConstants.SCR_WIDTH/2 + 90;
    private static float DEFAULT_Y = GameConstants.SCR_HEIGHT/2 + 90;

    private static float DEFAULT_ORIENTATION = 0;
    private static final int DEFAULT_PLAYER_LIFE = 100;

    private PVector scrCenter = GameConstants.SCR_CENTER;

    private PathFollower pathfollower;

    public Set<Bullet> bullets;
    public PVector playerTarget;
    public static float BulletDamage = 10;


    public State state;


    public Player(PApplet app)
    {
        super (app, color, size, DEFAULT_X, DEFAULT_Y, DEFAULT_ORIENTATION, DEFAULT_PLAYER_LIFE);
        this.app = app;

        setMaxVel(1f);
        setMaxAngularAcc(0.001f);
        setAngularROS(1.5f);
        setAngularROD(2.5f);

        bullets = new HashSet<>();
        playerTarget = new PVector(app.mouseX, app.mouseY);
        state = State.WANDER;
        pathfollower = new PathFollower(this);

    }

    public void shoot()
    {
        bullets.add(new Bullet(app, getPosition(), getOrientation(), GameConstants.DEFAULT_BULLET_SIZE, color));
    }
    
    public void update()
    {
        behaviour();
        super.update();

        for (Iterator<Bullet> i = bullets.iterator(); i.hasNext(); )
        {
            Bullet b = i.next();

            if (b.outOfBounds())
                i.remove();
            else
                b.update();
        }
    }

    public void behaviour()
    {
        if (obstacleCollisionDetected())
        {
            state = State.SEEKCENTER;
            pathfollower.findPath(this.getGridLocation(), GameConstants.GRAPH_CENTER);
            setMaxVel(2f);
        }

        switch(state)
        {
            case SEEKTARGET:
                Align(playerTarget);
                Arrive(playerTarget);
                if (velocity.mag() > maxVel - 0.1)
                {
                    state = State.WANDER;
                    setMaxVel(1f);
                }
                break;

            case SEEKCENTER:
                pathfollower.followPath();
                if (pathfollower.reachedTarget) {
                    state = State.WANDER;
                    setMaxVel(1f);
                }
                break;

            case WANDER:
                setMaxAngularAcc(0.001f);
                Wander();
                break;
        }
    }

    public void updateTarget()
    {
        playerTarget = new PVector(app.mouseX, app.mouseY);
        setMaxVel(3f);
        setMaxAngularAcc(GameConstants.DEFAULT_MAX_angularACC);
        state = State.SEEKTARGET;
    }

}

