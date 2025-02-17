<a id="logo" href="https://www.paymentcomponents.com" title="Payment Components" target="_blank">
    <img loading="lazy" src="https://i.postimg.cc/yN5TNy29/LOGO-HORIZONTAL2.png" alt="Payment Components">
</a>

# Message Translator CBPR+ Demo

The project is here to demonstrate how our [SDK](https://www.paymentcomponents.com/messaging-libraries/) for CBPR+
Message Translator works. For our demonstration we are going to use the demo SDK which can translate MT to CBPR+
messages.

This documentation describes how to incorporate the CBPR+ Translator Library into your project. The SDK is written in
Java.  
By following this guide you will be able to translate MT(ISO 15022) messages to CBPR+ messages
and vice versa according to CBPR+ guidelines.

It's a simple maven project, you can download it and run it, with Java 1.8 or above.

## SDK setup

Incorporate the
SDK [jar](https://nexus.paymentcomponents.com/repository/public/gr/datamation/translator-cbpr/4.37.0/translator-cbpr-4.37.0-demo.jar)
into your project by the regular IDE means.  
This process will vary depending upon your specific IDE and you should consult your documentation on how to deploy a
bean.  
For example in Intellij all that needs to be done is to import the jar files into a project. Alternatively, you can
import it as a Maven or Gradle dependency.

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
    <version>4.37.0</version>
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
```

For Java 11 or above

```xml

<dependency>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>jaxb2-maven-plugin</artifactId>
    <version>2.5.0</version>
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
implementation 'gr.datamation:translator-cbpr:4.37.0:demo@jar'
```

Import additional dependencies if not included in your project

```groovy
implementation group: 'org.codehaus.groovy', name: 'groovy-all', version: '2.5.11', ext: 'pom'
```

For Java 11 or above

```groovy
implementation group: 'org.codehaus.mojo', name: 'jaxb2-maven-plugin', version: '2.5.0'
```

## Supported MT > MX Translations

| MT message    | MX message           | Translator Class    | Available in Demo | Custom Translation Rules |
|---------------|----------------------|---------------------|:-----------------:|:------------------------:|
| MT101         | pain.001.001.09      | Mt101ToPain001      |                   |         &check;          |
| MT103         | pacs.008.001.08      | Mt103ToPacs008      |                   |                          |
| MT103STP      | pacs.008.001.08      | Mt103ToPacs008      |                   |                          |
| MT103(Return) | pacs.004.001.09      | Mt103ToPacs004      |                   |                          |
| MT103         | pacs.002.001.10      | Mt103ToPacs002      |                   |                          |
| MT192         | camt.056.001.08      | Mt192Mt292ToCamt056 |                   |                          |
| MT292         | camt.056.001.08      | Mt192Mt292ToCamt056 |                   |                          |
| MT196         | camt.029.001.09      | Mt196Mt296ToCamt029 |                   |                          |
| MT296         | camt.029.001.09      | Mt196Mt296ToCamt029 |                   |                          |
| MT202         | pacs.009.001.08.core | Mt202Mt205ToPacs009 |      &check;      |                          |
| MT202         | pacs.009.001.08.core | Mt202Mt205ToPacs009 |      &check;      |                          |
| MT205         | pacs.009.001.08.core | Mt202Mt205ToPacs009 |                   |                          |
| MT200         | pacs.009.001.08      | Mt200ToPacs009      |                   |         &check;          |
| MT205COV      | pacs.009.001.08.cov  | Mt202Mt205ToPacs009 |                   |                          |
| MT202(Return) | pacs.004.001.09      | Mt202Mt205ToPacs004 |                   |                          |
| MT202         | pacs.002.001.10      | Mt202Mt205ToPacs002 |                   |                          |
| MT205(Return) | pacs.004.001.09      | Mt202Mt205ToPacs004 |                   |                          |
| MT205         | pacs.009.001.08.core | Mt202Mt205ToPacs009 |                   |                          |
| MT205         | pacs.002.001.10      | Mt202Mt205ToPacs002 |                   |                          |
| MT205COV      | pacs.009.001.08.cov  | Mt202Mt205ToPacs009 |                   |                          |
| MT205(Return) | pacs.004.001.09      | Mt202Mt205ToPacs004 |                   |                          |
| MT210         | camt.057.001.06      | Mt210ToCamt057      |                   |         &check;          |
| MT900         | camt.054.001.08      | Mt900ToCamt054      |                   |                          |
| MT910         | camt.054.001.08      | Mt910ToCamt054      |                   |                          |
| MT940         | camt.053.001.08      | Mt940ToCamt053      |                   |         &check;          |
| MT941         | camt.052.001.08      | Mt941ToCamt052      |                   |         &check;          |
| MT942         | camt.052.001.08      | Mt942ToCamt052      |                   |         &check;          |
| MT950         | camt.053.001.08      | Mt950ToCamt053      |                   |         &check;          |

## Supported MX > MT Translations

| MX message           | MT message     | Translator Class       | Available in Demo | Custom Translation Rules |
|----------------------|----------------|------------------------|:-----------------:|:------------------------:|
| camt.029.001.09      | MT196          | Camt029ToMt196Mt296    |                   |                          |
| camt.029.001.09      | MT296          | Camt029ToMt196Mt296    |                   |                          |
| camt.052.001.08      | MT941          | Camt052ToMt941         |                   |                          |
| camt.052.001.08      | MT942          | Camt052ToMt942         |                   |                          |
| camt.053.001.08      | MT940          | Camt053ToMt940         |                   |         &check;          |
| camt.053.001.08      | MT950          | Camt053ToMt950         |                   |         &check;          |
| camt.054.001.08      | MT900          | Camt054ToMt910Mt900    |                   |                          |
| camt.054.001.08      | MT910          | Camt054ToMt910Mt900    |                   |                          |
| camt.056.001.08      | MT192          | Camt056ToMt192Mt292    |                   |                          |
| camt.056.001.08      | MT292          | Camt056ToMt192Mt292    |                   |                          |
| camt.057.001.08      | MT210          | Camt057ToMt210         |                   |                          |
| camt.058.001.08      | MT292          | Camt058ToMt292         |                   |                          |
| camt.105.001.02      | MT190          | Camt105ToMt190Mt290    |                   |                          |
| camt.105.001.02      | MT290          | Camt105ToMt190Mt290    |                   |                          |
| camt.106.001.02      | MT191          | Camt106ToMt191Mt291    |                   |                          |
| camt.106.001.02      | MT291          | Camt106ToMt191Mt291    |                   |                          |
| camt.107.001.01      | MT110          | Camt107ToMt110         |                   |                          |
| camt.108.001.01      | MT111          | Camt108ToMt111         |                   |                          |
| camt.109.001.01      | MT112          | Camt109ToMt112         |                   |                          |
| pacs.002.001.10      | MT199          | Pacs002ToMt199Mt299    |                   |                          |
| pacs.002.001.10      | MT299          | Pacs002ToMt199Mt299    |                   |                          |
| pacs.004.001.09      | MT103 (Return) | Pacs004ToMt103         |                   |                          |
| pacs.004.001.09      | MT202 (Return) | Pacs004ToMt202Mt205    |                   |                          |
| pacs.004.001.09      | MT205 (Return) | Pacs004ToMt202Mt205    |                   |                          |
| pacs.008.001.08      | MT103          | Pacs008ToMt103         |                   |                          |
| pacs.008.001.08.stp  | MT103          | Pacs008ToMt103         |                   |                          |
| pacs.009.001.08.core | MT202          | Pacs009ToMt202Mt205    |      &check;      |                          |
| pacs.009.001.08.core | MT205          | Pacs009ToMt202Mt205    |                   |                          |
| pacs.009.001.08.adv  | MT202ADV       | Pacs009ToMt202ADV      |                   |                          |
| pacs.009.001.08.cov  | MT202COV       | Pacs009ToMt202Mt205COV |                   |                          |
| pacs.009.001.08.cov  | MT205COV       | Pacs009ToMt202Mt205COV |                   |                          |
| pacs.010.001.03      | MT204          | Pacs010ToMt204         |                   |         &check;          |
| pacs.010.001.03.col  | MT204          | Pacs010ColToMt204      |                   |         &check;          |
| pain.001.001.09      | MT101          | Pacs010ColToMt204      |                   |         &check;          |

## Instructions

### Auto Translation

You have the option to provide the MT or CBPR+ message and the library auto translates it to its equivalent.  
Input should be in text format.  
Output could be either in text format or an Object.  
You need to call the following static methods of `CbprTranslator` class.  
In case of no error of the input message, you will get the translated message.  
Translated message is not validated.

```java
public static String translateMtToMx(String mtMessage) throws InvalidMtMessageException, StopTranslationException, TranslationUnhandledException

public static CbprMessage translateMtToMxObject(String mtMessage) throws InvalidMtMessageException, StopTranslationException, TranslationUnhandledException
```

```java
public static String translateMxToMt(String mxMessage) throws InvalidMxMessageException, StopTranslationException, TranslationUnhandledException

public static SwiftMessage translateMxToMtObject(String mxMessage) throws InvalidMxMessageException, StopTranslationException, TranslationUnhandledException
```

In case you want to get a full truncation report, you need to call the following static method of `CbprTranslator`
class.

```java
public TranslationResult<SwiftMessage> translateMxToMtFull(String mxMessage, String direction) throws InvalidMxMessageException, StopTranslationException, TranslationUnhandledException

public static TranslationResult<CbprMessage> translateMtToMxFull(String mtMessage) throws InvalidMtMessageException, StopTranslationException, TranslationUnhandledException
```

`TranslationResult` has the following methods:

- `getMessage()` - Returns the translated message in text format.
- `getErrorList()` - Returns the list of `TranslationError` objects.

### Explicit Translation

If you do not want to use the auto-translation functionality, you can call directly the Translator you want.  
In this case you need to know the exact translation mapping.  
Translator classes implement the `MtToCbprTranslator` or `CbprToMtTranslator` interface.  
The `translate(Object)`, does not validate the message.  
The `translate(String)`, validates the message.  
Translated message is not validated.

`MtToCbprTranslator` interface provides the following methods for both text and object format translations.

```java
String translate(String swiftMtMessageText) throws InvalidMtMessageException, StopTranslationException, TranslationUnhandledException;

CbprMessage translate(SwiftMessage swiftMtMessage) throws StopTranslationException, TranslationUnhandledException;
```

`CbprToMtTranslator` interface provides the following methods.

```java
String translate(String cbprMessageText) throws InvalidMxMessageException, StopTranslationException, TranslationUnhandledException;

SwiftMessage translate(CbprMessage cbprMessage) throws StopTranslationException, TranslationUnhandledException;

SwiftMessage[] translateMultipleMt(CbprMessage cbprMessage) throws StopTranslationException, TranslationUnhandledException;
```

The method `translateMultipleMt` translates a CBPR+ message to multiple MT messages. Until now, no translation uses this
method.

Both `MtToCbprTranslator` and `CbprToMtTranslator` interfaces provide a method to get the translation error list.

```java
List<TranslationError> getTranslationErrorList();
```

For the structure of `TranslationError` see [Error Handling](#translation-error-list).

In case that a translation uses this logic, the translation in text format will return the MT messages splitted
with `$`.  
For example:

```
:56A:INTERBIC
-}${1:F01TESTBICAXXXX1111111111}
```

Specifically for Pacs009 to Mt205 and Pacs009COV to Mt205Cov, Translators Pacs009ToMt202Mt205 and Pacs009ToMt202Mt205COV
respectively, you need to use setters Pacs009ToMt202Mt205.setTranslateTo(String translateTo)  and
Pacs009ToMt202Mt205COV.setTranslateTo(String translateTo) to specify that you want to translate a Pacs009 to an Mt205 or
it will always be translated to an Mt202.

```
Pacs009ToMt202Mt205.setTranslateTo("205");
Pacs009ToMt202Mt205COV.setTranslateTo("205");
```

### Message Validation

In order to validate the translated MT message, you can
use `MtMessageValidationUtils.validateMtMessage(SwiftMessage mtMessage)`,
`MtMessageValidationUtils.parseAndValidateMtMessage(String message)` or any other way you prefer.  
In order to validate the translated CBPR+ message, you can
use `CbprMessageValidationUtils.parseAndValidateCbprMessage(A appHdr, D documentMessage, CbprMessage.CbprMsgType cbprXsd)`
where
`CbprMsgType` can be retrieved from
CbprMessage.extractCbprMsgType(), `CbprMessageValidationUtils.autoParseAndValidateCbprMessage(String message)` or any
other way you prefer.

### Error Handling

#### InvalidMtMessageException & InvalidMxMessageException

When you translate a message, input message is validated. For example, in a MT→MX translation, the
first step is to validate the MT message and we proceed to translation only if the message is valid.  
This is the reason why this direction throws `InvalidMtMessageException`.  
The other direction throws `InvalidMxMessageException`.  
Both Exceptions contain a `validationErrorList` attribute which contains a description of the errors occurred.

#### StopTranslationException

When there is a condition in input message that obstructs the translation, a `StopTranslationException` is thrown which
contains a `translationErrorList`. The `TranslationError` has the structure:

- errorCode
- errorCategory
- errorDescription

#### TranslationUnhandledException

When there is an exception that is not known, like `NullPointerException`, an `TranslationUnhandledException` is thrown
the
actual exception is attached as the cause.

#### Translation Error List

Each Translator class has a `translationErrorList` attribute which contains a list of `TranslationError` objects. You
can access this list by calling the `getTranslationErrorList()` method of each Translator.  
The `TranslationError` has the below structure:

- `errorCode`
    - `errorText` - Mandatory field which contains the error code - e.g. `T0000T`
    - `errorCategory` Mandatory field that contains the error category - e.g. `TRUNC_N`
    - `errorDescription` - Mandatory field that contains a more detailed description for the error -
      e.g. `Field content has been truncated.`
- `originalFieldPath` - Optional field which contains the path of the original message -
  e.g. `NtryDtls/TxDtls/Refs/EndToEndId`
- `targetFieldPath` - Optional fields that contains the path of the translated message - e.g. `Tag61`
- `originalValue` - Optional field that contains the original value - e.g. `MUELL/FINP/RA12345`
- `alteredValue` - Optional field that contains the altered value e.g. `MUELL/FINP/RA12+`

### Modify the generated message

Once you have the translated message in text format, you can use our other Financial Messaging
Libraries ([Other Resources](#other-resources)) in order to create a Java Object and make any changes you want.

In order to create an CBPR+ Java Object from text use the below code. The class `FIToFICustomerCreditTransfer08` may
vary depending on the ISO20022 Message Type.   
_Other message types are
available [here](https://github.com/Payment-Components/demo-iso20022#supported-cbpr-message-types)_

```java
CbprMessage<BusinessApplicationHeader02, FIToFICustomerCreditTransfer08> cbprMessage = new CbprMessage(new BusinessApplicationHeader02(), new FIToFICustomerCreditTransfer08());
cbprMessage.

parseXml(translatedMessageText);

BusinessApplicationHeader02 businessApplicationHeader02 = cbprMessage.getAppHdr();
FIToFICustomerCreditTransfer08 fiToFICustomerCreditTransfer = cbprMessage.getDocument();

//In case you want to enclose the CBPR+ message under another Root Element, use the code below
cbprMessage.

encloseCbprMessage("RequestPayload"); //In case you want RequestPayload
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

- More information about our implementation of **MT library** can be found in our demo
  on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-swift-mt).
- More information about our implementation of **ISO20022 library** can be found in our demo
  on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-iso20022).
- More information about our implementation of **CBPR+ library** can be found in our demo
  on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-iso20022#cbpr-messages).
