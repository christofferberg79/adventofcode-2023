package cberg.aoc2023

class Day15(private val input: List<String>) {
    constructor(input: Input) : this(input.oneLine().split(","))

    private val boxes = List(256) { Box(it) }

    fun part1() = input.sumOf(::hash)

    fun part2(): Int {
        val regex = Regex("(.*)([=-])(.*)")
        for (step in input) {
            val (label, operation, value) = regex.matchEntire(step)?.destructured ?: error("invalid input: $step")
            val box = boxes[hash(label)]
            when (operation) {
                "-" -> box -= label
                "=" -> box[label] = value.toInt()
            }
        }
        return boxes.sumOf(Box::power)
    }

    private fun hash(s: String) = s.fold(0) { acc: Int, c: Char ->
        (acc + c.code) * 17 % 256
    }

    private class Box(private val n: Int) {
        private data class Lens(val label: String, val focalLength: Int)

        private val lenses = mutableListOf<Lens>()

        operator fun minusAssign(label: String) {
            lenses.removeIf { it.label == label }
        }

        operator fun set(label: String, focalLength: Int) {
            val lens = Lens(label, focalLength)
            val index = lenses.indexOfFirst { it.label == label }
            if (index == -1) {
                lenses += lens
            } else {
                lenses[index] = lens
            }
        }

        val power get() = (n + 1) * lenses.withIndex().sumOf { (index, lens) -> (index + 1) * lens.focalLength }
    }
}