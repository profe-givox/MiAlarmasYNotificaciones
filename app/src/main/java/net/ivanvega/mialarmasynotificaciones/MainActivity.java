package net.ivanvega.mialarmasynotificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;

    private static final String CHANNEL_ID = "RECORDATORIOS-TAREAS";

    Button btnNot, btnAlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1) Crear el canal de notificaciones
        MiReceiverAlarma.createNotificationChannel(getApplicationContext(), null);

        //createNotificationChannel(getApplicationContext(), null);

        btnNot = findViewById(R.id.btnNot);
        btnNot.setOnClickListener(view -> {
            //2) Construir  notificacion y lnzar
            this.mostrarNotificacion(getApplicationContext(), null);
        });

        btnAlar = findViewById(R.id.btnAlarm);
        btnAlar.setOnClickListener(view -> {
            programarAlarma();
        });




    }


    void programarAlarma(){
        Toast.makeText(this, "Se programo alarma de 60 s", Toast.LENGTH_LONG).show();

        //ALARMA. 1) Alarm Manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

/*
        alarmManager.setInexactRepeating();
         alarmManager.setAlarmClock();
*/

        //Alarma. 2) Definir un Pending Intent para cachar cuando la alarma sea lanzada.
        // Se requiere crear un Broadcast Receiver
        Intent intent = new Intent(getApplicationContext(),
                MiReceiverAlarma.class);
        PendingIntent alarmIntent =
                PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        10 * 1000, alarmIntent);

    }

    private void mostrarNotificacion(Context context, Intent intent) {
        //createNotificationChannel(context,intent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Titulo recordatorio")
                .setContentText("Te recuerdo tarea pendiente")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, builder.build());

    }

    public  void createNotificationChannel(Context ctx, Intent intent) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = ctx.getString(R.string.channel_name);
            String description = ctx.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this

            NotificationManager notificationManager = ctx.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}