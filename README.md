# SWIFT Message Translator CBPR+ Demo

The project is here to demonstrate how our [SDK](https://www.paymentcomponents.com/messaging-libraries/) for 
SWIFT Message Translator works. For our demonstration we are going to use the demo SDK which can convert SWIFT MT to SWIFT MX (ISO20022) messages. 

This documentation describes how to incorporate the SWIFT Translator Library into your project. The SDK is written in Java.
By following this guide you will be able to translate SWIFT MT(ISO 15022) messages to SWIFT MX(ISO 20022) messages and vice versa according to CBPR+ guidelines.

It's a simple maven project, you can download it and run it, with Java 1.8 or above.

## SDK setup
Incorporate the SDK [jar](https://nexus.paymentcomponents.com/repository/public/gr/datamation/translator-cbpr/1.5.6/translator-cbpr-1.5.6-demo.jar) into your project by the regular IDE means. 
This process will vary depending upon your specific IDE and you should consult your documentation on how to deploy a bean. 
For example in Intellij all that needs to be done is to import the jar files into a project.
Alternatively, you can import it as a Maven or Gradle dependency.  

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
    <artifactId>translator-cbpr</artifactId>
    <version>1.5.6</version>
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
implementation 'gr.datamation:translator-cbpr:1.5.6:demo@jar'
```

#### Supported Translations MT > MX From Message To Message

| MT message | MX message |
| --- | --- |
|MT103|pacs.008.001.08|
|MT103STP|pacs.008.001.08|
|MT101|pain.001.001.09|
|MT202COV|pacs.009.001.08|
|MT202|pacs.009.001.08|
|MT200|pacs.009.001.08|
|MT940|camt.053.001.08|
|MT950|camt.053.001.08|
|MT910|camt.054.001.08|
|MT900|camt.054.001.08|
|MT103(Return)|pacs.004.001.09|
|MT202(Return)|pacs.004.001.09|
|MT942|camt.052.001.08|
|MT941|camt.052.001.08|

#### Supported Translations MX > MT From Message To Message

| MX message | MT message |
| --- | --- |
|pacs.008.001.08|MT103|
|pacs.008.001.08.stp.eu | MT103STP |
|pacs.009.001.08.cov | MT202COV |
|pacs.009.001.08.core | MT202 |
|pacs.009.001.08|MT200|
|pain.001.001.09|MT101|
|camt.053.001.08|MT940|
|camt.054.001.08|MT910|
|camt.054.001.08|MT900|
|pacs.004.001.09|MT103(Return)|
|pacs.004.001.09|MT202(Return)|
|camt.052.001.08|MT942|
|camt.052.001.08|MT941|

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

#### Modify the generated message
Once you have the translated message as text, you can use our other Financial Messaging Libraries ([ISO20022](https://github.com/Payment-Components/demo-iso20022) and [MT](https://github.com/Payment-Components/demo-swift-mt)) in order to create a Java Object and make any changes you want.

In order to create an ISO20022 Java Object use the below code. The class `FIToFICustomerCreditTransfer08` may vary depending on the ISO20022 Message Type.   
_More information about CBPR+ Messages handling is available in PaymentComponents [wiki](https://wiki.paymentcomponents.com/display/SL/ISO20022+Documentation#ISO20022Documentation-CBPR+Handling) and [GitHub](https://github.com/Payment-Components/demo-iso20022#more-features-are-included-in-the-paid-version-like)._

```java
//For non CBPR+ Messages(pain.001.001.09)        
CustomerCreditTransferInitiation09 customerCreditTransferInitiation = new CustomerCreditTransferInitiation09();
customerCreditTransferInitiation.parseXML(xml);

//For CBPR+ Messages(pacs.008.001.08)
CbprMessage cbprMessage = new CbprMessage(new BusinessApplicationHeader02(), new FIToFICustomerCreditTransfer08());
cbprMessage.parseXml(xml);
BusinessApplicationHeader02 businessApplicationHeader02 = (BusinessApplicationHeader02) cbprMessage.getAppHdr(); 
FIToFICustomerCreditTransfer08 fiToFICustomerCreditTransfer = (FIToFICustomerCreditTransfer08) cbprMessage.getDocument();

//In case you want to enclose the CBPR+ message under another Root Element, use the code below
cbprMessage.encloseCbprMessage("RequestPayload"); //In case you want RequestPayload
```

In order to create an MT Java Object use the below code.

```java
SwiftMessage swiftMessage = new SwiftMsgProcessor().ParseMsgStringToObject(translatedMessage);
```

#### Code Samples

In this project you can see code for all the basic manipulation of an MX message, like:
- [Convert MT to MX](src/main/java/com/paymentcomponents/swift/translator/ConvertMTtoMX.java)
- [Convert MX to MT](src/main/java/com/paymentcomponents/swift/translator/ConvertMXtoMT.java)

#### Other resources
- More information about our implementation of SWIFT MT library can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-swift-mt).
- More information about our implementation of ISO20022 library can be found in our demo on [PaymentComponents GitHub](https://github.com/Payment-Components/demo-iso20022).
