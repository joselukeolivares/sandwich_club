package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView name;
    private TextView alsoKnownAsTag;
    private TextView alsoKnownAs;
    private TextView origin;
    private TextView description;
    private TextView ingredientsTag;
    private TextView ingredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        name=(TextView)findViewById(R.id.sandwichName);
        alsoKnownAs=(TextView)findViewById(R.id.also_known_tv);
        origin=(TextView)findViewById(R.id.origin_tv);
        ingredients=(TextView)findViewById(R.id.ingredients_tv);
        description=(TextView)findViewById(R.id.description_tv);

        name.setText(sandwich.getMainName());
        List<String> alsoKnownAsList=sandwich.getAlsoKnownAs();
        if(!sandwich.getPlaceOfOrigin().equals("")){
            origin.setText(sandwich.getPlaceOfOrigin());
        }
        List<String> ingredientsList=sandwich.getIngredients();

        if(!alsoKnownAsList.isEmpty()){
            for (String nickName:alsoKnownAsList){
                alsoKnownAs.append(nickName+"\n");
            }
        }else{
            alsoKnownAsTag=(TextView)findViewById(R.id.also_knownTag_tv);
            alsoKnownAsTag.setVisibility(View.INVISIBLE);

        }

        if(!ingredientsList.isEmpty()){
            for(String ingredientX:ingredientsList){
                ingredients.append(ingredientX+"\n");
            }
        }else{
            ingredientsTag=(TextView)findViewById(R.id.ingredientsTag_tv);
            ingredientsTag.setVisibility(View.INVISIBLE);
        }


        description.setText(sandwich.getDescription());
    }
}
