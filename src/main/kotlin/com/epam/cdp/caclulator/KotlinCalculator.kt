package com.epam.cdp.caclulator

import kotlin.math.round

class KotlinCalculator {

    fun calculate(input: String): Double {
        var mutableInput = input.removeParenthesis()
        var stringList = createListOfSubstrates(mutableInput)
        stringList = multiplyOrDivide(stringList) { arg1, arg2, op ->
            when (op) {
                "*" -> arg1 * arg2
                "/" -> arg1 / arg2
                else -> arg1
            }
        }

        val sum = addOrSubtract(stringList) { arg1, arg2, op ->
            when (op) {
                "+" -> arg1 + arg2
                "-" -> arg1 - arg2
                else -> arg1
            }
        }

        return "%.4f".format(sum).toDouble()
    }

    private fun addOrSubtract(stringList: java.util.ArrayList<String>, mathOperation: (Double, Double, String) -> Double): Double {
        var mutableStringList = stringList
        var firstOperant = 0.0
        var secondOperant = 0.0
        var operator = "+"

        var sum = mutableStringList.get(0).toDouble()
        for (i in 1 until mutableStringList.size - 1) {
            if (mutableStringList.get(i).equals("+") || mutableStringList.get(i).equals("-")) {
                firstOperant = sum
                operator = mutableStringList.get(i)
                secondOperant = mutableStringList.get(i + 1).toDouble()
                val operation = Operation(mutableStringList.get(i), firstOperant, secondOperant)

                sum = mathOperation(operation.operand1, operation.operand2, operator)
            }
        }
        return sum
    }

    private fun multiplyOrDivide(
        stringList: java.util.ArrayList<String>,
        mathOperation: (Double, Double, String) -> Double
    ): java.util.ArrayList<String> {
        var mutableStringList = stringList
        var firstOperant = 0.0
        var secondOperant = 0.0
        var operator = "+"

        while (mutableStringList.contains("*") || mutableStringList.contains("/")) {
            for (i in 1 until mutableStringList.size - 1) {
                if (mutableStringList.get(i).equals("*") || mutableStringList.get(i).equals("/")) {
                    firstOperant = mutableStringList.get(i - 1).toDouble()
                    operator = mutableStringList.get(i)
                    secondOperant = mutableStringList.get(i + 1).toDouble()
                    val operation = Operation(mutableStringList.get(i), firstOperant, secondOperant)

                    mutableStringList.removeAt(i + 1)
                    mutableStringList.removeAt(i)
                    mutableStringList.removeAt(i - 1)
                    mutableStringList.add(i - 1, mathOperation(operation.operand1, operation.operand2, operator).toString())
                    break
                }
            }
        }

        return mutableStringList
    }

    private fun createListOfSubstrates(input: String): java.util.ArrayList<String> {
        var mutableInput = input
        var stringTemp = ""
        var stringList = ArrayList<String>()

        if (mutableInput[0].equals('-')) {
            stringTemp += mutableInput[0]
            mutableInput.substring(1)
        }

        for (c in mutableInput) {
            if (c.isWhitespace()) {
                continue
            }
            if (c.isDigit()) {
                stringTemp += c
            } else {
                if (!stringTemp.equals("")) {
                    stringList.add(stringTemp)
                }
                stringList.add(c.toString())
                stringTemp = ""
            }
        }
        stringList.add(stringTemp)
        return stringList
    }

    private fun removeParenthesis(input: String): String {
        var mutableInput = "$input"
        while (mutableInput.contains("(")) {
            var stringTemp = mutableInput.substringAfter("(").substringBefore(")")
            var result = calculate(stringTemp)
            stringTemp = "(" + mutableInput.substringAfter("(").substringBefore(")") + ")"
            return mutableInput.replaceFirst(oldValue = stringTemp, newValue = result.toString())
        }

        return mutableInput
    }

}


private fun String.removeParenthesis(): String {
    var mutableInput = this
    while (mutableInput.contains("(")) {
        val stringTemp = mutableInput.substringAfter("(").substringBefore(")")
        val result = KotlinCalculator().calculate(stringTemp)
        val oldValue = "($stringTemp)"
        mutableInput = mutableInput.replaceFirst(oldValue = oldValue, newValue = result.toString())
    }
    return mutableInput
}


fun main() {
    val kc = KotlinCalculator()
    val result = kc.calculate("2 + 3*5")
    println(result)
}


