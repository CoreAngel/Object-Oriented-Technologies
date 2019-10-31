package unit;


import unit.state.UnitState;

public interface IUnit {
    ResponseCode notify(String CCIR_CODE);
    void notifyFirefighters();
    String getUnitName();
    void setState(UnitState state);
    String getTestCode();
    String getAlarmCode();
}
