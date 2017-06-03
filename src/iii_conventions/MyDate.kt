package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.days().compareTo(other.days())
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange =
        DateRange(this, other)

operator fun MyDate.inc(): MyDate {
    return this.nextDay()
}

operator fun MyDate.plus(interval: TimeInterval): MyDate =
        when (interval) {
            TimeInterval.DAY -> this.nextDay()
            TimeInterval.WEEK -> this.addTimeIntervals(TimeInterval.WEEK, 1)
            TimeInterval.YEAR -> this.addTimeIntervals(TimeInterval.YEAR, 1)
        }

operator fun MyDate.plus(multipliedTimeInterval: MultipliedTimeInterval): MyDate =
        this.addTimeIntervals(multipliedTimeInterval.interval, multipliedTimeInterval.multiplier)

class MultipliedTimeInterval(val interval: TimeInterval, val multiplier: Int)

fun MyDate.days(): Int {
    return year * 365 + month * 31 + dayOfMonth
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(multiplier: Int) : MultipliedTimeInterval = MultipliedTimeInterval(this, multiplier)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {

            var currentDate: MyDate = start

            override fun next(): MyDate {
                return currentDate++
            }

            override fun hasNext(): Boolean {
                return currentDate <= endInclusive
            }

        }
    }
}

operator fun DateRange.contains(other: MyDate): Boolean =
        start < other && other <= endInclusive
