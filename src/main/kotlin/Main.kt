import reactor.core.publisher.Mono

fun main(args: Array<String>) {
//        testErrorHandling()
    testCompletable()
}

private fun testCompletable() {
    completableMethod()
        .map {
            println("inside map")
        }
        .subscribe()
}

private fun completableMethod(): Mono<Unit> {
    return if (true) {
        Mono.just(Unit)
    } else {
        Mono.error(Exception("Some exception"))
    }
}

private fun testErrorHandling() {
    Mono.error<Int>(Exception("some error"))
        .subscribe {
            println(it)
        }
}
