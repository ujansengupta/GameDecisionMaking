package game_trees;

import java.util.ArrayList;

/**
 * Created by ujansengupta on 4/16/17.
 */
public abstract class Task
{
    public enum returnType
    {
        SUCCEEDED, FAILED, RUNNING, CANCELLED
    }

    public int TaskID;
    public ArrayList<Task> children;
    public returnType state;

    public abstract returnType run();

    /* Helper Methods */

    public int getID()
    {
        return TaskID;
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
