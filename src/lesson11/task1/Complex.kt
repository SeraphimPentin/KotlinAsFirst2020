@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.lang.IllegalArgumentException
import kotlin.math.abs

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)


    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-im, -re)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(re * other.re - im * other.im, re * other.im + other.re * im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (other.re * other.re + other.im * other.im),
        (im * other.re - re * other.im) / (other.re * other.re + other.im * other.im)
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean =
        other is Complex && re == other.re && im == other.im

    /**
     * Преобразование в строку
     */
    override fun toString(): String = when {
        im < 0 -> "$re${im}i"
        im > 0 -> "$re+${im}i"
        else -> "$re"
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}

/**
 *  Функция, заменяющая конструктор из строки вида x+yi
 */

fun Complex(s: String): Complex {
    var re = 0.0
    var im = 0.0
    val pattern = Regex("""^(-?\d+(\.\d+)?)([+-]\d+(\.\d+)?)i$""")
    when {
        s.matches(Regex("""^-?\d+(\.\d+)?$""")) -> re = s.toDouble()
        s.matches(Regex("""^-?\d+(\.\d+)?i$""")) ->
            im = s.substring(0, s.lastIndex).toDouble()
        s.matches(pattern) -> {
            re = pattern.matchEntire(s)?.groups?.get(1)?.value?.toDouble() ?: 0.0
            im = pattern.matchEntire(s)?.groups?.get(3)?.value?.toDouble() ?: 0.0
        }
        else -> throw IllegalArgumentException("Invalid format")
    }
    return Complex(re, im)
}