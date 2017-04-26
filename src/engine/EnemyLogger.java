package engine;

import engine.Engine;
import jdk.nashorn.api.scripting.JSObject;
import objects.Enemy;
import objects.GameObject;
import org.json.simple.*;
import processing.core.PApplet;
import processing.core.PVector;
import utility.GameConstants;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ujansengupta on 4/25/17.
 */
public class EnemyLogger
{
    public PApplet app;

    public JSONObject jsonObject;
    public JSONArray jsonArray;

    public Enemy character;
    public int timerStart;
    public int timerInterval;

    public EnemyLogger (PApplet app, Enemy enemy, int timerInterval)
    {
        this.app = app;
        character = enemy;
        this.timerInterval = timerInterval;
        jsonObject = new JSONObject();
        jsonArray = new JSONArray();
        timerStart = app.millis();
    }

    public void update()
    {
        if (app.millis() - timerStart >= timerInterval)
        {
            log();
            timerStart = app.millis();
        }
    }

    public void log()
    {
        JSONObject obj = new JSONObject();

        obj.put("BOUNDARY CHECK", character.outOfBounds());
        obj.put("OBSTACLE CHECK", character.obstacleNearby()|character.obstacleCollisionDetected());
        obj.put("DISTANCE CHECK", character.targetWithinShootingRange());
        obj.put("LOS CHECK", character.hasLOS(Engine.player.getPosition()));
        obj.put("STATE", Enemy.state.name());

        jsonArray.add(obj);
    }

    public void writeToFile(String fileName)
    {
        try (FileWriter file = new FileWriter("/Users/ujansengupta/Desktop/Courses/Building_Game_AI/Homeworks/Homework_3/Training_Data/" + fileName + ".json"))
        {
            jsonObject.put("Logs", jsonArray);
            file.write(jsonObject.toJSONString());
            //System.out.println("Successfully Copied JSON Object to File...");
            //System.out.println("\nJSON Object: " + jsonObject);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
