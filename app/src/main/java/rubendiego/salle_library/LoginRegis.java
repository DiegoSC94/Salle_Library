package rubendiego.salle_library;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * <p>Esta clase se utiliza para abrir los fragments de registro e inicio</p>
 *
 * @author Diego y Ruben on 10/03/2018.
 */
public class LoginRegis extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_regis);
        Fragment login = new Login();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity_login_regis, login);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}
