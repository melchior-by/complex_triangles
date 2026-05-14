package com.ay.triangles.app;

import java.util.Arrays;

public enum VertexLabelsMode {
    ON,
    OFF;

    private static final String LABELS_PREFIX = "--labels=";

    public static VertexLabelsMode fromArgs(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg != null && arg.startsWith(LABELS_PREFIX))
                .map(arg -> arg.substring(LABELS_PREFIX.length()).trim().toLowerCase())
                .findFirst()
                .map(VertexLabelsMode::fromValue)
                .orElse(ON);
    }

    public boolean isEnabled() {
        return this == ON;
    }

    private static VertexLabelsMode fromValue(String value) {
        if ("off".equals(value)) {
            return OFF;
        }
        return ON;
    }
}
