package dav.com.foody.Views.Main.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import dav.com.foody.Presenters.Profile.IPresenterLogin;
import dav.com.foody.Presenters.Profile.ProfileLogicLogin;
import dav.com.foody.R;
import dav.com.foody.Views.Login.LoginActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity  implements IViewProfile, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    TextView txtName, txtLogout;
    CircleImageView circleImageView;
    LinearLayout lnInfo;

    IPresenterLogin presenterLogin;

    AccessToken accessToken;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtName = (TextView) findViewById(R.id.txt_profile_name);
        txtLogout = (TextView) findViewById(R.id.txt_profile_logout);
        circleImageView = (CircleImageView) findViewById(R.id.img_profile_avatar);
        lnInfo = (LinearLayout) findViewById(R.id.ln_profile_info);

        txtLogout.setOnClickListener(this);
        lnInfo.setOnClickListener(this);

        presenterLogin = new ProfileLogicLogin(this);


        accessToken = presenterLogin.getAccessToken();
        mGoogleApiClient = presenterLogin.getGoogleApiClient(this,this);
        googleSignInResult = presenterLogin.getInfoLoginGoogle(mGoogleApiClient);

        changeTextName();
    }

    private void changeTextName() {
        if(accessToken != null){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        Log.d("kt123",object.toString());
                        name = object.getString("name");
                        txtName.setText(name);
                        if (object.has("picture")) {
                            String profilePicUrl = object.getJSONObject("picture").getJSONObject("data").getString("url");
                            Picasso.with(ProfileActivity.this).load(profilePicUrl).fit().centerInside().into(circleImageView);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields", "id,name,email,gender,cover,picture.type(large)");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }
        if(googleSignInResult != null){
            name = googleSignInResult.getSignInAccount().getDisplayName();
            if(googleSignInResult.getSignInAccount().getPhotoUrl()!=null){
                Picasso.with(ProfileActivity.this).load(googleSignInResult.getSignInAccount().getPhotoUrl()).fit().centerInside().into(circleImageView);
            }else{
                Picasso.with(ProfileActivity.this).load(R.drawable.ava0).fit().centerInside().into(circleImageView);
            }

            txtName.setText(name);
        }

        name = presenterLogin.getCachedLogin(this);
        if(!name.equals("")){
            txtName.setText(name);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_profile_logout:
                if(accessToken!=null){
                    LoginManager.getInstance().logOut();
                }
                if(googleSignInResult != null){
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                }
                if(!presenterLogin.getCachedLogin(this).equals("")){
                    presenterLogin.updateCachedLogin(this,"");
                }
                Toast.makeText(ProfileActivity.this,"Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                changeTextLogout();
                break;

            case R.id.ln_profile_info:
                Intent iLogin = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(iLogin);
                finish();
                break;
        }
    }

    private void changeTextLogout(){
        txtName.setText("Nhấp để đăng nhập");
        Picasso.with(ProfileActivity.this).load(R.drawable.ava0).fit().centerInside().into(circleImageView);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(ProfileActivity.this,"Kiểm tra lại kết nối", Toast.LENGTH_SHORT).show();
    }
}
