package com.paymentcomponents.swift.translator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConvertMTtoMXTest {

    @Test
    public void convertMTtoMXTest() {
        try {
            ConvertMTtoMX.convertMT200toMXpacs009();
        } catch (Exception ex) {
            Assertions.fail("Failed to translate message", ex);
        }
    }

}
