package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich=new Sandwich();
        List<String> alsoKnownAsList=new ArrayList<String>();
        List<String> ingredientsList=new ArrayList<String>();

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



            //Log.i("name",mainName);
            sandwich.setMainName(mainName);

            for (int i=0;i<alsoKnowAs.length();i++){
                String nickName=alsoKnowAs.getString(i);
                //Log.i(".Also know as",nickName);
                alsoKnownAsList.add(nickName);
            }
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            //Log.i("placeOfOrigin",placeOfOrigin);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            //Log.i("description",description);
            sandwich.setDescription(description);
            //Log.i("image",image);
            sandwich.setImage(image);
            for (int i=0;i<ingredients.length();i++){
                String ingredient=ingredients.getString(i);
                //Log.i("ingredient:",ingredient);
                ingredientsList.add(ingredient);
            }
            sandwich.setIngredients(ingredientsList);


        }catch (JSONException e){
            Log.e("Fail parsing the json",e.toString());
        }

        return sandwich;
    }
}
