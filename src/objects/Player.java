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
        WANDER, SEEKCENTER, SEEKTARGET, FLEE
    }

    private PApplet app;

    /* Player properties */

    public static PVector color = new PVector(109, 69, 1);
    public static float size = 20;

    private static float DEFAULT_X = GameConstants.SCR_WIDTH/2 + 90;
    private static float DEFAULT_Y = GameConstants.SCR_HEIGHT/2 + 90;

    private static float DEFAULT_ORIENTATION = 0;
    private static final int DEFAULT_PLAYER_LIFE = 100;
    private static float DEFAULT_MAXVEL = 1.5f;

    private PVector scrCenter = GameConstants.SCR_CENTER;

    private PathFollower pathfollower;

    public Set<Bullet> bullets;
    public PVector playerTarget;
    public static float BulletDamage = 10;

    public static float enemyDetectionRadius = 75;

    public State state;


    public Player(PApplet app)
    {
        super (app, color, size, DEFAULT_X, DEFAULT_Y, DEFAULT_ORIENTATION, DEFAULT_PLAYER_LIFE);
        this.app = app;

        setMaxVel(DEFAULT_MAXVEL);
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

        /*if (life < DEFAULT_PLAYER_LIFE)
            System.out.println("Here");*/

        setMaxVel(DEFAULT_MAXVEL * ((float) life/DEFAULT_PLAYER_LIFE));

        if(life <= 0)
            Engine.reset();

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
        /*if (Math.abs(PVector.sub(Engine.enemy.getPosition(), position).mag()) < enemyDetectionRadius)
        {
            state = State.FLEE;
        }*/

        if (outOfBounds() || obstacleCollisionDetected())
        {
            state = State.SEEKCENTER;
            pathfollower.findPath(this.getGridLocation(), GameConstants.GRAPH_CENTER);
        }

        switch(state)
        {
            case SEEKTARGET:
                if (!hasLOS(playerTarget))
                {
                    state = State.WANDER;
                    break;
                }

                Align(playerTarget);
                Arrive(playerTarget);
                if (velocity.mag() > maxVel - 0.1)
                    state = State.WANDER;
                break;

            case SEEKCENTER:
                pathfollower.followPath();
                //pathfollower.renderSearch();
                if (pathfollower.reachedTarget)
                    state = State.WANDER;
                break;

            case WANDER:
                Wander();
                break;

            /*case FLEE:
                Flee(Engine.enemy);
                break;*/
        }
    }

    public void updateTarget()
    {
        playerTarget = new PVector(app.mouseX, app.mouseY);
        setMaxVel(1.5f);
        //setMaxAngularAcc(GameConstants.DEFAULT_MAX_angularACC);
        state = State.SEEKTARGET;
    }

    public void reset()
    {
        setPosition(new PVector(DEFAULT_X, DEFAULT_Y));
        setMaxVel(DEFAULT_MAXVEL);
        setLife(DEFAULT_PLAYER_LIFE);
        state = State.WANDER;
    }

}

