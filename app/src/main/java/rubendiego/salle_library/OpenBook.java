package rubendiego.salle_library;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * <p>Esta clase te permite guardar en favoritos tu libro y tambien verlo abierto,es dir ,  ver sus caracteristicas mas ampliamente  </p>
 *
 * @author Ruben y Diego on 07/03/2018.
 */

public class OpenBook extends AppCompatActivity {
    public TextView titulo, autor, descripcion;
    public ImageView imageView;
    public Book Libro;
    public ArrayList<Book> librosFavoritos;
    public String libroConcatenado;

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

        Log.d("titulo libro", String.valueOf(Libro.getTitulo()));
        SharedPreferences sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /*
        SharedPreferences usuario = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
        String userFavorito = usuario.getString("username", "No hay info");
        SharedPreferences favoritos = getSharedPreferences(userFavorito, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = favoritos.edit();
        */

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

                librosFavoritos.add(new Book(Libro.getTitulo(), Libro.getDescription(), Libro.getAutor(), Libro.getImagen()));

                for (int i = 0; i < librosFavoritos.size(); i++) {
                    Log.d("insertadoTitulo", String.valueOf(librosFavoritos.get(i).getTitulo()));
                    Log.d("insertadoAutor", String.valueOf(librosFavoritos.get(i).getAutor()));
                    Log.d("imagen", String.valueOf(librosFavoritos.get(i).getImagen()));
                    Log.d("descripcion", String.valueOf(librosFavoritos.get(i).getDescription()));
                }

                SharedPreferences sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Log.d("listaDeLibros", String.valueOf(librosFavoritos.size()));
                //Gson gson = new Gson();
                //String json = gson.toJson(librosFavoritos);
                //Log.d("contenidoJsonOpenBook", String.valueOf(json));
                libroConcatenado = "";
                for (int i = 0; i < librosFavoritos.size(); i++) {
                    Book libro = librosFavoritos.get(i);
                    libroConcatenado += libro.getTitulo() + ";" + libro.getDescription() + ";" + libro.getAutor() + ";" + libro.getImagen() + ";";
                }
                Log.d("libroContatenado", String.valueOf(libroConcatenado));
                editor.putString("librosFavoritos", libroConcatenado);
                editor.commit();
                /*
                for (int i = 0; i < librosFavoritos.size(); i++) {
                    Log.d("size", String.valueOf(librosFavoritos.size()));
                    Log.d("infoTodoElLibro", String.valueOf(librosFavoritos.get(i).getTitulo()));
                }
*/
/*
                SharedPreferences usuario = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
                String userFavorito = usuario.getString("username", "No hay info");
                SharedPreferences favoritos = getSharedPreferences(userFavorito, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = favoritos.edit();
                if (favoritos.getString("listObjetos", "no hay nada") == "no hay nada") {
                    librosFavoritos.add(new Book(titulo.toString(), descripcion.toString(), autor.toString(), Libro.getImagen()));
                    String listObjetos = new Gson().toJson(librosFavoritos);


                    editor.putString("listObjetos", listObjetos);
                    editor.apply();
                } else {
                    String ObjetoGuardado = favoritos.getString("listObjetos", "no hay nada");
                    //Se crea un JSONArray y se guarda el string json

                    Type type = (Type) new TypeToken<ArrayList<Book>>() {
                    }.getType();
                    ArrayList<Book> listaDeLibros;
                    listaDeLibros = new Gson().fromJson(ObjetoGuardado, type);
                    for (int i = 0; i < listaDeLibros.size(); i++) {
                        Log.d("info1", String.valueOf(listaDeLibros.get(i).titulo));
                    }


                    if (librosFavoritos.size() < 10) {
                        listaDeLibros.add(Libro);
                        Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_LONG).show();
                        String listObjetos = new Gson().toJson(listaDeLibros);
                        editor.putString("listObjetos", listObjetos);

                        for (int i = 0; i < listaDeLibros.size(); i++) {
                            //Toast.makeText(this, listaDeLibros.get(i).titulo, Toast.LENGTH_LONG).show();
                            Log.d("info", String.valueOf(listaDeLibros.get(i).titulo));
                        }

                        editor.commit();

                    } else {
                        Toast.makeText(this, "Tienes el maximo de favoritos", Toast.LENGTH_LONG).show();
                    }

                }

*/
                break;

            default:

                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}

