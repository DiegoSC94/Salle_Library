package rubendiego.salle_library;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rubendiego.customsearch.CustomSearchView;


public class MainPage extends AppCompatActivity implements View.OnClickListener, CustomSearchView.OnSearchButtonClickedListener {

    private Button logOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        CustomSearchView customSearchView = (CustomSearchView) this.findViewById(R.id.customSearch);
        customSearchView.setHintText("Buscar...");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        CustomSearchView hola=new CustomSearchView(this);
    new BuscarLibro(hola);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOffBar:
                SharedPreferences datos = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = datos.edit();
                editor.putBoolean("sesion", false);

                editor.commit();
                Intent intent = new Intent(this, LoginRegis.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onSearchButtonClicked(CustomSearchView source, String currentText) {
        Toast.makeText(this, "Searching..." + currentText, Toast.LENGTH_SHORT).show();
    }
}
