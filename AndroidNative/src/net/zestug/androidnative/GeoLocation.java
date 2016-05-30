package net.zestug.androidnative;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class GeoLocation implements FREFunction {
	
	private LocationManager locationManager;
	
	@Override
	public FREObject call(FREContext extCtx, FREObject[] arg1) {
		
		locationManager = (LocationManager) extCtx.getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Toast.makeText(extCtx.getActivity().getApplicationContext(), "Requires location services enabled", Toast.LENGTH_LONG).show();
            return null;
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		
		
		return null;
	}
	
	public void makeUseOfNewLocation(Location location) {

        final double longitudeNetwork = location.getLongitude();
        final double latitudeNetwork = location.getLatitude();
        // Toast.makeText(MainActivity.this, "Network Provider update", Toast.LENGTH_SHORT).show();
    }

    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            makeUseOfNewLocation(location);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
    
    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }   locationManager.removeUpdates(locationListener);
    }*/
}
