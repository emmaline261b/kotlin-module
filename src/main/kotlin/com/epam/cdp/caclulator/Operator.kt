package com.epam.cdp.caclulator

import java.lang.IllegalArgumentException

enum class Operator(val sign: String) {
    ADDITION("+"),
    SUBSTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    fun checkSign(toCheck: String): Operator {
        enumValues<Operator>().forEach {
            if (toCheck.equals(it.sign)) {
                return it;
            }
        }
        throw IllegalArgumentException("No enum matches the string!")
    }

    fun mathOperation(firstOperand : Int, secondOperand : Int) : Int {
        if (this.equals(DIVISION) && secondOperand == 0) {
            throw IllegalArgumentException()
        }

        var result = 0;
        when (this) {
            ADDITION -> result = firstOperand + secondOperand
            SUBSTRACTION -> result = firstOperand - secondOperand
            MULTIPLICATION -> result = firstOperand * secondOperand
            DIVISION -> result = firstOperand / secondOperand
        }

        return result
    }
}