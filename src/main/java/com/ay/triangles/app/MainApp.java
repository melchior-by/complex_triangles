package com.ay.triangles.app;

import com.ay.triangles.reader.TriangleCsvReader;
import com.ay.triangles.service.TriangleSorter;

/**
 * Entry point for the Triangle Geometry Processing Application.
 */
public final class MainApp {
    private static final String DEFAULT_FILE_PATH = "input/triangles.csv";

    /**
     * Main method. Starts the application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String filePath = CsvPathOption.fromArgsOrDefault(args, DEFAULT_FILE_PATH);
        OutputMode outputMode = OutputMode.fromArgs(args);
        TriangleTypeFilter triangleTypeFilter = TriangleTypeFilter.fromArgs(args);
        VertexLabelsMode vertexLabelsMode = VertexLabelsMode.fromArgs(args);
        new TriangleApplicationRunner(new TriangleCsvReader(), new TriangleSorter())
                .run(filePath, outputMode, triangleTypeFilter, vertexLabelsMode);
    }
}