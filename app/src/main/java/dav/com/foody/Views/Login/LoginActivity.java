package dav.com.foody.Views.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

import dav.com.foody.Model.ModelLogin;
import dav.com.foody.Presenters.Profile.IPresenterLogin;
import dav.com.foody.Presenters.Profile.ProfileLogicLogin;
import dav.com.foody.R;
import dav.com.foody.Views.Main.MainActivity;
import dav.com.foody.Views.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, IViewLogin {

    GoogleApiClient mGoogleApiClient;


    Button btnLogin, btnFacebook, btnGoogle;
    TextView txtRegister, txtForgot;
    EditText edtEmail, edtPassword;
    ProgressDialog progressDialog;

    AccessToken accessToken;
    GoogleSignInResult googleSignInResult;

    ModelLogin modelLogin;
    IPresenterLogin presenterLogin;

    public static int SIGN_IN_GOOGLE_PLUS = 111;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("LoginFacebook", "Success");
                Intent iMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(iMain);
            }

            @Override
            public void onCancel() {
                Log.d("LoginFacebook", "Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Please check your connection and try again!", Toast.LENGTH_LONG).show();
                Log.d("LoginFacebook", "Error");
            }
        });
        setContentView(R.layout.activity_login);

        modelLogin = new ModelLogin();

        presenterLogin = new ProfileLogicLogin(this);
        accessToken = presenterLogin.getAccessToken();
        mGoogleApiClient = presenterLogin.getGoogleApiClient(this, this);
        googleSignInResult = presenterLogin.getInfoLoginGoogle(mGoogleApiClient);

        try {
            if (!presenterLogin.getCachedLogin(this).equals("") || accessToken != null || googleSignInResult != null) {
                Intent iMain = new Intent(this, MainActivity.class);
                startActivity(iMain);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnFacebook = (Button) findViewById(R.id.btn_login_facebook);
        btnGoogle = (Button) findViewById(R.id.btn_login_google);
        txtRegister = (TextView) findViewById(R.id.txt_login_register);
        txtForgot = (TextView) findViewById(R.id.txt_login_forgot);
        edtEmail = (EditText) findViewById(R.id.edt_login_email);
        edtPassword = (EditText) findViewById(R.id.edt_login_password);

        btnLogin.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        txtForgot.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_login_facebook:
                loginFaceBook();
                break;
            case R.id.btn_login_google:
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGooglePlus, SIGN_IN_GOOGLE_PLUS);
                showProcessDialog();

                break;
            case R.id.txt_login_register:
                Intent iRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(iRegister);
                break;
            case R.id.txt_login_forgot:

                break;
        }
    }

    private void loginFaceBook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

    }

    private void login() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        presenterLogin.checkLogin(this,email,password);
    }

    private void showProcessDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Đang xác thực!");
            progressDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_GOOGLE_PLUS) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                progressDialog.cancel();
                Intent iMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(iMain);
                finish();
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(LoginActivity.this, "Please check your connection and try again!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess() {
        Intent iMain = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(iMain);
        finish();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "Kiểm tra lại thông tin đăng nhập", Toast.LENGTH_SHORT).show();
    }
}
