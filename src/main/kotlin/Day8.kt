package cberg.aoc2023

import kotlin.math.max

class Day8(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val instructions = input.first()
    private val network = input.drop(2).associate { line ->
        line.split(" = (", ", ", ")").let { (node, left, right) -> node to (left to right) }
    }

    fun part1(): Int {
        var steps = 0
        var node = "AAA"
        while (node != "ZZZ") {
            val instruction = instructions[steps++ % instructions.length]
            node = network.getValue(node).let { (left, right) -> if (instruction == 'L') left else right }
        }
        return steps
    }

    fun part2(): Long {
        val startingNodes = network.keys.filter { it.endsWith('A') }
        val paths = startingNodes.map { startingNode ->
            var steps = 0L
            var node = startingNode
            val endNodes = mutableMapOf<String, Long>()
            while (true) {
                val instruction = instructions[(steps++ % instructions.length).toInt()]
                node = network.getValue(node).let { (left, right) -> if (instruction == 'L') left else right }
                if (node.endsWith('Z')) {
                    if (node in endNodes) {
                        break
                    } else {
                        endNodes[node] = steps
                    }
                }
            }
            Path(endNodes[node]!!, steps - endNodes[node]!!)
        }

        check(paths.all { it.first == it.cycle }) // necessary for the rest to work

        return paths
            .map { primeFactors(it.cycle) }
            .reduce { acc, map ->
                (acc.keys + map.keys).toSet().associateWith { key -> max(acc[key] ?: 0, map[key] ?: 0) }
            }
            .toList().fold(1L) { acc, e -> acc * e.first * e.second }
    }

    private fun primeFactors(n: Long): Map<Long, Int> {
        var factor = 2L
        var remainder = n
        val primeFactors = mutableListOf<Long>()
        while (remainder > 1) {
            if (remainder % factor == 0L) {
                primeFactors.add(factor)
                remainder /= factor
            } else {
                factor++
            }
        }
        return primeFactors.groupingBy { it }.eachCount()
    }

    private data class Path(val first: Long, val cycle: Long)
}