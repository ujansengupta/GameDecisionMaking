package game_trees.composite_nodes;

/**
 * Created by ujansengupta on 4/19/17.
 */
public class Double_Parallel extends Task
{
    public Double_Parallel(int taskID)
    {
        super(taskID);
    }

    @Override
    public returnType run()
    {
        returnType child1 = children.get(0).run();
        returnType child2 = children.get(1).run();

        if (child1 == returnType.SUCCEEDED || child2 == returnType.SUCCEEDED)
            return returnType.SUCCEEDED;

        return returnType.FAILED;
    }
}
