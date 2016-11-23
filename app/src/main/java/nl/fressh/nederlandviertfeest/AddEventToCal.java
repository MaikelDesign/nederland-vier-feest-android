package nl.fressh.nederlandviertfeest;

import android.content.Intent;
import android.provider.CalendarContract;
import java.util.Calendar;

public class AddEventToCal{

    public Intent onAddEventClicked(String title, String description, String location, String timeBegin, String timeEnd) {

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");



        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();

        System.out.println("startTime: " + startTime);

        long timeBeginInt = Long.parseLong(timeBegin) * 1000;

        System.out.println("beginTime: " + timeBeginInt);

        long timeEndInt = Long.parseLong(timeEnd) * 1000;
//        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeBeginInt);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, timeEndInt);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);

        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
//        intent.putExtra(CalendarContract.Events.RRULE, freq);

        return intent;
    }
}
