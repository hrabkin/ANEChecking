package net.zestug.androidnative;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class StartRecognitionFunction implements FREFunction {

	public static final String KEY = "startRecognition";
	
	@Override
	public FREObject call(FREContext extCtx, FREObject[] arg1) {
		
		ExtensionContext extContext = (ExtensionContext) extCtx;
		
		SpeechRecognizer speechRecognizer = extContext.getSpeechRecognizer();
		if (speechRecognizer == null)
			Log.d(ExtensionContext.TAG, "Cann't start speech recognition, recognizer not initialized");
		else
			speechRecognizer.startRecognition();
		
		return null;
	}

}
