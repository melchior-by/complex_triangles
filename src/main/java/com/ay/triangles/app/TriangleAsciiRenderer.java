package com.ay.triangles.app;

import com.ay.triangles.entity.Point;
import com.ay.triangles.entity.Triangle;

public final class TriangleAsciiRenderer {
    private static final int WIDTH = 33;
    private static final int HEIGHT = 15;
    private static final char EMPTY = ' ';
    private static final char EDGE = '*';

    public String render(Triangle triangle, boolean showLabels) {
        char[][] grid = new char[HEIGHT][WIDTH];
        fill(grid, EMPTY);

        Point[] points = new Point[]{triangle.a(), triangle.b(), triangle.c()};
        double minX = Math.min(points[0].x(), Math.min(points[1].x(), points[2].x()));
        double maxX = Math.max(points[0].x(), Math.max(points[1].x(), points[2].x()));
        double minY = Math.min(points[0].y(), Math.min(points[1].y(), points[2].y()));
        double maxY = Math.max(points[0].y(), Math.max(points[1].y(), points[2].y()));

        int[] a = mapToGrid(points[0], minX, maxX, minY, maxY);
        int[] b = mapToGrid(points[1], minX, maxX, minY, maxY);
        int[] c = mapToGrid(points[2], minX, maxX, minY, maxY);

        drawLine(grid, a[0], a[1], b[0], b[1]);
        drawLine(grid, b[0], b[1], c[0], c[1]);
        drawLine(grid, c[0], c[1], a[0], a[1]);

        if (showLabels) {
            plot(grid, a[0], a[1], 'A');
            plot(grid, b[0], b[1], 'B');
            plot(grid, c[0], c[1], 'C');
        }

        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < HEIGHT; row++) {
            builder.append('|');
            builder.append(grid[row]);
            builder.append('|');
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private void fill(char[][] grid, char value) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] = value;
            }
        }
    }

    private int[] mapToGrid(Point p, double minX, double maxX, double minY, double maxY) {
        double xRange = maxX - minX;
        double yRange = maxY - minY;

        int col = xRange == 0
                ? WIDTH / 2
                : (int) Math.round(((p.x() - minX) / xRange) * (WIDTH - 1));
        int row = yRange == 0
                ? HEIGHT / 2
                : (int) Math.round(((p.y() - minY) / yRange) * (HEIGHT - 1));

        return new int[]{clamp(col, 0, WIDTH - 1), clamp((HEIGHT - 1) - row, 0, HEIGHT - 1)};
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private void drawLine(char[][] grid, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        int x = x1;
        int y = y1;
        while (true) {
            plot(grid, x, y, EDGE);
            if (x == x2 && y == y2) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }

    private void plot(char[][] grid, int x, int y, char value) {
        if (y >= 0 && y < HEIGHT && x >= 0 && x < WIDTH) {
            grid[y][x] = value;
        }
    }
}
