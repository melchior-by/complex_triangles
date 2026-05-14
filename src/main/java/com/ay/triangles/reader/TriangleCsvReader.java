package com.ay.triangles.reader;

import com.ay.triangles.entity.Point;
import com.ay.triangles.entity.Triangle;
import com.ay.triangles.exception.FigureParseException;
import com.ay.triangles.exception.InvalidTriangleException;
import com.ay.triangles.factory.TriangleFactory;
import com.ay.triangles.observer.TriangleRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TriangleCsvReader implements ITriangleReader {
    private static final Logger LOGGER = LogManager.getLogger(TriangleCsvReader.class);
    private static final String DELIMITER = ";";
    private static final int FIELD_COUNT = 7;

    @Override
    public List<Triangle> read(String relativePath) {
        List<Triangle> triangles = new ArrayList<>();
        Path path = Path.of(relativePath);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                try {
                    Triangle triangle = parseLine(line);
                    TriangleRegistry.register(triangle);
                    triangles.add(triangle);
                } catch (FigureParseException | InvalidTriangleException e) {
                    LOGGER.warn("Skipping line due to error: {}", e.getMessage());
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error reading file '{}': {}", relativePath, e.getMessage());
        }
        return triangles;
    }

    private Triangle parseLine(String line) throws FigureParseException, InvalidTriangleException {
        String cleanLine = line.split("#")[0].trim();
        String[] parts = cleanLine.trim().split(DELIMITER);
        if (parts.length != FIELD_COUNT) {
            throw new FigureParseException("Invalid number of fields in line: " + line);
        }
        String id = parts[0].trim();
        try {
            double x1 = Double.parseDouble(parts[1].trim());
            double y1 = Double.parseDouble(parts[2].trim());
            double x2 = Double.parseDouble(parts[3].trim());
            double y2 = Double.parseDouble(parts[4].trim());
            double x3 = Double.parseDouble(parts[5].trim());
            double y3 = Double.parseDouble(parts[6].trim());

            Point a = new Point(x1, y1);
            Point b = new Point(x2, y2);
            Point c = new Point(x3, y3);

            return TriangleFactory.create(id, a, b, c);
        } catch (NumberFormatException e) {
            throw new FigureParseException("Invalid number format in line: " + line);
        }
    }
}