package Temperature.command_processer;
import Temperature.TemperatureModel;;
public abstract class Command {
    protected TemperatureModel temperatureModelRemote;
    public abstract void execute();
}
