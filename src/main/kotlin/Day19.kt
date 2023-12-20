package cberg.aoc2023

class Day19(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val workflows: Map<String, Workflow>
    private val parts: List<Part>

    init {
        val (l1, l2) = input.split("")
        workflows = l1.map(::parseWorkflow).associateBy(Workflow::name)
        parts = l2.map(::parsePart)
    }

    fun part1() = parts.filter { it.isAccepted() }.sumOf { it.rating }

    fun part2() = 0

    private class Workflow(val name: String, val rules: List<String>)

    private fun parseWorkflow(workflow: String) = Regex("([a-z]+)\\{(.+)}")
        .matchEntire(workflow)?.destructured
        ?.let { (name, rules) -> Workflow(name, rules.split(",")) }
        ?: error("Invalid workflow input: $workflow")

    private data class Part(val x: Int, val m: Int, val a: Int, val s: Int) {
        val rating get() = x + m + a + s
    }

    private operator fun Part.get(rating: String) = when (rating) {
        "x" -> x
        "m" -> m
        "a" -> a
        "s" -> s
        else -> error("Invalid rating name: $rating")
    }

    private fun parsePart(part: String) = Regex("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}")
        .matchEntire(part)?.destructured
        ?.let { (x, m, a, s) -> Part(x.toInt(), m.toInt(), a.toInt(), s.toInt()) }
        ?: error("Invalid part input: $part")

    private fun Part.isAccepted(): Boolean {
        var workflow = workflows["in"] ?: error("Couldn't find workflow 'in'")
        while (true) {
            for (rule in workflow.rules) {
                val action = if (':' in rule) {
                    val (rating, value, action) = rule.split("<", ">", ":")
                    if ('>' in rule && this[rating] > value.toInt() ||
                        '<' in rule && this[rating] < value.toInt()
                    ) action else continue
                } else {
                    rule
                }

                return when (action) {
                    "A" -> true
                    "R" -> false
                    else -> {
                        workflow = workflows[action] ?: error("Couldn't find workflow '$action'")
                        break
                    }
                }
            }
        }
    }
}
