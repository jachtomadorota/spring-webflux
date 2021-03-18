
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
                .subscribe(System.out::println,null, () -> System.out.println("Done"));
    }

    //unchecked exception

    @Test
    public void errorMono(){
        Mono.error(new RuntimeException())
                .log()
                .subscribe();
    }

    //checked exception - try..catch resources doesn't work in reactive programming

    @Test
    public void errorMonoChecked(){
        Mono.error(new Exception())
                .log()
                .subscribe(System.out::println, e -> System.out.println("Error " + e));
    }

    @Test
    public void errorDo(){
        Mono.error(new Exception())
                .doOnError(e -> System.out.println("Error " + e))
                .log()
                .subscribe();
    }

}
