package com.paymentcomponents.swift.translator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TranslateMtToMxTest {

    @Test
    public void translateMtToMxTest() {
        try {
            TranslateMtToMx.translateMt202ToPacs009_Auto();
            TranslateMtToMx.translateMt202ToPacs009_ExplicitText();
            TranslateMtToMx.translateMt202ToPacs009_ExplicitObject();
        } catch (Exception ex) {
            Assertions.fail("Failed to translate message", ex);
        }
    }

}
