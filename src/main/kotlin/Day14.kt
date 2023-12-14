package cberg.aoc2023

class Day14(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val platform = Array(input.size) { row -> CharArray(input[row].length) { col -> input[row][col] } }

    fun part1(): Int {
        tiltNorth()
        return calcLoad()
    }

    private fun calcLoad(): Int {
        return platform.mapIndexed { index, chars -> (platform.size - index) * chars.count { it == 'O' } }.sum()
    }

    fun part2(): Int {
        val map = mutableMapOf<Any, Pair<Int, Int>>()
        var count = 0
        while (true) {
            val key = platform.map { it.toList() }
            map.put(key, count to calcLoad())?.let { (prevCount, _) ->
                val solutionCount = prevCount + (1_000_000_000 - prevCount) % (count - prevCount)
                return map.values.first { (count, _) -> count == solutionCount }.second
            }

            doCycle()

            count++
        }
    }

    private fun doCycle() {
        tiltNorth()
        tiltWest()
        tiltSouth()
        tiltEast()
    }

    private fun tiltNorth() {
        for (i in platform.first().indices) {
            var rock = 0
            var target = 0

            while (target < platform.size && rock < platform.size) {
                if (platform[target][i] == '#') {
                    target++
                } else if (platform[rock][i] == '#') {
                    rock++
                    target = rock
                } else if (platform[rock][i] == 'O') {
                    platform[rock][i] = '.'
                    platform[target][i] = 'O'
                    target++
                    rock++
                } else {
                    rock++
                }
            }
        }
    }

    private fun tiltWest() {
        for (i in platform.indices) {
            var rock = 0
            var target = 0

            while (target < platform[i].size && rock < platform[i].size) {
                if (platform[i][target] == '#') {
                    target++
                } else if (platform[i][rock] == '#') {
                    rock++
                    target = rock
                } else if (platform[i][rock] == 'O') {
                    platform[i][rock] = '.'
                    platform[i][target] = 'O'
                    target++
                    rock++
                } else {
                    rock++
                }
            }
        }
    }

    private fun tiltSouth() {
        for (i in platform.first().indices) {
            var rock = platform.lastIndex
            var target = platform.lastIndex

            while (target >= 0 && rock >= 0) {
                if (platform[target][i] == '#') {
                    target--
                } else if (platform[rock][i] == '#') {
                    rock--
                    target = rock
                } else if (platform[rock][i] == 'O') {
                    platform[rock][i] = '.'
                    platform[target][i] = 'O'
                    target--
                    rock--
                } else {
                    rock--
                }
            }
        }
    }

    private fun tiltEast() {
        for (i in platform.indices) {
            var rock = platform[i].lastIndex
            var target = platform[i].lastIndex

            while (target >= 0 && rock >= 0) {
                if (platform[i][target] == '#') {
                    target--
                } else if (platform[i][rock] == '#') {
                    rock--
                    target = rock
                } else if (platform[i][rock] == 'O') {
                    platform[i][rock] = '.'
                    platform[i][target] = 'O'
                    target--
                    rock--
                } else {
                    rock--
                }
            }
        }
    }
}