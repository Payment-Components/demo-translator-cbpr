# SWIFT Message Translator CBPR+ Demo

The project is here to demonstrate how our [SDK](https://www.paymentcomponents.com/messaging-libraries/) for CBPR+
Message Translator works. For our demonstration we are going to use the demo SDK which can translate SWIFT MT to CBPR+ messages.

This documentation describes how to incorporate the CBPR+ Translator Library into your project. The SDK is written in Java.  
By following this guide you will be able to translate SWIFT MT(ISO 15022) messages to CBPR+ messages
and vice versa according to CBPR+ guidelines.

It's a simple maven project, you can download it and run it, with Java 1.8 or above.

## SDK setup

Incorporate the SDK [jar](https://nexus.paymentcomponents.com/repository/public/gr/datamation/translator-cbpr/3.3.1/translator-cbpr-3.3.1-demo.jar)
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
    <version>3.3.1</version>
    <classifier>demo</classifier>
</dependency>
```
You may need to exclude groovy if you face problems during runtime or tests
```xml
<exclusions>
    <exclusion>
        <groupId>org.codehaus.groovy</groupId>
        <artifactId>groovy-all</artifactId>
    </exclusion>
</exclusions>
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
implementation 'gr.datamation:translator-cbpr:3.3.1:demo@jar'
```

## Supported MT > MX Translations

| MT message    | MX message           | Translator Class     | Available in Demo |
| ----------    | ----------           | ----------------     | :---------------: |
| MT101         | pain.001.001.09      | Mt101ToPain001       |                   |
| MT103         | pacs.008.001.08      | Mt103ToPacs008       |                   |
| MT103STP      | pacs.008.001.08.stp  | Mt103STPToPacs008STP |                   |
| MT103(Return) | pacs.004.001.09      | Mt103ToPacs004       |                   |
| MT200         | pacs.009.001.08.core | Mt200ToPacs009       |                   |
| MT202         | pacs.009.001.08.core | Mt202ToPacs009       | &check;           |
| MT202COV      | pacs.009.001.08.cov  | Mt202ToPacs009       |                   |
| MT202(Return) | pacs.004.001.09      | Mt202ToPacs004       |                   |
| MT900         | camt.054.001.08      | Mt900ToCamt054       |                   | 
| MT910         | camt.054.001.08      | Mt910ToCamt054       |                   |
| MT940         | camt.053.001.08      | Mt940ToCamt053       |                   |
| MT941         | camt.052.001.08      | Mt941ToCamt052       |                   |
| MT942         | camt.052.001.08      | Mt942ToCamt052       |                   |
| MT950         | camt.053.001.08      | Mt950ToCamt053       |                   |

## Supported MX > MT Translations

| MT message           | MX message     | Translator Class     | Available in Demo |
| ----------           | ----------     | ----------------     | :---------------: |
| camt.052.001.08      | MT941          | Camt052ToMt941       |                   |
| camt.052.001.08      | MT942          | Camt052ToMt942       |                   |
| camt.053.001.08      | MT940          | Camt053ToMt940       |                   |
| camt.054.001.08      | MT900          | Camt054ToMt900       |                   |
| camt.054.001.08      | MT910          | Camt054ToMt910       |                   |
| pacs.004.001.09      | MT103 (Return) | Pacs004ToMt103       |                   |
| pacs.004.001.09      | MT202 (Return) | Pacs004ToMt202       |                   |
| pacs.008.001.08      | MT103          | Pacs008ToMt103       |                   |
| pacs.008.001.08.stp  | MT103STP       | Pacs008STPToMt103STP |                   |
| pacs.009.001.08.core | MT200          | Pacs009ToMt200       |                   |
| pacs.009.001.08.core | MT202          | Pacs009ToMt202       | &check;           |
| pacs.009.001.08.cov  | MT202COV       | Pacs009ToMt202COV    |                   |
| pain.001.001.09      | MT101          | Pain001ToMt101       |                   |

## Instructions

### Auto Translation

You have the option to provide the MT or CBPR+ message and the library auto translates it to its equivalent.  
Both input and output are in text format.  
You need to call the following static methods of `CbprTranslator` class.  
In case of no error, you will get the formatted translated message.
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

### Error Handling

When you translate a message, both input and output messages are validated. For example, in a MT→MX translation, the
first step is to validate the MT message and we proceed to translation only if the message is valid.  
This is the reason why both directions throw `InvalidMtMessageException` and `InvalidMxMessageException`.  
Both Exceptions contain a `validationErrorList` attribute which contains a description of the errors occurred.

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
- [Translate MT to MX](src/main/java/com/paymentcomponents/swift/translator/TranslateMtToMx.java)
- [Translate MX to MT](src/main/java/com/paymentcomponents/swift/translator/TranslateMxToMt.java)

### Other resources

- More information about our implementation of **SWIFT MT library** can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-swift-mt).
- More information about our implementation of **ISO20022 library** can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-iso20022).
- More information about our implementation of **CBPR+ library** can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-iso20022#cbpr-messages).