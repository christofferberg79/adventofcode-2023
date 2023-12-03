package cberg.aoc2023

import kotlin.math.abs

class Day3(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val symbols = parseSymbols(input)
    private val numbers = parseNumbers(input)

    fun part1() = numbers
        .filter { it.positions.any { pos -> symbols.keys.any { symbolPos -> symbolPos.isAdjacentTo(pos) } } }
        .sumOf { it.value }

    fun part2() = symbols
        .filterValues { it == '*' }
        .keys.sumOf { gearPos ->
            val gearNumbers = numbers.filter { n -> n.positions.any { pos -> gearPos.isAdjacentTo(pos) } }
            if (gearNumbers.size == 2) {
                gearNumbers[0].value * gearNumbers[1].value
            } else {
                0
            }
        }

    data class Pos(val x: Int, val y: Int)
    data class Number(val value: Int, val positions: List<Pos>)

    private fun parseSymbols(input: List<String>) = buildMap {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (!c.isDigit() && c != '.') {
                    put(Pos(x, y), c)
                }
            }
        }
    }

    private fun parseNumbers(input: List<String>) = buildList {
        input.forEachIndexed { y, line ->
            var value = ""
            var positions = mutableListOf<Pos>()
            line.forEachIndexed { x, c ->
                if (c.isDigit()) {
                    value += c
                    positions += Pos(x, y)
                } else if (value.isNotEmpty()) {
                    add(Number(value.toInt(), positions))
                    value = ""
                    positions = mutableListOf()
                }
            }
            if (value.isNotEmpty()) {
                add(Number(value.toInt(), positions))
            }
        }
    }

    private fun Pos.isAdjacentTo(other: Pos) = this != other && abs(this.x - other.x) <= 1 && abs(this.y - other.y) <= 1
}