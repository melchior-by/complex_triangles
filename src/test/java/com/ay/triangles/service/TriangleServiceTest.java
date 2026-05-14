package com.ay.triangles.service;

import com.ay.triangles.entity.Point;
import com.ay.triangles.entity.Triangle;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.ay.triangles.util.MathConstants.EPSILON;

public class TriangleServiceTest {

    private static final double LENGTH_LEG = 1.0;
    private static final double LENGTH_HYPOTENUSE = Math.sqrt(2.0);
    private static final double PERIMETER_OF_RIGHT_TRIANGLE = 2 * LENGTH_LEG + LENGTH_HYPOTENUSE;
    private static final double AREA_OF_RIGHT_TRIANGLE = 0.5;

    private static final double LENGTH_EQUILATERAL = 1.0;
    private static final double HEIGHT_EQUILATERAL = Math.sqrt(3.0) / 2;
    private static final double AREA_EQUILATERAL = Math.sqrt(3.0) / 4;

    private static Triangle createRightTriangle() {
        return new Triangle("T1",
                new Point(0.0, 0.0),
                new Point(LENGTH_LEG, 0.0),
                new Point(0.0, LENGTH_LEG));
    }

    private static Triangle createEquilateralTriangle() {
        return new Triangle("T2",
                new Point(0.0, 0.0),
                new Point(LENGTH_EQUILATERAL, 0.0),
                new Point(LENGTH_EQUILATERAL / 2, HEIGHT_EQUILATERAL));
    }

    private static Triangle createIsoscelesTriangle() {
        return new Triangle("T3",
                new Point(0.0, 0.0),
                new Point(2.0, 0.0),
                new Point(1.0, 1.0));
    }

    private static Triangle createObtuseTriangle() {
        return new Triangle("T4",
                new Point(0.0, 0.0),
                new Point(4.0, 0.0),
                new Point(2.0, 1.0));
    }

    @Test(description = "Should correctly calculate perimeter of a right triangle")
    public void testPerimeterOfRightTriangle() {
        Triangle triangle = createRightTriangle();
        double perimeter = TriangleService.perimeter(triangle);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(perimeter, PERIMETER_OF_RIGHT_TRIANGLE, EPSILON, "Right triangle perimeter mismatch");
        soft.assertTrue(perimeter > 0, "Perimeter must be positive");
        soft.assertAll();
    }

    @Test(description = "Should correctly calculate area of a right triangle")
    public void testAreaOfRightTriangle() {
        Triangle triangle = createRightTriangle();
        double area = TriangleService.area(triangle);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(area, AREA_OF_RIGHT_TRIANGLE, EPSILON, "Right triangle area mismatch");
        soft.assertTrue(area > 0, "Area must be positive");
        soft.assertAll();
    }

    @Test(description = "Should correctly identify a right triangle and reject others")
    public void testIsRightTriangle() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(TriangleService.isRight(createRightTriangle()), "Expected right triangle");
        soft.assertFalse(TriangleService.isRight(createEquilateralTriangle()), "Equilateral should not be right");
        soft.assertAll();
    }

    @Test(description = "Should correctly identify an equilateral triangle and reject others")
    public void testIsEquilateralTriangle() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(TriangleService.isEquilateral(createEquilateralTriangle()), "Expected equilateral triangle");
        soft.assertFalse(TriangleService.isEquilateral(createRightTriangle()), "Right triangle should not be equilateral");
        soft.assertAll();
    }

    @Test(description = "Should correctly identify an acute triangle and reject others")
    public void testIsAcuteTriangle() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(TriangleService.isAcute(createEquilateralTriangle()), "Equilateral triangle should be acute");
        soft.assertFalse(TriangleService.isAcute(createObtuseTriangle()), "Obtuse triangle should not be acute");
        soft.assertAll();
    }

    @Test(description = "Should correctly identify an obtuse triangle and reject others")
    public void testIsObtuseTriangle() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(TriangleService.isObtuse(createObtuseTriangle()), "Expected obtuse triangle");
        soft.assertFalse(TriangleService.isObtuse(createEquilateralTriangle()), "Equilateral should not be obtuse");
        soft.assertAll();
    }

    @Test(description = "Should correctly identify an isosceles triangle and reject scalene")
    public void testIsIsoscelesTriangle() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(TriangleService.isIsosceles(createIsoscelesTriangle()), "Expected isosceles triangle");

        Triangle scalene = new Triangle("T5",
                new Point(0.0, 0.0),
                new Point(3.0, 0.0),
                new Point(1.0, 2.0));
        soft.assertFalse(TriangleService.isIsosceles(scalene), "Scalene triangle should not be isosceles");

        soft.assertAll();
    }
}