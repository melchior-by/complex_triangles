package com.ay.triangles.repository;

import com.ay.triangles.entity.Triangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TriangleRepository implements ITriangleRepository {
    private final List<Triangle> triangles;

    public TriangleRepository() {
        this.triangles = new ArrayList<>();
    }

    public void add(Triangle triangle) {
        triangles.add(triangle);
    }

    public List<Triangle> getAll() {
        return Collections.unmodifiableList(triangles);
    }

    public void clear() {
        triangles.clear();
    }

    public int size() {
        return triangles.size();
    }
}