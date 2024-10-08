package com.paymentcomponents.converter.cbpr.demo;

import gr.datamation.converter.cbpr.CbprTranslator;
import gr.datamation.converter.cbpr.converters.mx.Pacs009ToMt202Mt205;
import gr.datamation.converter.cbpr.interfaces.CbprToMtTranslator;
import gr.datamation.converter.cbpr.utils.CbprMessageValidationUtils;
import gr.datamation.converter.common.exceptions.*;
import gr.datamation.converter.common.utils.MtMessageValidationUtils;
import gr.datamation.iso20022.cbpr.CbprMessage;
import gr.datamation.mt.common.SwiftMessage;
import gr.datamation.mt.processor.SwiftMsgProcessor;
import gr.datamation.mx.message.head.BusinessApplicationHeader02;
import gr.datamation.mx.message.pacs.FinancialInstitutionCreditTransfer08;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;

public class TranslateMxToMt {

    public static void main(String... args) {
        translatePacs009ToMt202_Auto();
        translatePacs009ToMt202_ExplicitText();
        translatePacs009ToMt202_ExplicitObject();
        translatePacs009ToMt202_WithErrorList();
    }

    public static void translatePacs009ToMt202_Auto() {
        // You have the option to provide the CBPR+ message in text format and get back the MT message in text format.
        // Translator auto detects the translation mapping.
        // In order to handle MT and CBPR+ messages, advice README.md
        String mtMessage = null;
        try {
            mtMessage = CbprTranslator.translateMxToMt(validMXMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("CBPR+ message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
            return;
        } catch (StopTranslationException e) {
            System.out.println("Translation errors occurred");
            e.getTranslationErrorList().forEach(System.out::println);
            return;
        } catch (TranslationUnhandledException e) {
            System.out.println("Unexpected error occurred");
            return;
        }

        //Validate the Translated message
        try {
            MtMessageValidationUtils.parseAndValidateMtMessage(mtMessage);
            System.out.println("Translated Message is: \n" + mtMessage);
        } catch (InvalidMtMessageException e) {
            System.out.println("MT message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        }

    }

    public static void translatePacs009ToMt202_ExplicitText() {
        // If you do not want to use the auto-translation functionality, you have the option to provide the CBPR+ message
        // in text format and get back the MT message in text format. In this case you need to know the exact translation mapping.
        // In order to handle MT and CBPR+ messages, advice README.md
        String mtMessage = null;
        try {
            CbprToMtTranslator<?, ?> mxToMtTranslator = new Pacs009ToMt202Mt205();
            mtMessage = mxToMtTranslator.translate(validMXMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("CBPR+ message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
            return;
        } catch (StopTranslationException e) {
            System.out.println("Translation errors occurred");
            e.getTranslationErrorList().forEach(System.out::println);
            return;
        } catch (TranslationUnhandledException e) {
            System.out.println("Unexpected error occurred");
            return;
        }

        //Validate the Translated message
        try {
            MtMessageValidationUtils.parseAndValidateMtMessage(mtMessage);
            System.out.println("Translated Message is: \n" + mtMessage);
        } catch (InvalidMtMessageException e) {
            System.out.println("MT message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        }

    }

    public static void translatePacs009ToMt202_ExplicitObject() {
        // If you do not want to use the auto-translation functionality, you have the option to provide the CBPR+ message
        // in Object format and get back the MT message in Object format. In this case you need to know the exact translation mapping.
        // In order to handle MT and CBPR+ messages, advice README.md
        SwiftMessage mtMessage = null;
        try {
            CbprMessage<BusinessApplicationHeader02, FinancialInstitutionCreditTransfer08> cbprMessage = CbprMessageValidationUtils.parseAndValidateCbprMessage(validMXMessage, BusinessApplicationHeader02.class, FinancialInstitutionCreditTransfer08.class, CbprMessage.CbprMsgType.PACS_009_CORE);
            //or CbprMessageValidationUtils.autoParseAndValidateCbprMessage(validMXMessage);
            CbprToMtTranslator<BusinessApplicationHeader02, FinancialInstitutionCreditTransfer08> mxToMtTranslator = new Pacs009ToMt202Mt205();
            mtMessage = mxToMtTranslator.translate(cbprMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("CBPR+ message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
            return;
        } catch (StopTranslationException e) {
            System.out.println("Translation errors occurred");
            e.getTranslationErrorList().forEach(System.out::println);
            return;
        } catch (TranslationUnhandledException e) {
            System.out.println("Unexpected error occurred");
            return;
        }

        //Validate the Translated message
        try {
            MtMessageValidationUtils.validateMtMessage(mtMessage);
            System.out.println("Translated Message is: \n" + new SwiftMsgProcessor().BuildMsgStringFromObject(mtMessage));
        } catch (InvalidMtMessageException e) {
            System.out.println("MT message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("MT message is invalid");
        }
    }

    public static void translatePacs009ToMt202_WithErrorList() {
        // You have the option to provide the CBPR+ message in text format and get back a `TranslationResult` which includes the MT message in object and a list with errors.
        // Translator auto detects the translation mapping.
        // In order to handle MT and CBPR+ messages, advice README.md
        TranslationResult<SwiftMessage> translationResult;
        try {
            CbprMessage<BusinessApplicationHeader02, FinancialInstitutionCreditTransfer08> cbprMessage = CbprMessageValidationUtils.autoParseCbprMessage(validMXMessage);
            translationResult = CbprTranslator.translateMxToMtFull(cbprMessage.convertToXML(), "O");

            assert translationResult.getErrorList().get(0).getErrorCode().getErrorText().equals("T0000M");
            assert translationResult.getErrorList().get(0).getErrorCode().getErrorCategory().name().equals("TRUNC_R");
            assert translationResult.getErrorList().get(0).getErrorCode().getErrorDescription().equals("Input content is not mapped to target message.");
        } catch (InvalidMxMessageException e) {
            System.out.println("CBPR+ message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
            return;
        } catch (StopTranslationException e) {
            System.out.println("Translation errors occurred");
            e.getTranslationErrorList().forEach(System.out::println);
            return;
        } catch (TranslationUnhandledException e) {
            System.out.println("Unexpected error occurred");
            return;
        } catch (JAXBException | UnsupportedEncodingException e) {
            System.out.println("Error during conversion to XML");
            return;
        }

        //Validate the Translated message
        try {
            MtMessageValidationUtils.validateMtMessage(translationResult.getMessage());
            System.out.println("Translated Message is: \n" + new SwiftMsgProcessor().BuildMsgStringFromObject(translationResult.getMessage()));
        } catch (InvalidMtMessageException e) {
            System.out.println("MT message is invalid");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("MT message is invalid");
        }

    }


    private static final String validMXMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\">\n" +
            "    <Fr>\n" +
            "        <FIId>\n" +
            "            <FinInstnId>\n" +
            "                <BICFI>BBBBUS33</BICFI>\n" +
            "            </FinInstnId>\n" +
            "        </FIId>\n" +
            "    </Fr>\n" +
            "    <To>\n" +
            "        <FIId>\n" +
            "            <FinInstnId>\n" +
            "                <BICFI>CCCCJPJT</BICFI>\n" +
            "            </FinInstnId>\n" +
            "        </FIId>\n" +
            "    </To>\n" +
            "    <BizMsgIdr>BBBB/120928-FICT/JPY/430</BizMsgIdr>\n" +
            "    <MsgDefIdr>pacs.009.001.08</MsgDefIdr>\n" +
            "    <BizSvc>swift.cbprplus.02</BizSvc>\n" +
            "    <MktPrctc>\n" +
            "        <Regy>str1234</Regy>\n" +
            "        <Id>str1234</Id>\n" +
            "    </MktPrctc>\n" +
            "    <CreDt>2012-12-13T12:12:12+13:00</CreDt>\n" +
            "    <PssblDplct>true</PssblDplct>\n" +
            "    <Prty>NORM</Prty>\n" +
            "</AppHdr>\n" +
            "\n" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.009.001.08\">\n" +
            "    <FICdtTrf>\n" +
            "        <GrpHdr>\n" +
            "            <MsgId>BBBB/120928-FICT/JPY/430</MsgId>\n" +
            "            <CreDtTm>2012-09-28T16:00:00+13:00</CreDtTm>\n" +
            "            <NbOfTxs>1</NbOfTxs>\n" +
            "            <SttlmInf>\n" +
            "                <SttlmMtd>INDA</SttlmMtd>\n" +
            "            </SttlmInf>\n" +
            "        </GrpHdr>\n" +
            "        <CdtTrfTxInf>\n" +
            "            <PmtId>\n" +
            "                <InstrId>BBBB/120928-FICT</InstrId>\n" +
            "                <EndToEndId>ABC/4562/2012-09-08</EndToEndId>\n" +
            "                <TxId>BBBB/120928-CCT/123/1</TxId>\n" +
            "                <UETR>00000000-0000-4000-8000-000000000000</UETR>\n" +
            "            </PmtId>\n" +
            "            <IntrBkSttlmAmt Ccy=\"JPY\">10000000</IntrBkSttlmAmt>\n" +
            "            <IntrBkSttlmDt>2012-09-29</IntrBkSttlmDt>\n" +
            "            <InstgAgt>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>BBBBUS33</BICFI>\n" +
            "                </FinInstnId>\n" +
            "            </InstgAgt>\n" +
            "            <InstdAgt>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>CCCCJPJT</BICFI>\n" +
            "                </FinInstnId>\n" +
            "            </InstdAgt>\n" +
            "            <IntrmyAgt1>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>INTERBIC</BICFI>\n" +
            "                    <ClrSysMmbId>\n" +
            "                        <ClrSysId>\n" +
            "                            <Cd>ATBLZ</Cd>\n" +
            "                        </ClrSysId>\n" +
            "                        <MmbId>MEMBERID</MmbId>\n" +
            "                    </ClrSysMmbId>\n" +
            "                </FinInstnId>\n" +
            "            </IntrmyAgt1>\n" +
            "            <IntrmyAgt1Acct>\n" +
            "                <Id>\n" +
            "                    <Othr>\n" +
            "                        <Id>INTERAGTACCT</Id>\n" +
            "                    </Othr>\n" +
            "                </Id>\n" +
            "            </IntrmyAgt1Acct>\n" +
            "            <Dbtr>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>BBBBUS33</BICFI>\n" +
            "                    <ClrSysMmbId>\n" +
            "                        <ClrSysId>\n" +
            "                            <Cd>ATBLZ</Cd>\n" +
            "                        </ClrSysId>\n" +
            "                        <MmbId>MEMBERID</MmbId>\n" +
            "                    </ClrSysMmbId>\n" +
            "                </FinInstnId>\n" +
            "            </Dbtr>\n" +
            "            <DbtrAcct>\n" +
            "                <Id>\n" +
            "                    <Othr>\n" +
            "                        <Id>DBTRACCT</Id>\n" +
            "                    </Othr>\n" +
            "                </Id>\n" +
            "            </DbtrAcct>\n" +
            "            <CdtrAgt>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>AAAAJPJT</BICFI>\n" +
            "                    <ClrSysMmbId>\n" +
            "                        <ClrSysId>\n" +
            "                            <Cd>ATBLZ</Cd>\n" +
            "                        </ClrSysId>\n" +
            "                        <MmbId>MEMBERID</MmbId>\n" +
            "                    </ClrSysMmbId>\n" +
            "                </FinInstnId>\n" +
            "            </CdtrAgt>\n" +
            "            <CdtrAgtAcct>\n" +
            "                <Id>\n" +
            "                    <Othr>\n" +
            "                        <Id>CDTRAGTACCT</Id>\n" +
            "                    </Othr>\n" +
            "                </Id>\n" +
            "            </CdtrAgtAcct>\n" +
            "            <Cdtr>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>AAAAGB2L</BICFI>\n" +
            "                    <ClrSysMmbId>\n" +
            "                        <ClrSysId>\n" +
            "                            <Cd>ATBLZ</Cd>\n" +
            "                        </ClrSysId>\n" +
            "                        <MmbId>MEMBERID</MmbId>\n" +
            "                    </ClrSysMmbId>\n" +
            "                </FinInstnId>\n" +
            "            </Cdtr>\n" +
            "            <CdtrAcct>\n" +
            "                <Id>\n" +
            "                    <Othr>\n" +
            "                        <Id>CDTRACCT</Id>\n" +
            "                    </Othr>\n" +
            "                </Id>\n" +
            "            </CdtrAcct>\n" +
            "            <InstrForNxtAgt>\n" +
            "                <InstrInf>2112323223</InstrInf>\n" +
            "            </InstrForNxtAgt>\n" +
            "        </CdtTrfTxInf>\n" +
            "    </FICdtTrf>\n" +
            "</Document>\n";

}
