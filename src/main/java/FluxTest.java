import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class FluxTest {

    @Test
    public void fluxTest(){
        Flux.just("A","B","C")
                .log()
                .subscribe();
    }

    //method fromIterable enables iterate on every object - without this method, method from Flux interface onNext() gather all objects together

    @Test
    public void fluxTestArray(){
        Flux.fromIterable(Arrays.asList("A","B","C"))
                .log()
                .subscribe();
    }

    @Test
    public void fluxInRange(){
        Flux.range(1,10)
                .log()
                .subscribe();
    }

    @Test
    public void fluxInterval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .take(2)
                .subscribe();
        Thread.sleep(5000);
    }

    @Test
    public void fluxTest2(){
        Flux.range(1,5)
                .log()
                .subscribe(null,null,null, s -> s.request(3));
    }

    @Test
    public void fluxTestBasesubscriber(){
        Flux.range(1,10)
                .log()
                .subscribe(new BaseSubscriber<Integer>() {
                    int elementToProcess = 3;
                    int counter = 0;
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("Subscribed!");
                        request(elementToProcess);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        counter++;
                        if(counter == elementToProcess){
                            counter =0;
                            Random random = new Random();
                            elementToProcess = random.ints(1,5)
                                    .findFirst().getAsInt();
                            request(elementToProcess);
                        }
                    }
                });
    }
}
