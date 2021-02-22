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
//str.matches(Regex("""^-?\d+(\.\d+)?$""")) -> re = str.toDouble()
//str.matches(Regex("""^-?\d+(\.\d+)?i$""")) ->
//im = str.substring(0, str.indexOf('i')).toDouble()
//str.matches(Regex("""^-?\d+(\.\d+)?[+-]\d+(\.\d+)?i$""")) -> {
//    val spl = str.split(Regex("""[+-]"""))
//    val signRe = if (spl.size > 2) -1.0 else 1.0
//    val signIm = if (str.contains(Regex("\\+"))) 1.0 else -1.0
//    spl.filter { it.isNotEmpty() }
//    re = spl[0].toDouble()
//    im = spl[1].substring(0, spl[1].indexOf('i')).toDouble() * signIm
//}
//else -> throw IllegalArgumentException("Invalid format")