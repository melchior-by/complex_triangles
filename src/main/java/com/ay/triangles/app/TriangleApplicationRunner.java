package com.ay.triangles.app;

import com.ay.triangles.entity.Triangle;
import com.ay.triangles.reader.ITriangleReader;
import com.ay.triangles.repository.TriangleRepository;
import com.ay.triangles.service.ITriangleSorter;
import com.ay.triangles.specification.TriangleSelector;
import com.ay.triangles.warehouse.TriangleData;
import com.ay.triangles.warehouse.Warehouse;

import java.util.List;

/**
 * Orchestrates the reading, processing, and output of triangle data.
 */
public class TriangleApplicationRunner {

    private final TriangleRepository repository = new TriangleRepository();
    private final Warehouse warehouse = Warehouse.getInstance();
    private final ITriangleReader reader;
    private final ITriangleSorter sorter;
    private final TriangleAsciiRenderer asciiRenderer = new TriangleAsciiRenderer();

    public TriangleApplicationRunner(ITriangleReader reader, ITriangleSorter sorter) {
        this.reader = reader;
        this.sorter = sorter;
    }

    /**
     * Runs the application workflow: reads, processes, and outputs triangle data.
     * @param filePath the path to the input file
     */
    public void run(String filePath, OutputMode outputMode, TriangleTypeFilter triangleTypeFilter,
                    VertexLabelsMode vertexLabelsMode) {
        readTriangles(filePath);
        printAllTriangles();
        if (outputMode == OutputMode.GRAPHICS) {
            printTriangleGraphics("\n🖼️ Triangle graphics:", repository.getAll(), vertexLabelsMode);
        }
        printWarehouseData();
        printTrianglesByTypeSortedByArea(triangleTypeFilter, outputMode, vertexLabelsMode);
    }

    private void readTriangles(String filePath) {
        System.out.println("📥 Reading triangles from file...");
        List<Triangle> triangles = reader.read(filePath);
        triangles.forEach(repository::add);
    }

    private void printAllTriangles() {
        printTriangles("\n✅ All valid triangles:", repository.getAll());
    }

    private void printWarehouseData() {
        System.out.println("\n🧠 Cached data from Warehouse:");
        for (Triangle t : repository.getAll()) {
            TriangleData data = warehouse.get(t.id());
            System.out.println(data.toFormattedString(t.id()));
        }
    }

    private void printTrianglesByTypeSortedByArea(TriangleTypeFilter triangleTypeFilter, OutputMode outputMode,
                                                  VertexLabelsMode vertexLabelsMode) {
        List<Triangle> filteredTriangles = TriangleSelector.select(repository.getAll(), triangleTypeFilter.toSpecification());
        String displayName = triangleTypeFilter.displayName();

        printTriangles(String.format("%n🔍 %s triangles:", capitalize(displayName)), filteredTriangles);
        if (outputMode == OutputMode.GRAPHICS && !filteredTriangles.isEmpty()) {
            printTriangleGraphics(String.format("%n🖼️ %s triangle graphics:", capitalize(displayName)),
                    filteredTriangles, vertexLabelsMode);
        }

        System.out.printf("%n📊 Sorting %s triangles by area:%n", displayName);
        sorter.sortByArea(filteredTriangles);
        filteredTriangles.forEach(t -> {
            TriangleData data = warehouse.get(t.id());
            System.out.println(data.toFormattedString(t.id()));
        });
    }

    private void printTriangles(String title, List<Triangle> triangles) {
        System.out.println(title);
        if (triangles.isEmpty()) {
            System.out.println("(none)");
            return;
        }
        triangles.forEach(t -> System.out.println("- " + t));
    }

    private void printTriangleGraphics(String title, List<Triangle> triangles, VertexLabelsMode vertexLabelsMode) {
        System.out.println(title);
        if (triangles.isEmpty()) {
            System.out.println("(none)");
            return;
        }

        for (Triangle triangle : triangles) {
            System.out.printf("Triangle %s%n", triangle.id());
            System.out.print(asciiRenderer.render(triangle, vertexLabelsMode.isEnabled()));
            System.out.println();
        }
    }

    private String capitalize(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }
}