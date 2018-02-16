package rubendiego.salle_library;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener {

    Button login,register;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        login.findViewById(R.id.iniciar);
        register.findViewById(R.id.register);

        login.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Fragment register = new Register();

                FragmentTransaction transation = getActivity().getFragmentManager().beginTransaction();

                transation.replace(R.id.fragment_login, register);
                transation.addToBackStack(null);
                transation.commit();
                break;
            case R.id.iniciar:
                Intent intent = new Intent(getActivity(),MainPage.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}