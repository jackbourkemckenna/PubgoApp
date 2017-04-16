package ie.jbmnetworks.pubgo;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import static ie.jbmnetworks.pubgo.R.id.tvPlace;

/**
 * Created by Jack on 4/5/2017.
 */

public class MapActivity extends AppCompatActivity {
    TextView placeIdText;
    TextView placeNameText;
    TextView placeAddressText;
    WebView attributionText;
    Button getPlaceButton;
    Button bMark;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;

    private final static int PLACE_PICKER_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //dealing with run time premishions
        requestPermission();
        placeNameText = (TextView) findViewById(R.id.tvPlaceName);
        placeAddressText = (TextView) findViewById(R.id.tvPlaceAddress);
        attributionText = (WebView) findViewById(R.id.wvAttribution);
        getPlaceButton = (Button) findViewById(R.id.btGetPlace);
        bMark = (Button) findViewById(R.id.btMark);
        placeIdText = (TextView) findViewById(tvPlace);

        Button bProfile = (Button) findViewById(R.id.bProfile);

        bProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, ProfileActivity.class));
            }
        });

        //Exit link button.
        Button bExit = (Button) findViewById(R.id.bExit);

        bExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, ExitActivity.class));
            }
        });



        getPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //make intent builder

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(MapActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void requestPermission() {
        //checking if the Permishion has not been granted and been run as a one time action.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //vershion check
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Tell the user that that location needs to be turned on.
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(MapActivity.this, data);
                placeNameText.setText(place.getName());
                placeAddressText.setText(place.getAddress());
                final CharSequence placeIdVar   = place.getId();
                if(placeIdVar.equals("ChIJCVErT4wOZ0gRTb822t6ZGw4" )){
                    placeIdText.setText(placeIdVar+"Woop");

                }else{
                    placeIdText.setText("Nope");
                }





                if (place.getAttributions() == null) {

                    attributionText.loadData("no attribution", "text/html; charset-utf-8", "UFT-8");
                } else {

                    attributionText.loadData(place.getAttributions().toString(), "text/html; charset-utf-9", "UFT-8");
                }

            }

        }
    }





}