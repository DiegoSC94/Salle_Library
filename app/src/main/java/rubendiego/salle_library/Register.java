package rubendiego.salle_library;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements View.OnClickListener{

    private Button registro;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register, container, false);
        registro = view.findViewById(R.id.iniciar_regis);
        registro.setOnClickListener(this);





        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iniciar_regis:
                SharedPreferences prefs = this.getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                //aqui poner los compos del editText
                editor.putString("email", "modificado@email.com");
                editor.putString("nombre", "Prueba");
                editor.apply();
        }
    }
}
