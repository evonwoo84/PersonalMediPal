package sg.edu.nus.personalmedipal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sg.edu.nus.personalmedipal.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickPersonalBio (View view) {
        Intent intent = new Intent(this, PersonalBioActivity.class);
        startActivity(intent);
    }

    public void onClickMedicine (View view) {
        Intent intent = new Intent(this, MedicineActivity.class);
        startActivity(intent);
    }

    public void onClickAppointment (View view) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
    }
}
