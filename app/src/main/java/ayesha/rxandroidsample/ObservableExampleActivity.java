package ayesha.rxandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class ObservableExampleActivity extends AppCompatActivity {

    private String TAG = "ObservableExampleActivity";
    private TextView tvResult;
    private Observable<String> animalObservable;
    private Observer<String> observer;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_example);
        tvResult = findViewById(R.id.tvResult);
        animalObservable = getAnimalObservable();
        observer = getAnimalObserver();

    }

    private Observable<String> getAnimalObservable() {
        return Observable.just("Cat", "Dog", "Bull", "Tiger", "Fish");
       // return Observable.fromArray("Cat", "Dog", "Bull", "Tiger", "Fish");
    }

    public void getData(View view) {
        animalObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //If we want to apply some filter before observing
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.startsWith("B");
                    }
                })
                .subscribe(observer);
    }

    public Observer<String> getAnimalObserver() {
        final StringBuilder stringBuilder = new StringBuilder();
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe()");
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
                stringBuilder.append(s + " ");
                tvResult.setText(stringBuilder.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError()");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete()");

            }
        };

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
