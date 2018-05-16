package rubendiego.salle_library;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * <p>Esta clase permite almacenar tus libros favoritos en una lista</p>
 *
 * @author Diego y Ruben on 17/03/2018.
 */

public class FavoriteList extends AppCompatActivity {
    private BookFavAdapter adapter;
    private Book Libro;
    private ListView listView;
    private ArrayList<Book> librosFavoritos;
    private String librosConcatenados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_favorite_book);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listView = findViewById(R.id.lista_favoritos);

        SharedPreferences sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        librosConcatenados = sharedPreferences.getString("librosFavoritos", "por defecto");

        loadData();


    }

    private void loadData(){
        if(!librosConcatenados.equals("por defecto")){
            if(!librosConcatenados.equals("")){
                librosFavoritos = new ArrayList<>();
                String[] datosLibros = librosConcatenados.split(";");

                for (int i = 0; i < datosLibros.length; i+=4) {
/*
                Log.d("datosLibrosTitulo:", String.valueOf(datosLibros[i]));
                Log.d("datosLibrosDescripcion:", String.valueOf(datosLibros[i+1]));
                Log.d("datosLibrosAutores:", String.valueOf(datosLibros[i+2]));
                Log.d("datosLibrosImagen:", String.valueOf(datosLibros[i+3]));
*/

                    Libro = new Book(datosLibros[i], datosLibros[i+1], datosLibros[i+2], datosLibros[i+3]);
                    Log.d("libro", String.valueOf(Libro.getTitulo()));

                    librosFavoritos.add(Libro);

                }
                adapter = new BookFavAdapter(this, librosFavoritos);
                listView.setAdapter(adapter);
                for (int i = 0; i < librosFavoritos.size(); i++) {
                    Log.d("size", String.valueOf(librosFavoritos.size()));
                    Log.d("ListaFavoritos", String.valueOf(librosFavoritos.get(i).getTitulo()));
                }
            }
        }else {
            librosFavoritos = new ArrayList<>();
            adapter = new BookFavAdapter(this, librosFavoritos);
            listView.setAdapter(adapter);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_favoritos, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delBar:

                SharedPreferences sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String libroConcatenado = null;

                editor.putString("librosFavoritos", libroConcatenado);
                editor.apply();

                loadData();
                finish();

                break;

            default:

                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
