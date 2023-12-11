fun calculateTotalRabbits(n: Int, m: Int): Long {
    val lifespan = MutableList(m) { if (it == 0) 1L else 0L }

    for (month in 1 until n) {
        val newborn = lifespan.subList(1, m).sum()
        for (i in m - 1 downTo 1) {
            lifespan[i] = lifespan[i - 1]
        }
        lifespan[0] = newborn
    }

    return lifespan.sum()
}

fun main() {
    val N = 85
    val M = 19

    val result = calculateTotalRabbits(N, M)
    println("кол-во месяцев - $N ")
    println("кроликов - $result")
}
//кол-во месяцев - 85
//кроликов - 258854078921095007