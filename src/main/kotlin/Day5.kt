package cberg.aoc2023

import kotlin.math.max
import kotlin.math.min

class Day5(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1(): Long {
        val lists = input.split("")
        val seeds = lists.first().single().substringAfter(": ").split(" ").map(String::toLong)

        val maps = lists.drop(1).map { mapInput ->
            mapInput.drop(1).map { mapLine ->
                mapLine.split(" ").map(String::toLong).let { (dest, source, length) -> MapLine(dest, source, length) }
            }.sortedBy { it.sourceRange.first }
        }

        return seeds.minOf { seed ->
            maps.fold(seed) { n, map ->
                map.firstOrNull { mapLine -> n in mapLine.sourceRange }
                    ?.let { mapLine -> n + mapLine.diff }
                    ?: n
            }
        }
    }

    data class MapLine(val sourceRange: LongRange, val diff: Long) {
        constructor(dest: Long, source: Long, length: Long) : this(source..<source + length, dest - source)
    }

    fun part2(): Long {
        val lists = input.split("")
        val seeds = lists.first().single().substringAfter(": ").split(" ").map(String::toLong)
            .windowed(2, 2)
            .map { (start, length) -> start..<start + length }

        val maps = lists.drop(1).map { mapInput ->
            mapInput.drop(1).map { mapLine ->
                mapLine.split(" ").map(String::toLong).let { (dest, source, length) -> MapLine(dest, source, length) }
            }.sortedBy { it.sourceRange.first }
        }

        return maps.fold(seeds) { ranges, map ->
            ranges.flatMap { range ->
                var remainder = range
                val newRanges = mutableListOf<LongRange>()
                for (line in map) {
                    newRanges += remainder.before(line.sourceRange)
                    newRanges += remainder.overlap(line.sourceRange).offset(line.diff)
                    remainder = remainder.after(line.sourceRange)
                }
                newRanges += remainder
                newRanges.filterNot { it.isEmpty() }
            }
        }.minOf { it.first }
    }

    private fun LongRange.before(other: LongRange) = first..min(other.first - 1, last)
    private fun LongRange.after(other: LongRange) = max(first, other.last + 1)..last
    private fun LongRange.overlap(other: LongRange) = max(first, other.first)..min(other.last, last)
    private fun LongRange.offset(offset: Long) = first + offset..last + offset
}
