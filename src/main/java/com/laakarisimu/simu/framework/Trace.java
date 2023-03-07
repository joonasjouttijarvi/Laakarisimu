package com.laakarisimu.simu.framework;

public class Trace {

    private static Level traceLevel;

    public static void setTraceLevel(Level lvl) {
        traceLevel = lvl;
    }

    public static void out(Level lvl, String txt) {
        if (lvl.ordinal() >= traceLevel.ordinal()) {
            System.out.println(txt);
        }
    }

    public enum Level {INFO, WAR, ERR}


}