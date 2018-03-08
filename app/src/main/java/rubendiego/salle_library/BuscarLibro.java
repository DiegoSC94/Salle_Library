package rubendiego.salle_library;

import android.os.AsyncTask;
import android.widget.TextView;

import rubendiego.customsearch.CustomSearchView;

/**
 * Created by ruben on 07/03/2018.
 */
public class BuscarLibro extends AsyncTask<String,Void,String> {
private CustomSearchView customSearchView;

    public BuscarLibro(CustomSearchView customSearchView) {
        this.customSearchView=customSearchView;
    }

    @Override
    protected String doInBackground(String... strings) {


        return ConexionApi.infoBook(strings[0]);
    }
}
