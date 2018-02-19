package rubendiego.salle_library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchMainActivity extends AppCompatActivity {

    private EditText tituloLibro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        //tituloLibro = findViewById(R.id.titulo_libro);
    }

    public void buscarLibro(View v){

    }
}
