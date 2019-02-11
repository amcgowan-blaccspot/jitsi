package net.java.sip.communicator.impl.protocol.jabber.extensions.speaker;

import net.java.sip.communicator.impl.protocol.jabber.extensions.DefaultPacketExtensionProvider;
import net.java.sip.communicator.util.Console;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.xmlpull.v1.XmlPullParser;

public class SpeakerIQProvider extends IQProvider<SpeakerIQ> {

    public SpeakerIQProvider() {

        Console.Log("Adding Audio Extension");
        ProviderManager.addExtensionProvider(
                SpeakerAudioExtension.ELEMENT_NAME,
                SpeakerIQ.NAMESPACE,
                new DefaultPacketExtensionProvider<>(
                        SpeakerAudioExtension.class
                )
        );
        Console.Log("Adding video extension");
        ProviderManager.addExtensionProvider(
                SpeakerVideoExtension.ELEMENT_NAME,
                SpeakerIQ.NAMESPACE,
                new DefaultPacketExtensionProvider<>(
                        SpeakerVideoExtension.class
                )
        );

    }

    @Override
    public SpeakerIQ parse(XmlPullParser parser, int initialDepth) throws Exception {

        Console.Log("parsing speaker xml");

        String endpointId = parser.getAttributeValue("", SpeakerIQ.ENDPOINTID_ATTR_NAME);

        Console.Log("Endpoing ID: " + endpointId);

        SpeakerIQ speakerIQ = new SpeakerIQ(endpointId);

        Console.Log("Created new speaker IQ");

        DefaultPacketExtensionProvider<SpeakerAudioExtension> audioProvider = new DefaultPacketExtensionProvider<>(SpeakerAudioExtension.class);
        DefaultPacketExtensionProvider<SpeakerVideoExtension> videoProvider = new DefaultPacketExtensionProvider<>(SpeakerVideoExtension.class);

        Console.Log("created extension providers");

        boolean done = false;


        int eventType;
        String elementName;

        while (!done)
        {
            Console.Log("Parsing");
            eventType = parser.next();
            elementName = parser.getName();


            if (eventType == XmlPullParser.START_TAG)
            {
                Console.Log("Start tag");

                if (elementName.equals(SpeakerAudioExtension.ELEMENT_NAME))
                {
                    Console.Log("Adding audio");
                    SpeakerAudioExtension audio
                            = audioProvider.parse(parser);



                    speakerIQ.addExtension(audio);
                }
                else if(elementName.equals(SpeakerVideoExtension.ELEMENT_NAME))
                {
                    Console.Log("Adding video");
                    SpeakerVideoExtension video
                            = videoProvider.parse(parser);

                    speakerIQ.addExtension(video);
                }
            }

            if ((eventType == XmlPullParser.END_TAG)
                    && parser.getName().equals(SpeakerIQ.ELEMENT_NAME))
            {
                Console.Log("Done parsing");
                done = true;
            }
        }
        Console.Log("Returning new speaker iq");
        return speakerIQ;
    }
}
