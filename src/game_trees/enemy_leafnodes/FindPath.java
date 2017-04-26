package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;
import objects.Enemy;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class FindPath extends Task
{
    public FindPath(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        Enemy.pathFollower.findPath(Engine.enemy.getGridLocation(), Engine.player.getGridLocation());
        return returnType.SUCCEEDED;
    }
}
