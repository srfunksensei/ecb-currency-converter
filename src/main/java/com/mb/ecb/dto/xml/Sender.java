package com.mb.ecb.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sender", propOrder = {
        "name"
})
public class Sender {

    @XmlElement(namespace = "http://www.gesmes.org/xml/2002-08-01", required = true)
    protected String name;

    public Sender() {

    }

    public Sender(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
