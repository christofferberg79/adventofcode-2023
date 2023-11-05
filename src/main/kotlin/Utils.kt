package cberg.aoc2023

fun <T> Iterable<T>.split(delimiter: T): Iterable<Iterable<T>> {
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