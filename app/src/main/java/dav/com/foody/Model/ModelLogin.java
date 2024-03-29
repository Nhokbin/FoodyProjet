package dav.com.foody.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dav.com.foody.Connect.DownLoadJSON;

import static dav.com.foody.Connect.SERVER_IP.LOGIN;

/**
 * Created by binhb on 06/05/2017.
 */

public class ModelLogin {

    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public GoogleApiClient getGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity)context),failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        return mGoogleApiClient;
    }

    public boolean checkLogin(Context context, String email, String password) {

        boolean result = false;

        String url = LOGIN;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String, String> hmEmail = new HashMap<>();
        hmEmail.put("email", email);

        HashMap<String,String> hmPassword = new HashMap<>();
        hmPassword.put("password", password);

        attrs.add(hmEmail);
        attrs.add(hmPassword);

        DownLoadJSON downloadJSON = new DownLoadJSON(context,url,attrs);
        downloadJSON.execute();


        try {
            String data = downloadJSON.get();
            Log.d("123",data);
            JSONObject object = new JSONObject(data);
            result = object.getBoolean("success");
            if(result){
                updateCachedLogin(context,email);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public AccessToken getAccessToken(){
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public String getCachedLogin(Context context){
        SharedPreferences cachedLogin = context.getSharedPreferences("login",Context.MODE_PRIVATE);
        String email = cachedLogin.getString("email","");
        return  email;
    }

    public void updateCachedLogin(Context context, String email) {
        SharedPreferences cachedLogin = context.getSharedPreferences("login",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedLogin.edit();
        editor.putString("email",email);
        editor.commit();
    }

    public GoogleSignInResult getInfoLoginGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            return opr.get();
        }
        return null;
    }

}
