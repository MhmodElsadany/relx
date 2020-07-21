package com.example.talbk.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.talbk.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Address extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    Button address;
    String locationAddress = "";
    String flag = "0";
    int again = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment.getMapAsync(this);
        address = findViewById(R.id.btn);
        address.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (flag.equals("1")) {
                    Toast.makeText(Address.this, "لم يتم تحديد موقعك لعدم تشغيل GPS", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Intent returnIntent = new Intent();
                    ;

                    if (locationAddress.equals("") || locationAddress.equals("Unable to get address for this location.")) {
                        Toast.makeText(Address.this, "لم يتم تحديد موقعك ", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Address.this, "تم تحديد موقعك بنجاح", Toast.LENGTH_SHORT).show();
                        //  returnIntent.putExtra("address", locationAddress);
                        returnIntent.putExtra("address", locationAddress);
                        setResult(Activity.RESULT_OK, returnIntent);


                    }

                    // startActivity(returnIntent);
                    finish();
                }
            }
        });
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
            flag = "0";

        } else {
            showGPSDisabledAlertToUser();
        }


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("التتبع غير مفعل فى جهازك ؟ هل تريد التفعيل! ")
                .setCancelable(false)
                .setPositiveButton("الذهاب الى الضبط لتفعيل GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(callGPSSettingIntent, 1);
                                requestLocation();
                                flag = "0";


                            }
                        });
        alertDialogBuilder.setNegativeButton("الغاء",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        flag = "1";
                        Toast.makeText(Address.this, "يجب تفعيل GPS لتحديد موقعك", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocation();
        getlocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestLocation();
        getlocation();

    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();

    }


    public static void getAddressFromLocation(final double latitude, final double longitude, final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, new Locale("ar"));
                String result = null;
                try {
                    List<android.location.Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        android.location.Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i));
                        }

                        sb.append(address.getFeatureName()).append("/");
                        sb.append(address.getLocality()).append("/");
                        sb.append(address.getSubAdminArea()).append("/");
                        sb.append(address.getAdminArea());

                        result = sb.toString();
                    }
                } catch (IOException e) {
                    Log.e("Location Address Loader", "Unable connect to Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "";
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(Address.this, "لم يتم تحديد موقعك", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void requestLocation() {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(60000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        //TODO: UI updates.
                    }
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        } else {
            LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        }

    }

    public void getlocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


        } else {
            LocationServices.getFusedLocationProviderClient
                    (this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Log.i("nnnnnnnnnn", location + "");
                    if (location != null) {
                        MarkerOptions currentUserLocation = new MarkerOptions();
                        LatLng currentUserLatLang = new LatLng(location.getLatitude(), location.getLongitude());
                        currentUserLocation.position(currentUserLatLang);
                        currentUserLocation.title("عنوانى");
                        mMap.addMarker(currentUserLocation).showInfoWindow();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUserLatLang, 16));
                        getAddressFromLocation(location.getLatitude(), location.getLongitude(), getApplicationContext(),
                                new GeocoderHandler());

                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            requestLocation();
            getlocation();
        }
    }
}

