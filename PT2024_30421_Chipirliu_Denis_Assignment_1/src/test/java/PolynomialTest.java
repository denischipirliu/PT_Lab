
import org.example.models.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {
    @ParameterizedTest
    @CsvSource({
            "2x^2+3x-1,x^3+2x-5,x^3+2x^2+5x-6",
            "3x^2+2x-1,2x^2+3x-1,5x^2+5x-2",
            "2x^2+3x-1,-2x^2-3x+1,0"
    })
    void testAddPolynomial(String p1, String p2, String expected) {
        Polynomial polynomial1 = new Polynomial(p1);
        Polynomial polynomial2 = new Polynomial(p2);
        Polynomial result = polynomial1.addPolynomial(polynomial2);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "2x^2+3x-1,x^3+2x-5,-x^3+2x^2+x+4",
            "3x^2+2x-1,2x^2+3x-1,x^2-x",
            "2x^2+3x-1,-2x^2-3x+1,4x^2+6x-2"
    })
    void testSubtractPolynomial(String p1, String p2, String expected) {
        Polynomial polynomial1 = new Polynomial(p1);
        Polynomial polynomial2 = new Polynomial(p2);
        Polynomial result = polynomial1.subtractPolynomial(polynomial2);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "2x^2+3x-1,x^3+2x-5,2.0x^5+3.0x^4+4.0x^3-10.0x^2-15.0x+5.0",
            "3x^2+2x-1,2x^2+3x-1,6.0x^4+9.0x^3-3.0x^2-2.0x+1.0",
            "2x^2+3x-1,-2x^2-3x+1,-4.0x^4-6.0x^3+2.0x^2+3.0x-1.0"
    })
    void testMultiplyPolynomial(String p1, String p2, String expected) {
        Polynomial polynomial1 = new Polynomial(p1);
        Polynomial polynomial2 = new Polynomial(p2);
        Polynomial result = polynomial1.multiplyPolynomial(polynomial2);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "2x^2+3x-1,x^3+2x-5,0",
            "3x^2+2x-1,2x^2+3x-1,1.5",
            "2x^2+3x-1,-2x^2-3x+1,-1.0"
    })
    void testDividePolynomial(String p1, String p2, String expected) throws Exception {
        Polynomial polynomial1 = new Polynomial(p1);
        Polynomial polynomial2 = new Polynomial(p2);
        Polynomial result = polynomial1.dividePolynomial(polynomial2);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "2x^2+3x-1,4x+3",
            "3x^2+2x-1,6x+2",
            "2x^2+3x-1,4x+3"
    })
    void testDerivativePolynomial(String p1, String expected) {
        Polynomial polynomial1 = new Polynomial(p1);
        Polynomial result = polynomial1.derivativePolynomial();
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "2x^2+3x-1,0.6666667x^3+1.5x^2-x",
            "3x^2+2x-1,x^3+x^2-x",
            "2x^2+3x-1,0.6666667x^3+1.5x^2-x"
    })
    void testIntegratePolynomial(String p1, String expected) {
        Polynomial polynomial1 = new Polynomial(p1);
        Polynomial result = polynomial1.integratePolynomial();
        assertEquals(expected, result.toString());
    }
}
