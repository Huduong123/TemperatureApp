package Temperature.observer;

import java.util.LinkedList;
import java.util.List;

import Temperature.TemperatureView;

public class Publisher {
    private List<Subcriber> subcribers = new LinkedList<>();

    public void subcriber(TemperatureView temperatureView) {
        subcribers.add(temperatureView);
    }

    public void unsubcriber(Subcriber s){
        subcribers.remove(s);
    }

    public void notifySubcribers() {
        for(Subcriber subcriber : subcribers){
            subcriber.update();
        }
    }
}
