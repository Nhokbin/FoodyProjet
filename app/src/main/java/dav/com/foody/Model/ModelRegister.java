package dav.com.foody.Model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dav.com.foody.Connect.DownLoadJSON;
import dav.com.foody.Views.Register.RegisterActivity;

import static dav.com.foody.Connect.SERVER_IP.REGISTER;

/**
 * Created by binhb on 06/05/2017.
 */

public class ModelRegister {
    public boolean create(RegisterActivity registerActivity, String email, String password) {

        boolean result = false;

        String url = REGISTER;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String, String> hmEmail = new HashMap<>();
        hmEmail.put("email", email);

        HashMap<String,String> hmPassword = new HashMap<>();
        hmPassword.put("password", password);

        attrs.add(hmEmail);
        attrs.add(hmPassword);
        DownLoadJSON downloadJSON = new DownLoadJSON(registerActivity,url,attrs);
        downloadJSON.execute();


        try {
            String data = downloadJSON.get();
            Log.d("123",data);
            JSONObject object = new JSONObject(data);
            result = object.getBoolean("success");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;

    }
}
