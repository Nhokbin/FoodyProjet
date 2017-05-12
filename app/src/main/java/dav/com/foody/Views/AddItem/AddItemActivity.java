package dav.com.foody.Views.AddItem;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dav.com.foody.CustomView.DialogAddItem;
import dav.com.foody.Objects.City;
import dav.com.foody.Objects.District;
import dav.com.foody.R;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtTimeStart,txtTimeClose,txtNameCountry, txtNameCity, txtNameDistrict;
    SimpleDateFormat format= new SimpleDateFormat("HH:mm");
    Time timeValue;
    Calendar calendar;
    ImageButton btnShowAlBum, btnShowCamera;

    private Uri imageUri;

    public static int cityId = 1;
    public static int districtId = -1;

    public static int citySelectPos = -1;
    public static int districtSelectPos = -1;

    public static List<City> cities = new ArrayList<>();
    public static List<District> districts = new ArrayList<>();

    private static final Integer SELECT_IMAGE = 1;
    private static final Integer TAKE_PICTURE = 2;

    static  final String TYPE_CITY = "city";
    static  final String TYPE_DISTRICT = "district";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        calendar = Calendar.getInstance();

        txtTimeStart = (TextView) findViewById(R.id.txt_add_item_time_start);
        txtTimeClose = (TextView) findViewById(R.id.txt_add_item_time_close);
        txtNameCountry = (TextView) findViewById(R.id.txt_add_item_name_country);
        txtNameCity = (TextView) findViewById(R.id.txt_add_item_name_city);
        txtNameDistrict = (TextView) findViewById(R.id.txt_add_item_name_district);

        btnShowAlBum = (ImageButton) findViewById(R.id.btn_add_item_show_album);
        btnShowCamera = (ImageButton) findViewById(R.id.btn_add_item_show_camera);

        txtTimeStart.setOnClickListener(this);
        txtTimeClose.setOnClickListener(this);
        btnShowAlBum.setOnClickListener(this);
        btnShowCamera.setOnClickListener(this);
        txtNameCity.setOnClickListener(this);
        txtNameDistrict.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_add_item_time_start:
                showTimePicker(txtTimeStart);
                break;
            case R.id.txt_add_item_time_close:
                showTimePicker(txtTimeClose);
                break;
            case R.id.btn_add_item_show_album:
                showAlbum();
                break;
            case R.id.btn_add_item_show_camera:
                showCamera();
                break;
            case R.id.txt_add_item_name_city:
                showDialog(1,"Chọn thành phố",TYPE_CITY);
                break;
            case R.id.txt_add_item_name_district:
                showDialog(1,"Chọn Quận/ Huyện",TYPE_DISTRICT);
                break;
        }
    }
    private void showDialog(int key,String title,String type){
        DialogAddItem dialog=new DialogAddItem();
        dialog.setCancelable(false);
        Bundle args = new Bundle();
        args.putInt("key", key);
        args.putString("title", title);
        args.putString("type", type);
        try{
            args.putInt("cityId",cityId);
        }catch (Exception e){

        }
        dialog.setArguments(args);
        dialog.show(getFragmentManager(), "abc");
    }
    private void showCamera() {
        Intent iCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasImageCaptureBug()) {
            iCamera.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/tmp")));
        } else {
            iCamera.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(iCamera, TAKE_PICTURE);
    }

    private void showAlbum() {
        Intent iAlbum = new Intent();
        iAlbum.setType("image/*");
        iAlbum.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(iAlbum, "Select Picture"),SELECT_IMAGE);
    }

    public boolean hasImageCaptureBug() {

        // list of known devices that have the bug
        ArrayList<String> devices = new ArrayList<String>();
        devices.add("android-devphone1/dream_devphone/dream");
        devices.add("generic/sdk/generic");
        devices.add("vodafone/vfpioneer/sapphire");
        devices.add("tmobile/kila/dream");
        devices.add("verizon/voles/sholes");
        devices.add("google_ion/google_ion/sapphire");

        return devices.contains(android.os.Build.BRAND + "/" + android.os.Build.PRODUCT + "/"
                + android.os.Build.DEVICE);

    }

    private void showTimePicker(final TextView textView) {
        TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                textView.setText(format.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                AddItemActivity.this,
                callBack,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }
}
