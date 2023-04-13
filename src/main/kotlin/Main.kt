import reactor.core.publisher.Mono
import kotlin.Exception

fun main(args: Array<String>) {

    myFunc()
        .subscribe {
            println("My string is: $it")
        }
}

private fun myFunc(): Mono<String> {
    return Mono.zip(
        Mono.just("a"),
        Mono.empty<String>()
            .switchIfEmpty(Mono.error(Exception("exception occurred")))
    )
        .map {
            "print ${it.t1} and ${it.t2}"
        }
        .doOnSuccess {
            println("something something")
        }
}
