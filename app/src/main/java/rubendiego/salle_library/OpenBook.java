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
    public Book[] librosFavoritos = new Book[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_open_book);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Intent intentLibro = getIntent();
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

                if (favoritos.getString("listObjetos", "no hay nada") == "no hay nada") {

                    //String jsonObjetos = new Gson().toJson(Libro);
                    //Crea preferencia

                    StringBuilder stringBuilder = new StringBuilder();

                        stringBuilder.append(titulo);
                        stringBuilder.append(",");
                        stringBuilder.append(autor);
                        stringBuilder.append(",");
                        stringBuilder.append(descripcion);
                        stringBuilder.append(",");
                        stringBuilder.append(imageView);
                        stringBuilder.append(",");


                    //Guarda lista de objetos, en formato .json
                    SharedPreferences.Editor editor = favoritos.edit();
                    editor.putString("listObjetos", stringBuilder.toString());
                    editor.apply();
                } else {
                    String json = new Gson().toJson(favoritos.getString("listObjetos", "no hay nada"));
                    //Se crea un JSONArray y se guarda el string json
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Type listType = (Type) new TypeToken<ArrayList<Book>>() {
                    }.getType();
                    List<Book> listObjetos = new Gson().fromJson(jsonArray, listType);
                    for (int i = 0; i <= listObjetos.size(); i++) {
                        librosFavoritos[i] = new Gson().fromJson(jsonArray);

                        if (librosFavoritos.length != 10) {
                            Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(this, "Tienes el maximo de favoritos", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

