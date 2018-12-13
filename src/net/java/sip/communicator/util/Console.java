package org.blaccspot;

public class Console {
    public static void Log(String msg) {
        if (!msg.contains("[")) {
            msg = "["+msg+"]";
        }
        msg = "<l><d>[JI DEBUGLOG]</d>" +
                msg.replace("[", "<type>")
                        .replace("]", "</type><xml>")
                        .trim() +
                "</xml></l>";
        if (System.console() != null) {
            System.console().writer().println(msg);
        } else {
            System.out.println(msg);
        }
    }
}