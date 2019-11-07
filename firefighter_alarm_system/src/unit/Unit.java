package unit;


import firefighter.IFirefighter;
import unit.state.UnitState;
import unit.state.UnitWorks;

import java.util.ArrayList;

public class Unit implements IUnit {

    private String unitName;
    private String testCode;
    private String alarmCode;
    private ArrayList<IFirefighter> observers = new ArrayList<>();
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
    public void addObserver(IFirefighter observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IFirefighter observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IFirefighter firefighter: observers) {
            this.notifyObserver(firefighter);
        }
    }

    @Override
    public void notifyObserver(IFirefighter observer) {
        observer.sendSms();
    }

}
