fun rabbit(n: Int, k: Int): Long {
    if (n == 1 || n == 2) {
        return 1
    }
    return rabbit(n - 1, k) + k * rabbit(n - 2, k)
}

fun main() {
    val n = 32
    val k = 5
    val ans = rabbit(n, k)
    println(ans)
}
//ответ 40238153982301