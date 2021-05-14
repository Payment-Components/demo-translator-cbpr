package com.paymentcomponents.swift.translator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TranslateMtToMxTest {

    @Test
    public void translateMtToMxTest() {
        try {
            TranslateMtToMx.translateMt200ToPacs009_Auto();
            TranslateMtToMx.translateMt200ToPacs009_ExplicitText();
            TranslateMtToMx.translateMt200ToPacs009_ExplicitObject();
        } catch (Exception ex) {
            Assertions.fail("Failed to translate message", ex);
        }
    }

}
