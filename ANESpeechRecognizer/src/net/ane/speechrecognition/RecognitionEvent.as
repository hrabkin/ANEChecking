package net.ane.speechrecognition
{
	import flash.events.Event;
	
	public class RecognitionEvent extends Event
	{
		public static const RECORDING_START:String = "RSTART";
		public static const RECOGNITION_INTERIM:String = "RINTERIM";
		public static const RECOGNITION_FINAL:String = "RFINAL";
		
		public var recognizedText:String;
		
		public function RecognitionEvent(type:String, text:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			this.recognizedText = text;
			super(type, bubbles, cancelable);
		}
	}
}