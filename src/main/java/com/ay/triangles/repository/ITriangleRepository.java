package com.ay.triangles.repository;

import com.ay.triangles.entity.Triangle;
import java.util.List;

/**
 * Repository interface for managing Triangle entities.
 */
public interface ITriangleRepository {
    /**
     * Adds a triangle to the repository.
     * @param triangle the triangle to add
     */
    void add(Triangle triangle);
    /**
     * Returns an unmodifiable list of all triangles in the repository.
     * @return list of triangles
     */
    List<Triangle> getAll();
    /**
     * Removes all triangles from the repository.
     */
    void clear();
    /**
     * Returns the number of triangles in the repository.
     * @return the size
     */
    int size();
} 