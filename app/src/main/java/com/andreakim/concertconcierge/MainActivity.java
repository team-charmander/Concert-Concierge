package com.andreakim.concertconcierge;


import android.support.annotation.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    //  got the following method from
    //  https://developer.android.com/training/location/retrieve-current.html
    //  (not working)

    //  @Override
    //  public void onConnected(Bundle connectionHint) {
    //  mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
    //  mGoogleApiClient);
    //  if (mLastLocation != null) {
    //  mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
    //  mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
    //      }
    // }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}