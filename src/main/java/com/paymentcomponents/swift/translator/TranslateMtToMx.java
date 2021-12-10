package com.paymentcomponents.swift.translator;

import gr.datamation.mx.CbprMessage;
import gr.datamation.mx.message.pacs.FinancialInstitutionCreditTransfer08;
import gr.datamation.swift.common.SwiftMessage;
import gr.datamation.swift.translator.cbpr.CbprTranslator;
import gr.datamation.swift.translator.cbpr.interfaces.MtToMxTranslator;
import gr.datamation.swift.translator.cbpr.translators.mt.Mt202ToPacs009;
import gr.datamation.swift.translator.common.exceptions.InvalidMtMessageException;
import gr.datamation.swift.translator.common.exceptions.InvalidMxMessageException;
import gr.datamation.swift.translator.common.utils.MtMessageValidationUtils;
import iso.pacs_009_001_08.Purpose2Choice;

public class TranslateMtToMx {

    public static void main(String... args) {
        translateMt202ToPacs009_Auto();
        translateMt202ToPacs009_ExplicitText();
        translateMt202ToPacs009_ExplicitObject();
    }

    public static void translateMt202ToPacs009_Auto() {
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

    public static void translateMt202ToPacs009_ExplicitText() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the MT message
            // in text format and get back the CBPR+ message in text format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            MtToMxTranslator<?, ?> mtToMxTranslator = new Mt202ToPacs009();
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

    public static void translateMt202ToPacs009_ExplicitObject() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the MT message
            // in Object format and get back the CBPR+ message in Object format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            SwiftMessage swiftMessage = MtMessageValidationUtils.parseAndValidateMtMessage(validMtMessage);
            MtToMxTranslator<?, ?> mtToMxTranslator = new Mt202ToPacs009();
            CbprMessage<?, ?> mxMessage = mtToMxTranslator.translate(swiftMessage);
            //In case is needed, you can add extra values in the generated object in case
            Purpose2Choice purpose2Choice = new Purpose2Choice();
            purpose2Choice.setCd("TEST");
            ((FinancialInstitutionCreditTransfer08)mxMessage.getDocument()).getMessage().getCdtTrfTxInf().get(0).setPurp(purpose2Choice);
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

    private static final String validMtMessage = "{1:F01AAAABEBBAXXX0000000000}{2:I202CCCCUS33AXXXN}{3:{121:c8b66b47-2bd9-48fe-be90-93c2096f27d2}}{4:\n" +
            ":20:987\n" +
            ":21:090525/123COV\n" +
            ":13C:/SNDTIME/1249+0200\n" +
            ":32A:090527USD10500,00\n" +
            ":52A:BKAUATWW\n" +
            ":56A:TESTBICD\n" +
            ":57A:TESTBICE\n" +
            ":58A:TESTBICF\n" +
            ":72:/INS/CHASUS33\n" +
            "-}{5:{MAC:75D138E4}{CHK:DE1B0D71FA96}}";

}
