package ie.jbmnetworks.pubgo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import android.widget.Button;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by x15313821 on 05/04/2017.
 */

public class DrinksActivity {

    public class LoginActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_drinks);


            final TextView etName = (EditText) findViewById(R.id.etName);
            final TextView etPrice = (EditText) findViewById(R.id.etPrice);
            final TextView etDrink_id = (EditText) findViewById(R.id.etDrink_id);
            final TextView etType = (EditText) findViewById(R.id.etType);
            final TextView pub_id = (EditText) findViewById(R.id.etPub_id);

            bRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String etname = etName.getText().toString();
                    final int etprice = Integer.parseInt(etPrice.getText().toString());
                    final int etdrink_id = Integer.parseInt(etDrink.getText().toString());
                    final String type = etType.getText().toString();
                }

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                int pub_id = jsonResponse.getInt("pub_id");

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ie.jbmnetworks.pubgo.DrinksActivity.this);
                                builder.setMessage("Pub Id not found")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest Drinks = new Drinks(pub_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ie.jbmnetworks.pubgo.DrinksActivity.this);
                queue.add(Drinks);
            }
        });
    }
}