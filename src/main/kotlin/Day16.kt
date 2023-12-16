package cberg.aoc2023

class Day16(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = solve(startingBeam())

    fun part2() = startingBeams().maxOf(::solve)

    private fun solve(start: Beam): Int {
        val todo = mutableListOf(start)
        val done = mutableSetOf<Beam>()

        while (todo.isNotEmpty()) {
            val beam = todo.removeFirst()
            if (beam.isInRange() && done.add(beam)) {
                todo += beam.move()
            }
        }
        return done.map { beam -> beam.pos }.toSet().size
    }

    private fun Beam.isInRange() = pos.y in input.indices && pos.x in input[pos.y].indices

    private fun Beam.move(): List<Beam> {
        val dirs = when (input[pos.y][pos.x]) {
            '\\' -> setOf(Vec(dir.y, dir.x))
            '/' -> setOf(Vec(-dir.y, -dir.x))
            '|' -> setOf(Vec(0, dir.y + dir.x), Vec(0, dir.y - dir.x))
            '-' -> setOf(Vec(dir.x + dir.y, 0), Vec(dir.x - dir.y, 0))
            else -> setOf(dir)
        }
        return dirs.map { Beam(pos + it, it) }
    }

    private fun startingBeam() = Beam(Vec(0, 0), Vec(1, 0))

    private fun startingBeams() = buildList {
        for (y in input.indices) {
            add(Beam(Vec(0, y), Vec(1, 0)))
            add(Beam(Vec(input.first().lastIndex, y), Vec(-1, 0)))
        }
        for (x in 1..<input.first().lastIndex) {
            add(Beam(Vec(x, 0), Vec(0, 1)))
            add(Beam(Vec(x, input.lastIndex), Vec(0, -1)))
        }
    }

    private data class Vec(val x: Int, val y: Int)

    private operator fun Vec.plus(other: Vec) = Vec(this.x + other.x, this.y + other.y)

    private data class Beam(val pos: Vec, val dir: Vec)
}
