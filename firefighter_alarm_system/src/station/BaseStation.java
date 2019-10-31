package station;

import observer.IObserver;
import station.sender.BaseStationSender;
import unit.IUnit;
import unit.ResponseCode;
import unit.state.UnitError;
import unit.state.UnitWorks;

import java.util.ArrayList;

public class BaseStation implements IObserver<IUnit> {

    private BaseStationSender sender;
    private ArrayList<IUnit> observers = new ArrayList<>();

    public BaseStation(BaseStationSender sender) {
        this.sender = sender;
    }

    public void setSender(BaseStationSender sender) {
        this.sender = sender;
    }

    @Override
    public void addObserver(IUnit observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IUnit observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IUnit observer: observers) {
            this.notifyObserver(observer);
        }
    }

    @Override
    public void notifyObserver(IUnit observer) {
        System.out.println("Unit: " + observer.getUnitName());
        ResponseCode respond = this.sender.send(observer);
        if (respond == ResponseCode.ERROR) {
            System.out.println("Unit: " + observer.getUnitName() + " set error state");
            observer.setState(new UnitError());
        } else {
            observer.setState(new UnitWorks());
        }
    }
}
