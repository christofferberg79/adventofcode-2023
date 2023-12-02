package cberg.aoc2023

class Day2(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.map { line -> parse(line) }
        .filter { game -> game.sets.all { set -> set.red <= 12 && set.green <= 13 && set.blue <= 14 } }
        .sumOf { game -> game.id }

    fun part2() = input.map { line -> parse(line).sets }
        .sumOf { sets -> sets.maxOf { it.red } * sets.maxOf { it.green } * sets.maxOf { it.blue } }

    data class Set(val red: Int, val green: Int, val blue: Int)
    data class Game(val id: Int, val sets: List<Set>)

    private fun parse(line: String) = line.split(": ").let { (id, sets) ->
        Game(
            id = id.substringAfter(" ").toInt(),
            sets = sets.split("; ").map { set ->
                val cubes = set.split(", ").associate {
                    it.split(" ").let { (n, color) -> color to n.toInt() }
                }
                Set(cubes["red"] ?: 0, cubes["green"] ?: 0, cubes["blue"] ?: 0)
            }
        )
    }
}