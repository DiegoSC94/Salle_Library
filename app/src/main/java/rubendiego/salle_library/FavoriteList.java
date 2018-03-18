package rubendiego.salle_library;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Diego on 17/03/2018.
 */

public class FavoriteList extends AppCompatActivity{
    private BookAdapter arrayAdapter;
    public Book Libro;
    private Book[] bookList=new Book[10];
    public ListView listView;
    public ArrayList<Book> librosFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_favorite_book);

listView=findViewById(R.id.lista_favoritos);
        SharedPreferences usuario = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
        String userFavorito = usuario.getString("username", "No hay info");
        SharedPreferences favoritos = getSharedPreferences(userFavorito, Context.MODE_PRIVATE);
        String ObjetoGuardado = favoritos.getString("listObjetos", "no hay nada");
        //Se crea un JSONArray y se guarda el string json

        java.lang.reflect.Type type = (java.lang.reflect.Type) new TypeToken<ArrayList<Book>>(){}.getType();
        librosFavoritos = new Gson().fromJson(ObjetoGuardado, type);
        for (int i=0;i<librosFavoritos.size();i++) {
            arrayAdapter = new BookAdapter(bookList, this);
            bookList[i] = (Book) librosFavoritos.get(i);
            listView.setAdapter(arrayAdapter);
            Toast.makeText(this, bookList[i].titulo, Toast.LENGTH_LONG).show();
        }

        /*
        SharedPreferences usuario = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = usuario.getString("libro", null);

        Type type = (Type) new TypeToken<ArrayList<Book>>(){}.getType();
        Libro = gson.fromJson(json, (java.lang.reflect.Type) type);
        */
    }
}
