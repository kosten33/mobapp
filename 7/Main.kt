enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

class Robot(var x: Int, var y: Int, var direction: Direction) {
    fun stepForward() {
        when (direction) {
            Direction.RIGHT -> x++
            Direction.LEFT -> x--
            Direction.UP -> y++
            Direction.DOWN -> y--
        }
    }

    fun turnRight() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.DOWN
            Direction.LEFT -> Direction.UP
            Direction.UP -> Direction.RIGHT
            Direction.DOWN -> Direction.LEFT
        }
    }

    fun turnLeft() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.UP
            Direction.LEFT -> Direction.DOWN
            Direction.UP -> Direction.LEFT
            Direction.DOWN -> Direction.RIGHT
        }
    }

    override fun toString(): String {
        return "(${x}, ${y}), facing $direction"
    }
}

fun moveRobot(r: Robot, toX: Int, toY: Int) {
    val dx = toX - r.x
    val dy = toY - r.y

    val turnCommands = when {
        dx > 0 -> when (r.direction) {
            Direction.RIGHT -> emptyList()
            Direction.LEFT -> listOf(r::turnRight)
            else -> listOf(r::turnRight, r::turnRight)
        }
        dx < 0 -> when (r.direction) {
            Direction.RIGHT -> listOf(r::turnLeft)
            Direction.LEFT -> emptyList()
            else -> listOf(r::turnLeft, r::turnLeft)
        }
        dy > 0 -> when (r.direction) {
            Direction.UP -> emptyList()
            else -> listOf(r::turnRight, r::turnRight)
        }
        dy < 0 -> when (r.direction) {
            Direction.DOWN -> emptyList()
            else -> listOf(r::turnLeft, r::turnLeft)
        }
        else -> emptyList()
    }

    turnCommands.forEach { it.invoke() }

    repeat(maxOf(Math.abs(dx), Math.abs(dy))) {
        r.stepForward()
    }
}

fun printRobot(r: Robot) {
    println("$r\n")
}

fun main() {
    val r = Robot(0, 0, Direction.RIGHT)

    println("Starting position:")
    printRobot(r)

    moveRobot(r, 1, 0) // Example movement to (1, 0)
    println("Moved to (1, 0):")
    printRobot(r)
}
//Starting position:
//(0, 0), facing RIGHT
//Moved to (1, 0):
//(1, 0), facing RIGHT