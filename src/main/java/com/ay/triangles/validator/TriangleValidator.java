package com.ay.triangles.validator;

import com.ay.triangles.entity.Point;

public final class TriangleValidator {

    private TriangleValidator() {}

    public static boolean isValidTriangle(Point a, Point b, Point c) {
        return !areCollinear(a, b, c) && satisfiesInequality(a, b, c);
    }

    private static boolean areCollinear(Point a, Point b, Point c) {
        double area = Math.abs(
                (a.x() * (b.y() - c.y())) +
                        (b.x() * (c.y() - a.y())) +
                        (c.x() * (a.y() - b.y()))
        ) / 2.0;
        return area == 0.0;
    }

    private static boolean satisfiesInequality(Point a, Point b, Point c) {
        double ab = distance(a, b);
        double bc = distance(b, c);
        double ca = distance(c, a);
        return ab + bc > ca && ab + ca > bc && bc + ca > ab;
    }

    private static double distance(Point p1, Point p2) {
        double dx = p1.x() - p2.x();
        double dy = p1.y() - p2.y();
        return Math.sqrt(dx * dx + dy * dy);
    }
}