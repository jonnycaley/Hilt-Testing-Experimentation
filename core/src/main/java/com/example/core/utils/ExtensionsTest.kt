package com.example.core.utils

import com.example.core.getOffsetFromUrl
import org.junit.Test
import org.junit.Assert.*

class ExtensionsTest {
    @Test
    fun getOffsetFromUrlIsValid_thenReturnOffset() {
        val actual = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20".getOffsetFromUrl()
        val expected = 20
        assertEquals(expected, actual)
    }

    @Test
    fun getOffsetFromUrlIsNotValid_thenReturnNull() {
        val actual = "test".getOffsetFromUrl()
        val expected = null
        assertEquals(expected, actual)
    }
}