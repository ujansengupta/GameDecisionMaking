package game_trees;

import game_trees.composite_nodes.*;
import game_trees.enemy_leafnodes.*;

/**
 * Created by ujansengupta on 4/19/17.
 */

public abstract class EnemyBehaviourTree
{
    public int taskID = 0;

    public Task root;
    public static Blackboard blackboard;

    public EnemyBehaviourTree()
    {
        blackboard = new Blackboard();
    }

    public void run()
    {
        root.run();
    }

    public abstract void createTree();
}
