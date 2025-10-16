import groovy.xml.XmlSlurper

def xmlData = '''
<n0:TransportationOrderGenericRequest xmlns:n0="http://sap.com/xi/SAPGlobal20/Global" xmlns:soap-env="http://schemas.xmlsoap.org/soap/envelope/" xmlns:prx="urn:sap.com:proxy:SX1:/1SAI/TAS7D5BCC4C0558D06D798D:758" xmlns:n1="http://sap.com/xi/LMD" xmlns:n2="http://sasol/xi/CustomTransportaionOrderFields">
    <MessageHeader>
        <CreationDateTime>2025-10-03T13:40:02.009Z</CreationDateTime>
        <SenderBusinessSystemID>SX1_110</SenderBusinessSystemID>
        <RecipientBusinessSystemID>BS_PROJECT44</RecipientBusinessSystemID>
    </MessageHeader>
    <TransportationOrder actionCode="04" changeOrdinalNumberValue="3" replicateIndicator="false" completeTransmissionIndicator="true">
        <ExternalPurchasingOrganisationalCentreID>P100</ExternalPurchasingOrganisationalCentreID>
        <ExternalPurchasingGroupID>S01</ExternalPurchasingGroupID>
        <TypeCode>ZFRO</TypeCode>
        <CategoryCode>TO</CategoryCode>
        <BusinessTransactionDocumentReference>
            <BusinessTransactionDocumentReference>
                <ID>1</ID>
                <TypeCode>T51</TypeCode>
            </BusinessTransactionDocumentReference>
			<BusinessTransactionDocumentReference>
                <ID>2</ID>
                <TypeCode>T52</TypeCode>
            </BusinessTransactionDocumentReference>
			<BusinessTransactionDocumentReference>
                <ID>3</ID>
                <TypeCode>T53</TypeCode>
            </BusinessTransactionDocumentReference>
			<BusinessTransactionDocumentReference>
                <ID>4</ID>
                <TypeCode>T54</TypeCode>
            </BusinessTransactionDocumentReference>
        </BusinessTransactionDocumentReference>
        <TextCollection>
            <Text>
                <ContentText>11</ContentText>
                <TypeCode>p44a</TypeCode>
            </Text>
			<Text>
                <ContentText>22</ContentText>
                <TypeCode>p12644</TypeCode>
            </Text>
			<Text>
                <ContentText>33</ContentText>
                <TypeCode>ewjku4</TypeCode>
            </Text>
        </TextCollection>
    </TransportationOrder>
</n0:TransportationOrderGenericRequest>
'''

def root = new XmlSlurper().parseText(xmlData)
def docRefNode = root.TransportationOrder.BusinessTransactionDocumentReference.BusinessTransactionDocumentReference.find {
    "T52".equalsIgnoreCase(it.TypeCode.text())
}

def id = docRefNode ? docRefNode.ID.text() : null

if (id == null || id == "") {
    println("T52 not found")
} else {
    println(id)
}

def textNode = root.TransportationOrder.TextCollection.Text.find {
    "p44a".equalsIgnoreCase(it.TypeCode.text())
}

def contentText = textNode ? textNode.ContentText.text() : null

if (contentText == null || contentText == "") {
    println("p44a not found")
} else {
    println(contentText)
}
