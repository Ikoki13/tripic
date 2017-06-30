package at.tripic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import at.tripic.adapters.PictureAdapter;


public class Main extends AppCompatActivity implements OnMapReadyCallback {
    private PictureAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new PictureAdapter(null, null);

        //map impl
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.gMaps);

        mapFragment.getMapAsync(this);
        //adView impl
        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        //mAdView = (AdView) findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);

    }

    public void searchHotspotsByLocation(View view) {
        Intent intent = new Intent(this, MapViewOverview.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng vienna = new LatLng(48.2082, 16.3738);
        googleMap.addMarker(new MarkerOptions().position(vienna)
                .title("Vienna"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(vienna));
        googleMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );
    }
}