package game_trees.enemy_leafnodes;

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
        return returnType.SUCCEEDED;
    }
}
