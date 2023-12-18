package cberg.aoc2023

class Day18(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1(): Int {
        val instructions = input.map { line -> line.split(' ').let { parts -> parts[0].single() to parts[1].toInt() } }
        val trench = mutableSetOf<Vec>()
        digTrench(trench, instructions)
        digOutInterior(trench)
        return trench.size
    }

    fun part2() = 0

    private val right = Vec(1, 0)
    private val down = Vec(0, 1)
    private val left = Vec(-1, 0)
    private val up = Vec(0, -1)
    private val dirMap = mapOf('R' to right, 'D' to down, 'L' to left, 'U' to up)

    private fun digTrench(trench: MutableSet<Vec>, instructions: List<Pair<Char, Int>>) {
        var pos = Vec(0, 0)
        for ((dir, dist) in instructions) {
            repeat(dist) {
                pos += dirMap[dir] ?: error("Invalid direction: $dir")
                trench += pos
            }
        }
    }

    private fun digOutInterior(trench: MutableSet<Vec>) {
        val start = findInside(trench)
        val todo = mutableSetOf(start)
        while (todo.isNotEmpty()) {
            val pos = todo.first()
            todo.remove(pos)
            trench += pos
            todo += dirMap.values.map { dir -> pos + dir }.filterNot { newPos -> newPos in trench }
        }
    }

    private fun findInside(trench: Set<Vec>): Vec {
        val minY = trench.minOf(Vec::y)
        return trench.filter { it.y == minY }.map { it + down }.first { it !in trench }
    }

    private data class Vec(val x: Int, val y: Int) {
        operator fun plus(other: Vec) = Vec(this.x + other.x, this.y + other.y)
    }
}