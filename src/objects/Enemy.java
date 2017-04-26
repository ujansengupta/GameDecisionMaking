package objects;

import engine.Engine;
import environment.Environment;
import environment.PathFollower;
import game_trees.ComplexEnemyBehaviorTree;
import game_trees.EnemyBehaviourTree;
import game_trees.enemy_leafnodes.Shoot;
import processing.core.PApplet;
import processing.core.PVector;
import utility.GameConstants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ujansengupta on 3/31/17.
 */
public class Enemy extends GameObject
{

    public enum State
    {
        SEEK, PATHFOLLOW, PURSUE, SHOOT
    }

    public static int life = 100;
    public static float size = 25;
    public static float orientation = 0;

    public static int shootInterval = 1000;
    public static int shootRadius = 200;
    public int lastShotTime;

    public Set<Bullet> bullets;
    public static float BulletDamage = 10;

    public static PVector color = new PVector(96, 122, 62);
    private static float DEFAULT_X = 0.5f * GameConstants.SCR_WIDTH;
    private static float DEFAULT_Y = 0.07f * GameConstants.SCR_HEIGHT;

    public static PathFollower pathFollower;
    public static boolean isFollowingPath = false;
    public static EnemyBehaviourTree behaviourTree;
    public static State state;

    public static boolean useLearntTree = true;

    public Enemy(PApplet app)
    {
        super (app, color, size, DEFAULT_X, DEFAULT_Y, orientation, life);

        bullets = new HashSet<>();
        behaviourTree = new ComplexEnemyBehaviorTree();
        pathFollower = new PathFollower(this);
        lastShotTime = app.millis();
    }

    public void shoot()
    {
        bullets.add(new Bullet(app, getPosition(), getOrientation(), GameConstants.DEFAULT_BULLET_SIZE, color));
    }

    public void update()
    {
        if (useLearntTree)
        {
            behaviour();
            if (hasReachedTarget())
                Engine.reset();
        }
        else
            behaviourTree.run();


        super.update();

        for (Iterator<Bullet> i = bullets.iterator(); i.hasNext(); )
        {
            Bullet b = i.next();

            if(b.hasHit(Engine.player))
            {
                i.remove();
                Engine.player.takeDamage(Enemy.BulletDamage);

                break;
            }
            else if (b.outOfBounds())
                i.remove();
            else
                b.update();
        }
    }

    public void behaviour()
    {
        if (hasLOS(Engine.player.getPosition()))
        {
            isFollowingPath = false;
            Pursue(Engine.player);
            if (targetWithinShootingRange() && app.millis() - lastShotTime >= shootInterval)
            {
                shoot();
                lastShotTime = app.millis();
            }
        }
        else if (obstacleNearby())
        {
            if (!isFollowingPath)
                pathFollower.findPath(getGridLocation(), Engine.player.getGridLocation());

            pathFollower.followPath();
        }
        else
        {
            isFollowingPath = false;
            Seek(Engine.player.getPosition());
        }
    }

    public void defaultBehaviour()
    {

    }

    public void avoidObstacle()
    {

    }

    public boolean targetWithinShootingRange()
    {
        return (PVector.sub(Engine.player.getPosition(), Engine.enemy.getPosition()).mag() < Enemy.shootRadius);
    }

    public boolean hasReachedTarget()
    {
        return (Engine.enemy.getGridIndex() == Engine.player.getGridIndex());
    }

    public void reset()
    {
        setPosition(new PVector(DEFAULT_X, DEFAULT_Y));
        setOrientation(orientation);
    }



}
