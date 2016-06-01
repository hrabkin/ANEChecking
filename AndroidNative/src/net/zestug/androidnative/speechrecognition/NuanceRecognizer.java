package net.zestug.androidnative.speechrecognition;

import android.widget.Toast;
import net.zestug.androidnative.ExtensionContext;
import net.zestug.androidnative.ExtensionContext.Events;
import android.content.Context;
import android.util.Log;

import com.nuance.speechkit.DetectionType;
import com.nuance.speechkit.Language;
import com.nuance.speechkit.RecognitionType;
import com.nuance.speechkit.Session;
import com.nuance.speechkit.Transaction;
import com.nuance.speechkit.TransactionException;
import com.nuance.speechkit.Recognition;

public class NuanceRecognizer extends SpeechRecognizer {
	
	private Session skSession;
    private Transaction recoTransaction;
	private Context context; 
	private ExtensionContext extContext;
	
	public NuanceRecognizer(ExtensionContext extCtx) {
		extContext = extCtx;
		context    = extCtx.getActivity().getApplicationContext();
		skSession  = Session.Factory.session(this.context, Configuration.SERVER_URI, Configuration.APP_KEY);
		Log.d(ExtensionContext.TAG, "Nuance recognizer initialized");
	}
	
	@Override
	public void startRecognition() {
		Transaction.Options options = new Transaction.Options();
        options.setRecognitionType(RecognitionType.SEARCH);
        options.setDetection(DetectionType.Short);
        options.setLanguage(new Language("eng-US"));

        //Start listening
        recoTransaction = skSession.recognize(options, recoListener);
	}

	@Override
	public void stopRecognition() {
		recoTransaction.stopRecording();
	}
	
	 private Transaction.Listener recoListener = new Transaction.Listener() {

	        @Override
	        public void onStartedRecording(Transaction transaction) {
	        	Log.d(ExtensionContext.TAG, "onStartedRecording");
	            

	        	extContext.dispatchStatusEventAsync(Events.RECORDING_START, null);
	        }

	        @Override
	        public void onFinishedRecording(Transaction transaction) {
	        	Log.d(ExtensionContext.TAG, "onFinishedRecording");
	        		
	        	
	        }

	        @Override
	        public void onRecognition(Transaction transaction, Recognition recognition) {
	        	Log.d(ExtensionContext.TAG, "onRecognition: " + recognition.getText());
	        	String interimResults = recognition.getText();

				Toast.makeText(extContext.getActivity()
						.getApplicationContext(), "Interim: " + interimResults, Toast.LENGTH_LONG).show();
	        	extContext.dispatchStatusEventAsync(Events.RECOGNITION_INTERIM, interimResults);
	        }
	        
	        @Override
	        public void onSuccess(Transaction transaction, String finalResults) {
	        	Log.d(ExtensionContext.TAG, "nonSuccess");

				Toast.makeText(extContext.getActivity()
						.getApplicationContext(), "FinalResults: " + finalResults, Toast.LENGTH_LONG).show();
	            //Notification of a successful transaction. Nothing to do here.
	        	extContext.dispatchStatusEventAsync(Events.RECOGNITION_FINAL, finalResults);
	        }

	        @Override
	        public void onError(Transaction transaction, String s, TransactionException e) {
	        	Log.d(ExtensionContext.TAG, "onError: " + e.getMessage() + ". " + s);
	            
	        }
	    };
}
