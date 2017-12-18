package dongzhong.testforeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        registerButton = (Button) findViewById(R.id.button_register_firstactivity);
        textView = (TextView) findViewById(R.id.textview_firstactivity);

        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void handleEventMessage(MessageEvent messageEvent) {
        final String messageType = messageEvent.getEventType();
        if (messageType != null && !messageType.isEmpty()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(messageType);
                }
            });
        }
    }
}
