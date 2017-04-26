package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;
import objects.Enemy;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class PathFollow extends Task
{
    public PathFollow(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        /*if (!Enemy.pathFollower.reachedTarget)
            Enemy.pathFollower.renderSearch();*/

        if (Enemy.pathFollower.reachedTarget)
        {
            setState(returnType.SUCCEEDED);
            return returnType.SUCCEEDED;
        }
        else
        {
            Enemy.pathFollower.followPath();

            Enemy.state = Enemy.State.PATHFOLLOW;

            setState(returnType.RUNNING);
            return returnType.RUNNING;
        }
    }
}
