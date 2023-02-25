package org.k.shortener.domain.id

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.UUID

class IdUtilsKtTest : FunSpec({
    test("UUID to BigInteger") {
        for (down in 0..10000L) {
            UUID(0, down).toBigInteger() shouldBe BigInteger.valueOf(down)
        }
    }

    test("biginteger to base62") {
        BigInteger.ZERO.toBase62() shouldBe "A"
        BigInteger.valueOf(62).toBase62() shouldBe "BA"
    }

    test("bytebuffer to long") {
        ByteBuffer.wrap(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0)).long shouldBe 0L
        ByteBuffer.wrap(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 1)).long shouldBe 1L
    }

    test("base62 to UUID") {
        "A".base62ToUUID() shouldBe UUID(0, 0)
        "B".base62ToUUID() shouldBe UUID(0, 1)
        "C".base62ToUUID() shouldBe UUID(0, 2)
        "D".base62ToUUID() shouldBe UUID(0, 3)
        "E".base62ToUUID() shouldBe UUID(0, 4)
        "F".base62ToUUID() shouldBe UUID(0, 5)
        "G".base62ToUUID() shouldBe UUID(0, 6)
        "H".base62ToUUID() shouldBe UUID(0, 7)
        "I".base62ToUUID() shouldBe UUID(0, 8)
        "J".base62ToUUID() shouldBe UUID(0, 9)
        "K".base62ToUUID() shouldBe UUID(0, 10)
        "L".base62ToUUID() shouldBe UUID(0, 11)
        "M".base62ToUUID() shouldBe UUID(0, 12)
        "N".base62ToUUID() shouldBe UUID(0, 13)
        "O".base62ToUUID() shouldBe UUID(0, 14)
        "P".base62ToUUID() shouldBe UUID(0, 15)
        "Q".base62ToUUID() shouldBe UUID(0, 16)
        "R".base62ToUUID() shouldBe UUID(0, 17)
        "S".base62ToUUID() shouldBe UUID(0, 18)
        "T".base62ToUUID() shouldBe UUID(0, 19)
        "U".base62ToUUID() shouldBe UUID(0, 20)
        "V".base62ToUUID() shouldBe UUID(0, 21)
        "9".base62ToUUID() shouldBe UUID(0, 61)
        "BA".base62ToUUID() shouldBe UUID(0, 62)
        "BB".base62ToUUID() shouldBe UUID(0, 63)
    }
})
