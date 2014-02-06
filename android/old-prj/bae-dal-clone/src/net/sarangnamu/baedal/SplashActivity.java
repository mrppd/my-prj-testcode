/* 
 * Copyright (c) 2003-2011, cheol-dong choi, twitter @aucd29
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions: 
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software. 
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE. 
 *  
 */

package net.sarangnamu.baedal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import net.sarangnamu.activity.SplashScreen;
import net.sarangnamu.baedal.network.NetworkBaedal;
import net.sarangnamu.baedal.config.ConfigBaedal;

public class SplashActivity extends SplashScreen {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		ConfigBaedal.instanceNMEA(getBaseContext());
		setTargetIntent(new Intent(this, Main.class));		
		
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.splash);
	}
	
	@Override
	public void work() throws Exception {

		// Get data
		Log.d("", "@@@@@@@ start read data via server");
		ConfigBaedal.mainContent = NetworkBaedal.getMainData();
		Log.d("", "@@json: " + ConfigBaedal.mainContent.toString());
		
		// Download images at s_banner_img tag datas 
		NetworkBaedal.downloadMainBannerImages();
		
		// CHECK NMEA
		ConfigBaedal.nmea.checkNMEA();
		ConfigBaedal.nmea.stopLocation();
	}
}
