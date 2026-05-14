package com.ay.triangles.warehouse;

import com.ay.triangles.entity.Triangle;
import com.ay.triangles.service.TriangleService;

import java.util.HashMap;
import java.util.Map;

public final class Warehouse implements TriangleEventListener {
    private static final Warehouse INSTANCE = new Warehouse();

    private final Map<String, TriangleData> cache;

    private Warehouse() {
        this.cache = new HashMap<>();
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public void store(Triangle triangle) {
        double area = TriangleService.area(triangle);
        double perimeter = TriangleService.perimeter(triangle);
        cache.put(triangle.id(), new TriangleData(area, perimeter));
    }

    public TriangleData get(String triangleId) {
        return cache.get(triangleId);
    }

    public boolean contains(String triangleId) {
        return cache.containsKey(triangleId);
    }

    public void clear() {
        cache.clear();
    }

    @Override
    public void onTriangleChanged(Triangle triangle) {
        store(triangle);
    }
}