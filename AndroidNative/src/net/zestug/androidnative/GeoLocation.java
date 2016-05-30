package net.zestug.androidnative;

import net.zestug.androidnative.ExtensionContext.Events;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class GeoLocation implements FREFunction {
	
	public static final String KEY = "geoLocation";
	private LocationManager locationManager;
	private ExtensionContext extContext;
	
	@Override
	public FREObject call(FREContext extCtx, FREObject[] arg1) {
		
		extContext = (ExtensionContext) extCtx;
		
		locationManager = (LocationManager) extContext.getActivity().getSystemService(Context.LOCATION_SERVICE);
		if (ContextCompat.checkSelfPermission(extContext.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(extContext.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
        	
        	Log.d(ExtensionContext.TAG, "Required permissions not provided");
        	extContext.dispatchStatusEventAsync(Events.GEOLOCATION_FAILED, "Requires permission");
            return null;
        }	locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        
		return null;
	}
	
	public void makeUseOfNewLocation(Location location) {

        final double longitudeNetwork = location.getLongitude();
        final double latitudeNetwork = location.getLatitude();
        // Toast.makeText(MainActivity.this, "Network Provider update", Toast.LENGTH_SHORT).show();
    	Log.d(ExtensionContext.TAG, "Required permissions not provided long: " + longitudeNetwork + " lat: " + latitudeNetwork);
    	String data = longitudeNetwork + "|" + latitudeNetwork;
    	extContext.dispatchStatusEventAsync(Events.GEOLOCATION_DONE, data);
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
    
    public void dispose() {

        if (ContextCompat.checkSelfPermission(extContext.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(extContext.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }   locationManager.removeUpdates(locationListener);
    }
}
