package dongzhong.testforeventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendMessageButton;
    private Button startFirstActivityButton;
    private Button startSecondActivityButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessageButton = (Button) findViewById(R.id.button_send_message);
        startFirstActivityButton = (Button) findViewById(R.id.button_start_first_activity);
        startSecondActivityButton = (Button) findViewById(R.id.button_start_second_activity);
        textView = (TextView) findViewById(R.id.textview_main_activity);

        sendMessageButton.setOnClickListener(this);
        startFirstActivityButton.setOnClickListener(this);
        startSecondActivityButton.setOnClickListener(this);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_send_message:
                EventBus.getDefault().postSticky(new MessageEvent("test for eventbus"));
                Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT);
                break;
            case R.id.button_start_first_activity:
                Intent firstIntent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(firstIntent);
                break;
            case R.id.button_start_second_activity:
                Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(secondIntent);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEventBusMessage(MessageEvent messageEvent) {
        final String eventType = messageEvent.getEventType();
        if (eventType != null && !eventType.isEmpty()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(eventType);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
