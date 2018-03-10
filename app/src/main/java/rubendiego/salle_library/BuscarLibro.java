package rubendiego.salle_library;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import rubendiego.customsearch.CustomSearchView;

/**
 * Created by ruben on 07/03/2018.
 */
public class BuscarLibro extends AsyncTask<String, Void, String> {
    private CustomSearchView customSearchView;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayList arrayList= new ArrayList();
    private Activity activity;


    public BuscarLibro(ListView listView, Activity activity) {
        this.listView = listView;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {


        return ConexionApi.infoBook(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray librosArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < librosArray.length(); i++) {
                JSONObject libro = librosArray.getJSONObject(i);


                JSONObject infoLibro = libro.getJSONObject("volumeInfo");

                try {
                    BookData.TITLE = infoLibro.getString("title");
                    BookData.AUTHORS = infoLibro.getString("authors");

                } catch (Exception ex) {
                    ex.printStackTrace();//cojemos la possible excepcion
                }
                if (BookData.TITLE != null && BookData.AUTHORS != null) {
                    arrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, arrayList);
                    arrayList.add(BookData.TITLE);
                    listView.setAdapter(arrayAdapter);

                }

            }
        }catch (Exception ex){
            Toast.makeText(activity,"No hay liros con el titulo: "+s,Toast.LENGTH_LONG).show();
        }
    }
}