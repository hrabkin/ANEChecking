package net.zestug.androidnative;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class ExtensionContext extends FREContext {
	
	public static class Events {
		public static final String RECORDING_START = "RSTART";
		public static final String RECOGNITION_INTERIM = "RINTERIM";
		public static final String RECOGNITION_FINAL = "RFINAL";
	}
	
	public static final String TAG = "androidnative";

	private SpeechRecognizer speechRecognizer;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Log.d(TAG, "Context disposed");
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		
		functions.put(InitFunction.KEY, new InitFunction());
		functions.put(StartRecognitionFunction.KEY, new StartRecognitionFunction());
		
		Log.d(TAG, "Context getFunctions()");
		
		return functions;
	}

	public SpeechRecognizer getSpeechRecognizer() {
		return speechRecognizer;
	}

	public void setSpeechRecognizer(SpeechRecognizer speechRecognizer) {
		this.speechRecognizer = speechRecognizer;
	}
}
