@file:Suppress("UNUSED_PARAMETER")

package lesson8.task2

import java.util.*
import kotlin.IllegalArgumentException
import kotlin.math.abs

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
data class Square(val column: Int, val row: Int) {
    var d = -1

    /**
     * Пример
     *
     * Возвращает true, если клетка находится в пределах доски
     */
    fun inside(): Boolean = column in 1..8 && row in 1..8

    /**
     * Простая (2 балла)
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String =
        if (inside()) arrayOf("a", "b", "c", "d", "e", "f", "g", "h")[column - 1] + row.toString() else ""
}

/**
 * Простая (2 балла)
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square {
    if (notation.length != 2) throw java.lang.IllegalArgumentException("Invalid notation")
    try {
        val square = Square(notation[0].toInt() - 96, notation[1].toString().toInt())
        if (!square.inside()) throw IllegalArgumentException("Invalid notation")
        return square
    } catch (e: Exception) {
        throw IllegalArgumentException("Invalid notation")
    }
}


/**
 * Простая (2 балла)
 *
 * Определить число ходов, за которое шахматная ладья пройдёт из клетки start в клетку end.
 * Шахматная ладья может за один ход переместиться на любую другую клетку
 * по вертикали или горизонтали.
 * Ниже точками выделены возможные ходы ладьи, а крестиками -- невозможные:
 *
 * xx.xxххх
 * xх.хxххх
 * ..Л.....
 * xх.хxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: rookMoveNumber(Square(3, 1), Square(6, 3)) = 2
 * Ладья может пройти через клетку (3, 3) или через клетку (6, 1) к клетке (6, 3).
 */
fun rookMoveNumber(start: Square, end: Square): Int {
    if (start.inside() && end.inside()) {
        if (start == end) return 0
        return if (start.column == end.column || start.row == end.row) 1 else 2
    } else {
        throw IllegalArgumentException("Wrong!!!")
    }
}

/**
 * Средняя (3 балла)
 *
 * Вернуть список из клеток, по которым шахматная ладья может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов ладьи см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: rookTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможен ещё один вариант)
 *          rookTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(3, 3), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          rookTrajectory(Square(3, 5), Square(8, 5)) = listOf(Square(3, 5), Square(8, 5))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun rookTrajectory(start: Square, end: Square): List<Square> {
    return when (rookMoveNumber(start, end)) {
        0 -> listOf(start)
        1 -> listOf(start, end)
        else -> listOf(start, Square(start.column, end.row), end)
    }
}

/**
 * Простая (2 балла)
 *
 * Определить число ходов, за которое шахматный слон пройдёт из клетки start в клетку end.
 * Шахматный слон может за один ход переместиться на любую другую клетку по диагонали.
 * Ниже точками выделены возможные ходы слона, а крестиками -- невозможные:
 *
 * .xxx.ххх
 * x.x.xххх
 * xxСxxxxx
 * x.x.xххх
 * .xxx.ххх
 * xxxxx.хх
 * xxxxxх.х
 * xxxxxхх.
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если клетка end недостижима для слона, вернуть -1.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Примеры: bishopMoveNumber(Square(3, 1), Square(6, 3)) = -1; bishopMoveNumber(Square(3, 1), Square(3, 7)) = 2.
 * Слон может пройти через клетку (6, 4) к клетке (3, 7).
 */
fun bishopMoveNumber(start: Square, end: Square): Int {
    if (start.inside() && end.inside()) {
        return if (start == end) 0
        else {
            if (abs(start.column - end.column) == abs(start.row - end.row)) 1
            else if ((start.column % 2 != end.column % 2 && start.row % 2 != end.row % 2) ||
                (start.column % 2 == end.column % 2 && start.row % 2 == end.row % 2)
            ) 2
            else -1
        }
    } else {
        throw IllegalArgumentException()
    }
}


/**
 * Сложная (5 баллов)
 *
 * Вернуть список из клеток, по которым шахматный слон может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов слона см. предыдущую задачу.
 *
 * Если клетка end недостижима для слона, вернуть пустой список.
 *
 * Если клетка достижима:
 * - список всегда включает в себя клетку start
 * - клетка end включается, если она не совпадает со start.
 * - между ними должны находиться промежуточные клетки, по порядку от start до end.
 *
 * Примеры: bishopTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          bishopTrajectory(Square(3, 1), Square(3, 7)) = listOf(Square(3, 1), Square(6, 4), Square(3, 7))
 *          bishopTrajectory(Square(1, 3), Square(6, 8)) = listOf(Square(1, 3), Square(6, 8))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun bishopTrajectory(start: Square, end: Square): List<Square> {
    when (bishopMoveNumber(start, end)) {
        0 -> return listOf(start)
        1 -> return listOf(start, end)
        2 -> {
            var square: Square
            var y = ((start.row - start.column) + (end.row + end.column)) / 2
            var x = ((end.row + end.column) - (start.row - start.column)) / 2
            square = Square(x, y)
            if (!square.inside()) {
                y = ((start.column + start.row) + (end.row - end.column)) / 2
                x = ((start.column + start.row) - (end.row - end.column)) / 2
                square = Square(x, y)
            }
            return listOf(start, square, end)
        }
        else -> return listOf()
    }
}


/**
 * Средняя (3 балла)
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxx
 * x...x
 * x.K.x
 * x...x
 * xxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: kingMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Король может последовательно пройти через клетки (4, 2) и (5, 2) к клетке (6, 3).
 */
fun kingMoveNumber(start: Square, end: Square): Int {
    if (!start.inside() || !end.inside()) throw java.lang.IllegalArgumentException("Invalid square")
    return if (abs(start.column - end.column) < abs(start.row - end.row)) {
        abs(start.column - end.column) + abs(start.row - end.row) - abs(start.column - end.column)
    } else {
        abs(start.row - end.row) + abs(start.column - end.column) - abs(start.row - end.row)
    }
}

/**
 * Сложная (5 баллов)
 *
 * Вернуть список из клеток, по которым шахматный король может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов короля см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: kingTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможны другие варианты)
 *          kingTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(4, 2), Square(5, 2), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          kingTrajectory(Square(3, 5), Square(6, 2)) = listOf(Square(3, 5), Square(4, 4), Square(5, 3), Square(6, 2))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun kingTrajectory(start: Square, end: Square): List<Square> = TODO()

/**
 * Сложная (6 баллов)
 *
 * Определить число ходов, за которое шахматный конь пройдёт из клетки start в клетку end.
 * Шахматный конь одним ходом вначале передвигается ровно на 2 клетки по горизонтали или вертикали,
 * а затем ещё на 1 клетку под прямым углом, образуя букву "Г".
 * Ниже точками выделены возможные ходы коня, а крестиками -- невозможные:
 *
 * .xxx.xxx
 * xxKxxxxx
 * .xxx.xxx
 * x.x.xxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: knightMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Конь может последовательно пройти через клетки (5, 2) и (4, 4) к клетке (6, 3).
 */
fun knightMoveNumber(start: Square, end: Square): Int = knightTrajectory(start, end).size - 1


/**
 * Очень сложная (10 баллов)
 *
 * Вернуть список из клеток, по которым шахматный конь может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов коня см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры:
 *
 * knightTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 * здесь возможны другие варианты)
 * knightTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(5, 2), Square(4, 4), Square(6, 3))
 * (здесь возможен единственный вариант)
 * knightTrajectory(Square(3, 5), Square(5, 6)) = listOf(Square(3, 5), Square(5, 6))
 * (здесь опять возможны другие варианты)
 * knightTrajectory(Square(7, 7), Square(8, 8)) =
 *     listOf(Square(7, 7), Square(5, 8), Square(4, 6), Square(6, 7), Square(8, 8))
 *
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun knightTrajectory(start: Square, end: Square): List<Square> {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException("Invalid notation")
    var chess = arrayOf(arrayOf<Square>())
    val queue = LinkedList<Square>()
    val path = mutableListOf<Square>()
    var s = Square(0, 0)
    var finishFounded = false

    for (row in 1..8) {
        var array = arrayOf(Square(0, 0))
        for (column in 1..8) {
            val square = Square(column, row)
            array += square
            if (square.column == start.column && square.row == start.row) {
                array[column].d = 0
                s = square // start
            }
        }
        chess += array
    }

    fun getNeighbours(square: Square): List<Square> {
        val neighbours = mutableListOf<Square>()

        var neighbour = Square(square.column - 1, square.row - 2)
        if (neighbour.inside()) neighbours += neighbour

        neighbour = Square(square.column + 1, square.row - 2)
        if (neighbour.inside()) neighbours += neighbour

        neighbour = Square(square.column + 2, square.row - 1)
        if (neighbour.inside()) neighbours += neighbour

        neighbour = Square(square.column + 2, square.row + 1)
        if (neighbour.inside()) neighbours += neighbour

        neighbour = Square(square.column + 1, square.row + 2)
        if (neighbour.inside()) neighbours += neighbour

        neighbour = Square(square.column - 1, square.row + 2)
        if (neighbour.inside()) neighbours += neighbour

        neighbour = Square(square.column - 2, square.row + 1)
        if (neighbour.inside()) neighbours += neighbour

        neighbour = Square(square.column - 2, square.row - 1)
        if (neighbour.inside()) neighbours += neighbour


        return neighbours
    }

    fun getPath() {
        var element = chess[end.row][end.column]
        do {
            path.add(0, element)
            val neighbors = getNeighbours(element)
            for ((column, row) in neighbors) {
                if (chess[row][column].d == element.d - 1) {
                    element = chess[row][column]
                    break
                }

            }
        } while (element.d > 0)

        if (start != end) path.add(0, start)
    }

    fun recursivePathSearch() {
        if (!finishFounded) {
            val square = queue.pop()
            val neighbours = getNeighbours(square)
            for ((column, row) in neighbours) {
                if (chess[row][column].d == -1) {
                    chess[row][column].d = square.d + 1
                    queue.add(chess[row][column])
                }
                if (chess[row][column].row == end.row && chess[row][column].column == end.column) {
                    finishFounded = true
                    getPath()
                    break
                }

            }
            if (queue.isNotEmpty()) recursivePathSearch()
        }

    }

    queue.add(s) // first of all adding start square ro queue
    recursivePathSearch() // and start research


    return path
}

