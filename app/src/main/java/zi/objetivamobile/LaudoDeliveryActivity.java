package zi.objetivamobile;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.business.LaudoBusiness;
import zi.objetivamobile.model.LatLngModel;
import zi.objetivamobile.model.LaudoItemModel;

public class LaudoDeliveryActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<LaudoItemModel> mLaudoItem;
    private LaudoBusiness mLaudoBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudo_delivery);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLaudoBusiness = new LaudoBusiness(this);
        mLaudoItem = mLaudoBusiness.getLaudo();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng campinas = new LatLng(-22.8900181,-47.0771342);
        LatLngModel latLng = new LatLngModel();
        LatLng localizacao = null;

        for (LaudoItemModel laudo :mLaudoItem) {
            localizacao = new LatLng(laudo.getLat(),laudo.getLng());
            mMap.addMarker(new MarkerOptions().position(localizacao).title(laudo.getIdentificacao()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(campinas));
        mMap.setMaxZoomPreference(17);
        mMap.setMinZoomPreference(8);
    }
}
