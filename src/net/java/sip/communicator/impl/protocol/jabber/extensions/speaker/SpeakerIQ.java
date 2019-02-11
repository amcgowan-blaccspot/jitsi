package net.java.sip.communicator.impl.protocol.jabber.extensions.speaker;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAudioSsrcs() {
        ArrayList<String> audioSsrcs = new ArrayList<>();

        List<ExtensionElement> audioSsrcsExtensions = this.getExtensions(SpeakerAudioExtension.ELEMENT_NAME, SpeakerIQ.NAMESPACE);

        for (ExtensionElement e : audioSsrcsExtensions
             ) {
            audioSsrcs.add(((SpeakerAudioExtension)e).getSsrc());
        }

        return audioSsrcs;
    }

    public List<String> getVideoSsrcs() {
        ArrayList<String> videoSsrcs = new ArrayList<>();

        List<ExtensionElement> videoSsrcsExtensions = this.getExtensions(SpeakerVideoExtension.ELEMENT_NAME, SpeakerIQ.NAMESPACE);

        for (ExtensionElement e : videoSsrcsExtensions
        ) {
            videoSsrcs.add(((SpeakerVideoExtension)e).getSsrc());
        }

        return videoSsrcs;
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
