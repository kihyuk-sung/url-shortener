package org.k.shortener.handler

import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.k.shortener.config.CreateShortUrlDto
import org.k.shortener.config.RouterConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest
@ContextConfiguration(classes = [RouterConfiguration::class])
internal class ShortUrlHandlerKtTest(private val webClient: WebTestClient) : FunSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        test("handleShortUrlHandler") {
            webClient.get()
                .uri("/helloWorld")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(String::class.java)
                .value {
                    it shouldBe "helloWorld"
                }
        }

        test("post url") {
            webClient.post()
                .uri("/url")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(CreateShortUrlDto("http://www.example.com"))
                .exchange()
                .expectBody<CreateShortUrlDto>()
                .value {
                    it shouldBe CreateShortUrlDto("http://www.example.com")
                }
        }
    }
}
