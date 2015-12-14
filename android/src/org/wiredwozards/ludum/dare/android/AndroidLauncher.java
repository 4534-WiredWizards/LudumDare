package org.wiredwozards.ludum.dare.android;

import org.wiredwizards.ludum.dare.LD34Main;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {
	
	private static String TAG = "InfiniteStone";
	protected AdView adView;

	//public GoogleApiClient apiClient = new GoogleApiClient.Builder(this).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN).build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout layout = new RelativeLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new LD34Main(), config);
		layout.addView(gameView);

		adView = new AdView(this);
		// Listen for ads and print when they appear
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad Loaded!");
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				Log.i(TAG, "Ad failed!");
			}
		});

		Log.i(TAG, "Device id: " + Secure.getString(getContext().getContentResolver(), Secure.ANDROID_ID));

		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-7345229224654271/4800313744");

		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice("637F5950F4CB830A87224FF619278AD4");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layout.addView(adView, adParams);
		adView.loadAd(builder.build());

		setContentView(layout);

	}
}
