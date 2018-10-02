package developersudhanshu.com.jokedisplaylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        TextView jokeDisplay = findViewById(R.id.tv_joke_display);

        if(getIntent() != null && getIntent().hasExtra(JokeConstants.JOKE_INTENT_EXTRA)) {
            String joke = getIntent().getStringExtra(JokeConstants.JOKE_INTENT_EXTRA);
            if(!TextUtils.isEmpty(joke)){
                jokeDisplay.setText(joke);
            }
        }
    }
}
