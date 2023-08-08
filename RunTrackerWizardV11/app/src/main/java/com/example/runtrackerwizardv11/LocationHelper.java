package com.example.runtrackerwizardv11;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationHelper {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Location previousLocation;
    private float totalDistance;
    public float longitude, latitude;
    public String locationName;
    private List<Location> routePoints;
    TextView stat, stat2;

    public TextView txtTravelled, txtLocationName, txtLat, txtLong;
    Double targetKm;
    Activity context;


    public LocationRequest locationRequest;

    public LocationHelper(Activity context, TextView txtTravelled, TextView txtLocationName, TextView txtLat, TextView txtLong) {
        this.context = context;
        this.txtTravelled = txtTravelled;
        this.txtLocationName = txtLocationName;
        this.txtLat = txtLat;
        this.txtLong = txtLong;

    }

    public LocationHelper(Activity context, TextView txtLocationName, TextView txtTravelled, Double targetKm) {
        this.context = context;
        this.txtLocationName = txtLocationName;
        this.txtTravelled = txtTravelled;
        this.targetKm = targetKm;
        routePoints = new ArrayList<>();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000); // Update interval in milliseconds

        Log.d("XYZ", "New helper.");
        setLocationCallback();
        startLocationUpdates();
    }

    public void restart(Double targetKm){
        this.targetKm = targetKm;
        this.totalDistance = 0;

    }

    public void setLocationCallback() {
        this.locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.d("XYZ", "Location null");
                    return;
                }

                Log.d("XYZ", "Looking for locations");

                for (Location location : locationResult.getLocations()) {
                    if (previousLocation != null) {
                        // Calculate distance between previous location and current location
                        float distance = previousLocation.distanceTo(location);
                        totalDistance += distance;
                        if(targetKm > 0 ){
                            String strKm = String.format("%.2f", (targetKm/100));
                            String msg = (totalDistance/100) + "KM / " + strKm  + "KM";
                            Log.d("XYZ", "Distance traveled: " + totalDistance + " meters");
                            txtTravelled.setText(msg);
                        }else{
                            txtTravelled.setText("");
                        }


                        // Check for significant route change
                        if (isRouteChanged(location)) {
                            Log.d("XYZ", "Route changed");
//                            stat.setText("Route changed");
                        }
                    }
                    previousLocation = location;
                    routePoints.add(location);
                    String msg2 = "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude();
                    Log.d("XYZ", msg2);

                    getPlaceNameFromCoordinates(location.getLatitude() , location.getLongitude() );
                }
            }
        };
    }

        public void initLocate(){
        // Initialize the FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        // Create a location request with desired parameters
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000); // Update interval in milliseconds

        // Check for location permission and request if not granted
//        if (ContextCompat.checkSelfPermission(context,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(context,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_LOCATION_PERMISSION);
//        } else {
//            startLocationUpdates();
//        }
    }






    public void getPlaceNameFromCoordinates(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String locationName = address.getAddressLine(0); // Get the full address as a string
                Log.d("XYZ", "Location Name: " + locationName);
                txtLocationName.setText(locationName);
            } else {
                Log.d("XYZ", "No address found for the coordinates.");
            }
        } catch (IOException e) {
            Log.e("XYZ", "Error: " + e.getMessage());
        }
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        startLocationUpdates();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopLocationUpdates();
//    }

    private void startLocationUpdates() {
        // Check if the ACCESS_FINE_LOCATION permission is granted
        Log.d("XYZ", "Start locacation updates");
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Request location updates
            fusedLocationClient.requestLocationUpdates(createLocationRequest(), locationCallback, null);
        } else {
            // Request the permission
            ActivityCompat.requestPermissions(context,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void handleLocationPermission() {
        // Handle the result of the permission request
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        } else {
            // Permission denied, show appropriate message or perform alternative actions
            Log.d("XYZ", "Location permission denied");
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startLocationUpdates();
//            } else {
//                // Permission denied, show appropriate message or perform alternative actions
//                Log.d("Permission", "Location permission denied");
//            }
//        }
//    }

    private LocationRequest createLocationRequest() {
        // Create a location request with desired parameters
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // Update interval in milliseconds
        locationRequest.setFastestInterval(5000); // Fastest update interval in milliseconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return locationRequest;
    }

    private boolean isRouteChanged(Location currentLocation) {
        if (routePoints.size() < 2) {
            // Route points are not enough to detect route changes
            return false;
        }

        // Get the last two route points
        Location lastPoint = routePoints.get(routePoints.size() - 1);
        Location secondLastPoint = routePoints.get(routePoints.size() - 2);

        // Calculate the bearing (direction) between the last two points
        float bearing = lastPoint.bearingTo(secondLastPoint);

        // Calculate the bearing from the last point to the current location
        float currentBearing = lastPoint.bearingTo(currentLocation);

        // Calculate the bearing difference
        float bearingDifference = Math.abs(bearing - currentBearing);

        // Consider a route change if the bearing difference exceeds a threshold (e.g., 45 degrees)
        return bearingDifference > 45.0f;
    }

}
