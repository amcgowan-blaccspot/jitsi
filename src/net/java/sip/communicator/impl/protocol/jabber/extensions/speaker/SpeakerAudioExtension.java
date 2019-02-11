package net.java.sip.communicator.impl.protocol.jabber.extensions.speaker;


import net.java.sip.communicator.impl.protocol.jabber.extensions.AbstractPacketExtension;

public class SpeakerAudioExtension extends AbstractPacketExtension {
    public static final String ELEMENT_NAME = "audio";
    public static final String SSRC_ATTR_NAME = "ssrc";

    public SpeakerAudioExtension() {
        super(SpeakerIQ.NAMESPACE, ELEMENT_NAME);
    }

    public void setSsrc(String ssrc) {
        setAttribute(SSRC_ATTR_NAME, ssrc);
    }

    public String getSsrc() {
        return getAttributeAsString(SSRC_ATTR_NAME);
    }
}



