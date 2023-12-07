package cberg.aoc2023

class Day7(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = solve(input)

    fun part2() = solve(input.map { it.replace('J', 'X') })

    private fun solve(input: List<String>) = input.asSequence()
        .map { it.split(" ") }
        .map { (hand, bid) -> comparableHand(hand) to bid.toInt() }
        .sortedBy { (hand, _) -> hand }
        .mapIndexed { index, (_, bid) -> (index + 1) * bid }
        .sum()

    private fun comparableHand(hand: String) = type(hand) + strengths(hand)

    private fun type(hand: String) = hand.groupingBy { it }.eachCount().let { count ->
        count.filterKeys { it != 'X' }.values.sortedDescending().let { type ->
            val jokers = count['X'] ?: 0
            if (type.isEmpty()) {
                listOf(jokers)
            } else {
                listOf(type[0] + jokers) + type.drop(1)
            }
        }
    }.joinToString("")

    private fun strengths(hand: String) = hand.map { c ->
        when (c) {
            'T' -> 'A'
            'J' -> 'B'
            'Q' -> 'C'
            'K' -> 'D'
            'A' -> 'E'
            'X' -> '1'
            else -> c
        }
    }.joinToString("")
}