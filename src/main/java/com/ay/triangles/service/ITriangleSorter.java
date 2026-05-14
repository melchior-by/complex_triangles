package com.ay.triangles.service;

import com.ay.triangles.entity.Triangle;
import java.util.List;

/**
 * Interface for sorting lists of triangles by various criteria.
 */
public interface ITriangleSorter {
    /**
     * Sorts triangles by their ID.
     * @param triangles the list to sort
     */
    void sortById(List<Triangle> triangles);
    /**
     * Sorts triangles by their perimeter.
     * @param triangles the list to sort
     */
    void sortByPerimeter(List<Triangle> triangles);
    /**
     * Sorts triangles by their area.
     * @param triangles the list to sort
     */
    void sortByArea(List<Triangle> triangles);
} 