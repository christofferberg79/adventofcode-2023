package cberg.aoc2023

class Day4(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.sumOf { line ->
        1.shl(matches(line) - 1)
    }

    fun part2(): Int {
        val copiesByCard = MutableList(input.size) { 1 }
        input.forEachIndexed { index, line ->
            for (offset in 1..matches(line)) {
                copiesByCard[index + offset] += copiesByCard[index]
            }
        }
        return copiesByCard.sum()
    }

    private fun matches(line: String): Int {
        val (winningNumbers, myNumbers) = line.split(":", "|").drop(1).map { it.trim().split(Regex(" +")).toSet() }
        return myNumbers.count { it in winningNumbers }
    }
}