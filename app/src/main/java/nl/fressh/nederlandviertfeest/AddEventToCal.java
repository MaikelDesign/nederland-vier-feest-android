package nl.fressh.nederlandviertfeest;

import android.content.Intent;
import android.provider.CalendarContract;
import java.util.Calendar;

public class AddEventToCal{

    public static Object onAddEventClicked;
    String Title;
    String Description;
    String Location;
    String Freq = "FREQ=YEARLY";


    public Intent onAddEventClicked() {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, Title);
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  Description);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, Location);
        intent.putExtra(CalendarContract.Events.RRULE, Freq);

        return intent;
    }
}
