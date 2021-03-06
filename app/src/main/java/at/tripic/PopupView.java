package at.tripic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PopupView extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_view);

        backButton = (Button) findViewById(R.id.photographerList_backButton);
    }

    public void navigateToLocalPhotographers(View view) {
        Intent intent = new Intent(this, LocalPhotographers.class);
        startActivity(intent);
    }
}
