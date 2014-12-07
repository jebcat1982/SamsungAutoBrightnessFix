package com.smartmadsoft.xposed.samsungautobrightnessfix;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Angler implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		
		if (!lpparam.packageName.equals("android"))
			return;
		
		findAndHookMethod("com.android.server.power.DisplayPowerController", lpparam.classLoader, "applyLightSensorMeasurementSEC", long.class, float.class, new XC_MethodHook() {			
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {				
				XposedHelpers.setObjectField(param.thisObject, "mTiltAngle", 80.0F);
			}			
		});
		
	}

}
