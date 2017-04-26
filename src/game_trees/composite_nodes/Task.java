package game_trees.composite_nodes;

import java.util.ArrayList;

/**
 * Created by ujansengupta on 4/16/17.
 */
public abstract class Task
{
    public enum returnType
    {
        SUCCEEDED, FAILED, RUNNING, CANCELLED, READY
    }

    public int taskID;
    public ArrayList<Task> children;
    public returnType state;

    public Task(int taskID)
    {
        this.taskID = taskID;
        children = new ArrayList<>();
        setState(returnType.READY);
    }

    public abstract returnType run();


    /* Helper Methods */

    public void addChild(Task child)
    {
        children.add(child);
    }

    public int getID()
    {
        return taskID;
    }

    public void setState(returnType state)
    {
        this.state = state;
    }

    public returnType getState()
    {
        return state;
    }

    public int whichChildRunning()
    {
        for (int i = 0; i<children.size(); i++)
        {
            if (children.get(i).getState() == returnType.RUNNING)
                return i;
        }

        return -1;
    }
}
