package net.zestug.androidnative;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import net.zestug.androidnative.speechrecognition.NuanceRecognizer;
import net.zestug.androidnative.speechrecognition.SpeechRecognizer;

public class InitFunction implements FREFunction {
	
	public static final String KEY = "initialize";
	
	@Override
	public FREObject call(FREContext extContext, FREObject[] params) {

		Log.d(ExtensionContext.TAG, "AndroidNative Initialization");
		ExtensionContext mExtContext = (ExtensionContext) extContext;

		SpeechRecognizer recognizer = new NuanceRecognizer(mExtContext);
		mExtContext.setSpeechRecognizer(recognizer);

		LocationManager locationManager = (LocationManager) extContext.getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		mExtContext.setLocationManager(locationManager);

		return null;
	}

}
