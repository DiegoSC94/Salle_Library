package rubendiego.salle_library;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * <p>Esta clase se conecta con la base de datos para recuperar los libros que se buscan</p>
 *
 * @author Ruben y Diego on 07/03/2018.
 */

public class ConexionApi {
    private static final String LOG_TAG = ConexionApi.class.getSimpleName();
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"; //base para api google books
    private static final String QUERY_PERAMETER = "q";//parametro para buscar string
    private static final String MAX_RESULTS = "maxResults"; //limite de resultados
    private static final String PRINT_TYPE = "printType"; //para para filtrar


    static String infoBook(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSON = null;

        try {
            //escuchar resultados
            Uri buildUri = Uri.parse(BOOK_BASE_URL).buildUpon().appendQueryParameter(QUERY_PERAMETER, queryString).appendQueryParameter(MAX_RESULTS, "20").appendQueryParameter(PRINT_TYPE, "books").build();

            URL requestUrl = new URL(buildUri.toString());//solicitud de url

            urlConnection = (HttpURLConnection) requestUrl.openConnection();//abrimos conexion con get
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();//conectamos

            //leemos la respuesta con imput string y stringbuffered despues lo convertimos a string
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line + "\n");//en cada linea si lo que recoge no es nulo hace ubbna linea y si salto
            }

            if (stringBuffer.length() == 0) {
                return null;//si no coje nada sale y retorna null
            }

            bookJSON = stringBuffer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();//printa el error
            return null;


        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader!=null){
                try{
                    reader.close();
                }catch (IOException exIO){
                    exIO.printStackTrace();//printamos el possible error de io
                }
            }
            Log.d(LOG_TAG, bookJSON);//hace un log del possible valor de bookJSON
            return bookJSON;
        }
    }


}

