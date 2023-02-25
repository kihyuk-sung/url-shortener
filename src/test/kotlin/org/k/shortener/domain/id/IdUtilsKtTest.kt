package org.k.shortener.domain.id

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigInteger
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
})
