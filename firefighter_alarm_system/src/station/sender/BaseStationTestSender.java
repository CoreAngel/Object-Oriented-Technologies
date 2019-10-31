package station.sender;

import unit.IUnit;
import unit.ResponseCode;

public class BaseStationTestSender implements BaseStationSender {

    @Override
    public ResponseCode send(IUnit unit) {
        System.out.println("Send: " + unit.getTestCode());
        String code = unit.getTestCode();
        return unit.notify(code);
    }
}
