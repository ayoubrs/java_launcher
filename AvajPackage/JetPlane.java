package AvajPackage;

public class JetPlane extends Aircraft implements Flyable
{
    private WeatherTower weatherTower;
   public JetPlane(String name, Coordinates coordinates){
    super(name, coordinates);
    // super(name, coordinates, type);
   }

   @Override
    public void updateConditions(){

    }

    @Override
    public void registerTower(WeatherTower weatherTower){
        
    }
}