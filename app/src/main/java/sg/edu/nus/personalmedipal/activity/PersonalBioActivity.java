package sg.edu.nus.personalmedipal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sg.edu.nus.personalmedipal.R;

public class PersonalBioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_bio);
    }

    public void onClickMyDetails(View view) {
        //Intent intent = new Intent(this, MyDetailsClass.class);
        //startActivity(intent);
    }

    public void onClickHealthBio(View view) {
        //Intent intent = new Intent(this, HealthBioActivity.class);
        //startActivity(intent);
    }

    public void onClickICE(View view) {
        //Intent intent = new Intent(this, ICEActivity.class);
        //startActivity(intent);
    }

    public void onClickMeasurement(View view) {
        //Intent intent = new Intent(this, MeasurementActivity.class);
        //startActivity(intent);
    }
}
