import reactor.core.publisher.Mono
import reactor.core.publisher.switchIfEmpty

fun main(args: Array<String>) {
    //    testErrorHandling()
    testCompletable()
    //    testEmpty()
}

fun testEmpty() {
    Mono.empty<Int>()
        .doOnError {
            println("inside error subscribe")
        }
        .switchIfEmpty {
            Mono.error(NoSuchElementException("not found exception"))
        }
        .subscribe()
}

private fun testCompletable() {
    completableMethod()
        .then(printSomething() { myString ->
            println(myString)
            Mono.just("Inside then")
                .map{
                    println(it)
                    it
                }
        })
        .subscribe()
}

fun printSomething(def: (abc: String) -> Mono<String>): Mono<String> {
    println("A")
    return def("D")
}

private fun completableMethod(): Mono<Unit> {
    return if (false) {
        Mono.empty()
    } else {
        Mono.error(Exception("Some exception"))
    }
}

private fun testErrorHandling() {
    Mono.just(1)
        .flatMap {
            Mono.error<Exception>(Exception("A"))
                .doOnError {
                    println("B")
                }
                .map {
                    println("C")
                    it
                }
        }
        .subscribe({}, {
            println("D")
        })
}
