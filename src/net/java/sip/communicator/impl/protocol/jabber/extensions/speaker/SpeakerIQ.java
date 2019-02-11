package net.java.sip.communicator.impl.protocol.jabber.extensions.speaker;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;

public class SpeakerIQ extends IQ {

    public static final String NAMESPACE ="urn:xmpp:speaker:1";
    public static final String ELEMENT_NAME = "speaker";
    public static final String ENDPOINTID_ATTR_NAME = "endpointId";
    private String endpointId;



    public SpeakerIQ(String endpointId)
    {
        super(ELEMENT_NAME, NAMESPACE);
        this.endpointId = endpointId;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }

    public String getEndpointId() {
        return this.endpointId;
    }

    @Override
    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder xml) {
        xml.attribute(ENDPOINTID_ATTR_NAME, getEndpointId());

        if (getExtensions().size() == 0)
        {
            xml.setEmptyElement();
        }
        else
        {
            xml.rightAngleBracket();

            for(ExtensionElement element : getExtensions()) {
                xml.append(element.toXML());
            }
        }

        return xml;
    }
}
