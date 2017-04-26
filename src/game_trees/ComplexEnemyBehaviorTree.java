package game_trees;

import game_trees.composite_nodes.Double_Parallel;
import game_trees.composite_nodes.Selector;
import game_trees.composite_nodes.Sequence;
import game_trees.enemy_leafnodes.*;

/**
 * Created by ujansengupta on 4/25/17.
 */
public class ComplexEnemyBehaviorTree extends EnemyBehaviourTree
{
    public ComplexEnemyBehaviorTree()
    {
        super();
        createTree();
    }

    @Override
    public void createTree()
    {
        root = new Selector(taskID++);

        root.addChild(new Sequence(taskID++));
        root.children.get(0).addChild(new OutOfBoundsDecision(taskID++));
        root.children.get(0).addChild(new OutOfBoundsAction(taskID++));

        root.addChild(new Sequence(taskID++));
        root.children.get(1).addChild(new ReachedTargetDecision(taskID++));
        root.children.get(1).addChild(new ReachedTargetAction(taskID++));

        root.addChild(new Sequence(taskID++));
        root.children.get(2).addChild(new LOSDecision(taskID++));
        root.children.get(2).addChild(new Double_Parallel(taskID++));
        root.children.get(2).children.get(1).addChild(new Pursue(taskID++));
        root.children.get(2).children.get(1).addChild(new Sequence(taskID++));
        root.children.get(2).children.get(1).children.get(1).addChild(new WithinShootRangeDecision(taskID++));
        root.children.get(2).children.get(1).children.get(1).addChild(new ShootIntervalDecision(taskID++));
        root.children.get(2).children.get(1).children.get(1).addChild(new Shoot(taskID++));

        root.addChild(new Selector(taskID++));
        root.children.get(3).addChild(new Sequence(taskID++));
        root.children.get(3).addChild(new Seek(taskID++));
        root.children.get(3).children.get(0).addChild(new DetectObstacle(taskID++));
        root.children.get(3).children.get(0).addChild(new FindPath(taskID++));
        root.children.get(3).children.get(0).addChild(new Selector(taskID++));
        root.children.get(3).children.get(0).children.get(2).addChild(new LOSDecision(taskID++));
        root.children.get(3).children.get(0).children.get(2).addChild(new PathFollow(taskID++));

    }

}
