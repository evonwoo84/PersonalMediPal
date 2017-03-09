package sg.edu.nus.personalmedipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sg.edu.nus.personalmedipal.R;

public class MediPalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi_pal);
    }

    public void onClickAppointment (View view) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
    }
}
