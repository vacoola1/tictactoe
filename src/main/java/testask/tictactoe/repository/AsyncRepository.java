package testask.tictactoe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.concurrent.Callable;

public abstract class AsyncRepository {
    private Scheduler jdbcScheduler;

    @Autowired
    protected void setScheduler(final Scheduler scheduler) {
        this.jdbcScheduler = scheduler;
    }

    protected <T> Mono<T> supplyAsync(Callable<T> callable) {
        return Mono.fromCallable(callable).publishOn(jdbcScheduler);
    }

    protected <T> Flux<T> supplyAsyncMany(Callable<Iterable<T>> callable) {
        return Mono.fromCallable(callable).flatMapMany(Flux::fromIterable).publishOn(jdbcScheduler);
    }

}
