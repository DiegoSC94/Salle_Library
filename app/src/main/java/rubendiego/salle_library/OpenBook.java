package rubendiego.salle_library;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 15/03/2018.
 */

public class OpenBook extends AppCompatActivity {
    public TextView titulo, autor, descripcion;
    public ImageView imageView;
    public Book Libro;
    public ArrayList<Book> librosFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_open_book);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Intent intentLibro = getIntent();
        librosFavoritos = new ArrayList<Book>();
        Libro = intentLibro.getParcelableExtra("Libro");
        titulo = findViewById(R.id.titulo_Open);
        autor = findViewById(R.id.autores_Open);
        descripcion = findViewById(R.id.descripcion_Open);
        imageView = findViewById(R.id.imagenLibro_Open);


        titulo.setText(Libro.getTitulo());
        autor.setText(Libro.getAutor());
        descripcion.setText(Libro.getDescription());
        Picasso.get().load(Libro.getImagen()).into(imageView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_open, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addBar:

                SharedPreferences usuario = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
                String userFavorito = usuario.getString("username", "No hay info");
                SharedPreferences favoritos = getSharedPreferences(userFavorito, Context.MODE_PRIVATE);

/*
                SharedPreferences.Editor editor = favoritos.edit();
                Gson gson = new Gson();
                String json = gson.toJson(librosFavoritos);
                editor.putString("libro", json);
                editor.apply();

*/
                if (favoritos.getString("listObjetos", "no hay nada") == "no hay nada") {
                    librosFavoritos.add(new Book(titulo.toString(), descripcion.toString(), autor.toString(), Libro.getImagen()));
                    String listObjetos = new Gson().toJson(librosFavoritos);


                    SharedPreferences.Editor editor = favoritos.edit();
                    editor.putString("listObjetos", listObjetos);
                    editor.apply();
                } else {
                    String ObjetoGuardado = favoritos.getString("listObjetos", "no hay nada");
                    //Se crea un JSONArray y se guarda el string json

                    ArrayList<Book> listaDeLibros;
                    listaDeLibros = new Gson().fromJson(ObjetoGuardado, (java.lang.reflect.Type) librosFavoritos);


                    if (librosFavoritos.size() < 10) {
                            listaDeLibros.add(new Book(titulo.toString(), descripcion.toString(), autor.toString(), Libro.getImagen()));
                            Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_LONG).show();
                            for (int i=0;i<listaDeLibros.size();i++){
                                Toast.makeText(this, listaDeLibros.get(i).titulo.toString(), Toast.LENGTH_LONG).show();

                            }

                        } else {
                            Toast.makeText(this, "Tienes el maximo de favoritos", Toast.LENGTH_LONG).show();
                        }
                    }


                break;

            default:

                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}

