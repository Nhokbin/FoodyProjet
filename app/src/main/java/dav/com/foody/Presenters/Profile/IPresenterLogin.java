package dav.com.foody.Presenters.Profile;

import android.content.Context;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by binhb on 09/05/2017.
 */

public interface IPresenterLogin {

    AccessToken getAccessToken();

    GoogleApiClient getGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener);
    GoogleSignInResult getInfoLoginGoogle(GoogleApiClient mGoogleApiClient);

    String getCachedLogin(Context context);
    void updateCachedLogin(Context context, String name);
    void checkLogin(Context context, String email, String password);
}
