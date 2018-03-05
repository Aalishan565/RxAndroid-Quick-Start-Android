package ayesha.rxandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv=findViewById(R.id.tv);
        observable = Observable.just("Hello world from rxjava");
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mTv.setText(s.toString());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void getData(View view) {
        observable.subscribe(observer);
    }
}
