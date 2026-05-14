package com.ay.triangles.reader;

import com.ay.triangles.entity.Triangle;
import java.util.List;

/**
 * Interface for reading triangles from a data source.
 */
public interface ITriangleReader {
    /**
     * Reads triangles from the specified path.
     * @param relativePath the path to the data source
     * @return list of triangles
     */
    List<Triangle> read(String relativePath);
} 