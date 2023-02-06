package com.epam.cdp.caclulator

class KotlinCalculator {

    fun calculate(input: String): Int {
        var mutableInput = "$input"
        while (mutableInput.contains("(")) {
            mutableInput = removeParenthesis(mutableInput)
        }
        var stringList = createListOfSubstrates(mutableInput)
        stringList = multiplyOrDivide(stringList)
        val sum = addOrSubtract(stringList)

        return sum
    }

    private fun addOrSubtract(stringList: java.util.ArrayList<String>): Int {
        var mutableStringList = stringList
        var firstOperant = 0
        var secondOperant = 0
        var operator = Operator.ADDITION

        var sum = mutableStringList.get(0).toInt()
        for (i in 1 until mutableStringList.size - 1) {
            if (mutableStringList.get(i).equals("+") || mutableStringList.get(i).equals("-")) {
                firstOperant = sum
                operator = operator.checkSign(stringList.get(i))
                secondOperant = mutableStringList.get(i + 1).toInt()

                sum = operator.mathOperation(firstOperant, secondOperant)
            }
        }
        return sum
    }

    private fun multiplyOrDivide(stringList: java.util.ArrayList<String>): java.util.ArrayList<String> {
        var mutableStringList = stringList
        var firstOperant = 0
        var secondOperant = 0
        var operator = Operator.ADDITION

        while (mutableStringList.contains("*") || mutableStringList.contains("/")) {
            for (i in 1 until mutableStringList.size - 1) {
                if (mutableStringList.get(i).equals("*") || mutableStringList.get(i).equals("/")) {
                    firstOperant = mutableStringList.get(i - 1).toInt()
                    operator = operator.checkSign(mutableStringList.get(i))
                    secondOperant = mutableStringList.get(i + 1).toInt()

                    mutableStringList.removeAt(i + 1)
                    mutableStringList.removeAt(i)
                    mutableStringList.removeAt(i - 1)
                    mutableStringList.add(i - 1, operator.mathOperation(firstOperant, secondOperant).toString())
                    break
                }
            }
        }

        return mutableStringList
    }

    private fun createListOfSubstrates(input: String): java.util.ArrayList<String> {
        var stringTemp = ""
        var stringList = ArrayList<String>()

        for (c in input) {
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


//fun main() {
//    val kc = KotlinCalculator()
//    val result = kc.calculate("(1+2)/3*2 - 2 * (1+1)/1")
//    println(result)
//}


