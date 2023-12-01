fun main() {
    fun digVal(line: String, pos: Int): Int? {
        return line[pos].toString().toIntOrNull()
    }

    fun digOrWordVal(line: String, pos: Int): Int? {
        val words = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        val fits = { word: String -> pos + word.length <= line.length }
        val matches = { word: String -> line.substring(pos, pos + word.length) == word }
        val isNotZero = { word: String -> words.indexOf(word) > 0 }

        val goodMatch = { word: String -> fits(word) && matches(word) && isNotZero(word) }

        words.forEach {
            if (goodMatch(it)) {
                return words.indexOf(it)
            }
        }

        return digVal(line, pos)
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

    val testInputA = readInput("Day01_test_a")
    check(part1(testInputA) == 142)

    val testInputB = readInput("Day01_test_b")
    check(part2(testInputB) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
