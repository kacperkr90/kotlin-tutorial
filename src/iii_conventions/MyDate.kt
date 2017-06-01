package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.days().compareTo(other.days())
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

fun MyDate.days(): Int {
    return year * 365 + month * 31 + dayOfMonth
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun DateRange.contains(other: MyDate): Boolean =
        start < other && other <= endInclusive
