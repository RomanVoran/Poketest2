package com.roman.poketest2.domain

enum class Themes {
    DARK, DEFAULT, LIGHT;

    companion object {
        fun fromString(name: String) = when (name) {
            DARK.name -> DARK
            LIGHT.name -> LIGHT
            else -> DEFAULT
        }
    }
}