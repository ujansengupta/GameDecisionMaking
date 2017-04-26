package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class DetectObstacle extends Task
{
    public DetectObstacle(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        if (Engine.enemy.obstacleCollisionDetected())
            return returnType.SUCCEEDED;

        return returnType.FAILED;
    }
}
