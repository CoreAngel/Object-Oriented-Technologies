package main;

import firefighter.Firefighter;
import station.BaseStation;
import station.sender.BaseStationAlarmSender;
import station.sender.BaseStationTestSender;
import unit.Unit;
import unit.state.UnitError;

public class Main {

    // http://retsuz.pl/studia/5.pdf

    public static void main(String[] args) {
        BaseStationAlarmSender alarmSender = new BaseStationAlarmSender();
        BaseStationTestSender testSender = new BaseStationTestSender();

        BaseStation baseStation = new BaseStation(testSender);

        Unit unit1 = new Unit("Brzesko", "Brzesko Test", "Brzesko Alarm");
        Firefighter firefighter1 = new Firefighter("Adam", "Strazak", "111-111-111");
        Firefighter firefighter2 = new Firefighter("Pawel", "Strazak", "222-222-222");
        unit1.addObserver(firefighter1);
        unit1.addObserver(firefighter2);

        Unit unit2 = new Unit("Gorlice", "Gorlice Test", "Gorlice Alarm");
        Firefighter firefighter3 = new Firefighter("Piotr", "Strazak", "333-333-333");
        Firefighter firefighter4 = new Firefighter("Lukasz", "Strazak", "444-444-444");
        unit2.addObserver(firefighter3);
        unit2.addObserver(firefighter4);

        baseStation.addObserver(unit1);
        baseStation.addObserver(unit2);

        baseStation.notifyObservers();

        baseStation.setSender(alarmSender);
        unit2.setState(new UnitError());
        baseStation.notifyObservers();
    }
}
