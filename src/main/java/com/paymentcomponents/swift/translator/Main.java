package com.paymentcomponents.swift.translator;

public class Main {

    public static void main(String[] args) {
        TranslateMtToMx.translateMt200ToPacs009_Auto();
        TranslateMtToMx.translateMt200ToPacs009_ExplicitText();
        TranslateMtToMx.translateMt200ToPacs009_ExplicitObject();

        TranslateMxToMt.translatePacs009ToMt200_Auto();
        TranslateMxToMt.translatePacs009ToMt200_ExplicitText();
        TranslateMxToMt.translatePacs009ToMt200_ExplicitObject();
    }

}
