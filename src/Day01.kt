fun main() {
    fun digVal(line: String, pos: Int): Int? {
        return line[pos].toString().toIntOrNull()
    }

    fun digOrWordVal(line: String, pos: Int): Int? {
        if (digVal(line, pos) != null) {
            return digVal(line, pos)
        }

        val words = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        words.forEach {
            if (pos + it.length <= line.length && line.substring(pos, pos + it.length) == it && words.indexOf(it) > 0) {
                return words.indexOf(it)
            }
        }

        return null
    }

    fun lineValue(line: String, evaluator: (String, Int) -> Int?): Int {
        val digs = line.indices.mapNotNull { evaluator(line, it) }.map { it }

        return 10 * digs.first() + digs.last()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { lineValue(it) { line, pos -> digVal(line, pos) } }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { lineValue(it) { line, pos -> digOrWordVal(line, pos) } }
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
