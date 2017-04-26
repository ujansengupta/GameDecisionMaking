package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.EnemyBehaviourTree;
import game_trees.composite_nodes.Task;
import objects.Enemy;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class Pursue extends Task
{
    public Pursue(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        Enemy.pathFollower.reachedTarget = true;
        Engine.enemy.Pursue(Engine.player);
        Enemy.state = Enemy.State.PURSUE;

        return returnType.SUCCEEDED;
    }
}
