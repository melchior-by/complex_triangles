package com.ay.triangles.specification;

import com.ay.triangles.entity.Triangle;
import java.util.Objects;

/**
 * Specification interface for filtering and classifying triangles.
 * Supports composition via and, or, and not combinators.
 */
public interface TriangleSpecification {
    /**
     * Checks if the triangle satisfies this specification.
     * @param triangle the triangle to check
     * @return true if satisfied, false otherwise
     */
    boolean isSatisfiedBy(Triangle triangle);

    /**
     * Returns a specification that is the logical AND of this and another specification.
     * @param other the other specification
     * @return combined AND specification
     */
    default TriangleSpecification and(TriangleSpecification other) {
        Objects.requireNonNull(other);
        return triangle -> this.isSatisfiedBy(triangle) && other.isSatisfiedBy(triangle);
    }

    /**
     * Returns a specification that is the logical OR of this and another specification.
     * @param other the other specification
     * @return combined OR specification
     */
    default TriangleSpecification or(TriangleSpecification other) {
        Objects.requireNonNull(other);
        return triangle -> this.isSatisfiedBy(triangle) || other.isSatisfiedBy(triangle);
    }

    /**
     * Returns a specification that is the logical NOT of this specification.
     * @return negated specification
     */
    default TriangleSpecification not() {
        return triangle -> !this.isSatisfiedBy(triangle);
    }
}