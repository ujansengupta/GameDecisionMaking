package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;
import objects.Enemy;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class ReachedTargetAction extends Task
{
    public ReachedTargetAction(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        Engine.reset();
        return returnType.SUCCEEDED;
    }
}
