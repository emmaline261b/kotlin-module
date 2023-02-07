package com.epam.cdp.caclulator

import java.lang.IllegalArgumentException

enum class Operator(val symbol: String) {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    MODULO("%");


    fun checkSign(toCheck: String): Operator {
        enumValues<Operator>().forEach {
            if (toCheck.equals(it.symbol)) {
                return it;
            }
        }
        throw IllegalArgumentException("No enum matches the string!")
    }

    fun mathOperation(firstOperand : Int, secondOperand : Int) : Int {
        if (this.equals(DIVISION) && secondOperand == 0) {
            throw IllegalArgumentException()
        }

        var result = when (this) {
            ADDITION -> firstOperand + secondOperand
            SUBTRACTION -> firstOperand - secondOperand
            MULTIPLICATION -> firstOperand * secondOperand
            DIVISION -> firstOperand / secondOperand
            MODULO -> firstOperand % secondOperand
        }

        return result
    }
}