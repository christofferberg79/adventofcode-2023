package cberg.aoc2023

class Day9(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.sumOf { line -> parse(line).next() }

    fun part2() = input.sumOf { line -> parse(line).reversed().next() }

    private fun parse(line: String) = line.split(" ").map(String::toInt)

    private fun List<Int>.next() = toMutableList().apply {
        for (j in lastIndex downTo 0) {
            for (i in 0..<j) {
                this[i] = this[i + 1] - this[i]
            }
        }
    }.sum()
}