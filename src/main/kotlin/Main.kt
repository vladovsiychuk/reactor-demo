import reactor.core.publisher.Mono
import reactor.core.publisher.switchIfEmpty
import java.sql.Wrapper

fun main(args: Array<String>) {

    test()
}

fun test() {
    Mono.just("A")
        .flatMap {
            monoVoid(it)
        }
        .then(
            Mono.just("B")
                .flatMap {
                  Mono.just(0)
//                  monoVoid(it)
                }
                .switchIfEmpty(
                    Mono.just("C")
                        .map {
                            println(it)
                        }
                        .flatMap {
                            Mono.empty()
                        }
                )
        )
        .subscribe()
}

fun monoVoid(value: String): Mono<Void> {
    println(value)

    return Mono.empty()
}
