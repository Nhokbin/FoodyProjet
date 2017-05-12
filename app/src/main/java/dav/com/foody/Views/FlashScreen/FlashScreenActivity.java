package dav.com.foody.Views.FlashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import dav.com.foody.R;
import dav.com.foody.Views.Login.LoginActivity;

public class FlashScreenActivity extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        progressBar = (ProgressBar) findViewById(R.id.pg_flash_screen);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (Exception e){

                }finally{
                    Intent intent=new Intent(FlashScreenActivity.this,LoginActivity.class);
                    startActivity(intent); finish();
                }
            }
        });
        thread.start();

    }
}
