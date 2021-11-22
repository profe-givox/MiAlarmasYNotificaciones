package net.ivanvega.mialarmasynotificaciones;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MiReceiverAlarma extends BroadcastReceiver {
    private static final String CHANNEL_ID = "RECORDATORIOS-TAREAS";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("DIF-ALARMA", "PERRO-ROJO");
        mostrarNotificacion(context, intent);
            Log.d("DIF-ALARMA", "NOTIFICACION LANZADA");



    }

    private void mostrarNotificacion(Context context, Intent intent) {
        //createNotificationChannel(context,intent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Titulo recordatorio")
                .setContentText("Te recuerdo tarea pendiente")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, builder.build());

    }

    public static void createNotificationChannel(Context ctx, Intent intent) {
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
