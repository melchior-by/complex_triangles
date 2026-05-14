package com.ay.triangles.app;

import com.ay.triangles.specification.AcuteSpecification;
import com.ay.triangles.specification.EquilateralSpecification;
import com.ay.triangles.specification.IsoscelesSpecification;
import com.ay.triangles.specification.ObtuseSpecification;
import com.ay.triangles.specification.RightSpecification;
import com.ay.triangles.specification.TriangleSpecification;

import java.util.Arrays;

public enum TriangleTypeFilter {
    RIGHT("right"),
    ACUTE("acute"),
    OBTUSE("obtuse"),
    EQUILATERAL("equilateral"),
    ISOSCELES("isosceles");

    private static final String TYPE_PREFIX = "--type=";

    private final String value;

    TriangleTypeFilter(String value) {
        this.value = value;
    }

    public static TriangleTypeFilter fromArgs(String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg != null && arg.startsWith(TYPE_PREFIX))
                .map(arg -> arg.substring(TYPE_PREFIX.length()).trim().toLowerCase())
                .findFirst()
                .map(TriangleTypeFilter::fromValue)
                .orElse(RIGHT);
    }

    public String displayName() {
        return value;
    }

    public TriangleSpecification toSpecification() {
        return switch (this) {
            case RIGHT -> new RightSpecification();
            case ACUTE -> new AcuteSpecification();
            case OBTUSE -> new ObtuseSpecification();
            case EQUILATERAL -> new EquilateralSpecification();
            case ISOSCELES -> new IsoscelesSpecification();
        };
    }

    private static TriangleTypeFilter fromValue(String value) {
        return switch (value) {
            case "acute" -> ACUTE;
            case "obtuse" -> OBTUSE;
            case "equilateral" -> EQUILATERAL;
            case "isosceles" -> ISOSCELES;
            default -> RIGHT;
        };
    }
}
