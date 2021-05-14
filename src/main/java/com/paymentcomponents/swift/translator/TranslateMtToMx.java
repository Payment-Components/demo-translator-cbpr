package com.paymentcomponents.swift.translator;

import gr.datamation.mx.CbprMessage;
import gr.datamation.swift.common.SwiftMessage;
import gr.datamation.swift.translator.cbpr.CbprTranslator;
import gr.datamation.swift.translator.cbpr.exceptions.InvalidMtMessageException;
import gr.datamation.swift.translator.cbpr.exceptions.InvalidMxMessageException;
import gr.datamation.swift.translator.cbpr.interfaces.MtToMxTranslator;
import gr.datamation.swift.translator.cbpr.translators.mt.Mt200ToPacs009;
import gr.datamation.swift.translator.cbpr.utils.MessageValidationUtils;

public class TranslateMtToMx {

    public static void main(String... args) {
        translateMt200ToPacs009_Auto();
        translateMt200ToPacs009_ExplicitText();
        translateMt200ToPacs009_ExplicitObject();
    }

    public static void translateMt200ToPacs009_Auto() {
        try {
            // You have the option to provide the MT message in text format and get back the CBPR+ message in text format.
            // Translator auto detects the translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            String mxMessage = CbprTranslator.translateMtToMx(validMtMessage);
            System.out.println("Translated Message is: \n" + mxMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (InvalidMtMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println("Unexpected error occurred");
            System.err.println(ex.getMessage());
        }
    }

    public static void translateMt200ToPacs009_ExplicitText() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the MT message
            // in text format and get back the CBPR+ message in text format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            MtToMxTranslator mtToMxTranslator = new Mt200ToPacs009();
            String mxMessage = mtToMxTranslator.translate(validMtMessage);
            System.out.println("Translated Message is: \n" + mxMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (InvalidMtMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println("Unexpected error occurred");
            System.err.println(ex.getMessage());
        }
    }

    public static void translateMt200ToPacs009_ExplicitObject() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the MT message
            // in Object format and get back the CBPR+ message in Object format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            SwiftMessage swiftMessage = MessageValidationUtils.parseAndValidateMtMessage(validMtMessage);
            MtToMxTranslator mtToMxTranslator = new Mt200ToPacs009();
            CbprMessage mxMessage = mtToMxTranslator.translate(swiftMessage);
            System.out.println("Translated Message is: \n" + mxMessage.convertToXML());
        } catch (InvalidMxMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (InvalidMtMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println("Unexpected error occurred");
            System.err.println(ex.getMessage());
        }
    }

    private static final String validMtMessage = "{1:F01TESTBICYXXXX1111111111}{2:O2000139210201TESTBICZXXXX11111111112102010139N}{3:{121:00000000-0000-4000-8000-000000000000}}{4:\n" +
            ":20:BBBB/120928-FICT\n" +
            ":32A:120929JPY10000000,\n" +
            ":53B:/ACCOUNTID\n" +
            ":56A:/INTERAGTACCT\n" +
            "INTERBIC\n" +
            ":57A:/CDTRAGTACCT\n" +
            "AAAAJPJT\n" +
            ":72:/CLSTIME/1212+1300\n" +
            "/INS/TESTBICD\n" +
            "-}";

}
