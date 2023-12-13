package cberg.aoc2023

fun <T> Iterable<T>.split(delimiter: T): List<List<T>> {
    val lists = mutableListOf(mutableListOf<T>())
    for (item in this) {
        if (item == delimiter) {
            lists.add(mutableListOf())
        } else {
            lists.last() += item
        }
    }
    return lists
}

fun <T> Iterable<T>.split(delimiter: (T) -> Boolean): List<List<T>> {
    val lists = mutableListOf(mutableListOf<T>())
    for (item in this) {
        if (delimiter(item)) {
            lists.add(mutableListOf())
        } else {
            lists.last() += item
        }
    }
    return lists
}