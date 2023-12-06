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
        val seedRanges = lists.first().single().substringAfter(": ").split(" ").map(String::toLong)
            .windowed(2, 2)
            .map { (start, length) -> start..<start + length }

        val conversionMaps = lists.drop(1).map { mapInput ->
            mapInput.drop(1).map { mapLine ->
                mapLine.split(" ").map(String::toLong).let { (dest, source, length) -> MapLine(dest, source, length) }
            }.sortedBy { it.sourceRange.first }
        }

        // Pass each seed range through all the maps.
        // For each conversion map, split each incoming range according to the conversion ranges of the map.
        // This results in a new set of sub-ranges where any sub-range that overlaps a conversion range is
        // converted according to that conversion range.
        // The sub-ranges that do not overlap any conversion range are kept with their original values.
        // The new sub-ranges are then passed to the next conversion map.
        return conversionMaps.fold(seedRanges) { ranges, conversionMap ->
            ranges.flatMap { incomingRange ->
                // At this point have an incoming range and a map of conversion ranges
                // Incoming range:       |----------------------------------------------------|
                // Conversion ranges:            |---------|       |---------|         |---------|
                // Resulting sub-ranges: |------||---------||-----||---------||-------||------|
                //                        as is   converted  as is  converted  as is    converted

                // Going from left to right, we chop off parts af the incoming range are either
                // completely outside or completely inside a conversion range. The chopped off
                // pieces are put in 'newRanges' and the rest is put in 'remainder'.

                // After going through two of the conversion ranges in the example above,
                // the state will look like this:
                // incomingRange:        |----------------------------------------------------|
                // conversionMap:                |---------|       |---------|         |---------|
                // newRanges:            |------||---------||-----||---------|
                // remainder:                                                 |---------------|
                var remainder = incomingRange
                val newRanges = mutableListOf<LongRange>()
                for (conversionRange in conversionMap) {
                    newRanges += remainder.before(conversionRange.sourceRange)
                    newRanges += remainder.overlap(conversionRange.sourceRange).offset(conversionRange.diff)
                    remainder = remainder.after(conversionRange.sourceRange)
                }
                newRanges += remainder

                // Pass the new ranges to the next conversion map (excluding any inverted ranges
                // that may have been created byt the before/ovelap/after code above)
                return@flatMap newRanges.filterNot { it.isEmpty() }
            }
        }.minOf { it.first }
    }

    private fun LongRange.before(other: LongRange) = first..min(other.first - 1, last)
    private fun LongRange.after(other: LongRange) = max(first, other.last + 1)..last
    private fun LongRange.overlap(other: LongRange) = max(first, other.first)..min(other.last, last)
    private fun LongRange.offset(offset: Long) = first + offset..last + offset
}
