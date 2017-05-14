package dav.com.foody.Presenters.Profile;

import android.content.Context;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import dav.com.foody.Model.ModelLogin;
import dav.com.foody.Views.Login.IViewLogin;
import dav.com.foody.Views.Main.Profile.IViewProfile;

/**
 * Created by binhb on 09/05/2017.
 */

public class ProfileLogicLogin implements IPresenterLogin{

    IViewProfile viewProfile;
    IViewLogin viewLogin;
    ModelLogin modelLogin;

    public ProfileLogicLogin(IViewLogin viewLogin){
        this.viewLogin = viewLogin;
        modelLogin = new ModelLogin();
    }
    public ProfileLogicLogin(IViewProfile iViewProfile){
        this.viewProfile = iViewProfile;
        modelLogin = new ModelLogin();
    }

    @Override
    public AccessToken getAccessToken() {
        AccessToken accessToken = modelLogin.getAccessToken();
        if(accessToken != null){
            return accessToken;
        }
        return null;

    }

    @Override
    public GoogleApiClient getGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener) {
        GoogleApiClient googleApiClient = modelLogin.getGoogleApiClient(context,failedListener);
        if(googleApiClient != null){
            return  googleApiClient;
        }
        return null;
    }

    @Override
    public GoogleSignInResult getInfoLoginGoogle(GoogleApiClient mGoogleApiClient) {
        GoogleSignInResult googleSignInResult = modelLogin.getInfoLoginGoogle(mGoogleApiClient);
        if(googleSignInResult != null){
            return  googleSignInResult;
        }
        return null;
    }

    @Override
    public String getCachedLogin(Context context) {
        return modelLogin.getCachedLogin(context);

    }

    @Override
    public void updateCachedLogin(Context context, String name) {
        modelLogin.updateCachedLogin(context,name);
    }

    @Override
    public void checkLogin(Context context, String email, String password) {
        if(modelLogin.checkLogin(context,email,password)){
            viewLogin.loginSuccess();
        }else{
            viewLogin.loginFailed();
        }

    }
}
