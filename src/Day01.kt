fun main() {
    fun digval(line: String, pos: Int): Int? {
        return line[pos].toString().toIntOrNull()
    }

    fun digorwordval(line: String, pos: Int): Int? {
        if (digval(line, pos) != null) {
            return digval(line, pos)
        }

        val words = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        words.forEach {
            if (pos + it.length <= line.length && line.substring(pos, pos + it.length) == it && words.indexOf(it) > 0) {
                return words.indexOf(it)
            }
        }

        return null
    }

    fun linedigval(line: String): Int {
        val digs = line.indices.map { digval(line, it) }.filter { it != null }.map { it!! }

        return 10 * digs.first() + digs.last()
    }

    fun linedigwordval(line: String): Int {
        val digs = line.indices.map { digorwordval(line, it) }.filter { it != null }.map { it!! }

        return 10 * digs.first() + digs.last()
    }

    fun part1(input: List<String>): Int {
        return input.map { linedigval(it) }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { linedigwordval(it) }.sum()
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
