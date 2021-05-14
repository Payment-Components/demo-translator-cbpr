package com.paymentcomponents.swift.translator;

import gr.datamation.mx.CbprMessage;
import gr.datamation.swift.common.SwiftMessage;
import gr.datamation.swift.processor.SwiftMsgProcessor;
import gr.datamation.swift.translator.cbpr.CbprTranslator;
import gr.datamation.swift.translator.cbpr.exceptions.InvalidMtMessageException;
import gr.datamation.swift.translator.cbpr.exceptions.InvalidMxMessageException;
import gr.datamation.swift.translator.cbpr.interfaces.MxToMtTranslator;
import gr.datamation.swift.translator.cbpr.translators.mx.Pacs009ToMt200;
import gr.datamation.swift.translator.cbpr.utils.MessageValidationUtils;

public class TranslateMxToMt {

    public static void main(String... args) {
        translatePacs009ToMt200_Auto();
        translatePacs009ToMt200_ExplicitText();
        translatePacs009ToMt200_ExplicitObject();
    }

    public static void translatePacs009ToMt200_Auto() {
        try {
            // You have the option to provide the CBPR+ message in text format and get back the MT message in text format.
            // Translator auto detects the translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            String mtMessage = CbprTranslator.translateMxToMt(validMXMessage);
            System.out.println("Translated Message is: " + mtMessage);
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

    public static void translatePacs009ToMt200_ExplicitText() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the CBPR+ message
            // in text format and get back the MT message in text format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            MxToMtTranslator mxToMtTranslator = new Pacs009ToMt200();
            String mtMessage = mxToMtTranslator.translate(validMXMessage);
            System.out.println("Translated Message is: " + mtMessage);
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

    public static void translatePacs009ToMt200_ExplicitObject() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the CBPR+ message
            // in Object format and get back the MT message in Object format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            CbprMessage cbprMessage = MessageValidationUtils.autoParseAndValidateCbprMessage(validMXMessage);
            MxToMtTranslator mxToMtTranslator = new Pacs009ToMt200();
            SwiftMessage mtMessage = mxToMtTranslator.translate(cbprMessage);
            System.out.println("Translated Message is: " + new SwiftMsgProcessor().BuildMsgStringFromObject(mtMessage));
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

    private static final String validMXMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<RequestPayload>\n" +
            "    <AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\">\n" +
            "        <Fr>\n" +
            "            <FIId>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>TESTBICY</BICFI>\n" +
            "                </FinInstnId>\n" +
            "            </FIId>\n" +
            "        </Fr>\n" +
            "        <To>\n" +
            "            <FIId>\n" +
            "                <FinInstnId>\n" +
            "                    <BICFI>TESTBICZ</BICFI>\n" +
            "                </FinInstnId>\n" +
            "            </FIId>\n" +
            "        </To>\n" +
            "        <BizMsgIdr>BBBB/120928-FICT/JPY/430</BizMsgIdr>\n" +
            "        <MsgDefIdr>pacs.009.001.08</MsgDefIdr>\n" +
            "        <BizSvc>swift.cbprplus.01</BizSvc>\n" +
            "        <MktPrctc>\n" +
            "            <Regy>str1234</Regy>\n" +
            "            <Id>str1234</Id>\n" +
            "        </MktPrctc>\n" +
            "        <CreDt>2012-12-13T12:12:12+13:00</CreDt>\n" +
            "        <CpyDplct>CODU</CpyDplct>\n" +
            "        <PssblDplct>true</PssblDplct>\n" +
            "        <Prty>NORM</Prty>\n" +
            "    </AppHdr>\n" +
            "\n" +
            "    <Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.009.001.08\">\n" +
            "        <FICdtTrf>\n" +
            "            <GrpHdr>\n" +
            "                <MsgId>BBBB/120928-FICT/JPY/430</MsgId>\n" +
            "                <CreDtTm>2012-09-28T16:00:00+13:00</CreDtTm>\n" +
            "                <NbOfTxs>1</NbOfTxs>\n" +
            "                <SttlmInf>\n" +
            "                    <SttlmMtd>INDA</SttlmMtd>\n" +
            "                    <SttlmAcct>\n" +
            "                        <Id>\n" +
            "                            <Othr>\n" +
            "                                <Id>ACCOUNTID</Id>\n" +
            "                            </Othr>\n" +
            "                        </Id>\n" +
            "                    </SttlmAcct>\n" +
            "                </SttlmInf>\n" +
            "            </GrpHdr>\n" +
            "            <CdtTrfTxInf>\n" +
            "                <PmtId>\n" +
            "                    <InstrId>BBBB/120928-FICT</InstrId>\n" +
            "                    <EndToEndId>ABC/4562/2012-09-08</EndToEndId>\n" +
            "                    <TxId>BBBB/120928-CCT/123/1</TxId>\n" +
            "                    <UETR>00000000-0000-4000-8000-000000000000</UETR>\n" +
            "                </PmtId>\n" +
            "                <IntrBkSttlmAmt Ccy=\"JPY\">10000000</IntrBkSttlmAmt>\n" +
            "                <IntrBkSttlmDt>2012-09-29</IntrBkSttlmDt>\n" +
            "                <SttlmTmReq>\n" +
            "                    <CLSTm>12:12:12+13:00</CLSTm>\n" +
            "                </SttlmTmReq>\n" +
            "                <PrvsInstgAgt1>\n" +
            "                    <FinInstnId>\n" +
            "                        <BICFI>TESTBICD</BICFI>\n" +
            "                    </FinInstnId>\n" +
            "                </PrvsInstgAgt1>\n" +
            "                <InstgAgt>\n" +
            "                    <FinInstnId>\n" +
            "                        <BICFI>BBBBUS33</BICFI>\n" +
            "                    </FinInstnId>\n" +
            "                </InstgAgt>\n" +
            "                <InstdAgt>\n" +
            "                    <FinInstnId>\n" +
            "                        <BICFI>CCCCJPJT</BICFI>\n" +
            "                    </FinInstnId>\n" +
            "                </InstdAgt>\n" +
            "                <IntrmyAgt1>\n" +
            "                    <FinInstnId>\n" +
            "                        <BICFI>INTERBIC</BICFI>\n" +
            "                    </FinInstnId>\n" +
            "                </IntrmyAgt1>\n" +
            "                <IntrmyAgt1Acct>\n" +
            "                    <Id>\n" +
            "                        <Othr>\n" +
            "                            <Id>INTERAGTACCT</Id>\n" +
            "                        </Othr>\n" +
            "                    </Id>\n" +
            "                </IntrmyAgt1Acct>\n" +
            "                <Dbtr>\n" +
            "                    <FinInstnId>\n" +
            "                        <BICFI>BBBBUS33</BICFI>\n" +
            "                    </FinInstnId>\n" +
            "                </Dbtr>\n" +
            "                <CdtrAgt>\n" +
            "                    <FinInstnId>\n" +
            "                        <BICFI>AAAAJPJT</BICFI>\n" +
            "                    </FinInstnId>\n" +
            "                </CdtrAgt>\n" +
            "                <CdtrAgtAcct>\n" +
            "                    <Id>\n" +
            "                        <Othr>\n" +
            "                            <Id>CDTRAGTACCT</Id>\n" +
            "                        </Othr>\n" +
            "                    </Id>\n" +
            "                </CdtrAgtAcct>\n" +
            "                <Cdtr>\n" +
            "                    <FinInstnId>\n" +
            "                        <BICFI>BBBBUS33</BICFI>\n" +
            "                    </FinInstnId>\n" +
            "                </Cdtr>\n" +
            "            </CdtTrfTxInf>\n" +
            "        </FICdtTrf>\n" +
            "    </Document>\n" +
            "</RequestPayload>\n";

}
