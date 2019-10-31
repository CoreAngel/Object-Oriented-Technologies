package unit.state;

import unit.IUnit;
import unit.ResponseCode;

public class UnitWorks implements UnitState {
    @Override
    public ResponseCode notify(IUnit unit, String CCIR_CODE) {
        if (unit.getAlarmCode().equals(CCIR_CODE)) {
            unit.notifyFirefighters();
            return ResponseCode.ALARM_OK;
        } else if (unit.getTestCode().equals(CCIR_CODE)) {
            return ResponseCode.TEST_OK;
        } else {
            return ResponseCode.ERROR;
        }
    }
}
