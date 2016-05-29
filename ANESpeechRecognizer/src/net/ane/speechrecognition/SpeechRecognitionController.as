package net.ane.speechrecognition
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	public class SpeechRecognitionController extends EventDispatcher
	{
		private const extensionID:String = "net.zestug.speechrecognizer";
		private var extContext:ExtensionContext;
		
		private var _recognizedText:String = "";
		
		public function SpeechRecognitionController()
		{
			extContext = ExtensionContext.createExtensionContext(
						 extensionID, null);
			extContext.call("initialize");
			extContext.addEventListener(StatusEvent.STATUS, onStatus);
		}

		public function startRecognition():void 
		{
			extContext.call("startRecognition");		
		}
		
		public function stopRecognition():void 
		{
			
		}
		
		private function onStatus(event:StatusEvent):void {
			_recognizedText = String(event.level);
			dispatchEvent(new RecognitionEvent(RecognitionEvent.RECOGNITION_FINAL, _recognizedText, false, false));
		}
		
		public function dispose():void 
		{ 
			stopRecognition();
			extContext.dispose(); 
		}
		
		public function get recognizedText():String
		{
			return _recognizedText;
		}
		
		public function set recognizedText(value:String):void
		{
			if (_recognizedText == value)
				return;
			_recognizedText = value;
		}
	}
}