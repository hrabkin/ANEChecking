package net.zestug.speechrecognizer;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class InitFunction implements FREFunction {
	
	public static final String KEY = "initialize";
	
	@Override
	public FREObject call(FREContext extContext, FREObject[] params) {
		
		Log.d(ExtensionContext.TAG, "SpeechRecognizer Initialization");
		ExtensionContext mExtContext = (ExtensionContext) extContext;

		SpeechRecognizer recognizer = new NuanceRecognizer(mExtContext); 
		mExtContext.setSpeechRecognizer(recognizer);
		
		return null;
	}

}
