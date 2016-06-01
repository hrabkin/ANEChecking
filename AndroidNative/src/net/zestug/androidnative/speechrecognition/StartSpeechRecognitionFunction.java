package net.zestug.androidnative.speechrecognition;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import net.zestug.androidnative.ExtensionContext;

public class StartSpeechRecognitionFunction implements FREFunction {

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
