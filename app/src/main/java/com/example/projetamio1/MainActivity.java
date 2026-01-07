package com.example.projetamio1;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
        Log.d("MainActivity", "Création de l'activité");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// 2. Start the Service
        Intent serviceIntent = new Intent(this, MainService.class);
        startService(serviceIntent);

    }
    @Override
    protected void onDestroy() {
        // 3. Stop the Service
        // We create an intent (or reuse one) to tell the system to stop the service
        Intent serviceIntent = new Intent(this, MainService.class);
        stopService(serviceIntent);

        Log.d("MainActivity", "Destruction de l'activité - Arrêt du service");

        super.onDestroy();
    }
}

