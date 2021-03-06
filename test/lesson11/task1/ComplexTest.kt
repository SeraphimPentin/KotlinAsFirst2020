package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag

internal class ComplexTest {

    private fun assertApproxEquals(expected: Complex, actual: Complex, eps: Double) {
        assertEquals(expected.re, actual.re, eps)
        assertEquals(expected.im, actual.im, eps)
    }

    @Test
    @Tag("2")
    fun plus() {
        assertApproxEquals(Complex("4-2i"), Complex("1+2i") + Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("3-2i"), Complex("2+5i") + Complex("1-7i"), 1e-10)
    }

    @Test
    @Tag("2")
    operator fun unaryMinus() {
        assertApproxEquals(Complex(1.0, -2.0), -Complex(2.0, -1.0), 1e-10)
        assertApproxEquals(Complex(5.0, 3.0), -Complex(-3.0, -5.0), 1e-10)
    }

    @Test
    @Tag("2")
    fun minus() {
        assertApproxEquals(Complex("1+12i"), Complex("2+5i") - Complex("1-7i"), 1e-10)
    }

    @Test
    @Tag("4")
    fun times() {
        assertApproxEquals(Complex("9+7i"), Complex("2+3i") * Complex("3-1i"), 1e-10)
        assertApproxEquals(Complex("37-9i"), Complex("2+5i") * Complex("1-7i"), 1e-10)

    }

    @Test
    @Tag("4")
    fun div() {
        assertApproxEquals(Complex("0.8+1.4i"), Complex("3+2i") / Complex("2-1i"), 1e-10)
        assertApproxEquals(Complex("-0.66+0.38i"), Complex("2+5i") / Complex("1-7i"), 1e-10)
    }

    @Test
    @Tag("2")
    fun equals() {
        assertApproxEquals(Complex(1.0, 2.0), Complex("1+2i"), 1e-12)
        assertApproxEquals(Complex(1.0, 0.0), Complex(1.0), 1e-12)
    }
}