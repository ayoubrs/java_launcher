package aircraftRelated;

import java.io.*;
import java.util.*;
import myExceptions.MyCustomException;
import weatherRelated.*;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    HashMap<String, String> logMessage = new HashMap<String, String>();
    public FileWriter myWriter;
    
    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        
        logMessage.put("SUN", "OMG! Winter is coming! | 0 10 2");
        logMessage.put("RAIN", "It's raining. Better watch out for lightings. | 0 5 0");
        logMessage.put("FOG", "Fog, can't see. | 0 1 0");
        logMessage.put("SNOW", "It's snowing. We're gonna crash. | 0 0 -7");
    }
    
    @Override
    public void updateConditions() {
        
        try {
            String weather = weatherTower.getWeather(coordinates);

            File myObj = new File("simulation.txt");
            // if (!myObj.createNewFile())
            //     throw new MyCustomException("the named file already exists simulation.txt");
            // myWriter = new FileWriter("simulation.txt");
            myWriter = new FileWriter(myObj, true);

            if (logMessage.get(weather) != null) {
                updateCoordinates(weather, logMessage);
                myWriter.write("JetPlane#" + this.name + "(" + this.id + "): " + logMessage.get(weather).split("|")[0]
                        + ".\n");

                if (coordinates.getHeight() <= 0) {
                    weatherTower.unregister(this);
                    myWriter.write(
                            "Tower says: JetPlane#" + this.name + "(" + this.id + ")"
                                    + " unregistered from weather tower.\n");
                    myWriter.close();
                }
                myWriter.close();
            } else {
                myWriter.close();
                throw new MyCustomException("Uknown Weather!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        try {

            this.weatherTower = weatherTower;
            this.weatherTower.register(this);

            myWriter = new FileWriter("simulation.txt");
            myWriter.write("Tower says: JetPlane#" + name + "(" + id + ")" + " registered to weather tower.\n");
            myWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}