package com.mservice.shared.utils;

/**
 * Simplified LogUtils to avoid missing log4j dependencies
 */
public class LogUtils {
    
    public static void init() {
        // No-op or use System.out
        System.out.println("[MoMo] Logger initialized (Simplified)");
    }

    public static void info(String serviceCode, Object object) {
        System.out.println("[MoMo INFO] [" + serviceCode + "]: " + object);
    }

    public static void info(Object object) {
        System.out.println("[MoMo INFO] " + object);
    }

    public static void debug(Object object) {
        System.out.println("[MoMo DEBUG] " + object);
    }

    public static void error(Object object) {
        System.err.println("[MoMo ERROR] " + object);
    }

    public static void warn(Object object) {
        System.out.println("[MoMo WARN] " + object);
    }
}
