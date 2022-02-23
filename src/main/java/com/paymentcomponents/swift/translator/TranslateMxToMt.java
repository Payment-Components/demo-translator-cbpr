package com.paymentcomponents.swift.translator;

import gr.datamation.mx.CbprMessage;
import gr.datamation.mx.message.head.BusinessApplicationHeader02;
import gr.datamation.mx.message.pacs.FinancialInstitutionCreditTransfer08;
import gr.datamation.swift.common.SwiftMessage;
import gr.datamation.swift.processor.SwiftMsgProcessor;
import gr.datamation.swift.translator.cbpr.CbprTranslator;
import gr.datamation.swift.translator.cbpr.interfaces.MxToMtTranslator;
import gr.datamation.swift.translator.cbpr.translators.mx.Pacs009ToMt202;
import gr.datamation.swift.translator.cbpr.utils.CbprMessageValidationUtils;
import gr.datamation.swift.translator.common.exceptions.InvalidMtMessageException;
import gr.datamation.swift.translator.common.exceptions.InvalidMxMessageException;
import gr.datamation.swift.translator.common.utils.MtMessageValidationUtils;

public class TranslateMxToMt {

    public static void main(String... args) {
        translatePacs009ToMt202_Auto();
        translatePacs009ToMt202_ExplicitText();
        translatePacs009ToMt202_ExplicitObject();
    }

    public static void translatePacs009ToMt202_Auto() {
        try {
            // You have the option to provide the CBPR+ message in text format and get back the MT message in text format.
            // Translator auto detects the translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            String mtMessage = CbprTranslator.translateMxToMt(validMXMessage);
            //Validate the Translated message
            MtMessageValidationUtils.parseAndValidateMtMessage(mtMessage);
            System.out.println("Translated Message is: \n" + mtMessage);
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

    public static void translatePacs009ToMt202_ExplicitText() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the CBPR+ message
            // in text format and get back the MT message in text format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            MxToMtTranslator<?, ?> mxToMtTranslator = new Pacs009ToMt202();
            String mtMessage = mxToMtTranslator.translate(validMXMessage);
            //Validate the Translated message
            MtMessageValidationUtils.parseAndValidateMtMessage(mtMessage);
            System.out.println("Translated Message is: \n" + mtMessage);
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

    public static void translatePacs009ToMt202_ExplicitObject() {
        try {
            // If you do not want to use the auto-translation functionality, you have the option to provide the CBPR+ message
            // in Object format and get back the MT message in Object format. In this case you need to know the exact translation mapping.
            // In order to handle MT and CBPR+ messages, advice README.md
            CbprMessage<BusinessApplicationHeader02, FinancialInstitutionCreditTransfer08> cbprMessage =
                    CbprMessageValidationUtils.parseAndValidateCbprMessage(validMXMessage, BusinessApplicationHeader02.class, FinancialInstitutionCreditTransfer08.class, CbprMessage.CbprMsgType.PACS_009_CORE);
            //or CbprMessageValidationUtils.autoParseAndValidateCbprMessage(validMXMessage);
            MxToMtTranslator<BusinessApplicationHeader02, FinancialInstitutionCreditTransfer08> mxToMtTranslator = new Pacs009ToMt202();
            SwiftMessage mtMessage = mxToMtTranslator.translate(cbprMessage);
            //Validate the Translated message
            MtMessageValidationUtils.validateMtMessage(mtMessage);
            System.out.println("Translated Message is: \n" + new SwiftMsgProcessor().BuildMsgStringFromObject(mtMessage));
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
            "<AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\">\n" +
            "    <Fr>\n" +
            "        <FIId>\n" +
            "            <FinInstnId>\n" +
            "                <BICFI>TESTBICY</BICFI>\n" +
            "            </FinInstnId>\n" +
            "        </FIId>\n" +
            "    </Fr>\n" +
            "    <To>\n" +
            "        <FIId>\n" +
            "            <FinInstnId>\n" +
            "                <BICFI>TESTBICZ</BICFI>\n" +
            "            </FinInstnId>\n" +
            "        </FIId>\n" +
            "    </To>\n" +
            "    <BizMsgIdr>BBBB/120928-FICT/JPY/430</BizMsgIdr>\n" +
            "    <MsgDefIdr>pacs.009.001.08</MsgDefIdr>\n" +
            "    <BizSvc>swift.cbprplus.01</BizSvc>\n" +
            "    <MktPrctc>\n" +
            "        <Regy>str1234</Regy>\n" +
            "        <Id>str1234</Id>\n" +
            "    </MktPrctc>\n" +
            "    <CreDt>2012-12-13T12:12:12+13:00</CreDt>\n" +
            "    <CpyDplct>CODU</CpyDplct>\n" +
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
