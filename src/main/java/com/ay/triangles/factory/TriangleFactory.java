package com.ay.triangles.factory;

import com.ay.triangles.entity.Point;
import com.ay.triangles.entity.Triangle;
import com.ay.triangles.exception.InvalidTriangleException;
import com.ay.triangles.validator.TriangleValidator;

public final class TriangleFactory {

    private TriangleFactory() {
        // static-only utility
    }

    public static Triangle create(String id, Point a, Point b, Point c) throws InvalidTriangleException {
        if (!TriangleValidator.isValidTriangle(a, b, c)) {
            throw new InvalidTriangleException("Invalid triangle: points are collinear or violate triangle inequality");
        }
        return new Triangle(id, a, b, c);
    }
}