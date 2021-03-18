
import org.junit.*;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void firstMono(){
        Mono.just("A")
        .log()
                .doOnSubscribe(subs -> System.out.println(subs))
                .doOnRequest(req -> System.out.println(req))
                .doOnSuccess(suc -> System.out.println(suc))
        .subscribe(System.out::println);
    }

    @Test
    public void emptyMono(){
        Mono.empty()
                .log()
                .subscribe(System.out::println);
    }

}
