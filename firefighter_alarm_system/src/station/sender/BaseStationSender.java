package station.sender;

import unit.IUnit;
import unit.ResponseCode;

public interface BaseStationSender {
    ResponseCode send(IUnit unit);
}
