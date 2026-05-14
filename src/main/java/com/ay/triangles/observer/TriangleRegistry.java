package com.ay.triangles.observer;

import com.ay.triangles.entity.Triangle;
import com.ay.triangles.warehouse.Warehouse;

public final class TriangleRegistry {

    private TriangleRegistry() {}

    public static void register(Triangle triangle) {
        Warehouse.getInstance().onTriangleChanged(triangle);
    }
}