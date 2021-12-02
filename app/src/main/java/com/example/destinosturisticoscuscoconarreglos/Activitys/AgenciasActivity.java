package com.example.destinosturisticoscuscoconarreglos.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.destinosturisticoscuscoconarreglos.Adaptadores.AgenciasAdaptadores;
import com.example.destinosturisticoscuscoconarreglos.Adaptadores.AgenciasItems;
import com.example.destinosturisticoscuscoconarreglos.MainActivity;
import com.example.destinosturisticoscuscoconarreglos.R;

import java.util.ArrayList;

public class AgenciasActivity extends AppCompatActivity {

    AgenciasAdaptadores adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencias);

        lista = findViewById(R.id.listDestinosBuscar);

        adapter = new AgenciasAdaptadores(this, getDatos());

        lista.setAdapter(adapter);


        // AgenciasAdaptadores
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // La posición donde se hace clic en el elemento de lista se obtiene de la
                // la posición de parámetro de la vista de lista de Android
                AgenciasItems.agenciasItems item = (AgenciasItems.agenciasItems) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "click Largo " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    //metodo para retornar o recibir metodos

    private ArrayList<AgenciasItems.agenciasItems> getDatos() {
        return AgenciasItems.ArregloLista();
    }

    // evento se repite n veces , se usa varias vecxes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buscar_destinos, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}