package net.java.sip.communicator.impl.protocol.jabber.extensions.speaker;


import net.java.sip.communicator.impl.protocol.jabber.extensions.AbstractPacketExtension;

public class SpeakerAudioExtension extends AbstractPacketExtension {
    public static final String ELEMENT_NAME = "audio";
    public SpeakerAudioExtension() { super(null, ELEMENT_NAME);}
}



