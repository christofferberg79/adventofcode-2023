package cberg.aoc2023

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun day1() {
        assertEquals(142, Day1(Input("day1_ex1")).part1())
        assertEquals(55971, Day1(Input("day1")).part1())
        assertEquals(281, Day1(Input("day1_ex2")).part2())
        assertEquals(54719, Day1(Input("day1")).part2())
    }

    @Test
    fun day2() {
        assertEquals(8, Day2(Input("day2_ex1")).part1())
        assertEquals(2447, Day2(Input("day2")).part1())
        assertEquals(2286, Day2(Input("day2_ex1")).part2())
        assertEquals(56322, Day2(Input("day2")).part2())
    }

    @Test
    fun day3() {
        assertEquals(4361, Day3(Input("day3_ex1")).part1())
        assertEquals(550064, Day3(Input("day3")).part1())
        assertEquals(467835, Day3(Input("day3_ex1")).part2())
        assertEquals(85010461, Day3(Input("day3")).part2())
    }

    @Test
    fun day4() {
        assertEquals(13, Day4(Input("day4_ex1")).part1())
        assertEquals(23673, Day4(Input("day4")).part1())
        assertEquals(30, Day4(Input("day4_ex1")).part2())
        assertEquals(12263631, Day4(Input("day4")).part2())
    }

    @Test
    fun day5() {
        assertEquals(35, Day5(Input("day5_ex1")).part1())
        assertEquals(621354867, Day5(Input("day5")).part1())
        assertEquals(46, Day5(Input("day5_ex1")).part2())
        assertEquals(15880236, Day5(Input("day5")).part2())
    }

    @Test
    fun day6() {
        assertEquals(288, Day6(Input("day6_ex1")).part1())
        assertEquals(500346, Day6(Input("day6")).part1())
        assertEquals(71503, Day6(Input("day6_ex1")).part2())
        assertEquals(42515755, Day6(Input("day6")).part2())
    }

    @Test
    fun day7() {
        assertEquals(6440, Day7(Input("day7_ex1")).part1())
        assertEquals(248569531, Day7(Input("day7")).part1())
        assertEquals(5905, Day7(Input("day7_ex1")).part2())
        assertEquals(250382098, Day7(Input("day7")).part2())
    }

    @Test
    fun day8() {
        assertEquals(2, Day8(Input("day8_ex1")).part1())
        assertEquals(6, Day8(Input("day8_ex2")).part1())
        assertEquals(22199, Day8(Input("day8")).part1())
        assertEquals(6, Day8(Input("day8_ex3")).part2())
        assertEquals(13334102464297, Day8(Input("day8")).part2())
    }

    @Test
    fun day9() {
        assertEquals(114, Day9(Input("day9_ex1")).part1())
        assertEquals(1819125966, Day9(Input("day9")).part1())
        assertEquals(2, Day9(Input("day9_ex1")).part2())
        assertEquals(1140, Day9(Input("day9")).part2())
    }

    @Test
    fun day10() {
        assertEquals(8, Day10(Input("day10_ex1")).part1())
        assertEquals(6842, Day10(Input("day10")).part1())
        assertEquals(8, Day10(Input("day10_ex2")).part2())
        assertEquals(10, Day10(Input("day10_ex3")).part2())
        assertEquals(393, Day10(Input("day10")).part2())
    }

    @Test
    fun day11() {
        assertEquals(374, Day11(Input("day11_ex1")).part1())
        assertEquals(9521776, Day11(Input("day11")).part1())
        assertEquals(1030, Day11(Input("day11_ex1")).part2(10))
        assertEquals(8410, Day11(Input("day11_ex1")).part2(100))
        assertEquals(553224415344, Day11(Input("day11")).part2(1000000))
    }

    @Test
    fun day12() {
        assertEquals(21, Day12(Input("day12_ex1")).part1())
        assertEquals(7251, Day12(Input("day12")).part1())
        assertEquals(525152, Day12(Input("day12_ex1")).part2())
        assertEquals(2128386729962, Day12(Input("day12")).part2())
    }

    @Test
    fun day13() {
        assertEquals(405, Day13(Input("day13_ex1")).part1())
        assertEquals(35210, Day13(Input("day13")).part1())
        assertEquals(400, Day13(Input("day13_ex1")).part2())
        assertEquals(31974, Day13(Input("day13")).part2())
    }

    @Test
    fun day14() {
        assertEquals(136, Day14(Input("day14_ex1")).part1())
        assertEquals(108614, Day14(Input("day14")).part1())
        assertEquals(64, Day14(Input("day14_ex1")).part2())
        assertEquals(96447, Day14(Input("day14")).part2())
    }

    @Test
    fun day15() {
        assertEquals(1320, Day15(Input("day15_ex1")).part1())
        assertEquals(511498, Day15(Input("day15")).part1())
        assertEquals(145, Day15(Input("day15_ex1")).part2())
        assertEquals(284674, Day15(Input("day15")).part2())
    }

    @Test
    @Ignore
    fun day16() {
        assertEquals(0, Day16(Input("day16_ex1")).part1())
        assertEquals(0, Day16(Input("day16")).part1())
        assertEquals(0, Day16(Input("day16_ex1")).part2())
        assertEquals(0, Day16(Input("day16")).part2())
    }

    @Test
    @Ignore
    fun day17() {
        assertEquals(0, Day17(Input("day17_ex1")).part1())
        assertEquals(0, Day17(Input("day17")).part1())
        assertEquals(0, Day17(Input("day17_ex1")).part2())
        assertEquals(0, Day17(Input("day17")).part2())
    }

    @Test
    @Ignore
    fun day18() {
        assertEquals(0, Day18(Input("day18_ex1")).part1())
        assertEquals(0, Day18(Input("day18")).part1())
        assertEquals(0, Day18(Input("day18_ex1")).part2())
        assertEquals(0, Day18(Input("day18")).part2())
    }

    @Test
    @Ignore
    fun day19() {
        assertEquals(0, Day19(Input("day19_ex1")).part1())
        assertEquals(0, Day19(Input("day19")).part1())
        assertEquals(0, Day19(Input("day19_ex1")).part2())
        assertEquals(0, Day19(Input("day19")).part2())
    }

    @Test
    @Ignore
    fun day20() {
        assertEquals(0, Day20(Input("day20_ex1")).part1())
        assertEquals(0, Day20(Input("day20")).part1())
        assertEquals(0, Day20(Input("day20_ex1")).part2())
        assertEquals(0, Day20(Input("day20")).part2())
    }

    @Test
    @Ignore
    fun day21() {
        assertEquals(0, Day21(Input("day21_ex1")).part1())
        assertEquals(0, Day21(Input("day21")).part1())
        assertEquals(0, Day21(Input("day21_ex1")).part2())
        assertEquals(0, Day21(Input("day21")).part2())
    }

    @Test
    @Ignore
    fun day22() {
        assertEquals(0, Day22(Input("day22_ex1")).part1())
        assertEquals(0, Day22(Input("day22")).part1())
        assertEquals(0, Day22(Input("day22_ex1")).part2())
        assertEquals(0, Day22(Input("day22")).part2())
    }

    @Test
    @Ignore
    fun day23() {
        assertEquals(0, Day23(Input("day23_ex1")).part1())
        assertEquals(0, Day23(Input("day23")).part1())
        assertEquals(0, Day23(Input("day23_ex1")).part2())
        assertEquals(0, Day23(Input("day23")).part2())
    }

    @Test
    @Ignore
    fun day24() {
        assertEquals(0, Day24(Input("day24_ex1")).part1())
        assertEquals(0, Day24(Input("day24")).part1())
        assertEquals(0, Day24(Input("day24_ex1")).part2())
        assertEquals(0, Day24(Input("day24")).part2())
    }

    @Test
    @Ignore
    fun day25() {
        assertEquals(0, Day25(Input("day25_ex1")).part1())
        assertEquals(0, Day25(Input("day25")).part1())
        assertEquals(0, Day25(Input("day25_ex1")).part2())
        assertEquals(0, Day25(Input("day25")).part2())
    }
}