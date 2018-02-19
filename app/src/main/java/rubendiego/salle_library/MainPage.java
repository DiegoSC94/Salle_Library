package rubendiego.salle_library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.PreparedStatement;

public class MainPage extends Activity implements View.OnClickListener {

    private Button logOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        logOff = findViewById(R.id.logOff);
        logOff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logOff:
                SharedPreferences datos = getSharedPreferences("baseDeDatos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=datos.edit();
                editor.putBoolean("sesion",false);

                editor.commit();
                Intent intent= new Intent(this,LoginRegis.class);
                startActivity(intent);
                break;
        }
    }
}
