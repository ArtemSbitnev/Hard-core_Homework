package mycalc;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 24.11.13
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
public class MyCalculatorTest {
    @Test
    public void testPlus() {
        double result = MyCalculator.eval("3+5");
        assertEquals(result, 8.0, 0.01);
    }

    @Test
    public void testMinus() {
        double result = MyCalculator.eval("-3-5");
        assertEquals(result, -8.0, 0.01);
    }

    @Test
    public void testMultiply() {
        double result = MyCalculator.eval("-3*(-5)");
        assertEquals(result, 15.0, 0.01);
    }

    @Test
    public void testDivide() {
        double result = MyCalculator.eval("2/4");
        assertEquals(result, 0.5, 0.01);
    }

    @Test
    public void testDouble() {
        double result = MyCalculator.eval("1.3+2.4-1.5*3");
        assertEquals(result, -0.8, 0.01);
    }

    @Test
    public void testBrackets() {
        double result = MyCalculator.eval("5*(-3+4)+2.5");
        assertEquals(result, 7.5, 0.01);
    }

    @Test
    public void testDoubleBrackets() {
        double result = MyCalculator.eval("5*((-3+4)+2.5)");
        assertEquals(result, 17.5, 0.01);
    }

    @Test
    public void testDegree() {
        double result = MyCalculator.eval("3+1.1^2");
        assertEquals(result, 4.21, 0.01);
    }

    @Test
    public void testDegreeWithBrackets() {
        double result = MyCalculator.eval("(3+1.1)^2");
        assertEquals(result, 16.81, 0.01);
    }

    @Test
    public void testComplexProblem() {
        double result = MyCalculator.eval("2^3*((3+1.1)^2-15)-2*3");
        assertEquals(result, 8.48, 0.01);
    }

    @Test
    public void testErrorBrackets() {
        MyCalculator.eval("5+3)");
        String result = MyCalculator.getError();
        assertEquals(result, "no opening bracket");
    }

    @Test
    public void testErrorOperators() {
        MyCalculator.eval("5*+3");
        String result = MyCalculator.getError();
        assertEquals(result, "two operators");
    }

    @Test
    public void testErrorPoints() {
        MyCalculator.eval("5.38.5+4.3");
        String result = MyCalculator.getError();
        assertEquals(result, "can not been two points in one digit");
    }

    @Test
    public void testFuncMin() {
        double result = MyCalculator.eval("min(2.1,1,5.3)");
        assertEquals(result, 1, 0.01);
    }

    @Test
    public void testFuncMax() {
        double result = MyCalculator.eval("max(2.1,1,5.3)");
        assertEquals(result, 5.3, 0.01);
    }

    @Test
    public void testFuncSum() {
        double result = MyCalculator.eval("sum(2.1,1,5.3)");
        assertEquals(result, 8.4, 0.01);
    }

    @Test
    public void testFuncSqrt() {
        double result = MyCalculator.eval("sqrt(1.21)");
        assertEquals(result, 1.1, 0.01);
    }

    @Test
    public void testErrorInFunc() {
        MyCalculator.eval("sum(2.1,1,,5.3)");
        String result = MyCalculator.getError();
        assertEquals(result, "no number");
    }

    @Test
    public void testErrorInFunc2() {
        MyCalculator.eval("min(2.1*,6)");
        String result = MyCalculator.getError();
        assertEquals(result, "error in function");
    }

    @Test
    public void testComplexFunc() {
        double result = MyCalculator.eval("2*(sqrt(1.21)+max(1,2,3)^2)-min(4,3,2,1)");
        assertEquals(result, 19.2, 0.01);
    }
}
