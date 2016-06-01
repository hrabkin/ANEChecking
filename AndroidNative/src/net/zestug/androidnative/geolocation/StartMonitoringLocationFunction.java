package net.zestug.androidnative.geolocation;

import net.zestug.androidnative.ExtensionContext;
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

public class StartMonitoringLocationFunction implements FREFunction {
	
	public static final String KEY = "geoLocation";
	
	@Override
	public FREObject call(FREContext extCtx, FREObject[] arg1) {

        ExtensionContext extContext = (ExtensionContext) extCtx;

		if (ContextCompat.checkSelfPermission(extContext.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
        	
        	Log.d(ExtensionContext.TAG, "Required permissions not provided");
        	extContext.dispatchStatusEventAsync(Events.GEOLOCATION_FAILED, "Requires permission");
            return null;
        }	extContext.getLocationManager().requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 5000, 500, extContext);
        
		return null;
	}
}
