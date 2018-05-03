package rubendiego.salle_library;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_favorite_book);

        listView = findViewById(R.id.lista_favoritos);

        //loadData();

        SharedPreferences sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        String librosConcatenados = sharedPreferences.getString("librosFavoritos", "por defecto");
        librosFavoritos = new ArrayList<>();
        //Gson gson = new Gson();
        //String json = sharedPreferences.getString("librosFavoritos", "por defecto");
        //Log.d("ContenidoJson", String.valueOf(json));
        //Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        String[] datosLibros = librosConcatenados.split(";");

        for (int i = 0; i < datosLibros.length; i+=4) {
            //String[] libros = librosConcatenados.split(";");
            //Log.d("libros:", String.valueOf(libros[i]));

            Log.d("datosLibrosTitulo:", String.valueOf(datosLibros[i]));
            Log.d("datosLibrosDescripcion:", String.valueOf(datosLibros[i+1]));
            Log.d("datosLibrosAutores:", String.valueOf(datosLibros[i+2]));
            Log.d("datosLibrosImagen:", String.valueOf(datosLibros[i+3]));


            Libro = new Book(datosLibros[i], datosLibros[i+1], datosLibros[i+2], datosLibros[i+3]);
            Log.d("libro", String.valueOf(Libro.getTitulo()));

            librosFavoritos.add(Libro);

        }
        //librosFavoritos = gson.fromJson(json, type);
        adapter = new BookFavAdapter(this, librosFavoritos);
        listView.setAdapter(adapter);
        for (int i = 0; i < librosFavoritos.size(); i++) {
            Log.d("size", String.valueOf(librosFavoritos.size()));
            Log.d("ListaFavoritos", String.valueOf(librosFavoritos.get(i).getTitulo()));
        }

        /*
        SharedPreferences usuario = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
        String userFavorito = usuario.getString("username", "No hay info");
        SharedPreferences favoritos = getSharedPreferences(userFavorito, Context.MODE_PRIVATE);
        String ObjetoGuardado = favoritos.getString("listObjetos", "no hay nada");

        //Se crea un JSONArray y se guarda el string json

        java.lang.reflect.Type type = (java.lang.reflect.Type) new TypeToken<ArrayList<Book>>() {
        }.getType();
        librosFavoritos = new Gson().fromJson(ObjetoGuardado, type);
        arrayAdapter = new BookAdapter((Book[]) librosFavoritos.toArray(), this);
        listView.setAdapter(arrayAdapter);
*/
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("librosFavoritos", null);
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        librosFavoritos = gson.fromJson(json, type);
        adapter = new BookFavAdapter(this, librosFavoritos);
        listView.setAdapter(adapter);
        for (int i = 0; i < librosFavoritos.size(); i++) {
            Log.d("size", String.valueOf(librosFavoritos.size()));
            Log.d("ListaFavoritos", String.valueOf(librosFavoritos.get(i).getTitulo()));
        }

        if (librosFavoritos == null){
            librosFavoritos = new ArrayList<>();
        }
    }
}
