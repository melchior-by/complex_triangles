package com.ay.triangles.service;

import com.ay.triangles.entity.Point;
import com.ay.triangles.entity.Triangle;

import static com.ay.triangles.util.MathConstants.EPSILON;

public final class TriangleService {

    private TriangleService() {}

    public static double perimeter(Triangle t) {
        return sideLength(t.a(), t.b()) +
                sideLength(t.b(), t.c()) +
                sideLength(t.c(), t.a());
    }

    public static double area(Triangle t) {
        double ab = sideLength(t.a(), t.b());
        double bc = sideLength(t.b(), t.c());
        double ca = sideLength(t.c(), t.a());
        double s = (ab + bc + ca) / 2.0;
        return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca)); // Формула Герона
    }

    public static boolean isEquilateral(Triangle t) {
        double ab = sideLength(t.a(), t.b());
        double bc = sideLength(t.b(), t.c());
        double ca = sideLength(t.c(), t.a());
        return equal(ab, bc) && equal(bc, ca);
    }

    public static boolean isIsosceles(Triangle t) {
        double ab = sideLength(t.a(), t.b());
        double bc = sideLength(t.b(), t.c());
        double ca = sideLength(t.c(), t.a());
        return equal(ab, bc) || equal(bc, ca) || equal(ca, ab);
    }

    public static boolean isRight(Triangle t) {
        double a2 = square(sideLength(t.a(), t.b()));
        double b2 = square(sideLength(t.b(), t.c()));
        double c2 = square(sideLength(t.c(), t.a()));
        return equal(a2 + b2, c2) ||
                equal(b2 + c2, a2) ||
                equal(c2 + a2, b2);
    }

    public static boolean isAcute(Triangle t) {
        double[] sides = sortedSquaredSides(t);
        return sides[0] + sides[1] > sides[2];
    }

    public static boolean isObtuse(Triangle t) {
        double[] sides = sortedSquaredSides(t);
        return sides[0] + sides[1] < sides[2];
    }

    // ========= PRIVATE HELPERS ==========

    private static double sideLength(Point p1, Point p2) {
        double dx = p1.x() - p2.x();
        double dy = p1.y() - p2.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static boolean equal(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    private static double square(double v) {
        return v * v;
    }

    private static double[] sortedSquaredSides(Triangle t) {
        double ab = square(sideLength(t.a(), t.b()));
        double bc = square(sideLength(t.b(), t.c()));
        double ca = square(sideLength(t.c(), t.a()));
        double[] values = { ab, bc, ca };
        java.util.Arrays.sort(values);
        return values;
    }
}