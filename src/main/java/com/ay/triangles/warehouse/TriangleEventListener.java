package com.ay.triangles.warehouse;

import com.ay.triangles.entity.Triangle;

public interface TriangleEventListener {
    void onTriangleChanged(Triangle triangle);
}