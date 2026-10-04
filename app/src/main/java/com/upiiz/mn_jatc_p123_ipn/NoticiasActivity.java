package com.upiiz.mn_jatc_p123_ipn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NoticiasActivity extends AppCompatActivity {

    String[] titulos = {
            "El IPN hace realidad Recinto Universitario Polivirtual en Tamaulipas",
            "Participa IPN en el Torneo Metropolitano de Tiro con Arco",
            "Con creatividad e innovación inauguran 5° Hackatón, edición 2025",
            "Presenta Arturo Reyes Sandoval Informe de Autoevaluación",
            "IPN, presente en foro de ANUIES sobre IA en docencia",
            "Aquí tu nueva noticia"
    };
    String[] tiempos = {"2d", "2d", "2d", "3d", "2d", "1d"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_noticias);

        View header = findViewById(R.id.header);
        int dp16 = (int) (16 * getResources().getDisplayMetrics().density);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            header.setPadding(dp16, systemBars.top + dp16, dp16, dp16);
            return insets;
        });

        // Lista de noticias
        LinearLayout container = findViewById(R.id.containerNews);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < titulos.length; i++) {
            View fila = inflater.inflate(R.layout.item_noticia, container, false);
            ((TextView) fila.findViewById(R.id.tv_titulo)).setText(titulos[i]);
            ((TextView) fila.findViewById(R.id.tv_tiempo)).setText(tiempos[i]);
            container.addView(fila);
        }

        // Tarjetas
        findViewById(R.id.card_eventos).setOnClickListener(v -> mostrar("Eventos"));
        findViewById(R.id.card_deportes).setOnClickListener(v -> mostrar("Deportes"));
        findViewById(R.id.card_pulso).setOnClickListener(v -> mostrar("Pulso"));
        findViewById(R.id.card_academia).setOnClickListener(v -> mostrar("Academia"));
        findViewById(R.id.btn_perfil).setOnClickListener(v -> mostrar("Perfil"));

        // Barra inferior: primero marcar "Noticias" y después poner el listener
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(R.id.nav_noticias);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            } else if (id == R.id.nav_noticias) {
                return true;
            } else if (id == R.id.nav_servicios) {
                startActivity(new Intent(this, Servicios.class));
                finish();
            } else if (id == R.id.nav_acerca) {
                mostrar("Acerca de");
            } else if (id == R.id.nav_acceso) {
                mostrar("Acceso");
            }
            return false;
        });
    }

    private void mostrar(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}