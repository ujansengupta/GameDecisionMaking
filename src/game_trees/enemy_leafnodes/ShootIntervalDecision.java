package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;
import objects.Enemy;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class ShootIntervalDecision extends Task
{
    public ShootIntervalDecision(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        int timeDiff = Engine.enemy.app.millis() - Engine.enemy.lastShotTime;

        if (timeDiff > Enemy.shootInterval)
        {
            Engine.enemy.lastShotTime = Engine.enemy.app.millis();
            return returnType.SUCCEEDED;
        }


        return returnType.FAILED;
    }
}
