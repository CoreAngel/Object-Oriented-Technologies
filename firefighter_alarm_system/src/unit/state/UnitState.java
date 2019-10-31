package unit.state;

import unit.IUnit;
import unit.ResponseCode;

public interface UnitState {
    ResponseCode notify(IUnit unit, String CCIR_CODE);
}
