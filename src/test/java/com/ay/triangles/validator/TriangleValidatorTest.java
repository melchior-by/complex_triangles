package com.ay.triangles.validator;

import com.ay.triangles.entity.Point;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TriangleValidatorTest {

    @Test(description = "Validates a classic right-angled triangle with legs of length 1")
    public void testValidTriangle() {
        Point a = new Point(0.0, 0.0);
        Point b = new Point(1.0, 0.0);
        Point c = new Point(0.0, 1.0);
        assertTrue(TriangleValidator.isValidTriangle(a, b, c));
    }

    @Test(description = "Detects invalid triangle formed by three collinear points")
    public void testCollinearPoints() {
        Point a = new Point(0.0, 0.0);
        Point b = new Point(1.0, 1.0);
        Point c = new Point(2.0, 2.0);
        assertFalse(TriangleValidator.isValidTriangle(a, b, c));
    }

    @Test(description = "Detects invalid triangle violating triangle inequality rule (a + b <= c)")
    public void testViolatesInequality() {
        Point a = new Point(0.0, 0.0);
        Point b = new Point(10.0, 0.0);
        Point c = new Point(20.0, 0.0);
        assertFalse(TriangleValidator.isValidTriangle(a, b, c));
    }
}