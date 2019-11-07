package unit;

import firefighter.IFirefighter;
import observer.Observable;
import unit.state.UnitState;

public interface IUnit extends Observable<IFirefighter> {
    ResponseCode notify(String CCIR_CODE);
    String getUnitName();
    void setState(UnitState state);
    String getTestCode();
    String getAlarmCode();
}
