package com.ay.triangles.entity;

public final class Triangle {
    private final String id;
    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(String id, Point a, Point b, Point c) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String id() {
        return id;
    }

    public Point a() {
        return a;
    }

    public Point b() {
        return b;
    }

    public Point c() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle[" + id + "]: " + a + ", " + b + ", " + c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Triangle)) return false;
        Triangle other = (Triangle) obj;
        return id.equals(other.id) &&
                a.equals(other.a) &&
                b.equals(other.b) &&
                c.equals(other.c);
    }

    @Override
    public int hashCode() {
        int result = 23;
        result = 31 * result + id.hashCode();
        result = 31 * result + a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        return result;
    }
}