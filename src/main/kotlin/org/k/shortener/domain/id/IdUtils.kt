package org.k.shortener.domain.id

import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.*

fun Id<UUID>.toBase62(): String = value
    .toBigInteger()
    .toBase62()

fun UUID.toBase62(): String = this
    .toBigInteger()
    .toBase62()

fun UUID.toBigInteger(): BigInteger = ByteBuffer.wrap(ByteArray(16))
    .apply { putLong(this@toBigInteger.mostSignificantBits) }
    .apply { putLong(this@toBigInteger.leastSignificantBits) }
    .array()
    .let(::BigInteger)

fun BigInteger.toBase62(): String = this.toBase62(mutableListOf())

private val BIGINT_62 = BigInteger.valueOf(62)

private tailrec fun BigInteger.toBase62(acc: MutableList<Int>): String = when (this) {
    BigInteger.ZERO -> acc.apply { if (acc.isEmpty()) acc.add(0) }
        .reversed()
        .map { it.toBase62Digit() }
        .joinToString("")

    else -> divide(BIGINT_62)
        .toBase62(
            acc.apply {
                add(
                    mod(BIGINT_62).toInt()
                )
            }
        )
}

private val base62DigitMap = charArrayOf(
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    'H',
    'I',
    'J',
    'K',
    'L',
    'M',
    'N',
    'O',
    'P',
    'Q',
    'R',
    'S',
    'T',
    'U',
    'V',
    'W',
    'X',
    'Y',
    'Z',
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g',
    'h',
    'i',
    'j',
    'k',
    'l',
    'm',
    'n',
    'o',
    'p',
    'q',
    'r',
    's',
    't',
    'u',
    'v',
    'w',
    'x',
    'y',
    'z',
    '0',
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
)

private fun Int.toBase62Digit(): Char = base62DigitMap.getOrNull(this) ?: throw AssertionError()

private val base62Digit = mapOf(
    'A' to (0).toBigInteger(),
    'B' to (1).toBigInteger(),
    'C' to (2).toBigInteger(),
    'D' to (3).toBigInteger(),
    'E' to (4).toBigInteger(),
    'F' to (5).toBigInteger(),
    'G' to (6).toBigInteger(),
    'H' to (7).toBigInteger(),
    'I' to (8).toBigInteger(),
    'J' to (9).toBigInteger(),
    'K' to (10).toBigInteger(),
    'L' to (11).toBigInteger(),
    'M' to (12).toBigInteger(),
    'N' to (13).toBigInteger(),
    'O' to (14).toBigInteger(),
    'P' to (15).toBigInteger(),
    'Q' to (16).toBigInteger(),
    'R' to (17).toBigInteger(),
    'S' to (18).toBigInteger(),
    'T' to (19).toBigInteger(),
    'U' to (20).toBigInteger(),
    'V' to (21).toBigInteger(),
    'W' to (22).toBigInteger(),
    'X' to (23).toBigInteger(),
    'Y' to (24).toBigInteger(),
    'Z' to (25).toBigInteger(),
    'a' to (26).toBigInteger(),
    'b' to (27).toBigInteger(),
    'c' to (28).toBigInteger(),
    'd' to (29).toBigInteger(),
    'e' to (30).toBigInteger(),
    'f' to (31).toBigInteger(),
    'g' to (32).toBigInteger(),
    'h' to (33).toBigInteger(),
    'i' to (34).toBigInteger(),
    'j' to (35).toBigInteger(),
    'k' to (36).toBigInteger(),
    'l' to (37).toBigInteger(),
    'm' to (38).toBigInteger(),
    'n' to (39).toBigInteger(),
    'o' to (40).toBigInteger(),
    'p' to (41).toBigInteger(),
    'q' to (42).toBigInteger(),
    'r' to (43).toBigInteger(),
    's' to (44).toBigInteger(),
    't' to (45).toBigInteger(),
    'u' to (46).toBigInteger(),
    'v' to (47).toBigInteger(),
    'w' to (48).toBigInteger(),
    'x' to (49).toBigInteger(),
    'y' to (50).toBigInteger(),
    'z' to (51).toBigInteger(),
    '0' to (52).toBigInteger(),
    '1' to (53).toBigInteger(),
    '2' to (54).toBigInteger(),
    '3' to (55).toBigInteger(),
    '4' to (56).toBigInteger(),
    '5' to (57).toBigInteger(),
    '6' to (58).toBigInteger(),
    '7' to (59).toBigInteger(),
    '8' to (60).toBigInteger(),
    '9' to (61).toBigInteger(),
)

fun String.base62ToUUID(): UUID = this
    .toBigInteger()
    .toByteArray()
    .run {
        val upper = ByteArray(8)
        val lower = ByteArray(8)
        for (i in 0 until 8) {
            lower[7 - i] = this.getOrElse(i) { 0 }
        }
        for (i in 8 until 16) {
            upper[15 - i] = this.getOrElse(i) { 0 }
        }
        upper to lower
    }
    .let { (upper, lower) -> ByteBuffer.wrap(upper) to ByteBuffer.wrap(lower) }
    .let { (upper, lower) -> upper.long to lower.long }
    .let { (upper, lower) -> UUID(upper, lower) }
private fun String.toBigInteger(): BigInteger = fold(BigInteger.ZERO) { acc, c ->
    acc * BIGINT_62 + (base62Digit[c] ?: throw NumberFormatException())
}
