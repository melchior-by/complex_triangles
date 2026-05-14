package com.ay.triangles.app;

import java.util.Arrays;

public final class CsvPathOption {
    private static final String FILE_PREFIX = "--file=";

    private CsvPathOption() {
    }

    public static String fromArgsOrDefault(String[] args, String defaultPath) {
        return Arrays.stream(args)
                .filter(arg -> arg != null && arg.startsWith(FILE_PREFIX))
                .map(arg -> arg.substring(FILE_PREFIX.length()).trim())
                .filter(path -> !path.isEmpty())
                .findFirst()
                .orElse(defaultPath);
    }
}
