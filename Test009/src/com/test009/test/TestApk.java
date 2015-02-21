package com.test009.test;

import junit.framework.TestCase;

import com.jayway.android.robotium.solo.By;
import com.jayway.android.robotium.solo.Solo;

import android.R;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

@SuppressWarnings("unchecked")
public class TestApk extends ActivityInstrumentationTestCase2 {
	//private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.soft.apk008v/com.soft.apk008.Apk008Activity";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.soft.apk008.Apk008Activity";
	private static final String TAG = "Test009";

	private static Class launcherActivityClass;
	static {
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TestApk() throws ClassNotFoundException {
		super(launcherActivityClass);
	}
	
	private Solo solo;
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testCanOpenSettings(){  
		Log.d(TAG, "Wait for 008 to start.");
		solo.waitForText("008����v", 1, 15000);
		
		Log.d(TAG, "Click config button.");
		Activity act = solo.getCurrentActivity();
        
        int main_centerImageId = act.getResources().getIdentifier("com.soft.apk008v:id/main_centerImg", "id" , act.getPackageName());
        View main_centerImageView = act.findViewById(main_centerImageId);
        
        solo.clickOnView(main_centerImageView);
        
        solo.waitForText("008����0113", 1, 10000);
		
        Log.d(TAG, "Click save button.");
		Activity loadActivity = solo.getCurrentActivity();
        int saveButtonId = loadActivity.getResources().getIdentifier("com.soft.apk008v:id/button_restore", "id" , loadActivity.getPackageName());
        View saveButtonView = loadActivity.findViewById(saveButtonId);
        
        solo.clickOnView(saveButtonView);
        
        solo.sleep(5000);
        
        Log.d(TAG, "done");
    }
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}