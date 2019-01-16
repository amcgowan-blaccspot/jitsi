package net.java.sip.communicator.impl.protocol.jabber;


import net.java.sip.communicator.impl.protocol.jabber.extensions.jingle.JingleIQ;
import org.jivesoftware.smack.*;

public abstract class JingleListener {
    public abstract JingleIQ invoke(JingleIQ iq, XMPPConnection connection);
}
