import reactor.core.publisher.Mono

fun main(args: Array<String>) {
    testErrorHandling()
}

private fun testErrorHandling() {
    Mono.empty<Int>()
        .switchIfEmpty(Mono.error(NoSuchElementException("Element was not found")))
        .flatMap {
            Mono.empty<Int>()
        }
        .switchIfEmpty(Mono.error(NoSuchElementException("Second error message")))
        .onErrorResume { Mono.error(it) }
        .subscribe()
}
