package com.mb.ecb.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "cube"
})
public class Cube {

    @XmlElement(name = "Cube")
    protected List<TimeCube> cube;

    public Cube() {
    }

    public Cube(final List<TimeCube> cube) {
        this.cube = cube;
    }

    public List<TimeCube> getCube() {
        return cube;
    }
}
