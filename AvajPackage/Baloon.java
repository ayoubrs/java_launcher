package AvajPackage;

public class Baloon extends Aircraft implements Flyable
{
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates, String type)
    {
        super(name, coordinates);
    }

    @Override
    public void updateConditions()
    {
    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
    }
}