# SWIFT Message Translator Demo

The project is here to demonstrate how our [SDK](https://www.paymentcomponents.com/messaging-libraries/) for 
SWIFT Message Translator works. For our demonstration we are going to use the demo SDK which can convert SWIFT MT to SWIFT MX (ISO20022) messages. 

It's a simple maven project, you can download it and run it, with Java 1.8 or above.

#### Overview
This documentation describes how to incorporate the SWIFT Translator Library into your project. The SDK is written in Java.
By following this guide you will be able to translate SWIFT MT(ISO 15022) messages to SWIFT MX(ISO 20022) messages and vice versa.

#### Import library
The first step is to import the translator library into your project.  
The jar that you will download should look like this: swift-translator-xx.x.x.jar

##### Import via IDE
Import the library into your project by the regular IDE means. This process will vary depending upon your specific IDE and you should consult your documentation on how to import an external library. For example in Intellij, all that needs to be done is to import the jar file into a project.

Alternatively, you can import it as a dependency

##### Maven
Define repository in the repositories section
```xml
<repository>
    <id>paymentcomponents</id>
    <url>https://nexus.paymentcomponents.com/repository/public</url>
</repository>
```
Import the SDK
```xml
<dependency>
  <groupId>gr.datamation</groupId>
  <artifactId>swift-translator</artifactId>
  <version>1.5.3</version>
  <classifier>demo</classifier>
</dependency>
```

##### Gradle 
Define repository in the repositories section
```groovy
repositories {
    maven {
        url "https://nexus.paymentcomponents.com/repository/public"
    }
}
```
Import the SDK
```groovy
implementation 'gr.datamation:swift-translator:1.5.3:demo@jar'
```

#### Supported Translations MT > MX From Message To Message

| MT message | MX message |
| --- | --- |
| MT103 | pacs.008.001.08 |
| MT103STP | pacs.008.001.08 |
| MT101 | pain.001.001.09 |
| MT202COV | pacs.009.001.08 |
| MT202 | pacs.009.001.08 |
| MT200 | pacs.009.001.08 |
| MT940 | camt.053.001.08 |
| MT950 | camt.053.001.08 |
| MT910 | camt.054.001.08 |
| MT900 | camt.054.001.08 |
| MT103 (Return) | pacs.004.001.09 |
| MT202 (Return) | pacs.004.001.09 |
| MT942 | camt.052.001.08 |
| MT941 | camt.052.001.08 | 

#### Supported Translations MX > MT From Message To Message

| MX message | MT message |
| --- | --- |
| pacs.008.001.08 | MT103 |
| pacs.008.001.08.stp.eu | MT103STP |
| pacs.009.001.08.cov | MT202COV |
| pacs.009.001.08.core | MT202 |
| pacs.009.001.08 | MT200 |
| pain.001.001.09 | MT101 |
| camt.053.001.08 | MT940 |
| camt.054.001.08 | MT910 |
| camt.054.001.08 | MT900 |
| pacs.004.001.09 | MT103(Return) |
| pacs.004.001.09 | MT202(Return) |
| camt.052.001.08 | MT942 |
| camt.052.001.08 | MT941 |

#### Instructions
In order to translate an MT Message to MX Message and vice versa, you only need to call the library with the message that you want to translate and it automatically translates the message to the relevant format. The only restriction is that the message should be included in the table above.

The input and the output are simple texts. 

In order to translate the message, you will need only two methods from the library. Both are static methods of Converter class. The methods responsible for the translate are:

```java
public static String convertMtToMx(String mtMessage) throws InvalidMxMessageException, InvalidMtMessageException
```

```java
public static String convertMxToMt(String mxMessage) throws InvalidMxMessageException, InvalidMxMessageException
```

In case of no error, you will get the formatted translated message.

Line ending
In some cased, depending on your system, it is advised to replace the line ending of the message.

```java
message = message.replaceAll("\r\n", "\n").replaceAll("\n", "\r\n");
```

#### Multiple messages
In some cases, an ISO2002 message could be translated to more than one MT message. In this case, the delimiter $ is added between the messages.

For example

```
:56A:INTERBIC
-}${1:F01TESTBICAXXXX1111111111}
```

#### Error Handling
When we translate a message, we validate both the input message and the output message. For example, in a MT→MX translation, the first step is to validate the MT message and we proceed to translation only if the message is valid.

This is the reason why both methods throw InvalidMtMessageException and InvalidMxMessageException

Both Exceptions contain a validationErrorList attribute which contains a description of the error occurred.

#### Code Samples
```java
import gr.datamation.swift.translator.Converter;
import gr.datamation.swift.translator.exceptions.InvalidMtMessageException;
import gr.datamation.swift.translator.exceptions.InvalidMxMessageException;

public class MtToMx {

    public static void main(String[] args) {
        String message = "{1:F01AAAABEBBAXXX0000000000}{2:I200CCCCUS33AXXXN}{3:{108:MT200 005 OF 013}{433:/AOK/EXCLUDED            }}{4:\n" +
                ":20:00322\n" +
                ":32A:090527USD10500,00\n" +
                ":53B:/1/ACCOUNTID\n" +
                ":56A:/1/ACCOUNTID6\n" +
                "TESTBICD\n" +
                ":57A:/1/ACCOUNTID7\n" +
                "TESTBICE\n" +
                ":72:/ACC/SOME GENERIC INFO HERE\n" +
                "//AND SOME OTHER HERE\n" +
                "/INT/INTERMEDIARY\n" +
                "/INS/CHASUS33\n" +
                "-}{5:{MAC:00000000}{CHK:7C36CF9824B0}{TNG:}}{S:{SAC:}{COP:P}}\n";

        message = message.replaceAll("\r\n", "\n")
                .replaceAll("\n", "\r\n");
        try {
            String translatedMessage = Converter.convertMtToMx(message);
            System.out.println("Translated Message is: " + translatedMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (InvalidMtMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        }
    }

}
```

```java
import gr.datamation.swift.translator.Converter;
import gr.datamation.swift.translator.exceptions.InvalidMtMessageException;
import gr.datamation.swift.translator.exceptions.InvalidMxMessageException;

public class MxToMt {

    public static void main(String[] args) {
                 String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<RequestPayload>\n" +
                "    <AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\">\n" +
                "        <Fr>\n" +
                "            <OrgId>\n" +
                "                <Id>\n" +
                "                    <OrgId>\n" +
                "                        <AnyBIC>TESTBICA</AnyBIC>\n" +
                "                    </OrgId>\n" +
                "                </Id>\n" +
                "            </OrgId>\n" +
                "        </Fr>\n" +
                "        <To>\n" +
                "            <FIId>\n" +
                "                <FinInstnId>\n" +
                "                    <BICFI>TESTBICA</BICFI>\n" +
                "                </FinInstnId>\n" +
                "            </FIId>\n" +
                "        </To>\n" +
                "        <BizMsgIdr>str1234</BizMsgIdr>\n" +
                "        <MsgDefIdr>str1234</MsgDefIdr>\n" +
                "        <CreDt>2012-12-13T12:12:12+13:00</CreDt>\n" +
                "    </AppHdr>\n" +
                "    <Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08\">\n" +
                "        <FIToFICstmrCdtTrf>\n" +
                "            <GrpHdr>\n" +
                "                <MsgId>5387354</MsgId>\n" +
                "                <CreDtTm>2020-06-22T13:38:50.889+03:00</CreDtTm>\n" +
                "                <NbOfTxs>1</NbOfTxs>\n" +
                "                <SttlmInf>\n" +
                "                    <SttlmMtd>INDA</SttlmMtd>\n" +
                "                </SttlmInf>\n" +
                "            </GrpHdr>\n" +
                "            <CdtTrfTxInf>\n" +
                "                <PmtId>\n" +
                "                    <InstrId>ONLYMANDATORY</InstrId>\n" +
                "                    <EndToEndId>NOTPROVIDED</EndToEndId>\n" +
                "                    <TxId>5387354</TxId>\n" +
                "                    <UETR>00000000-0000-4000-8000-000000000000</UETR>\n" +
                "                </PmtId>\n" +
                "                <IntrBkSttlmAmt Ccy=\"USD\">1101.50</IntrBkSttlmAmt>\n" +
                "                <IntrBkSttlmDt>2012-09-29</IntrBkSttlmDt>\n" +
                "                <ChrgBr>DEBT</ChrgBr>\n" +
                "                <InstgAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>BBBBUS33</BICFI>\n" +
                "                    </FinInstnId>\n" +
                "                </InstgAgt>\n" +
                "                <InstdAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>AAAAGB2L</BICFI>\n" +
                "                    </FinInstnId>\n" +
                "                </InstdAgt>\n" +
                "                <Dbtr>\n" +
                "                    <Id>\n" +
                "                        <OrgId>\n" +
                "                            <AnyBIC>TESTBICA</AnyBIC>\n" +
                "                        </OrgId>\n" +
                "                    </Id>\n" +
                "                </Dbtr>\n" +
                "                <DbtrAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>TESTBICA</BICFI>\n" +
                "                    </FinInstnId>\n" +
                "                </DbtrAgt>\n" +
                "                <CdtrAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>TESTBICB</BICFI>\n" +
                "                    </FinInstnId>\n" +
                "                </CdtrAgt>\n" +
                "                <Cdtr>\n" +
                "                    <Id>\n" +
                "                        <OrgId>\n" +
                "                            <AnyBIC>TESTBICB</AnyBIC>\n" +
                "                        </OrgId>\n" +
                "                    </Id>\n" +
                "                </Cdtr>\n" +
                "            </CdtTrfTxInf>\n" +
                "        </FIToFICstmrCdtTrf>\n" +
                "    </Document>\n" +
                "</RequestPayload>";

        message = message.replaceAll("\r\n", "\n")
                .replaceAll("\n", "\r\n");
        try {
            String translatedMessage = Converter.convertMxToMt(message);
            System.out.println("Translated Message is: " + translatedMessage);
        } catch (InvalidMxMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        } catch (InvalidMtMessageException e) {
            System.out.println("The following errors occurred");
            e.getValidationErrorList().forEach(System.out::println);
        }
    }

}
```

#### Modify the generated message
Once you have the translated message as text, you can use our other Financial Messaging Libraries(ISO20022 and MT) in order to create a Java Object and make any changes you want.

In order to create an ISO20022 Java Object use the below code. The class FIToFICustomerCreditTransfer08 may vary depending on the ISO20022 Message Type.

```java
//For non CBPR+ Messages(pain.001.001.09)        
CustomerCreditTransferInitiation09 customerCreditTransferInitiation = new CustomerCreditTransferInitiation09();
customerCreditTransferInitiation.parseXML(xml);

//For CBPR+ Messages(pacs.008.001.08)
CbprMessage cbprMessage = new CbprMessage(new BusinessApplicationHeader02(), new FIToFICustomerCreditTransfer08());
cbprMessage.parseXml(xml);
BusinessApplicationHeader02 businessApplicationHeader02 = (BusinessApplicationHeader02) cbprMessage.getAppHdr(); 
FIToFICustomerCreditTransfer08 fiToFICustomerCreditTransfer = (FIToFICustomerCreditTransfer08) cbprMessage.getDocument();
In case you want to enclose the CBPR+ message under another Root Element, use the code below

cbprMessage.encloseCbprMessage("RequestPayload"); //In case you want RequestPayload
```

In order to create an MT Java Object use the below code.

```java
SwiftMessage swiftMessage = new SwiftMsgProcessor().ParseMsgStringToObject(translatedMessage);
```

#### GUI
The jar file is executable and once you run it, a Java FX program will open which will help you to instantly translate messages.

<img src="https://wiki.paymentcomponents.com/download/attachments/44597263/image2020-9-10_16-1-1.png?version=1&modificationDate=1599742864180&api=v2" />