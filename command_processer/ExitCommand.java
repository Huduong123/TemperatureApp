package Temperature.command_processer;

import Temperature.TemperatureModel;

public class ExitCommand extends Command {
 public ExitCommand(TemperatureModel temperatureModel) {
        this.temperatureModelRemote = temperatureModel;
    }

    @Override
    public void execute() {
        this.temperatureModelRemote.exit();
    }
}
