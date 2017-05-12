package dav.com.foody.Views.Main.Profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import dav.com.foody.Presenters.Profile.IPresenterLogin;
import dav.com.foody.Presenters.Profile.ProfileLogicLogin;
import dav.com.foody.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity  implements IViewProfile{

    TextView txtName;
    CircleImageView circleImageView;
    IPresenterLogin presenterLogin;
    AccessToken accessToken;

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtName = (TextView) findViewById(R.id.txt_profile_name);
        circleImageView = (CircleImageView) findViewById(R.id.img_profile_avatar);

        presenterLogin = new ProfileLogicLogin(this);
        accessToken = presenterLogin.getAccessToken();
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

    }


}
