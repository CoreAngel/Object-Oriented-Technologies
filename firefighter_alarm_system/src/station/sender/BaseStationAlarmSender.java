package station.sender;

import unit.IUnit;
import unit.ResponseCode;

public class BaseStationAlarmSender implements BaseStationSender {
    @Override
    public ResponseCode send(IUnit unit) {
        System.out.println("Send: " + unit.getAlarmCode());
        String code = unit.getAlarmCode();
        return unit.notify(code);
    }
}
