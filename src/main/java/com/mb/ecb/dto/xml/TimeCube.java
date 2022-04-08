package com.mb.ecb.dto.xml;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "cube"
})
public class TimeCube {

    @XmlAttribute(name = "time")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar time;

    @XmlElement(name = "Cube")
    protected List<CurrencyCube> cube;

    public TimeCube() {

    }

    public TimeCube(final XMLGregorianCalendar time, final List<CurrencyCube> cube) {
        this.time = time;
        this.cube = cube;
    }

    public XMLGregorianCalendar getTime() {
        return time;
    }

    public List<CurrencyCube> getCube() {
        return cube;
    }
}
