package Temperature.command_processer;

import Temperature.TemperatureModel;

public class C2FCommand  extends Command{

    private double c;
    public C2FCommand(TemperatureModel temperatureModel, double c){
        this.c = c;
        this.temperatureModelRemote = temperatureModel;
    }

    @Override
    public void execute() {
        temperatureModelRemote.c2f(c);
        
    }
}







