package my.assignment.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.textview);
       getBatteryPercentage();
    }
    public void getBatteryPercentage() {

        final BroadcastReceiver mBrodcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);

                int currentlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (currentlevel >= 0 && scale > 0) {
                    level = (currentlevel * 100) / scale;
                }
                txt.setText("Bettery Level Remaining:" + level + "%");
                txt.setTextColor(Color.GREEN
                );
            }

        };
        IntentFilter iFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBrodcastReceiver,iFilter);
    }

}
