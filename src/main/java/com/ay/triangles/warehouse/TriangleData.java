package com.ay.triangles.warehouse;

public final class TriangleData {
    private final double area;
    private final double perimeter;

    public TriangleData(double area, double perimeter) {
        this.area = area;
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public String toFormattedString(String triangleId) {
        return String.format("%s | area = %.2f | perimeter = %.2f", triangleId, area, perimeter);
    }

    @Override
    public String toString() {
        return "area=" + area + ", perimeter=" + perimeter;
    }
}