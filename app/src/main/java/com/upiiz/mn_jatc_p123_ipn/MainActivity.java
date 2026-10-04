package com.upiiz.mn_jatc_p123_ipn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View header = findViewById(R.id.header);
        int dp16 = (int) (16 * getResources().getDisplayMetrics().density);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            header.setPadding(dp16, systemBars.top + dp16, dp16, dp16);
            return insets;
        });

        // Carrusel
        ImageCarousel carousel = findViewById(R.id.carousel);
        carousel.registerLifecycle(getLifecycle());

        List<CarouselItem> list = new ArrayList<>();
        list.add(new CarouselItem(R.drawable.poli1, "Poli 1"));
        list.add(new CarouselItem(R.drawable.poli2, "Poli 2"));
        list.add(new CarouselItem(R.drawable.poli3, "Poli 3"));
        list.add(new CarouselItem(R.drawable.poli4, "Poli 4"));

        carousel.setShowCaption(true);
        carousel.setShowIndicator(true);
        carousel.setShowNavigationButtons(false);
        carousel.setData(list);

        // Tarjetas de acceso rápido
        findViewById(R.id.card_calendario).setOnClickListener(v -> mostrar("Calendario"));
        findViewById(R.id.card_donar).setOnClickListener(v -> mostrar("Donar"));
        findViewById(R.id.card_oncetv).setOnClickListener(v -> mostrar("Once TV"));
        findViewById(R.id.card_emergencias).setOnClickListener(v -> mostrar("Emergencias"));
        findViewById(R.id.btn_perfil).setOnClickListener(v -> mostrar("Perfil"));
        findViewById(R.id.card_cultura_eventos).setOnClickListener(v -> mostrar("Eventos"));
        findViewById(R.id.card_cultura_noticias).setOnClickListener(v ->
                startActivity(new Intent(this, NoticiasActivity.class)));
        findViewById(R.id.card_cultura_gaceta).setOnClickListener(v -> mostrar("Gaceta"));
        findViewById(R.id.card_cultura_cartelera).setOnClickListener(v -> mostrar("Cartelera"));
        findViewById(R.id.card_servicios_denuncia).setOnClickListener(v -> mostrar("Denuncia segura"));

        // Barra inferior
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_noticias) {
                startActivity(new Intent(this, NoticiasActivity.class));
            } else if (id == R.id.nav_servicios) {
                startActivity(new Intent(this, Servicios.class));
            } else if (id == R.id.nav_acerca) {
                mostrar("Acerca de");
            } else if (id == R.id.nav_acceso) {
                mostrar("Acceso");
            }
            return false; // no cambia la pestaña marcada
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    private void mostrar(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
