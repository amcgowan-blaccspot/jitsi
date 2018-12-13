package net.java.sip.communicator.util;

public class Console {
    public static void Log(String msg) {
        msg = "[JI] [DEBUGLOG] " + msg;
        if (System.console() != null) {
            System.console().writer().println(msg);
        } else {
            System.out.println(msg);
        }
    }
}