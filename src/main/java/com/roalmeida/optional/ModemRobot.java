package com.roalmeida.optional;

import java.util.Optional;

public class ModemRobot {

    public boolean priceIsInRange1(Modem modem) {
        boolean isInRange = false;
        if (modem != null && modem.getPrice() != null
            && (modem.getPrice() >= 10 && modem.getPrice() <= 15)) {
            isInRange = true;
        }
        return isInRange;
    }

    public boolean priceIsInRange2(Modem modem) {
        return Optional.ofNullable(modem)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }
}
