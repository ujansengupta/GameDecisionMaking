package game_trees.enemy_leafnodes;

import engine.Engine;
import game_trees.EnemyBehaviourTree;
import game_trees.composite_nodes.Task;
import objects.Enemy;
import objects.Enemy.State;


/**
 * Created by ujansengupta on 4/19/17.
 */
public class Seek extends Task
{
    public Seek(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        Enemy.pathFollower.reachedTarget = true;

        Engine.enemy.Align(Engine.player.getPosition());
        Engine.enemy.Seek(Engine.player.getPosition());

        Enemy.state = Enemy.State.SEEK;

        return returnType.SUCCEEDED;
    }
}
