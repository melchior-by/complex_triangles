package com.ay.triangles.warehouse;

import com.ay.triangles.entity.Point;
import com.ay.triangles.entity.Triangle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WarehouseTest {

    private static final String TRIANGLE_ID = "T1";
    private static final Triangle UNIT_RIGHT_TRIANGLE = new Triangle(
            TRIANGLE_ID,
            new Point(0.0, 0.0),
            new Point(1.0, 0.0),
            new Point(0.0, 1.0)
    );
    private static final double UNIT_AREA = 0.5;
    private static final double UNIT_PERIMETER = 1.0 + 1.0 + Math.sqrt(2);
    private static final double DELTA = 1e-6;

    @BeforeMethod
    public void clearWarehouse() {
        Warehouse.getInstance().clear();
    }

    @Test(description = "Should store a triangle and retrieve correct cached area and perimeter")
    public void testStoreAndRetrieve() {
        Warehouse warehouse = Warehouse.getInstance();

        warehouse.store(UNIT_RIGHT_TRIANGLE);

        TriangleData data = warehouse.get(TRIANGLE_ID);
        assertEquals(data.getArea(), UNIT_AREA, DELTA);
        assertEquals(data.getPerimeter(), UNIT_PERIMETER, DELTA);
    }

    @Test(description = "Should recalculate area and perimeter when triangle is changed")
    public void testOnTriangleChanged() {
        Triangle rightTriangle = new Triangle(
                TRIANGLE_ID,
                new Point(0.0, 0.0),
                new Point(3.0, 0.0),
                new Point(0.0, 4.0)
        );
        double expectedArea = 6.0;
        double expectedPerimeter = 3.0 + 4.0 + 5.0;

        Warehouse warehouse = Warehouse.getInstance();

        warehouse.onTriangleChanged(rightTriangle);

        TriangleData data = warehouse.get(TRIANGLE_ID);
        assertEquals(data.getArea(), expectedArea, DELTA);
        assertEquals(data.getPerimeter(), expectedPerimeter, DELTA);
    }

    @Test(description = "Should return true after triangle is stored")
    public void testContains() {
        Warehouse warehouse = Warehouse.getInstance();

        warehouse.store(UNIT_RIGHT_TRIANGLE);

        assertTrue(warehouse.contains(TRIANGLE_ID));
    }
}