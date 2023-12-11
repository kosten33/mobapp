fun countVowels(input: String) = input.count { it in "aeiouAEIOU" }

fun main() {
    val res = countVowels("country")
    println(res)
}