package com.paymentcomponents.swift.translator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConvertMXtoMTTest {

    @Test
    public void convertMXtoMTTest() {
        try {
            ConvertMXtoMT.convertMXpacs009toMT200();
        } catch (Exception ex) {
            Assertions.fail("Failed to translate message", ex);
        }
    }

}
