package cberg.aoc2023

import cberg.aoc2023.Day17.Dir.*

class Day17(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = solve(1, 3)

    fun part2() = solve(4, 10) // Runs out of memory with the real input

    private fun solve(minStraight: Int, maxStraight: Int): Int {
        val todo = mutableMapOf(Node(Vec(0, 0), RIGHT, 0) to (0 to emptyList<Node>()))
        val done = mutableMapOf<Node, Pair<Int, List<Node>>>()

        while (todo.isNotEmpty()) {
            val (node, dist) = todo.minBy { it.value.first }
            todo.remove(node)
            done[node] = dist
            for (newDir in entries) {
                if (newDir.v == -node.dir.v) {
                    continue
                }
                if (newDir != node.dir && node.steps in 1..<minStraight) {
                    continue
                }
                if (newDir == node.dir && node.steps == maxStraight) {
                    continue
                }
                val newNode = Node(
                    pos = node.pos + newDir.v,
                    dir = newDir,
                    steps = if (newDir == node.dir) node.steps + 1 else 1
                )
                if (newNode.isOutOfRange()) {
                    continue
                }
                if (newNode in done) {
                    continue
                }
                val newDist = dist.first + input[newNode.pos.y][newNode.pos.x].digitToInt()
                val bestSoFar = todo[newNode]
                if (bestSoFar == null || bestSoFar.first > newDist) {
                    todo[newNode] = newDist to dist.second + node
                }
            }
        }
        val target = Vec(input.last().lastIndex, input.lastIndex)
        return done.filterKeys { node -> node.pos == target }.minOf { it.value.first }
    }

    private fun Node.isOutOfRange() = pos.y !in input.indices || pos.x !in input[pos.y].indices

    private data class Vec(val x: Int, val y: Int) {
        operator fun plus(other: Vec) = Vec(this.x + other.x, this.y + other.y)
        operator fun unaryMinus() = Vec(-x, -y)
    }

    private data class Node(val pos: Vec, val dir: Dir, val steps: Int)

    private enum class Dir(val v: Vec) {
        UP(Vec(0, -1)),
        RIGHT(Vec(1, 0)),
        DOWN(Vec(0, 1)),
        LEFT(Vec(-1, 0))
    }
}