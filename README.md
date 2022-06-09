# Message Translator CBPR+ Demo

The project is here to demonstrate how our [SDK](https://www.paymentcomponents.com/messaging-libraries/) for CBPR+
Message Translator works. For our demonstration we are going to use the demo SDK which can translate MT to CBPR+ messages.

This documentation describes how to incorporate the CBPR+ Translator Library into your project. The SDK is written in Java.  
By following this guide you will be able to translate MT(ISO 15022) messages to CBPR+ messages
and vice versa according to CBPR+ guidelines.

It's a simple maven project, you can download it and run it, with Java 1.8 or above.

## SDK setup

Incorporate the SDK [jar](https://nexus.paymentcomponents.com/repository/public/gr/datamation/translator-cbpr/3.33.0/translator-cbpr-3.33.0-demo.jar)
into your project by the regular IDE means.  
This process will vary depending upon your specific IDE and you should consult your documentation on how to deploy a bean.  
For example in Intellij all that needs to be done is to import the jar files into a project. Alternatively, you can import it as a Maven or Gradle dependency.

### Maven

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
    <artifactId>translator-cbpr</artifactId>
    <version>3.21.1</version>
    <classifier>demo</classifier>
</dependency>
```
Import additional dependencies if not included in your project
```xml
<dependency>
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy-all</artifactId>
    <version>2.5.11</version>
    <scope>compile</scope>
    <type>pom</type>
</dependency>

<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.1</version>
</dependency>

<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.1</version>
</dependency>
```

### Gradle

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
implementation 'gr.datamation:translator-cbpr:3.21.1:demo@jar'
```
Import additional dependencies if not included in your project
```groovy
implementation group: 'org.codehaus.groovy', name: 'groovy-all', version: '2.5.11', ext: 'pom'
implementation group: 'javax.xml.bind', name: 'jaxb-api', version: '2.3.1'
implementation group: 'org.glassfish.jaxb', name: 'jaxb-runtime', version: '2.3.1'
```

## Supported MT > MX Translations
| MT message    | MX message           | Translator Class     | Available in Demo | 
| ----------    | ----------           | ----------------     | :---------------: | 
| MT103         | pacs.004.001.09      | Mt103ToPacs004       |                   | 
| MT103         | pacs.008.001.08      | Mt103ToPacs008       |                   | 
| MT103STP      | pacs.008.001.08      | Mt103STPToPacs008STP |                   | 
| MT103(Return) | pacs.004.001.09      | Mt103ToPacs004       |                   | 
| MT192         | camt.056.001.08      | Mt192Mt292ToCamt056  |                   | 
| MT292         | camt.056.001.08      | Mt192Mt292ToCamt056  |                   | 
| MT196         | camt.029.001.09      | Mt196Mt296ToCamt029  |                   | 
| MT296         | camt.029.001.09      | Mt196Mt296ToCamt029  |                   | 
| MT202         | pacs.009.001.08.core | Mt202Mt205ToPacs009  | &check;           | 
| MT205         | pacs.009.001.08.core | Mt202Mt205ToPacs009  |                   | 
| MT202COV      | pacs.009.001.08.cov  | Mt202Mt205ToPacs009  |                   | 
| MT205COV      | pacs.009.001.08.cov  | Mt202Mt205ToPacs009  |                   | 
| MT202(Return) | pacs.004.001.09      | Mt202Mt205ToPacs004  |                   | 
| MT205(Return) | pacs.004.001.09      | Mt202Mt205ToPacs004  |                   | 
| MT205         | pacs.009.001.08.core | Mt202Mt205ToPacs009  |                   | 
| MT205COV      | pacs.009.001.08.cov  | Mt202Mt205ToPacs009  |                   | 
| MT205(Return) | pacs.004.001.09      | Mt202Mt205ToPacs004  |                   | 
| MT900         | camt.054.001.08      | Mt900ToCamt054       |                   | 
| MT910         | camt.054.001.08      | Mt910ToCamt054       |                   |

## Supported MX > MT Translations

| MX message           | MT message     | Translator Class      | Available in Demo |
| ----------           | ----------     |-----------------------| :---------------: |
| camt.029.001.09      | MT196          | Camt029ToMt196Mt296   |                   |
| camt.029.001.09      | MT296          | Camt029ToMt196Mt296   |                   |
| camt.052.001.08      | MT942          | Camt052ToMt942        |                   |
| camt.053.001.08      | MT940          | Camt053ToMt940        |                   |
| camt.054.001.08      | MT900          | Camt054ToMt910Mt900   |                   |
| camt.054.001.08      | MT910          | Camt054ToMt910Mt900   |                   |
| camt.056.001.08      | MT192          | Camt056ToMt192Mt292   |                   |
| camt.056.001.08      | MT292          | Camt056ToMt192Mt292   |                   |
| camt.057.001.08      | MT210          | Camt057ToMt210        |                   |
| pacs.002.001.10      | MT199          | Pacs002ToMt199Mt299   |                   |
| pacs.002.001.10      | MT299          | Pacs002ToMt199Mt299   |                   |
| pacs.004.001.09      | MT103 (Return) | Pacs004ToMt103        |                   |
| pacs.004.001.09      | MT202 (Return) | Pacs004ToMt202Mt205   |                   |
| pacs.004.001.09      | MT205 (Return) | Pacs004ToMt202Mt205   |                   |
| pacs.008.001.08      | MT103          | Pacs008ToMt103        |                   |
| pacs.008.001.08.stp  | MT103          | Pacs008ToMt103        |                   |
| pacs.009.001.08.core | MT202          | Pacs009ToMt202Mt205   | &check;           |
| pacs.009.001.08.adv  | MT202ADV       | Pacs009ToMt202ADV     |                   |
| pacs.009.001.08.cov  | MT202COV       | Pacs009ToMt202Mt205COV|                   |

## Instructions

### Auto Translation

You have the option to provide the MT or CBPR+ message and the library auto translates it to its equivalent.  
Both input and output are in text format.  
You need to call the following static methods of `CbprTranslator` class.  
In case of no error of the input message, you will get the formatted translated message.  
Translated message is not validated.  
```java
public static String translateMtToMx(String mtMessage) throws InvalidMxMessageException, InvalidMtMessageException
```
```java
public static String translateMxToMt(String mxMessage) throws InvalidMxMessageException, InvalidMxMessageException
```

### Explicit Translation

If you do not want to use the auto-translation functionality, you can call directly the Translator you want.  
In this case you need to know the exact translation mapping.  
Translator classes implement the `MtToMxTranslator` or `MxToMtTranslator` interface.  
The `translate(Object)`, does not validate the message.  
The `translate(String)`, validates the message.  
Translated message is not validated.  

`MtToMxTranslator` interface provides the following methods for both text and object format translations.
```java
String translate(String swiftMtMessageText) throws Exception;
CbprMessage<A, D> translate(SwiftMessage swiftMtMessage) throws Exception;
```

`MxToMtTranslator` interface provides the following methods.
```java
String translate(String cbprMessageText) throws Exception;
SwiftMessage translate(CbprMessage<A, D> cbprMessage) throws Exception;
SwiftMessage[] translateMultipleMt(CbprMessage<A, D> cbprMessage) throws Exception;
```

The method `translateMultipleMt` translates a CBPR+ message to multiple MT messages. Until now, no translation uses this method.  
In case that a translation uses this logic, the translation in text format will return the MT messages splitted with `$`.  
For example:
```
:56A:INTERBIC
-}${1:F01TESTBICAXXXX1111111111}
```

Specifically for Pacs009 to Mt205 and Pacs009COV to Mt205Cov, Translators Pacs009ToMt202Mt205 and Pacs009ToMt202Mt205COV respectively, you need to use setters Pacs009ToMt202Mt205.setTranslateTo(String translateTo)  and Pacs009ToMt202Mt205COV.setTranslateTo(String translateTo) to specify that you want to translate a Pacs009 to an Mt205 or it will always be translated to an Mt202.
```
Pacs009ToMt202Mt205.setTranslateTo("205");
Pacs009ToMt202Mt205COV.setTranslateTo("205");
```

### Error Handling

When you translate a message, input message is validated. For example, in a MT→MX translation, the
first step is to validate the MT message and we proceed to translation only if the message is valid.  
This is the reason why this directions throws `InvalidMtMessageException`.  
The other direction throws `InvalidMxMessageException`.  
Both Exceptions contain a `validationErrorList` attribute which contains a description of the errors occurred.
In order to validate the translated MT message, you can use `MtMessageValidationUtils.validateMtMessage(SwiftMessage mtMessage)`, 
`MtMessageValidationUtils.parseAndValidateMtMessage(String message)` or any other way you prefer.  
In order to validate the translated MX message, you can use `CbprMessageValidationUtils.parseAndValidateCbprMessage(A appHdr, D documentMessage, CbprMessage.CbprMsgType cbprXsd)` where 
`CbprMsgType` can be retrieved from CbprMessage.extractCbprMsgType(), `CbprMessageValidationUtils.autoParseAndValidateCbprMessage(String message)` or any other way you prefer.

### Modify the generated message

Once you have the translated message in text format, you can use our other Financial Messaging
Libraries ([Other Resources](#other-resources)) in order to create a Java Object and make any changes you want.

In order to create an CBPR+ Java Object from text use the below code. The class `FIToFICustomerCreditTransfer08` may vary depending on the ISO20022 Message Type.   
_Other message types are available [here](https://github.com/Payment-Components/demo-iso20022#supported-cbpr-message-types)_
```java
CbprMessage<BusinessApplicationHeader02, FIToFICustomerCreditTransfer08> cbprMessage = new CbprMessage(new BusinessApplicationHeader02(), new FIToFICustomerCreditTransfer08());
cbprMessage.parseXml(translatedMessageText);
BusinessApplicationHeader02 businessApplicationHeader02 = cbprMessage.getAppHdr();
FIToFICustomerCreditTransfer08 fiToFICustomerCreditTransfer = cbprMessage.getDocument();

//In case you want to enclose the CBPR+ message under another Root Element, use the code below
cbprMessage.encloseCbprMessage("RequestPayload"); //In case you want RequestPayload
```

In order to create an MT Java Object use the below code.
```java
SwiftMessage swiftMessage = new SwiftMsgProcessor().ParseMsgStringToObject(translatedMessageText);
```

### Code Samples

In this project you can see code for all the basic manipulation of an MT or CBPR+ message, like:
- [Translate MT to MX](src/main/java/com/paymentcomponents/converter/cbpr/demo/TranslateMtToMx.java)
- [Translate MX to MT](src/main/java/com/paymentcomponents/converter/cbpr/demo/TranslateMxToMt.java)

### Other resources

- More information about our implementation of **MT library** can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-swift-mt).
- More information about our implementation of **ISO20022 library** can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-iso20022).
- More information about our implementation of **CBPR+ library** can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-iso20022#cbpr-messages).
