package com.paymentcomponents.converter.cbpr.demo;

public class Main {

    public static void main(String[] args) {
        TranslateMtToMx.translateMt202ToPacs009_Auto();
        TranslateMtToMx.translateMt202ToPacs009_ExplicitText();
        TranslateMtToMx.translateMt202ToPacs009_ExplicitObject();

        TranslateMxToMt.translatePacs009ToMt202_Auto();
        TranslateMxToMt.translatePacs009ToMt202_ExplicitText();
        TranslateMxToMt.translatePacs009ToMt202_ExplicitObject();
    }

}
