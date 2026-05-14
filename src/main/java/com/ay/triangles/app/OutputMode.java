package com.ay.triangles.app;

import java.util.Arrays;

public enum OutputMode {
    BASIC,
    GRAPHICS;

    private static final String MODE_PREFIX = "--mode=";

    public static OutputMode fromArgs(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg != null && arg.startsWith(MODE_PREFIX))
                .map(arg -> arg.substring(MODE_PREFIX.length()).trim().toLowerCase())
                .findFirst()
                .map(OutputMode::fromValue)
                .orElse(BASIC);
    }

    private static OutputMode fromValue(String value) {
        if ("graphics".equals(value)) {
            return GRAPHICS;
        }
        return BASIC;
    }
}
