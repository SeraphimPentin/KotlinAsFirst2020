@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 14.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {
    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */

    private val map = mutableMapOf<String, MutableList<String>>()

    fun addHuman(name: String): Boolean {
        val r = map.containsKey(name)
        return if (!r) {
            map[name] = mutableListOf()
            true
        } else false
    }


    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun removeHuman(name: String): Boolean {
        val r = map.containsKey(name)
        return if (r) {
            map.remove(name)
            true
        } else false
    }

    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */
    fun addPhone(name: String, phone: String): Boolean {
        val r = map.containsKey(name)
        if (!r) {
            return false
        } else {
            if (map[name]?.contains(phone) == true) return false
            else {
                for (currentName in map.keys) {
                    val cn = map[currentName] ?: return false
                    if (cn.contains(phone)) return false
                }
            }
        }
        map[name]?.add(phone)
        return true
    }

    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */
    fun removePhone(name: String, phone: String): Boolean {
        if (!map.containsKey(name)) return false
        else {
            if (map[name]?.contains(phone) == false) return false
        }
        map[name]?.remove(phone)
        return true
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> {
        val setNum = mutableSetOf<String>()
        if (!map.containsKey(name)) return emptySet()
        else {
            val values = map[name]
            for (num in values?.indices!!)
                setNum += values[num]
        }
        return setNum
    }

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? {
        var name: String? = null
        for (key in map.keys) {
            if (map[key]?.contains(phone) != false) name = key
        }
        return name
    }

    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneBook) return false
        for (name in map.keys) {
            if (other.map.containsKey(name) && other.map[name] == map[name]) return true
        }
        return false
    }

    override fun hashCode(): Int = javaClass.hashCode()
}