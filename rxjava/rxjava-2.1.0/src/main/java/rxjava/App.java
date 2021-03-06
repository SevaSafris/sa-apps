package rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import util.Util;

public class App {

  public static void main(String[] args) throws Exception {

    Observable.range(1, 5)
        .subscribe(new Observer<Integer>() {
          @Override
          public void onSubscribe(Disposable d) {
            System.out.println("on subscribe: " + d);

          }

          @Override
          public void onNext(Integer s) {
            System.out.println("on next " + s);

          }

          @Override
          public void onError(Throwable e) {
            System.out.println("on error: " + e);

          }

          @Override
          public void onComplete() {
            System.out.println("on complete");

          }
        });

    Observable.just("Hello", "World")
        .subscribe(System.out::println);

    Util.checkSpan("rxjava-2", 2);

  }

}
