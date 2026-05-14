package com.ay.triangles.service;

import com.ay.triangles.entity.Point;
import com.ay.triangles.entity.Triangle;
import com.ay.triangles.warehouse.TriangleData;
import com.ay.triangles.warehouse.Warehouse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TriangleSorterTest {

    private static final String ID1 = "T1";
    private static final String ID2 = "T2";
    private static final double AREA_SMALL = 0.5;
    private static final double AREA_LARGE = 2.0;

    private static Triangle smallAreaTriangle;
    private static Triangle largeAreaTriangle;
    private static List<Triangle> triangleList;
    private static ITriangleSorter sorter;

    @BeforeClass
    public void setUp() {
        smallAreaTriangle = new Triangle(ID1,
                new Point(0.0, 0.0),
                new Point(1.0, 0.0),
                new Point(0.0, 1.0)); // area = 0.5

        largeAreaTriangle = new Triangle(ID2,
                new Point(0.0, 0.0),
                new Point(2.0, 0.0),
                new Point(0.0, 2.0)); // area = 2.0

        triangleList = new ArrayList<>();
        triangleList.add(largeAreaTriangle);  // Intentionally unsorted
        triangleList.add(smallAreaTriangle);

        Warehouse warehouse = Warehouse.getInstance();
        warehouse.onTriangleChanged(smallAreaTriangle);
        warehouse.onTriangleChanged(largeAreaTriangle);

        sorter = new TriangleSorter();
    }

    @Test(description = "Should sort triangles in ascending order by area using Warehouse data")
    public void testSortByArea() {
        sorter.sortByArea(triangleList);

        assertEquals(triangleList.get(0).id(), ID1); // smaller area first
        assertEquals(triangleList.get(1).id(), ID2); // larger area second

        TriangleData first = Warehouse.getInstance().get(ID1);
        TriangleData second = Warehouse.getInstance().get(ID2);

        assertEquals(first.getArea(), AREA_SMALL, 1e-6);
        assertEquals(second.getArea(), AREA_LARGE, 1e-6);
        assertEquals(first.getArea() < second.getArea(), true);
    }
}