package net.adobe.nativeextension
{
import flash.events.EventDispatcher;
import flash.events.StatusEvent;
import flash.external.ExtensionContext;

import net.adobe.nativeextension.events.LocationDataEvent;
import net.adobe.nativeextension.events.RecognitionEvent;

public class NativeFeaturesController extends EventDispatcher {
	private const extensionID:String = "net.zestug.androidnative";

	public function NativeFeaturesController()
		{
			extContext = ExtensionContext.createExtensionContext(
					extensionID, "");
			extContext.call("initialize");
			extContext.addEventListener(StatusEvent.STATUS, onStatus);
		}

	private var extContext:ExtensionContext;
	private var _recognizedText:String = "";

	public function startRecognition():void {
		extContext.call("startRecognition");
	}

	public function startListeningLocation():void {
			extContext.call("geoLocation");
	}

	public function dispose():void {
			// stopRecognition();
		extContext.dispose();
	}

	private function onStatus(event:StatusEvent):void {

		_recognizedText = String(event.level);
		switch (event.code) {
			default:
			case LocationDataEvent.GEOLOCATION_DONE:

				var rawData:Array = _recognizedText.split("|");
				dispatchEvent(new LocationDataEvent(LocationDataEvent.GEOLOCATION_DONE, rawData[0], rawData[1], false, false));
				break;
			case RecognitionEvent.RECOGNITION_INTERIM:

				dispatchEvent(new RecognitionEvent(RecognitionEvent.RECOGNITION_FINAL, _recognizedText, false, false));
				break;
			case RecognitionEvent.RECOGNITION_FINAL:

				dispatchEvent(new RecognitionEvent(RecognitionEvent.RECOGNITION_FINAL, _recognizedText, false, false));
				break;
		}
		}
	}
}