
package net.zestug.androidnative;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class ExtensionMain implements FREExtension {
	
	private ExtensionContext context;
	
	@Override
	public FREContext createContext(String arg0) {
		
		if (context == null)
			context = new ExtensionContext();
		
		return context;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}
