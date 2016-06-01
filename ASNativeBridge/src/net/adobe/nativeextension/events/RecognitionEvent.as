package net.adobe.nativeextension.events
{
import flash.events.Event;

public class RecognitionEvent extends Event
	{
		public static const RECORDING_START:String = "RSTART";
		public static const RECOGNITION_INTERIM:String = "RINTERIM";
		public static const RECOGNITION_FINAL:String = "RFINAL";
		
		public function RecognitionEvent(type:String, text:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.recognizedText = text;
		}

	public var recognizedText:String;
	}
}