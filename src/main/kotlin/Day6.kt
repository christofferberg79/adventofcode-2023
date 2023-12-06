package cberg.aoc2023

class Day6(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = parse1(input).map { (t, d) -> (0..t).count { hold -> hold * (t - hold) > d } }.reduce(Int::times)

    fun part2() = parse2(input).let { (t, d) -> (0..t).count { hold -> hold * (t - hold) > d } }

    private fun parse1(input: List<String>) = input
        .map { line -> line.substringAfter(":").trim().split(Regex(" +")).map(String::toInt) }
        .let { (times, distances) -> times.zip(distances) }

    private fun parse2(input: List<String>) = input
        .map { line -> line.substringAfter(":").replace(" ", "").toLong() }
        .let { (time, distance) -> time to distance }
}