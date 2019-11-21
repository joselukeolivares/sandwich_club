package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich=new Sandwich();

        JSONObject reader;

        try{
            reader=new JSONObject(json);
            JSONObject preSandwich=reader.getJSONObject("name");

            String mainName=preSandwich.getString("mainName");
            JSONArray alsoKnowAs=preSandwich.getJSONArray("alsoKnownAs");
            String placeOfOrigin=reader.getString("placeOfOrigin");
            String description=reader.getString("description");
            String image=reader.getString("image");
            JSONArray ingredients=reader.getJSONArray("ingredients");



            Log.i("name",mainName);

            for (int i=0;i<alsoKnowAs.length();i++){
                String nickName=alsoKnowAs.getString(i);
                Log.i("Also know as",nickName);
            }
            Log.i("placeOfOrigin",placeOfOrigin);
            Log.i("description",description);
            Log.i("image",image);
            for (int i=0;i<ingredients.length();i++){
                String ingredient=ingredients.getString(i);
                Log.i("ingredient:",ingredient);
            }

        }catch (JSONException e){
            Log.e("Fail parsing the json",e.toString());
        }

        return null;
    }
}
