package cberg.aoc2023

class Day10(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val north = Pos(0, -1)
    private val east = Pos(1, 0)
    private val south = Pos(0, 1)
    private val west = Pos(-1, 0)

    private val turns = mapOf(
        north to (mapOf('|' to north, '7' to west, 'F' to east)),
        east to (mapOf('-' to east, 'J' to north, '7' to south)),
        south to (mapOf('|' to south, 'L' to east, 'J' to west)),
        west to (mapOf('-' to west, 'F' to south, 'L' to north)),
    )

    private val s = findS()
    private val path = findPath()

    fun part1() = path.size / 2

    fun part2() = allPositions().count { it.isInside(path) }

    private fun findS() = allPositions().first { pos -> tile(pos) == 'S' }

    private fun allPositions() =
        input.indices.asSequence().flatMap { y -> input[y].indices.asSequence().map { x -> Pos(x, y) } }

    private fun findPath(): Set<Pos> {
        var dir = findDirFromS(s)
        var pos = s + dir
        val path = mutableSetOf(s)
        while (pos != s) {
            path.add(pos)
            dir = turns.getValue(dir).getValue(tile(pos))
            pos += dir
        }
        return path
    }

    private fun findDirFromS(s: Pos) = turns.keys.first { dir -> tile(s + dir) in turns.getValue(dir) }

    private fun tile(p: Pos) = input.getOrNull(p.y)?.getOrNull(p.x) ?: '.'

    private data class Pos(val x: Int, val y: Int)

    private operator fun Pos.plus(other: Pos) = Pos(this.x + other.x, this.y + other.y)

    private fun Pos.isInside(path: Set<Pos>): Boolean {
        if (this in path) {
            return false
        }
        var pos = this
        val dir = if (pos.y > s.y) south else north // avoid stepping on S, because we don't know if it's a corner
        var onStartingSide = true
        var enteringCorner: Char? = null
        val oppositeCorners = mapOf('F' to 'J', '7' to 'L', 'J' to 'F', 'L' to '7')
        while (pos.y in input.indices) {
            pos += dir
            if (pos in path) {
                val tile = tile(pos)
                if (tile == '-') { // crossing path
                    onStartingSide = !onStartingSide
                } else if (tile in oppositeCorners) {
                    if (enteringCorner == null) { // stepping on to path
                        enteringCorner = tile
                    } else { // leaving path
                        if (tile == oppositeCorners[enteringCorner]) { // leaving on the other side
                            onStartingSide = !onStartingSide
                        }
                        enteringCorner = null
                    }
                }
            }
        }

        return !onStartingSide
    }
}
