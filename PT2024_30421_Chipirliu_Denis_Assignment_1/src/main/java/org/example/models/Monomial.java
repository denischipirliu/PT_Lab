package org.example.models;

public class Monomial {
    private final int degree;
    private final Number coefficient;

    public Monomial(int degree, Number coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException("Degree cannot be negative");
        }
        this.degree = degree;
        this.coefficient = coefficient;
    }

    public int getDegree() {
        return degree;
    }

    public Number getCoefficient() {
        return coefficient;
    }

    public Monomial add(Monomial other) {
        return new Monomial(this.degree, this.coefficient.intValue() + other.coefficient.intValue());

    }

    public Monomial subtract(Monomial other) {
        return new Monomial(this.degree, this.coefficient.intValue() - other.coefficient.intValue());
    }

    public Monomial multiply(Monomial other) {
        return new Monomial(this.degree + other.degree, this.coefficient.floatValue() * other.coefficient.floatValue());
    }

    public Monomial divide(Monomial other) {
        return new Monomial(this.degree - other.degree, this.coefficient.floatValue() / other.coefficient.floatValue());
    }

    public Monomial derivative() {
        if (this.degree == 0) {
            return null;
        } else {
            return new Monomial(this.degree - 1, this.coefficient.intValue() * this.degree);
        }
    }

    public Monomial integrate() {
        return new Monomial(this.degree + 1, this.coefficient.floatValue() / (this.degree + 1));
    }
}
