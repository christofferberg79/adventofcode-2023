package cberg.aoc2023

class Day20(input: List<String>) {
    constructor(input: Input) : this(input.lines())

    private val modules: Map<String, Module> = input.map { line ->
        val (l, r) = line.split(" -> ")
        val destinations = r.split(", ")
        when (l.first()) {
            '%' -> FlipFlop(l.drop(1), destinations)
            '&' -> Conjunction(l.drop(1), destinations)
            else -> Broadcast(l, destinations)
        }
    }.associateBy { module -> module.name }

    init {
        for (source in modules.values) {
            for (destination in source.destinations.mapNotNull { modules[it] }) {
                destination.registerInput(source.name)
            }
        }
    }

    fun part1(): Int {
        var highCount = 0
        var lowCount = 0

        repeat(1000) {
            val pulses = mutableListOf(Pulse(false, "button", "broadcaster"))
            while (pulses.isNotEmpty()) {
                val pulse = pulses.removeFirst()
                if (pulse.isHigh) highCount++ else lowCount++
                modules[pulse.destination]?.let { destination ->
                    pulses.addAll(destination.receive(pulse))
                }
            }
        }

        return highCount * lowCount
    }

    fun part2() = 0

    private sealed class Module(val name: String, val destinations: List<String>) {
        abstract fun receive(pulse: Pulse): List<Pulse>
        open fun registerInput(name: String) {}
        fun send(pulseValue: Boolean) = destinations.map { destination -> Pulse(pulseValue, name, destination) }
    }

    private class FlipFlop(name: String, destinations: List<String>) : Module(name, destinations) {
        private var on = false
        override fun receive(pulse: Pulse) = if (pulse.isLow) {
            on = !on
            send(on)
        } else {
            emptyList()
        }
    }

    private class Conjunction(name: String, destinations: List<String>) : Module(name, destinations) {
        private val received = mutableMapOf<String, Boolean>()
        override fun receive(pulse: Pulse): List<Pulse> {
            received[pulse.source] = pulse.value
            return send(!received.values.all { it })
        }

        override fun registerInput(name: String) {
            received[name] = false
        }
    }

    private class Broadcast(name: String, destinations: List<String>) : Module(name, destinations) {
        override fun receive(pulse: Pulse) = send(pulse.value)
    }

    private class Pulse(val value: Boolean, val source: String, val destination: String) {
        val isHigh get() = value
        val isLow get() = !value
    }
}