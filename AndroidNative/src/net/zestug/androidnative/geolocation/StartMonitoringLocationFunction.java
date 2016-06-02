package net.zestug.androidnative.geolocation;

import android.location.LocationManager;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import net.zestug.androidnative.ExtensionContext;

public class StartMonitoringLocationFunction implements FREFunction {
	
	public static final String KEY = "geoLocation";
	
	@Override
	public FREObject call(FREContext extCtx, FREObject[] arg1) {

        ExtensionContext extContext = (ExtensionContext) extCtx;
		extContext.getLocationManager().requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 5000, 500, extContext);
        
		return null;
	}
}
