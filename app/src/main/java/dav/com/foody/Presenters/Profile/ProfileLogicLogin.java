package dav.com.foody.Presenters.Profile;

import com.facebook.AccessToken;

import dav.com.foody.Model.ModelLogin;
import dav.com.foody.Views.Main.Profile.IViewProfile;

/**
 * Created by binhb on 09/05/2017.
 */

public class ProfileLogicLogin implements IPresenterLogin{

    IViewProfile viewProfile;

    public ProfileLogicLogin(IViewProfile iViewProfile){
        this.viewProfile = iViewProfile;
    }

    @Override
    public AccessToken getAccessToken() {

        ModelLogin modelLogin = new ModelLogin();
        AccessToken accessToken = modelLogin.getAccessToken();
        if(accessToken != null){
            return accessToken;
        }
        return null;

    }
}
