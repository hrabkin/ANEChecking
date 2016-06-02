package net.zestug.androidnative;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import net.zestug.androidnative.geolocation.StartMonitoringLocationFunction;
import net.zestug.androidnative.speechrecognition.SpeechRecognizer;
import net.zestug.androidnative.speechrecognition.StartSpeechRecognitionFunction;

import java.util.HashMap;
import java.util.Map;

public class ExtensionContext extends FREContext implements LocationListener {

	public static final String TAG = "AndroidNative";
	private SpeechRecognizer speechRecognizer;
	private LocationManager locationManager;

	@Override
	public void dispose() {
		Log.d(TAG, "Context disposed");

		if (speechRecognizer != null)
			speechRecognizer.stopRecognition();

		if (locationManager != null)
			locationManager.removeUpdates(this);
	}
	
	@Override
	public Map<String, FREFunction> getFunctions() {

		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();

		functions.put(InitFunction.KEY, new InitFunction());
		functions.put(StartSpeechRecognitionFunction.KEY, new StartSpeechRecognitionFunction());
		functions.put(StartMonitoringLocationFunction.KEY, new StartMonitoringLocationFunction());

		Log.d(TAG, "Context getFunctions()");

		return functions;
	}

	public SpeechRecognizer getSpeechRecognizer() {
		return speechRecognizer;
	}

	public void setSpeechRecognizer(SpeechRecognizer speechRecognizer) {
		this.speechRecognizer = speechRecognizer;
	}

	public LocationManager getLocationManager() {
		return locationManager;
	}

	public void setLocationManager(LocationManager locationManager) {
		this.locationManager = locationManager;
	}

	// Location Updates
	@Override
	public void onLocationChanged(Location location) {

		final double longitudeNetwork = location.getLongitude();
		final double latitudeNetwork = location.getLatitude();
		// Toast.makeText(MainActivity.this, "Network Provider update", Toast.LENGTH_SHORT).show();
		Log.d(ExtensionContext.TAG, "long: " + longitudeNetwork + " lat: " + latitudeNetwork);
		String data = longitudeNetwork + "|" + latitudeNetwork;

		Toast.makeText(getActivity()
				.getApplicationContext(), "Location: " + data, Toast.LENGTH_LONG).show();
		dispatchStatusEventAsync(Events.GEOLOCATION_DONE, data);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	public static class Events {
		public static final String RECORDING_START = "RSTART";
		public static final String RECOGNITION_INTERIM = "RINTERIM";
		public static final String RECOGNITION_FINAL = "RFINAL";
		public static final String GEOLOCATION_DONE = "GEO_DONE";
		public static final String GEOLOCATION_FAILED = "GEO_FAIL";
	}
}
