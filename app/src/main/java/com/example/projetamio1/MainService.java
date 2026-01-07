package com.example.projetamio1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class MainService extends Service {
    private static final String TAG = "MainService";
    private Timer timer;  // Déclarer le timer comme variable d'instance

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service créé");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service started (START_STICKY)");

        // Créer et démarrer le timer pour la tâche périodique
        timer = new Timer();

        // scheduleAtFixedRate(tâche, délai initial en ms, période en ms)
        // 0 = démarrage immédiat, 30000 = 30 secondes
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Cette méthode s'exécute toutes les 30 secondes
                Log.d(TAG, "Tâche périodique exécutée - Timer tick");
            }
        }, 0, 30000);

        // This ensures the service restarts automatically if killed by the system
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // IMPORTANT: Arrêter le timer pour éviter les fuites mémoire
        if (timer != null) {
            timer.cancel();
            timer = null;
            Log.d(TAG, "Timer annulé");
        }

        Log.d(TAG, "Service stopped");
    }
}
