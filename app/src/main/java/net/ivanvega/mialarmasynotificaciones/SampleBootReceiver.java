package net.ivanvega.mialarmasynotificaciones;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class SampleBootReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                    // Set the alarm here.
                    // Restauro o reprogramo las alarmas
                    // ir a la base de datos verificar todas las tareas y
                    // recordotarios que no se han cumplido para reprogramarlas
                    //con alarm manager


                AlarmManager alarmManager;

                 alarmManager =  (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                Intent intentR = new Intent(context,
                        MiReceiverAlarma.class);
                PendingIntent alarmIntent =
                        PendingIntent.getBroadcast(context, 0, intentR, 0);

                alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() +
                                20 * 1000, alarmIntent);

            }
        }
    }
    