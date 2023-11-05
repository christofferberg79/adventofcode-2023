package cberg.aoc2023

import java.io.File

class Input(filename: String) {
    private val file = File("input/$filename")

    fun oneLine() = lines().first()
    fun lines() = file.readLines()
    fun <R> lines(transform: (String) -> R) = lines().map(transform)
    fun intLines() = lines().map { it.toInt() }
}