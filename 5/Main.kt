import java.io.File

fun funGraph(length: Int, measurements: List<Double>): List<Double> {
    val graph = mutableListOf<Double>()

    graph.add(measurements[0])

    for (i in 1 until length - 1) {
        val average = (measurements[i - 1] + measurements[i] + measurements[i + 1]) / 3.0
        graph.add(average)
    }

    graph.add(measurements[length - 1])
    return graph
}

fun main() {
    val file = File("src/measurements.txt")
    val lines = file.readLines()

    val length = lines[0].toInt()
    val measurements = lines[1].split(" ").map { it.toDouble() }

    val graph = funGraph(length, measurements)
    for (value in graph) {
        println("%.1f".format(value))
    }
}
