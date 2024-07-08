package Temperature.command_processer;

import Temperature.TemperatureModel;

public class F2CCommand extends Command{

    private double f;
    public F2CCommand(TemperatureModel temperatureModel, double f){
        this.f = f;
        this.temperatureModelRemote = temperatureModel;
    }

    @Override
    public void execute() {
        temperatureModelRemote.f2c(f);
        
    }
}
