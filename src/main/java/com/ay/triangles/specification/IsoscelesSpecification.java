package com.ay.triangles.specification;

import com.ay.triangles.entity.Triangle;
import com.ay.triangles.service.TriangleService;

public final class IsoscelesSpecification implements TriangleSpecification {
    @Override
    public boolean isSatisfiedBy(Triangle triangle) {
        return TriangleService.isIsosceles(triangle);
    }
}