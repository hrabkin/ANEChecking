package net.zestug.androidnative;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class InitFunction implements FREFunction {
	
	public static final String KEY = "initialize";
	
	@Override
	public FREObject call(FREContext extContext, FREObject[] params) {
		
		Log.d(ExtensionContext.TAG, "androidnative Initialization");
		ExtensionContext mExtContext = (ExtensionContext) extContext;

		SpeechRecognizer recognizer = new NuanceRecognizer(mExtContext); 
		mExtContext.setandroidnative(recognizer);
		
		return null;
	}

}
