package nl.fressh.nederlandviertfeest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Ruben on 19-11-16.
 */

public class TimeZoneChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("OnTimeChange");
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_TIME_CHANGED)) {
            Toast.makeText(context, "Time / date has changed", Toast.LENGTH_SHORT).show();
        } else if (action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {
            Toast.makeText(context, "Timezone has changed", Toast.LENGTH_SHORT).show();
        }
    }
}
