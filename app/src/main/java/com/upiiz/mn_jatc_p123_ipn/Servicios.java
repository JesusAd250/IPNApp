package com.upiiz.mn_jatc_p123_ipn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Servicios extends AppCompatActivity {

    String[] secciones = {
            "Trámites y Gestión",
            "Salud y Bienestar",
            "Beneficios Externos",
            "Información General",
            "Medios y Comunicación",
            "Seguridad"
    };

    String[][] items = {
            {"Credencial Escolar", "Becas", "Registro Examen", "Reinscripción"},
            {"Registra tu NSS", "Afiliación al IMSS", "Cédula PrevenIMSS", "Seguros de Vida y Accidentes"},
            {"Aerolínea Mexicana"},
            {"Calendario Escolar", "Beneficios", "Sendero Seguro"},
            {"Radio IPN", "Canal Once", "Gaceta politécnica", "Cartelera"},
            {"Botón de Pánico", "Números de Emergencia", "Denuncia Segura"}
    };

    int[][] iconos = {
            {R.drawable.ic_person, R.drawable.ic_book, R.drawable.ic_news, R.drawable.ic_news},
            {R.drawable.ic_heart, R.drawable.ic_heart, R.drawable.ic_heart, R.drawable.ic_heart},
            {R.drawable.ic_globe},
            {R.drawable.ic_calendar, R.drawable.ic_heart, R.drawable.ic_info},
            {R.drawable.ic_mic, R.drawable.ic_tv, R.drawable.ic_book, R.drawable.ic_theater},
            {R.drawable.ic_sos, R.drawable.ic_sos, R.drawable.ic_report}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_servicios);

        View header = findViewById(R.id.header);
        int dp16 = (int) (16 * getResources().getDisplayMetrics().density);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            header.setPadding(dp16, systemBars.top + dp16, dp16, dp16);
            return insets;
        });

        // Secciones y filas
        LinearLayout container = findViewById(R.id.containerServicios);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int s = 0; s < secciones.length; s++) {
            View seccion = inflater.inflate(R.layout.item_seccion, container, false);
            ((TextView) seccion.findViewById(R.id.tv_seccion_titulo)).setText(secciones[s]);
            LinearLayout filas = seccion.findViewById(R.id.container_items);

            for (int i = 0; i < items[s].length; i++) {
                View fila = inflater.inflate(R.layout.item_servicio, filas, false);
                String nombre = items[s][i];
                ((TextView) fila.findViewById(R.id.tv_nombre)).setText(nombre);
                ((ImageView) fila.findViewById(R.id.iv_icono)).setImageResource(iconos[s][i]);
                fila.setOnClickListener(v -> mostrar(nombre));
                filas.addView(fila);
            }
            container.addView(seccion);
        }

        findViewById(R.id.btn_perfil).setOnClickListener(v -> mostrar("Perfil"));

        // Barra inferior
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(R.id.nav_servicios);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            } else if (id == R.id.nav_noticias) {
                startActivity(new Intent(this, NoticiasActivity.class));
                finish();
            } else if (id == R.id.nav_servicios) {
                return true;
            } else if (id == R.id.nav_acerca) {
                mostrar("Acerca de");
            } else if (id == R.id.nav_acceso) {
                mostrar("Acceso");
            }
            return true;
        });
    }

    private void mostrar(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}