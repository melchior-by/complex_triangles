package com.ay.triangles.service;

import com.ay.triangles.entity.Triangle;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class TriangleSorter implements ITriangleSorter {
    @Override
    public void sortById(List<Triangle> triangles) {
        Collections.sort(triangles, Comparator.comparing(Triangle::id));
    }
    @Override
    public void sortByPerimeter(List<Triangle> triangles) {
        Collections.sort(triangles, Comparator.comparingDouble(TriangleService::perimeter));
    }
    @Override
    public void sortByArea(List<Triangle> triangles) {
        Collections.sort(triangles, Comparator.comparingDouble(TriangleService::area));
    }
}