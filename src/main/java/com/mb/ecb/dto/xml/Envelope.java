package com.mb.ecb.dto.xml;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "Envelope", namespace = "http://www.gesmes.org/xml/2002-08-01")
@XmlType(name = "Envelope", propOrder = {
        "subject",
        "sender",
        "cube"
})
@XmlAccessorType(XmlAccessType.NONE)
public class Envelope {

    @XmlElement(namespace = "http://www.gesmes.org/xml/2002-08-01", required = true)
    protected String subject;

    @XmlElement(name = "Sender", namespace = "http://www.gesmes.org/xml/2002-08-01", required = true)
    protected Sender sender;

    @XmlElement(name = "Cube", required = true)
    protected Cube cube;

    public Envelope() {
    }

    public Envelope(final String subject, final Sender sender, final Cube cube) {
        this.subject = subject;
        this.sender = sender;
        this.cube = cube;
    }

    public String getSubject() {
        return subject;
    }

    public Sender getSender() {
        return sender;
    }

    public Cube getCube() {
        return cube;
    }
}
