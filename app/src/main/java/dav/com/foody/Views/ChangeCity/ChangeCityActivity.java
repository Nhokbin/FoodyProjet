package dav.com.foody.Views.ChangeCity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dav.com.foody.R;

public class ChangeCityActivity extends AppCompatActivity {

    TextView txtChangeCounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);

        txtChangeCounty = (TextView) findViewById(R.id.txt_change_county);
    }
}
