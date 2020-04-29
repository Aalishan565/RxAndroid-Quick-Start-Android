package ayesha.rxandroidsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnJust;
    private Button btnFromArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnJust = findViewById(R.id.btnObservable);
        btnFromArray = findViewById(R.id.btnFromArray);
        btnJust.setOnClickListener(this);
        btnFromArray.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnObservable:
                startActivity(new Intent(this, ObservableExampleActivity.class));
                break;
            case R.id.btnFromArray:

                break;
            default:
        }

    }
}
