package com.example.runtrackerwizardv11;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class DistanceTrackingActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Location previousLocation;
    private float totalDistance;
    private List<Location> routePoints;
    TextView stat, stat2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_tracking);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        routePoints = new ArrayList<>();
        stat = findViewById(R.id.locationStatus);
        stat2 = findViewById(R.id.locationStatus2);
        stat.setText("Hello");
        stat2.setText("Hello2");

        // Create a location callback to receive location updates
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (previousLocation != null) {
                        // Calculate distance between previous location and current location
                        float distance = previousLocation.distanceTo(location);
                        totalDistance += distance;
                        String msg = "Distance traveled: " + totalDistance + " meters";
                        Log.d("Distance", "Distance traveled: " + totalDistance + " meters");
                        stat.setText(msg);

                        // Check for significant route change
                        if (isRouteChanged(location)) {
                            Log.d("Route", "Route changed");
                            stat.setText("Route changed");
                        }
                    }
                    previousLocation = location;
                    routePoints.add(location);
                    String msg2 = "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude();
                    Log.d("Location", msg2);

                    stat2.setText(msg2);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void startLocationUpdates() {
        // Check if the ACCESS_FINE_LOCATION permission is granted
        Log.d("Route", "Start locacation updates");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Request location updates
            fusedLocationClient.requestLocationUpdates(createLocationRequest(), locationCallback, null);
        } else {
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void handleLocationPermission() {
        // Handle the result of the permission request
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        } else {
            // Permission denied, show appropriate message or perform alternative actions
            Log.d("Permission", "Location permission denied");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                // Permission denied, show appropriate message or perform alternative actions
                Log.d("Permission", "Location permission denied");
            }
        }
    }

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