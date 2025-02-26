package org.example.models;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private final TreeMap<Integer, Monomial> monomials;

    public Polynomial() {
        this.monomials = new TreeMap<>(Comparator.reverseOrder());
    }

    public Polynomial(String polynomial) {
        this();
        parsePolynomial(polynomial);
    }

    public void addMonomial(Monomial monomial) {
        monomials.put(monomial.getDegree(), monomial);
    }

    public Polynomial addPolynomial(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            result.addMonomial(monomial);
        }
        for (Map.Entry<Integer, Monomial> entry : polynomial.monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            if (result.monomials.containsKey(degree)) {
                result.addMonomial(result.monomials.get(degree).add(monomial));
            } else {
                result.addMonomial(monomial);
            }
        }
        return result;
    }

    public Polynomial subtractPolynomial(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            result.addMonomial(monomial);
        }
        for (Map.Entry<Integer, Monomial> entry : polynomial.monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            if (result.monomials.containsKey(degree)) {
                result.addMonomial(result.monomials.get(degree).subtract(monomial));
            } else {
                result.addMonomial(new Monomial(degree, -monomial.getCoefficient().intValue()));
            }
        }
        return result;
    }

    public Polynomial multiplyPolynomial(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> p : monomials.entrySet()) {
            Monomial multiplier = p.getValue();
            for (Map.Entry<Integer, Monomial> q : polynomial.monomials.entrySet()) {
                Monomial newMonomial = multiplier.multiply(q.getValue());
                if (result.monomials.containsKey(newMonomial.getDegree())) {
                    result.monomials.get(newMonomial.getDegree()).add(newMonomial);
                } else {
                    result.addMonomial(newMonomial);
                }
            }
        }
        return result;
    }

    public Polynomial dividePolynomial(Polynomial polynomial) throws Exception{
        if(polynomial.monomials.isEmpty() || polynomial.monomials.firstEntry().getValue().getCoefficient().floatValue() == 0)
            throw new Exception();
        Polynomial result = new Polynomial();
        Polynomial remainder = this;
        while (!remainder.monomials.isEmpty() && remainder.monomials.firstKey() >= polynomial.monomials.firstKey()){
            Monomial monomial = remainder.monomials.firstEntry().getValue().divide(polynomial.monomials.firstEntry().getValue());
            result.addMonomial(monomial);
            Polynomial temp = new Polynomial();
            temp.addMonomial(monomial);
            Polynomial product = polynomial.multiplyPolynomial(temp);
            remainder = remainder.subtractPolynomial(product);
            remainder.cleanUp();
        }
        if (result.monomials.isEmpty())
            result.addMonomial(new Monomial(0, 0));
        return result;
    }

    public Polynomial derivativePolynomial() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            Monomial derivative = entry.getValue().derivative();
            if (derivative != null)
                result.addMonomial(derivative);
        }
        return result;
    }

    public Polynomial integratePolynomial() {
        Polynomial result = new Polynomial();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            Monomial integrate = entry.getValue().integrate();
            result.addMonomial(integrate);
        }
        return result;
    }

    private void cleanUp() {
        monomials.entrySet().removeIf(entry -> entry.getValue().getCoefficient().floatValue() == 0);
    }


    public void parsePolynomial(String polynomial) {
        Pattern pattern = Pattern.compile("([+-]?\\d*)x\\^?(\\d*)|([+-]?\\d+)|([+-]?x\\^?(\\d*))");
        Matcher matcher = pattern.matcher(polynomial);

        while (matcher.find()) {
            String coefficientString = matcher.group(1);
            String degreeString = matcher.group(2);
            String constantString = matcher.group(3);

            int coefficient;
            if (coefficientString != null && !coefficientString.isEmpty()) {
                if (coefficientString.equals("+")) {
                    coefficient = 1;
                } else if (coefficientString.equals("-")) {
                    coefficient = -1;
                } else {
                    coefficient = Integer.parseInt(coefficientString);
                }
            } else if (constantString != null && !constantString.isEmpty()) {
                coefficient = Integer.parseInt(constantString);
            } else {
                coefficient = 1;
            }

            int degree;
            if (degreeString != null && !degreeString.isEmpty()) {
                degree = Integer.parseInt(degreeString);
            } else if (constantString != null && !constantString.isEmpty()) {
                degree = 0;
            } else {
                degree = 1;
            }

            Monomial monomial = new Monomial(degree, coefficient);
            addMonomial(monomial);
        }
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            boolean first = entry.getKey().equals(monomials.firstKey());
            Monomial monomial = entry.getValue();
            if (!first && monomial.getCoefficient().floatValue() > 0) {
                stringBuilder.append("+");
            } else if (monomial.getCoefficient().floatValue() == 0) {
                continue;
            }
            if (monomial.getDegree() > 0) {
                if (monomial.getCoefficient().floatValue() == -1)
                    stringBuilder.append("-");
                else if (monomial.getCoefficient().floatValue() != 1)
                    stringBuilder.append(monomial.getCoefficient().toString());
                stringBuilder.append("x");
                if (monomial.getDegree() > 1) {
                    stringBuilder.append("^").append(monomial.getDegree());
                }
            } else if (monomial.getDegree() == 0) {
                stringBuilder.append(monomial.getCoefficient().toString());
            }

        }
        if (stringBuilder.isEmpty())
            stringBuilder.append("0");
        return stringBuilder.toString();
    }

}
