package rubendiego.salle_library;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import rubendiego.customsearch.CustomSearchView;


public class MainPage extends AppCompatActivity implements View.OnClickListener, CustomSearchView.OnSearchButtonClickedListener, AdapterView.OnItemClickListener {

    private Button logOff;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        CustomSearchView customSearchView = (CustomSearchView) this.findViewById(R.id.customSearch);
        customSearchView.setHintText("Buscar...");
        customSearchView.setOnSearchButtonClickedListener(this);
        listView = findViewById(R.id.listView);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listView.setOnItemClickListener(this);
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

            case R.id.listFav:
                Intent intent2 = new Intent(this, FavoriteList.class);
                startActivity(intent2);
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
        new BuscarLibro(listView, this).execute(currentText);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Book bookList = (Book) listView.getItemAtPosition(position);

        Toast.makeText(getApplication(), (CharSequence) bookList.titulo, Toast.LENGTH_LONG).show();

        Book libro = (Book) bookList;

        //bundle.putParcelable("Libro", libro);

        Intent newActivity = new Intent(MainPage.this, OpenBook.class);
        newActivity.putExtra("Libro", (Parcelable) libro);
        startActivity(newActivity);


    }
}
