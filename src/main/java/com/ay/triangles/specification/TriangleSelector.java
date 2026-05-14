package com.ay.triangles.specification;

import com.ay.triangles.entity.Triangle;

import java.util.ArrayList;
import java.util.List;

public final class TriangleSelector {

    private TriangleSelector() {}

    public static List<Triangle> select(List<Triangle> triangles, TriangleSpecification spec) {
        List<Triangle> result = new ArrayList<>();
        for (Triangle t : triangles) {
            if (spec.isSatisfiedBy(t)) {
                result.add(t);
            }
        }
        return result;
    }
}