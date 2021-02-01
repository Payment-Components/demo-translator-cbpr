package com.paymentcomponents.swift.translator;

import gr.datamation.swift.translator.Converter;
import gr.datamation.swift.translator.exceptions.InvalidMtMessageException;
import gr.datamation.swift.translator.exceptions.InvalidMxMessageException;

public class ConvertMTtoMX {

    public static void main(String... args) {
        convertMT200toMXpacs009();
    }

    public static void convertMT200toMXpacs009() {
        try {
            // In order to translate an MT Message to MX Message and vice versa, you only need to call the library
            // with the message that you want to translate and it automatically translates the message to the relevant format.
            // The only restriction is that the message should be included in the supported messages (advice README).
            //
            //The input and the output are simple texts.
            String mxMessage = Converter.convertMtToMx(valixMtMessage);
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

    private static final String valixMtMessage = "{1:F01TESTBICYXXXX1111111111}{2:O2000139210201TESTBICZXXXX11111111112102010139N}{3:{121:00000000-0000-4000-8000-000000000000}}{4:\n" +
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
