@XmlSchema(
        namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "gesmes", namespaceURI = "http://www.gesmes.org/xml/2002-08-01"),
                @XmlNs(prefix = "", namespaceURI = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
        }
)
package com.mb.ecb.dto.xml;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
