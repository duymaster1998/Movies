package edu.xda.abc.utils;

import edu.xda.abc.api.Api;
import edu.xda.abc.api.NetWorking;


public class Utils {
    public static final String BASE_URL = "http://192.168.1.16:3000/";
//    public static final String BASE_URL = "http://192.168.51.149:3000/";
//    private static final String BASE_URL = "http://192.168.1.37:3000/";
    public static final String URI_IMAGE = BASE_URL+"movie/";
    public static final String URI_IMAGE_USER = BASE_URL+"persional/";
    public static final String URI_IMAGE_ACTOR = BASE_URL+"srcactor/";
    public static Api getInstance() {
        return NetWorking.getMovieClient().create(Api.class);
    }
}
