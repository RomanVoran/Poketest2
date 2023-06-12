package com.roman.poketest2.domain

enum class ListFormat {
    LIST, GRID;

    companion object {
        fun fromString(name: String?) = when (name) {
            LIST.name -> LIST
            GRID.name -> GRID
            else -> LIST
        }
    }
}