package com.paymentcomponents.converter.cbpr.demo;

import gr.datamation.converter.cbpr.CbprTranslator;
import gr.datamation.converter.cbpr.converters.mt.Mt202Mt205ToPacs009;
import gr.datamation.converter.cbpr.interfaces.MtToCbprTranslator;
import gr.datamation.converter.cbpr.utils.CbprMessageValidationUtils;
import gr.datamation.converter.common.exceptions.InvalidMtMessageException;
import gr.datamation.converter.common.exceptions.InvalidMxMessageException;
import gr.datamation.converter.common.exceptions.StopTranslationException;
import gr.datamation.converter.common.exceptions.TranslationUnhandledException;
import gr.datamation.converter.common.utils.MtMessageValidationUtils;
import gr.datamation.iso20022.cbpr.CbprMessage;
import gr.datamation.mt.common.SwiftMessage;
import gr.datamation.mx.message.pacs.FinancialInstitutionCreditTransfer08;
import xsd.pacs_009_001_08.Purpose2Choice;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;

public class TranslateMtToMx {

    public static void main(String... args) {
        translateMt202ToPacs009_Auto();
        translateMt202ToPacs009_ExplicitText();
        translateMt202ToPacs009_ExplicitObject();
    }

    public static void translateMt202ToPacs009_Auto() {
        String mxMessage = "";

        // You have the option to provide the MT message in text format and get back the CBPR+ message in text format.
        // Translator auto detects the translation mapping.
        // In order to handle MT and CBPR+ messages, advice README.md
        try {
            mxMessage = CbprTranslator.translateMtToMx(validMtMessage);
        } catch (InvalidMtMessageException e) {
            System.out.println("MT message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (StopTranslationException e) {
            System.out.println("Translation errors occurred");
            e.getTranslationErrorList().forEach(System.out::println);
            return;
        } catch (TranslationUnhandledException e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
            return;
        }

        //Validate the Translated message
        try {
            CbprMessageValidationUtils.autoParseAndValidateCbprMessage(mxMessage);
            System.out.println("Translated Message is: \n" + mxMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("CBPR+ message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        }
    }

    public static void translateMt202ToPacs009_ExplicitText() {
        String mxMessage = "";

        // If you do not want to use the auto-translation functionality, you have the option to provide the MT message
        // in text format and get back the CBPR+ message in text format. In this case you need to know the exact translation mapping.
        // In order to handle MT and CBPR+ messages, advice README.md
        try {
            MtToCbprTranslator<?, ?> mtToMxTranslator = new Mt202Mt205ToPacs009();
            mxMessage = mtToMxTranslator.translate(validMtMessage);
        } catch (InvalidMtMessageException e) {
            System.out.println("MT message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (StopTranslationException e) {
            System.out.println("Translation errors occurred");
            e.getTranslationErrorList().forEach(System.out::println);
            return;
        } catch (TranslationUnhandledException e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
            return;
        }

        //Validate the Translated message
        try {
            CbprMessageValidationUtils.autoParseAndValidateCbprMessage(mxMessage);
            System.out.println("Translated Message is: \n" + mxMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("CBPR+ message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        }
    }

    public static void translateMt202ToPacs009_ExplicitObject() {
        // If you do not want to use the auto-translation functionality, you have the option to provide the MT message
        // in Object format and get back the CBPR+ message in Object format. In this case you need to know the exact translation mapping.
        // In order to handle MT and CBPR+ messages, advice README.md
        CbprMessage<?, ?> mxMessage = null;
        try {
            SwiftMessage swiftMessage = MtMessageValidationUtils.parseAndValidateMtMessage(validMtMessage);
            MtToCbprTranslator<?, ?> mtToMxTranslator = new Mt202Mt205ToPacs009();
            mxMessage = mtToMxTranslator.translate(swiftMessage);
        } catch (InvalidMtMessageException e) {
            System.out.println("MT message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (StopTranslationException e) {
            System.out.println("Translation errors occurred");
            e.getTranslationErrorList().forEach(System.out::println);
            return;
        } catch (TranslationUnhandledException e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
            return;
        }


        //Validate the Translated message, CbprMsgType can also be retrieved from mxMessage.extractCbprMsgType()
        try {
            CbprMessageValidationUtils.parseAndValidateCbprMessage(mxMessage.getAppHdr(), mxMessage.getDocument(), CbprMessage.CbprMsgType.PACS_009_CORE);
        } catch (InvalidMxMessageException e) {
            System.out.println("CBPR+ message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
            return;
        }

        //In case is needed, you can add extra values in the generated object in case
        Purpose2Choice purpose2Choice = new Purpose2Choice();
        purpose2Choice.setCd("TEST");
        ((FinancialInstitutionCreditTransfer08) mxMessage.getDocument()).getMessage().getCdtTrfTxInf().get(0).setPurp(purpose2Choice);

        try {
            System.out.println("Translated Message is: \n" + mxMessage.convertToXML());
        } catch (JAXBException | UnsupportedEncodingException e) {
            System.out.println("Unexpected error occurred");
            e.printStackTrace();
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
