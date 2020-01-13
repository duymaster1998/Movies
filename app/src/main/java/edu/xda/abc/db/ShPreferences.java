package edu.xda.abc.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import edu.xda.abc.models.ResponseLoginModel;

public class ShPreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    ResponseLoginModel model;

    private static final String KEY = "accout_name";
    private static final String VALUE = "accout";

    private int PRIVATE_MODE = 0;

    public ShPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public  ResponseLoginModel getModel(){
        Gson gson = new Gson();
        String json=sharedPreferences.getString(KEY,"");
        model = gson.fromJson(json,ResponseLoginModel.class);
        return model;
    }
    public  void setModel(ResponseLoginModel model){
        Gson gson = new Gson();
        String json = gson.toJson(model);
        editor.putString(KEY,json);
        editor.commit();
    }
    public void clearAccount(){
        sharedPreferences.edit().clear().apply();
    }
}
