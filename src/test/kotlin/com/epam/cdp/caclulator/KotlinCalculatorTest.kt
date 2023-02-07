package com.epam.cdp.caclulator

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class KotlinCalculatorTest : StringSpec({

    "The addition of 1+2 should be 3" {
        val kc = KotlinCalculator()
        assert(kc.calculate("1+2").equals(3))
    }

    "calculate method" should {
        "return the sum of the input string" {
            val calculator = KotlinCalculator()
            val input = "2 + 3*5"
            val result = calculator.calculate(input)
            result shouldBe 17
        }
        "return the subtraction of the input string" {
            val calculator = KotlinCalculator()
            val input = "2 - 3 *5"
            val result = calculator.calculate(input)
            result shouldBe -13
        }
        "return the division of the input string" {
            val calculator = KotlinCalculator()
            val input = "6 / 3"
            val result = calculator.calculate(input)
            result shouldBe 2
        }
        "return the multiplication of the input string" {
            val calculator = KotlinCalculator()
            val input = "6 * 3"
            val result = calculator.calculate(input)
            result shouldBe 18
        }
    }


})
