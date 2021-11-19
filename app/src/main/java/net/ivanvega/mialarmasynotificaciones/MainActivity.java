package net.ivanvega.mialarmasynotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        programarAlarma();


    }


    void programarAlarma(){
        Toast.makeText(this, "Se programo alarma de 60 s", Toast.LENGTH_LONG).show();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

/*
        alarmManager.setInexactRepeating();
         alarmManager.setAlarmClock();
*/

        Intent intent = new Intent(this, MiReceiverAlarma.class);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        10 * 1000, alarmIntent);

    }

}