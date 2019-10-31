package unit;


import firefighter.Firefighter;
import observer.IObserver;
import unit.state.UnitState;
import unit.state.UnitWorks;

import java.util.ArrayList;

public class Unit implements IUnit, IObserver<Firefighter> {

    private String unitName;
    private String testCode;
    private String alarmCode;
    private ArrayList<Firefighter> observers = new ArrayList<>();
    private UnitState state = new UnitWorks();

    public Unit(String unitName, String testCode, String alarmCode) {
        this.unitName = unitName;
        this.testCode = testCode;
        this.alarmCode = alarmCode;
    }

    public void setState(UnitState state) {
        this.state = state;
    }


    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    @Override
    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    @Override
    public ResponseCode notify(String CCIR_CODE) {
        return state.notify(this, CCIR_CODE);
    }

    @Override
    public void notifyFirefighters() {
        this.notifyObservers();
    }

    @Override
    public void addObserver(Firefighter observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Firefighter observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Firefighter firefighter: observers) {
            firefighter.sendSms();
        }
    }

    @Override
    public void notifyObserver(Firefighter observer) {
        observer.sendSms();
    }
}
