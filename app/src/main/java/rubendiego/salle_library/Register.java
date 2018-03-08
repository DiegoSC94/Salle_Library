package rubendiego.salle_library;


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
public class Register extends Fragment implements View.OnClickListener {

    private Button registro, login;
    private EditText user, pass;


    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        registro = view.findViewById(R.id.iniciar_regis);
        registro.setOnClickListener(this);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(this);

        user = view.findViewById(R.id.username);
        pass = view.findViewById(R.id.password);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iniciar_regis:

                SharedPreferences datos = this.getActivity().getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);

                //aqui poner los compos del editText
                String userName, passName;
                userName = user.getText().toString();
                passName = pass.getText().toString();
                if (userName.equals(datos.getString("username", "ho hay info"))) {
                    Toast.makeText(getActivity(), "el usuario ya esta registrado", Toast.LENGTH_LONG).show();
                } else {
                    if (userName.isEmpty()) {
                        Toast.makeText(getActivity(), "Usuario no puede estar en blanco", Toast.LENGTH_LONG).show();

                    } else if (passName.isEmpty()) {
                        Toast.makeText(getActivity(), "Password no puede estar en blanco", Toast.LENGTH_LONG).show();

                    } else {
                        SharedPreferences.Editor editor = datos.edit();

                        editor.putString("username", userName);
                        editor.putString("password", passName);
                        editor.commit();
                        Toast.makeText(getActivity(), "Usuario " + userName + " se ha registrado correctamente", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), MainPage.class);
                        startActivity(intent);
                    }
                }


                break;
            case R.id.login:
                Fragment login = new Login();

                FragmentTransaction transation = getActivity().getFragmentManager().beginTransaction();

                transation.replace(R.id.activity_login_regis, login);
                transation.addToBackStack(null);
                transation.commit();
                break;
        }
    }
}
