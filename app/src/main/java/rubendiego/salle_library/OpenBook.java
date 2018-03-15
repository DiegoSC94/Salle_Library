package rubendiego.salle_library;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Diego on 15/03/2018.
 */

public class OpenBook extends AppCompatActivity{
public TextView titulo,autor,descripcion;
public ImageView imageView;
   public Intent intentLibro=getIntent();
public  Book Libro=intentLibro.getParcelableExtra("Libro");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_open_book);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        titulo=findViewById(R.id.titulo_Open);
        autor=findViewById(R.id.autores_Open);
        descripcion=findViewById(R.id.descripcion_Open);
        imageView=findViewById(R.id.imagenLibro_Open);



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
                SharedPreferences datos = getSharedPreferences("faboritos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = datos.edit();
                editor.putString("Libro", String.valueOf(Libro));

                editor.commit();
                Intent intent = new Intent(this, LoginRegis.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
