package dav.com.foody.Connect;

/**
 * Created by binhb on 28/04/2017.
 */

public final class SERVER_IP {

    public static final String IP = "192.168.1.13";
    public static final String PORT = "8080";
    public static final String SERVER ="http://"+ IP +":"+PORT+"/";


    public static final String CATEGORY = SERVER +"category/";
    public static final String CITY = SERVER + "city/";
    public static final String DISTRICT = SERVER + "district/";
    public static final String USER = SERVER + "user/";
    public static final String ITEM = SERVER + "item/";
    public static final String DISH = SERVER + "dish/";

    public static final String LOGIN = USER +"check-login";
    public static final String REGISTER = USER +"register";

    public static final String GET_ALL = "getall";
    public static final String GET_BY_ID = "get-by-id/";
    public static final String GET_BY_CITY = "get-by-city/";

    public static final String GET_BY_PARAMS = ITEM + "get-by-params";
    public static final String GET_BY_OPTIONS = ITEM + "get-by-options";
    public static final String GET_BY_CATEGORY = ITEM + "get-by-categories";
    public static final String GET_BY_ADDRESS = ITEM +"get-by-address";

    public static final String GET_DISH_BY_OPTIONS = DISH + "get-by-options";
    public static final String GET_DISH_BY_CATEGORY = DISH + "get-by-categories";

    public static final String IMAGE = SERVER + "image/";
    public static final String IMAGE_AVATAR = IMAGE + "avatar/";

    //http://localhost:8080/image/category/sang-trong.png
}
