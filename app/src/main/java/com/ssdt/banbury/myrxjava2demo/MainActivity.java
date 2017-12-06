package com.ssdt.banbury.myrxjava2demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 参考http://www.jianshu.com/p/a93c79e9f689
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        aboutCreate();
//        aboutMap();
//        aboutZip();
//        aboutConcat();
//        aboutFlatMap();
//        aboutConcatMap();
//        aboutDistinct();
//        aboutFilter();
//        aboutBuffer();
//        aboutTimer();
//        aboutInterval();
//        aboutDoOnNext();
//        aboutSkip();
//        aboutTake();
//        aboutJust();
//        aboutSingle();
//        aboutDebounce();
//        aboutDefer();
//        aboutLast();
//        aboutMerge();
//        aboutReduce();
//        aboutScan();
//        aboutWindow();
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    /**
     * 需要注意的几点是：
     * 1）在发射事件中，我们在发射了数值3之后，直接调用了e.onComlete()，虽然无法接收事件，但发送事件还是继续的。
     * 2) 另外一个值得注意的点是，在RxJava 2.x中，可以看到发射事件方法相比1.x多了一个throws Excetion，意味着我们做一些特定操作再也不用try-catch了。
     * 3) 并且2.x 中有一个Disposable概念，这个东西可以直接调用切断，可以看到，当它的isDisposed()返回为false的时候，接收器能正常接收事件，但当其为true的时候，接收器停止了接收。所以可以通过此参数动态控制接收事件了。
     */
    private void aboutCreate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Log.e(TAG, "subscribe: 1" + "\n");
                e.onNext(2);
                Log.e(TAG, "subscribe: 2" + "\n");
                e.onNext(3);
                Log.e(TAG, "subscribe: 3" + "\n");
                e.onComplete();
                e.onNext(4);
                Log.e(TAG, "subscribe: 4" + "\n");
            }
        }).subscribe(new Observer<Integer>() {

            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(TAG, "onNext : value : " + integer + "\n");
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                    Log.e(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n");
            }
        });
    }

    /**
     * map基本作用就是将一个Observable通过某种函数关系，转换为另一种Observable，例子中就是把我们的Integer数据变成了String类型。
     */
    private void aboutMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "This is result" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }

    /**
     * 需要注意的是：
     * 1) zip 组合事件的过程就是分别从发射器A和发射器B各取出一个事件来组合，并且一个事件只能被使用一次，组合的顺序是严格按照事件发送的顺序来进行的，所以上面截图中，可以看到，1永远是和A 结合的，2永远是和B结合的。
     * 2) 最终接收器收到的事件数量是和发送器发送事件最少的那个发送器的发送事件数目相同，所以如截图中，5很孤单，没有人愿意和它交往，孤独终老的单身狗。
     */
    private void aboutZip() {
        Observable.zip(Helper.getStringObservable(), Helper.getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "zip : accept : " + s + "\n");
            }
        });
    }

    /**
     * 发射器B把自己的三个孩子送给了发射器A，让他们组合成了一个新的发射器
     * concat 可以做到不交错的发射两个甚至多个 Observable 的发射事件，并且只有前一个 Observable 终止( onComplete() ) 后才会定义下一个 Observable
     */
    private void aboutConcat() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "zip : accept : " + integer + "\n");
            }
        });
    }

    /**
     * FlatMap 是一个很有趣的东西，我坚信你在实际开发中会经常用到。
     * 它可以把一个发射器Observable 通过某种方法转换为多个Observables，然后再把这些分散的Observables装进一个单一的发射器Observable。
     * 但有个需要注意的是，flatMap并不能保证事件的顺序，如果需要保证，需要用到我们下面要讲的ConcatMap。
     */
    private void aboutFlatMap() {
        Observable.just(1, 2, 3)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("I am value " + integer);
                        }
                        int delayTime = (int) (1 + Math.random() * 10);
                        Log.e(TAG, "aboutFlatMap + apply: " + delayTime);
                        //做一个延时
                        return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "aboutFlatMap + zip : accept : " + s + "\n");
                    }
                });
    }

    /**
     * concatMap 与 FlatMap 的唯一区别就是 concatMap 保证了顺序
     */
    private void aboutConcatMap() {
        Observable.just(1, 2, 3)
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("I am value " + integer);
                        }
                        int delayTime = (int) (1 + Math.random() * 10);
                        Log.e(TAG, "aboutConcatMap + apply: " + delayTime);
                        return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "aboutConcatMap + zip : accept : " + s + "\n");
                    }
                });
    }

    /**
     * 去重
     */
    private void aboutDistinct() {
        Observable.just(1, 1, 2, 2, 3, 3, 1, 2, 3, 4).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "aboutDistinct + zip : accept : " + integer + "\n");
            }
        });
    }

    /**
     * 过滤器
     */
    private void aboutFilter() {
        Observable.just(1, -1, 2, 3, 4, 1)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer > 0;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "aboutFilter + zip : accept : " + integer + "\n");
                    }
                });
    }

    /**
     * buffer 操作符接受两个参数，buffef(count,skip)，作用是将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，然后生成一个 Observable 。
     * <p>
     * 发放输出如下：
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer size : 3
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer value :
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 1
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 2
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 3
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer size : 3
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer value :
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 2
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 3
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 4
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer size : 3
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer value :
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 3
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 4
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 5
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer size : 2
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer value :
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 4
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 5
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer size : 1
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: buffer value :
     * 12-05 05:46:08.361 9505-9505/com.ssdt.banbury.myrxjava2demo E/MainActivity: 5
     * <p>
     * 我们把1,2,3,4,5依次发射出来，经过buffer 操作符，其中参数 count 为3， skip 为1，而我们的输出 依次是 123,234，345，45，5。显而易见，我们 buffer 的第一个参数是count，代表最大取值，在事件足够的时候，一般都是取count个值，然后每次跳过skip个事件。
     */
    private void aboutBuffer() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 1)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.e(TAG, "buffer size : " + integers.size() + "\n");
                        Log.e(TAG, "buffer value : ");
                        for (Integer i : integers) {
                            Log.e(TAG, i + "");
                        }
                        Log.e(TAG, "\n");
                    }
                });
    }

    /**
     * timer 很有意思，相当于一个定时任务。在1.x 中它还可以执行间隔逻辑，但在2.x中此功能被交给了 interval，下一个会介绍。但需要注意的是，timer 和 interval 均默认在新线程。
     */
    private void aboutTimer() {
        Log.e(TAG, "aboutTimer: " + System.currentTimeMillis() / 1000);
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "timer :" + aLong + " at " + System.currentTimeMillis() / 1000 + "\n");
                    }
                });
    }

    Disposable disposable;

    /**
     * interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
     */
    private void aboutInterval() {
        Log.e(TAG, "aboutInterval: " + System.currentTimeMillis() / 1000);
        //由于我们这个是间隔执行，所以当我们的Activity都销毁的时候，实际上这个操作还依然在进行，
        // 所以，我们得花点小心思让我们在不需要它的时候干掉它。
        // 查看源码发现，我们subscribe(Cousumer<? super T> onNext)返回的是Disposable，我们可以在这上面做文章。
        disposable = Observable.interval(2, 4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        Log.e(TAG, "Interval :" + integer + " at " + System.currentTimeMillis() / 1000 + "\n");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * doOnNext
     * 它的作用是让订阅者在接收到数据之前干点有意思的事情。假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。
     */
    private void aboutDoOnNext() {
        Observable.just(1, 2, 3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "doOnNext 保存 " + integer + "成功" + "\n");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "doOnNext :" + integer + "\n");
                    }
                });
    }


    /**
     * skip 很有意思，其实作用就和字面意思一样，接受一个 long 型参数 count ，代表跳过 count 个数目开始接收。
     */
    private void aboutSkip() {
        Observable.just(1, 2, 3)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "doOnNext :" + integer + "\n");
                    }
                });
    }

    /**
     * take，接受一个 long 型参数 count ，代表至多接收 count 个数据。
     */
    private void aboutTake() {
        Observable.just(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "accept: take : " + integer + "\n");
                    }
                });
    }

    /**
     * just，没什么好说的，其实在前面各种例子都说明了，就是一个简单的发射器依次调用onNext()方法。
     */
    private void aboutJust() {
        Observable.just(1, 2, 3, 4, 5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "accept: take : " + integer + "\n");
                    }
                });
    }

    /**
     * Single 只会接收一个参数，而SingleObserver 只会调用onError 或者onSuccess。
     */
    private void aboutSingle() {
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        Log.e(TAG, "single : onSuccess : " + integer + "\n");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "single : onError : " + e.getMessage() + "\n");
                    }
                });
    }

    /**
     * 去除发送频率过快的项，看起来好像没啥用处，但你信我，后面绝对有地方很有用武之地。
     * 后面的发射项如果间隔前面的发射项的，间隔时间在debounce的间隔时间内，则前面的都会被去除掉，而只保留最后一个；
     */
    private void aboutDebounce() {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1); // skip
                        Thread.sleep(400);
                        emitter.onNext(2); // skip 与1间隔400，小于500，去除1
                        Thread.sleep(499);
                        emitter.onNext(3); // skip 与2间隔499，小于500，去除2
                        Thread.sleep(100);
                        emitter.onNext(4); // deliver 与3间隔100，小于500，去除3
                        Thread.sleep(605);
                        emitter.onNext(5); // deliver 与4间隔605，大于500，保留4
                        Thread.sleep(510);
                        emitter.onComplete();
                    }
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "debounce :" + integer + "\n");
                    }
                });
    }

    /**
     * 简单地时候就是每次订阅都会创建一个新的Observable，并且如果没有被订阅，就不会产生新的Observable
     */
    private void aboutDefer() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });

        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "aboutDefer : " + integer + "\n");
            }
        });
    }

    /**
     * todo 不是特别理解这个值的意思
     * last 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项。
     */
    private void aboutLast() {
        Observable.just(1, 2, 3)
                .last(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "aboutLast : " + integer + "\n");
                    }
                });
    }

    /**
     * merge 顾名思义，熟悉版本控制工具的你一定不会不知道 merge 命令，
     * 而在 Rx 操作符中，merge 的作用是把多个 Observable 结合起来，接受可变参数，也支持迭代器集合。
     * 注意它和 concat 的区别在于，不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
     */
    private void aboutMerge() {
        Observable.merge(Observable.just(1, 2, 3, 7, 8, 9), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "aboutMerge : " + integer + "\n");
                    }
                });
    }

    /**
     * reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值。
     * 1 + 2 = 3 + 3 = 6
     */
    private void aboutReduce() {
        Observable.just(1, 2, 3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "aboutReduce : " + integer + "\n");
                    }
                });
    }

    /**
     * scan 操作符作用和上面的 reduce 一致，唯一区别是 reduce 是个只追求结果，而 scan 会始终如一地把每一个步骤都输出。
     */
    private void aboutScan() {
        Observable.just(1, 2, 3)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "aboutScan : " + integer + "\n");
                    }
                });
    }

    /**
     * 按照实际划分窗口，将数据发送给不同的Observable
     * <p>
     * 12-05 07:21:16.721 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: Sub Divide begin...
     * 12-05 07:21:16.722 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 0
     * 12-05 07:21:17.717 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 1
     * 12-05 07:21:18.717 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 2
     * 12-05 07:21:19.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: Sub Divide begin...
     * 12-05 07:21:19.719 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 3
     * 12-05 07:21:20.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 4
     * 12-05 07:21:21.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 5
     * 12-05 07:21:22.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: Sub Divide begin...
     * 12-05 07:21:22.719 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 6
     * 12-05 07:21:23.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 7
     * 12-05 07:21:24.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 8
     * 12-05 07:21:25.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: Sub Divide begin...
     * 12-05 07:21:25.719 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 9
     * 12-05 07:21:26.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 10
     * 12-05 07:21:27.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 11
     * 12-05 07:21:28.717 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: Sub Divide begin...
     * 12-05 07:21:28.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 12
     * 12-05 07:21:29.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 13
     * 12-05 07:21:30.718 32688-32688/com.ssdt.banbury.myrxjava2demo E/MainActivity: aboutWindow : 14
     */
    private void aboutWindow() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(15)
                .window(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        Log.e(TAG, "Sub Divide begin...\n");
                        longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        Log.e(TAG, "aboutWindow : " + aLong + "\n");
                                    }
                                });
                    }
                });
    }
}
