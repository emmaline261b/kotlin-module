package com.epam.cdp.caclulator

import java.util.Scanner

class CalcIO {

    private val scanner = Scanner(System.`in`)

    fun readInput(input: String): String {
        var input = ""
        while (scanner.hasNext()) {
            input += scanner.nextLine();
        }
        return input;
    }

    fun printOutput(output: String) {
        println(output);
    }


}