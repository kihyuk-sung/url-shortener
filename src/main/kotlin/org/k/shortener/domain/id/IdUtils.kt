package org.k.shortener.domain.id

import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.*

fun Id<UUID>.toBase62(): String = value
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
