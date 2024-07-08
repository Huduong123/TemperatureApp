package Temperature;

import Temperature.command_processer.CommandProcessor;

public class TemperatureApp {
public static void main(String[] args) {
    TemperatureModel temperatureModelRemote = new TemperatureModel();
    CommandProcessor commandProcessorRemote = CommandProcessor.makeCommandProcessor();
    TemperatureView temperatureViewRemote = new TemperatureView(temperatureModelRemote, commandProcessorRemote);
    

    }
}
