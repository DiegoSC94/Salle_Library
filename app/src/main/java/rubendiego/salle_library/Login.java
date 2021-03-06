package rubendiego.salle_library;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener {

    private Button login, register;
    private EditText user, pass;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        boolean sesionActiva = obtenerValoresSesion(getActivity());
        if (sesionActiva) {
            //Abre pantalla principal.
            Intent myIntent = new Intent(getActivity(), MainPage.class);
            startActivity(myIntent);
        }


        login = (Button) view.findViewById(R.id.iniciar);
        register = view.findViewById(R.id.register);
        register.setOnClickListener(this);
        login.setOnClickListener(this);

        user = view.findViewById(R.id.username);
        pass = view.findViewById(R.id.password);


        return view;
    }

    /**
     *
     * @param activity es la actividad que le pasas para que puedas usar la base de datos
     *
     *<p>Aqui dentro se va a comprovar si el usuario tiene una sesion activa entrando en la base de datos y mirando mediante un boolean si la sesion esta iniciada o ya ha sido cerrada</p>
     *
     * @return retorna true (si la sesion esta iniciada) o false (si la sesion esta cerrada)
     */
    private boolean obtenerValoresSesion(Activity activity) {
        SharedPreferences sesion = getActivity().getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
        return sesion.getBoolean("sesion", false); //valor default false
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Fragment register = new Register();

                FragmentTransaction transation = getActivity().getFragmentManager().beginTransaction();

                transation.replace(R.id.activity_login_regis, register);
                transation.addToBackStack(null);
                transation.commit();
                break;
            case R.id.iniciar:
                SharedPreferences datos = this.getActivity().getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);

                if (user.getText().toString().equals(datos.getString("username", "ho hay info"))) {

                    if (pass.getText().toString().equals(datos.getString("password", "ho hay info"))) {
                        SharedPreferences.Editor editor = datos.edit();
                        editor.putBoolean("sesion", true);
                        editor.commit();

                        Intent intent = new Intent(getActivity(), MainPage.class);
                        startActivity(intent);

                        Toast.makeText(getActivity(), "Has iniciado sesion", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Este password no es el correcto para este usuario?", Toast.LENGTH_LONG).show();
                        pass.setText("");
                    }

                } else {
                    Toast.makeText(getActivity(), "no existe este usuario", Toast.LENGTH_LONG).show();
                    user.setText("");
                    pass.setText("");
                }

                break;
        }
    }
}
