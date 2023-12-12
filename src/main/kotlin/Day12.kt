package cberg.aoc2023

class Day12(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.sumOf { line ->
        val (chars, ints) = parse(line)
        solve(chars, ints)
    }

    fun part2() = input.sumOf { line ->
        val (chars, ints) = parse(line)
        val expandedChars = chars + '?' + chars + '?' + chars + '?' + chars + '?' + chars
        val expandedInts = ints + ints + ints + ints + ints
        solve(expandedChars, expandedInts)
    }

    private fun parse(line: String) = line.split(" ").let { (part1, part2) ->
        part1.toList() to part2.split(",").map { it.toInt() }
    }

    private data class CacheKey(val chars: List<Char>, val ints: List<Int>, val prev: Char)

    private val cache = mutableMapOf<CacheKey, Long>()

    private fun solve(chars: List<Char>, ints: List<Int>, prev: Char = '.'): Long =
        cache.getOrPut(CacheKey(chars, ints, prev)) {
            when {
                chars.isEmpty() -> if (ints.any { it > 0 }) 0 else 1
                ints.isEmpty() -> if (chars.any { it == '#' }) 0 else 1
                else -> {
                    val char = chars.first()
                    val int = ints.first()
                    val restOfChars = chars.drop(1)
                    when (char) {
                        '#' -> {
                            if (int > 0) solve(restOfChars, listOf(int - 1) + ints.drop(1), char)
                            else 0
                        }

                        '.' -> { // char == '.'
                            if (prev == '.') solve(restOfChars, ints, char)
                            else if (int == 0) solve(restOfChars, ints.drop(1), char)
                            else 0
                        }

                        else -> { // char == '?'
                            solve(listOf('#') + restOfChars, ints, prev) + solve(listOf('.') + restOfChars, ints, prev)
                        }
                    }
                }
            }
        }

}