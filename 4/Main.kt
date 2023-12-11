fun findLongestRepeatingDecimal() {
    var maxLength = 0
    var result = 0

    for (divisor in 2 until 1000) {
        val hasRemainder = BooleanArray(divisor)
        var numerator = 1
        var position = 0

        while (numerator != 0 && !hasRemainder[numerator]) {
            hasRemainder[numerator] = true
            numerator *= 10
            numerator %= divisor
            position++
        }

        if (position - (if (numerator == 0) 0 else 1) > maxLength) {
            maxLength = position - (if (numerator == 0) 0 else 1)
            result = divisor
        }
    }

    println(result)
}

fun main() {
    findLongestRepeatingDecimal()
}
//ответ 983