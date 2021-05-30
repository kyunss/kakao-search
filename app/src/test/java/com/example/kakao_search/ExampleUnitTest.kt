package com.example.kakao_search

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val testBaseImpl = BaseImpl(HashSet<Int>())

        val test = Test2(
            name = "",
            address = ""
        )

        test.hashCode()
    }
}

interface Base {
    fun print()
}

class BaseImpl(val b: Set<Int>) : Set<Int> by b{
    fun test (){

    }
}

data class Test2(
    val name: String,
    val address: String
)