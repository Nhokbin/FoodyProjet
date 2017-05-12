package dav.com.foody.Views.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dav.com.foody.Model.ModelRegister;
import dav.com.foody.R;
import dav.com.foody.Views.Login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtEmail, edtPassword, edtRePassword;
    Button btnRegister;

    ModelRegister modelRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = (EditText) findViewById(R.id.edt_register_email);
        edtPassword = (EditText) findViewById(R.id.edt_register_password);
        edtRePassword = (EditText) findViewById(R.id.edt_register_repassword);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);

        modelRegister = new ModelRegister();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_register:
                createUser();
            break;
        }
    }

    private void createUser(){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String rePassword= edtRePassword.getText().toString();

        if(email.trim().equals("") || password.trim().equals("") || rePassword.trim().equals("")){
            Toast.makeText(this,"Phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }else if(!password.equals(rePassword)){
            Toast.makeText(this,"Xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT).show();
        }else{
            boolean result = modelRegister.create(this,email,password);
            if(result){
                Intent iLogin = new Intent(this, LoginActivity.class);
                startActivity(iLogin);
                finish();
            }else{
                Toast.makeText(this,"Đăng ký không thành công", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
