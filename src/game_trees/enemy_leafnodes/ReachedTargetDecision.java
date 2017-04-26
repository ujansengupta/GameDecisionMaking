package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.composite_nodes.Task;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class ReachedTargetDecision extends Task
{
    public ReachedTargetDecision(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        if (Engine.enemy.getGridIndex() == Engine.player.getGridIndex())
            return returnType.SUCCEEDED;
        else
            return returnType.FAILED;
    }
}
