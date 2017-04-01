package ie.jbmnetworks.pubgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        Button bEdit = (Button) findViewById(R.id.bEdit);

        bEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ExitActivity.this, EditActivity.class));
            }
        });

        Button bExit = (Button) findViewById(R.id.bExit);

        bExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View e) {
                startActivity(new Intent(ExitActivity.this, EditActivity.class));
            }
        });


    }
}
