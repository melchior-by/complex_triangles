package com.ay.triangles.specification;

import com.ay.triangles.entity.Triangle;
import com.ay.triangles.service.TriangleService;

public final class AcuteSpecification implements TriangleSpecification {
    @Override
    public boolean isSatisfiedBy(Triangle triangle) {
        return TriangleService.isAcute(triangle);
    }
}