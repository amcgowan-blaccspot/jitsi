package net.java.sip.communicator.impl.protocol.jabber;

import net.java.sip.communicator.impl.protocol.jabber.extensions.jingle.JingleIQ;

import java.util.*;

public class JingleListeners {
    public enum JingleEvent {
        OnReceive,
        OnBeforeSend
    }

    private static Map<JingleEvent, ArrayList<JingleListener>> listeners = new HashMap<>();


    public static void addListener(JingleEvent event, JingleListener listener) {
        if (!listeners.containsKey(event)) {
            listeners.put(event, new ArrayList<JingleListener>());
        }
        listeners.get(event).add(listener);
    }

    public static JingleIQ triggerEvent(JingleEvent event, JingleIQ jingle) {
        if (listeners.containsKey(event)) {
            for (JingleListener j : listeners.get(event)) {
                jingle = j.invoke(jingle);
            }
        }

        return jingle;
    }

}

