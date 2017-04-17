package ie.jbmnetworks.pubgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserAreaActivity extends AppCompatActivity {
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");

        requestQueue = Volley.newRequestQueue(this);
        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etAge = (EditText) findViewById(R.id.etAge);
        Button bMark =(Button) findViewById(R.id.btMark);

        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        //Test Button


        bMark.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(UserAreaActivity.this, testActivity.class));
            }
        });


        //map link button.
        Button bMap = (Button) findViewById(R.id.bMap);

        bMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(UserAreaActivity.this, MapActivity.class));

                //Sending off Volley request
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://pubgo-jackbourkemckenna.c9users.io/AppplaceId.php", null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONArray jsonArray = response.getJSONArray("arr");

                                    ArrayList<String> placeIdArray = new ArrayList<String>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject Pub_id = jsonArray.getJSONObject(i);

                                         placeIdArray.add(Pub_id.getString("pub_id"));




                                        //textView.append("pubId"+pubId);
                                    }




                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley", "Error");

                            }
                        }
                );
                requestQueue.add(jsonObjectRequest);



            }


        });


        //location map link
/*
        Button bLocation = (Button) findViewById(R.id.bLocation);

        bLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(UserAreaActivity.this, LocationSourceDemoActivity.class));
            }
        });


*/
    }

}
