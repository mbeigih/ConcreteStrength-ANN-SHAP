package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static double[] _shap1 = null;
public static double _base = 0;
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _pw1 = null;
public static anywheresoftware.b4a.objects.WorkbookWrapper _hpcann = null;
public static double[] _scaler_mean_ = null;
public static double[] _scaler_scale_ = null;
public static double[][] _coef_0 = null;
public static double[] _intercepts_0 = null;
public static double[][] _coef_1 = null;
public static double[] _intercepts_1 = null;
public static double[] _coef_2 = null;
public static double _intercepts_2 = 0;
public static anywheresoftware.b4a.objects.WorkbookWrapper _hpcsvr = null;
public static double _gamma = 0;
public static double[][] _support_vectors = null;
public static double[] _dual_coef = null;
public static double _intercept = 0;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label12 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label15 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label18 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label21 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label24 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label9 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar1 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar2 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar3 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar4 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar5 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar6 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar7 = null;
public anywheresoftware.b4a.objects.SeekBarWrapper _seekbar8 = null;
public b4a.example.plot _plot = null;
public b4a.example.shap _shap = null;
public b4a.example.adaboost _adaboost = null;
public b4a.example.randomforest _randomforest = null;
public b4a.example.svr _svr = null;
public b4a.example.xgboost _xgboost = null;
public b4a.example.ann _ann = null;
public b4a.example.catboost _catboost = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (plot.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
double _a = 0;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 63;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 64;BA.debugLine="Activity.LoadLayout(\"Layout\")";
mostCurrent._activity.LoadLayout("Layout",mostCurrent.activityBA);
 //BA.debugLineNum = 65;BA.debugLine="SeekBar1.Value=(540-70)/(702-70)*100";
mostCurrent._seekbar1.setValue((int) ((540-70)/(double)(702-70)*100));
 //BA.debugLineNum = 66;BA.debugLine="SeekBar2.Value=(0-0)/(440-0)*100";
mostCurrent._seekbar2.setValue((int) ((0-0)/(double)(440-0)*100));
 //BA.debugLineNum = 67;BA.debugLine="SeekBar3.Value=(0-0)/(544-0)*100";
mostCurrent._seekbar3.setValue((int) ((0-0)/(double)(544-0)*100));
 //BA.debugLineNum = 68;BA.debugLine="SeekBar4.Value=(162-105)/(295-105)*100";
mostCurrent._seekbar4.setValue((int) ((162-105)/(double)(295-105)*100));
 //BA.debugLineNum = 69;BA.debugLine="SeekBar5.Value=(2.5-0)/(35.1-0)*100";
mostCurrent._seekbar5.setValue((int) ((2.5-0)/(double)(35.1-0)*100));
 //BA.debugLineNum = 70;BA.debugLine="SeekBar6.Value=(1040-723)/(1218-723)*100";
mostCurrent._seekbar6.setValue((int) ((1040-723)/(double)(1218-723)*100));
 //BA.debugLineNum = 71;BA.debugLine="SeekBar7.Value=(676-280)/(992.6-280)*100";
mostCurrent._seekbar7.setValue((int) ((676-280)/(double)(992.6-280)*100));
 //BA.debugLineNum = 72;BA.debugLine="SeekBar8.Value=(28-1)/(365-1)*100";
mostCurrent._seekbar8.setValue((int) ((28-1)/(double)(365-1)*100));
 //BA.debugLineNum = 73;BA.debugLine="Dim a As Double=DateTime.Now";
_a = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 75;BA.debugLine="HPCANN.Initialize(File.DirAssets, \"ANN.xls\")";
_hpcann.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ANN.xls");
 //BA.debugLineNum = 76;BA.debugLine="For i=0 To 7";
{
final int step12 = 1;
final int limit12 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 77;BA.debugLine="scaler_mean_ (i) =HPCANN.GetSheet(1).GetCellValu";
_scaler_mean_[_i] = (double)(Double.parseDouble(_hpcann.GetSheet((int) (1)).GetCellValue((int) (0),_i)));
 //BA.debugLineNum = 78;BA.debugLine="scaler_scale_ (i) = HPCANN.GetSheet(2).GetCellVa";
_scaler_scale_[_i] = (double)(Double.parseDouble(_hpcann.GetSheet((int) (2)).GetCellValue((int) (0),_i)));
 }
};
 //BA.debugLineNum = 81;BA.debugLine="For i=0 To 186";
{
final int step16 = 1;
final int limit16 = (int) (186);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
 //BA.debugLineNum = 82;BA.debugLine="For j=0 To 7";
{
final int step17 = 1;
final int limit17 = (int) (7);
_j = (int) (0) ;
for (;_j <= limit17 ;_j = _j + step17 ) {
 //BA.debugLineNum = 83;BA.debugLine="coef_0 (i,j) = HPCANN.GetSheet(3).GetCellValue(";
_coef_0[_i][_j] = (double)(Double.parseDouble(_hpcann.GetSheet((int) (3)).GetCellValue(_j,_i)));
 }
};
 //BA.debugLineNum = 85;BA.debugLine="intercepts_0 (i) = HPCANN.GetSheet(4).GetCellVal";
_intercepts_0[_i] = (double)(Double.parseDouble(_hpcann.GetSheet((int) (4)).GetCellValue((int) (0),_i)));
 }
};
 //BA.debugLineNum = 88;BA.debugLine="For i=0 To 70";
{
final int step22 = 1;
final int limit22 = (int) (70);
_i = (int) (0) ;
for (;_i <= limit22 ;_i = _i + step22 ) {
 //BA.debugLineNum = 89;BA.debugLine="For j=0 To 186";
{
final int step23 = 1;
final int limit23 = (int) (186);
_j = (int) (0) ;
for (;_j <= limit23 ;_j = _j + step23 ) {
 //BA.debugLineNum = 90;BA.debugLine="coef_1 (i,j) = HPCANN.GetSheet(5).GetCellValue(";
_coef_1[_i][_j] = (double)(Double.parseDouble(_hpcann.GetSheet((int) (5)).GetCellValue(_j,_i)));
 }
};
 //BA.debugLineNum = 92;BA.debugLine="intercepts_1 (i) = HPCANN.GetSheet(6).GetCellVal";
_intercepts_1[_i] = (double)(Double.parseDouble(_hpcann.GetSheet((int) (6)).GetCellValue((int) (0),_i)));
 }
};
 //BA.debugLineNum = 95;BA.debugLine="For i=0 To 70";
{
final int step28 = 1;
final int limit28 = (int) (70);
_i = (int) (0) ;
for (;_i <= limit28 ;_i = _i + step28 ) {
 //BA.debugLineNum = 96;BA.debugLine="coef_2 (i) = HPCANN.GetSheet(7).GetCellValue(i,0";
_coef_2[_i] = (double)(Double.parseDouble(_hpcann.GetSheet((int) (7)).GetCellValue(_i,(int) (0))));
 }
};
 //BA.debugLineNum = 98;BA.debugLine="intercepts_2 = HPCANN.GetSheet(8).GetCellValue(0,";
_intercepts_2 = (double)(Double.parseDouble(_hpcann.GetSheet((int) (8)).GetCellValue((int) (0),(int) (0))));
 //BA.debugLineNum = 100;BA.debugLine="Log(DateTime.Now-a)";
anywheresoftware.b4a.keywords.Common.LogImpl("3131109",BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_a),0);
 //BA.debugLineNum = 102;BA.debugLine="HPCSVR.Initialize(File.DirAssets, \"SVR.xls\")";
_hpcsvr.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"SVR.xls");
 //BA.debugLineNum = 103;BA.debugLine="For i=0 To 7";
{
final int step34 = 1;
final int limit34 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit34 ;_i = _i + step34 ) {
 //BA.debugLineNum = 104;BA.debugLine="scaler_mean_ (i) =HPCSVR.GetSheet(1).GetCellValu";
_scaler_mean_[_i] = (double)(Double.parseDouble(_hpcsvr.GetSheet((int) (1)).GetCellValue((int) (0),_i)));
 //BA.debugLineNum = 105;BA.debugLine="scaler_scale_ (i) = HPCSVR.GetSheet(2).GetCellVa";
_scaler_scale_[_i] = (double)(Double.parseDouble(_hpcsvr.GetSheet((int) (2)).GetCellValue((int) (0),_i)));
 }
};
 //BA.debugLineNum = 107;BA.debugLine="gamma = HPCSVR.GetSheet(3).GetCellValue(0,0)";
_gamma = (double)(Double.parseDouble(_hpcsvr.GetSheet((int) (3)).GetCellValue((int) (0),(int) (0))));
 //BA.debugLineNum = 109;BA.debugLine="For i=0 To 1622";
{
final int step39 = 1;
final int limit39 = (int) (1622);
_i = (int) (0) ;
for (;_i <= limit39 ;_i = _i + step39 ) {
 //BA.debugLineNum = 110;BA.debugLine="For j=0 To 7";
{
final int step40 = 1;
final int limit40 = (int) (7);
_j = (int) (0) ;
for (;_j <= limit40 ;_j = _j + step40 ) {
 //BA.debugLineNum = 111;BA.debugLine="support_vectors(i,j) = HPCSVR.GetSheet(4).GetCe";
_support_vectors[_i][_j] = (double)(Double.parseDouble(_hpcsvr.GetSheet((int) (4)).GetCellValue(_j,_i)));
 }
};
 }
};
 //BA.debugLineNum = 115;BA.debugLine="For i=0 To 1622";
{
final int step44 = 1;
final int limit44 = (int) (1622);
_i = (int) (0) ;
for (;_i <= limit44 ;_i = _i + step44 ) {
 //BA.debugLineNum = 116;BA.debugLine="dual_coef (i) = HPCSVR.GetSheet(5).GetCellValue(";
_dual_coef[_i] = (double)(Double.parseDouble(_hpcsvr.GetSheet((int) (5)).GetCellValue((int) (0),_i)));
 }
};
 //BA.debugLineNum = 119;BA.debugLine="intercept =HPCSVR.GetSheet(6).GetCellValue(0,0)";
_intercept = (double)(Double.parseDouble(_hpcsvr.GetSheet((int) (6)).GetCellValue((int) (0),(int) (0))));
 //BA.debugLineNum = 123;BA.debugLine="SHAP.LoadData(File.DirAssets, \"data.csv\")";
mostCurrent._shap._loaddata /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"data.csv");
 //BA.debugLineNum = 134;BA.debugLine="pw1.KeepAlive(True)";
_pw1.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
double[] _x = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
double _t1 = 0;
int _i = 0;
 //BA.debugLineNum = 157;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 158;BA.debugLine="Dim x(8) As Double";
_x = new double[(int) (8)];
;
 //BA.debugLineNum = 159;BA.debugLine="x(0) = Label3.text";
_x[(int) (0)] = (double)(Double.parseDouble(mostCurrent._label3.getText()));
 //BA.debugLineNum = 160;BA.debugLine="x(1) = Label6.text";
_x[(int) (1)] = (double)(Double.parseDouble(mostCurrent._label6.getText()));
 //BA.debugLineNum = 161;BA.debugLine="x(2) = Label9.text";
_x[(int) (2)] = (double)(Double.parseDouble(mostCurrent._label9.getText()));
 //BA.debugLineNum = 162;BA.debugLine="x(3) = Label12.text";
_x[(int) (3)] = (double)(Double.parseDouble(mostCurrent._label12.getText()));
 //BA.debugLineNum = 163;BA.debugLine="x(4) = Label15.text";
_x[(int) (4)] = (double)(Double.parseDouble(mostCurrent._label15.getText()));
 //BA.debugLineNum = 164;BA.debugLine="x(5) = Label18.text";
_x[(int) (5)] = (double)(Double.parseDouble(mostCurrent._label18.getText()));
 //BA.debugLineNum = 165;BA.debugLine="x(6) = Label21.text";
_x[(int) (6)] = (double)(Double.parseDouble(mostCurrent._label21.getText()));
 //BA.debugLineNum = 166;BA.debugLine="x(7) = Label24.text";
_x[(int) (7)] = (double)(Double.parseDouble(mostCurrent._label24.getText()));
 //BA.debugLineNum = 171;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 172;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 173;BA.debugLine="Dim SHAP1() As Double";
_shap1 = new double[(int) (0)];
;
 //BA.debugLineNum = 174;BA.debugLine="Dim t1 As Double = DateTime.Now";
_t1 = anywheresoftware.b4a.keywords.Common.DateTime.getNow();
 //BA.debugLineNum = 175;BA.debugLine="m = SHAP.ExplainPrediction(x)";
_m = mostCurrent._shap._explainprediction /*anywheresoftware.b4a.objects.collections.Map*/ (mostCurrent.activityBA,_x);
 //BA.debugLineNum = 176;BA.debugLine="Log(DateTime.Now - t1)";
anywheresoftware.b4a.keywords.Common.LogImpl("3196627",BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_t1),0);
 //BA.debugLineNum = 177;BA.debugLine="Dim SHAP1() As Double=m.Get(\"shap_values\")";
_shap1 = (double[])(_m.Get((Object)("shap_values")));
 //BA.debugLineNum = 178;BA.debugLine="For i = 0 To 7";
{
final int step17 = 1;
final int limit17 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit17 ;_i = _i + step17 ) {
 //BA.debugLineNum = 179;BA.debugLine="Log(\"Feature \" & i & _ 	        \" SHAP = \" & SHA";
anywheresoftware.b4a.keywords.Common.LogImpl("3196630","Feature "+BA.NumberToString(_i)+" SHAP = "+BA.NumberToString(_shap1[_i]),0);
 }
};
 //BA.debugLineNum = 182;BA.debugLine="Log(\"Base Value = \" & m.Get(\"base_value\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("3196633","Base Value = "+BA.ObjectToString(_m.Get((Object)("base_value"))),0);
 //BA.debugLineNum = 183;BA.debugLine="Log(\"Prediction = \" & m.Get(\"prediction\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("3196634","Prediction = "+BA.ObjectToString(_m.Get((Object)("prediction"))),0);
 //BA.debugLineNum = 184;BA.debugLine="Log(\"Sum SHAP = \" & m.Get(\"sum_prediction\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("3196635","Sum SHAP = "+BA.ObjectToString(_m.Get((Object)("sum_prediction"))),0);
 //BA.debugLineNum = 185;BA.debugLine="Log(ANN.n)";
anywheresoftware.b4a.keywords.Common.LogImpl("3196636",BA.NumberToString(mostCurrent._ann._n /*int*/ ),0);
 //BA.debugLineNum = 186;BA.debugLine="Log(\"Button4 \" & DateTime.Now)";
anywheresoftware.b4a.keywords.Common.LogImpl("3196637","Button4 "+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),0);
 //BA.debugLineNum = 187;BA.debugLine="base = m.Get(\"base_value\")";
_base = (double)(BA.ObjectToNumber(_m.Get((Object)("base_value"))));
 //BA.debugLineNum = 188;BA.debugLine="StartActivity(Plot)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._plot.getObject()));
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 44;BA.debugLine="Dim Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private Label12 As Label";
mostCurrent._label12 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private Label15 As Label";
mostCurrent._label15 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private Label18 As Label";
mostCurrent._label18 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private Label21 As Label";
mostCurrent._label21 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private Label24 As Label";
mostCurrent._label24 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private Label6 As Label";
mostCurrent._label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private Label9 As Label";
mostCurrent._label9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private SeekBar1 As SeekBar";
mostCurrent._seekbar1 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private SeekBar2 As SeekBar";
mostCurrent._seekbar2 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private SeekBar3 As SeekBar";
mostCurrent._seekbar3 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private SeekBar4 As SeekBar";
mostCurrent._seekbar4 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private SeekBar5 As SeekBar";
mostCurrent._seekbar5 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private SeekBar6 As SeekBar";
mostCurrent._seekbar6 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private SeekBar7 As SeekBar";
mostCurrent._seekbar7 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private SeekBar8 As SeekBar";
mostCurrent._seekbar8 = new anywheresoftware.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
plot._process_globals();
shap._process_globals();
adaboost._process_globals();
randomforest._process_globals();
svr._process_globals();
xgboost._process_globals();
ann._process_globals();
catboost._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Dim SHAP1() As Double";
_shap1 = new double[(int) (0)];
;
 //BA.debugLineNum = 20;BA.debugLine="Dim base As Double";
_base = 0;
 //BA.debugLineNum = 21;BA.debugLine="Dim pw1 As PhoneWakeState";
_pw1 = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 23;BA.debugLine="Dim HPCANN As ReadableWorkbook";
_hpcann = new anywheresoftware.b4a.objects.WorkbookWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Dim scaler_mean_ (8) As Double";
_scaler_mean_ = new double[(int) (8)];
;
 //BA.debugLineNum = 25;BA.debugLine="Dim scaler_scale_ (8) As Double";
_scaler_scale_ = new double[(int) (8)];
;
 //BA.debugLineNum = 26;BA.debugLine="Dim coef_0 (187, 8) As Double";
_coef_0 = new double[(int) (187)][];
{
int d0 = _coef_0.length;
int d1 = (int) (8);
for (int i0 = 0;i0 < d0;i0++) {
_coef_0[i0] = new double[d1];
}
}
;
 //BA.debugLineNum = 27;BA.debugLine="Dim intercepts_0 (187) As Double";
_intercepts_0 = new double[(int) (187)];
;
 //BA.debugLineNum = 28;BA.debugLine="Dim coef_1 (71,187) As Double";
_coef_1 = new double[(int) (71)][];
{
int d0 = _coef_1.length;
int d1 = (int) (187);
for (int i0 = 0;i0 < d0;i0++) {
_coef_1[i0] = new double[d1];
}
}
;
 //BA.debugLineNum = 29;BA.debugLine="Dim intercepts_1 (71) As Double";
_intercepts_1 = new double[(int) (71)];
;
 //BA.debugLineNum = 30;BA.debugLine="Dim coef_2 (71) As Double";
_coef_2 = new double[(int) (71)];
;
 //BA.debugLineNum = 31;BA.debugLine="Dim intercepts_2 As Double";
_intercepts_2 = 0;
 //BA.debugLineNum = 33;BA.debugLine="Dim HPCSVR As ReadableWorkbook";
_hpcsvr = new anywheresoftware.b4a.objects.WorkbookWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Dim scaler_mean_ (8) As Double";
_scaler_mean_ = new double[(int) (8)];
;
 //BA.debugLineNum = 35;BA.debugLine="Dim scaler_scale_ (8) As Double";
_scaler_scale_ = new double[(int) (8)];
;
 //BA.debugLineNum = 36;BA.debugLine="Dim gamma As Double";
_gamma = 0;
 //BA.debugLineNum = 37;BA.debugLine="Dim support_vectors(1623,8) As Double";
_support_vectors = new double[(int) (1623)][];
{
int d0 = _support_vectors.length;
int d1 = (int) (8);
for (int i0 = 0;i0 < d0;i0++) {
_support_vectors[i0] = new double[d1];
}
}
;
 //BA.debugLineNum = 38;BA.debugLine="Dim dual_coef(1623) As Double";
_dual_coef = new double[(int) (1623)];
;
 //BA.debugLineNum = 39;BA.debugLine="Dim intercept As Double";
_intercept = 0;
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _radiobutton1_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 261;BA.debugLine="Private Sub RadioButton1_CheckedChange(Checked As";
 //BA.debugLineNum = 262;BA.debugLine="SHAP.LoadData(File.DirAssets, \"background_25.csv\"";
mostCurrent._shap._loaddata /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"background_25.csv");
 //BA.debugLineNum = 264;BA.debugLine="End Sub";
return "";
}
public static String  _radiobutton2_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 256;BA.debugLine="Private Sub RadioButton2_CheckedChange(Checked As";
 //BA.debugLineNum = 257;BA.debugLine="SHAP.LoadData(File.DirAssets, \"background_50.csv\"";
mostCurrent._shap._loaddata /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"background_50.csv");
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static String  _radiobutton3_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Private Sub RadioButton3_CheckedChange(Checked As";
 //BA.debugLineNum = 252;BA.debugLine="SHAP.LoadData(File.DirAssets, \"background_100.csv";
mostCurrent._shap._loaddata /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"background_100.csv");
 //BA.debugLineNum = 254;BA.debugLine="End Sub";
return "";
}
public static String  _radiobutton4_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 246;BA.debugLine="Private Sub RadioButton4_CheckedChange(Checked As";
 //BA.debugLineNum = 247;BA.debugLine="SHAP.LoadData(File.DirAssets, \"background_250.csv";
mostCurrent._shap._loaddata /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"background_250.csv");
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _radiobutton5_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 241;BA.debugLine="Private Sub RadioButton5_CheckedChange(Checked As";
 //BA.debugLineNum = 242;BA.debugLine="SHAP.LoadData(File.DirAssets, \"data.csv\")";
mostCurrent._shap._loaddata /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"data.csv");
 //BA.debugLineNum = 244;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar1_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Private Sub SeekBar1_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 236;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 237;BA.debugLine="Label3.Text=Round2(70 + Value*(702-70)/100, 1)";
mostCurrent._label3.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(70+_value*(702-70)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 239;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar2_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 229;BA.debugLine="Private Sub SeekBar2_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 230;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 231;BA.debugLine="Label6.Text=Round2(0 + Value*(440-0)/100, 1)";
mostCurrent._label6.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(0+_value*(440-0)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar3_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 223;BA.debugLine="Private Sub SeekBar3_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 224;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 225;BA.debugLine="Label9.Text=Round2(0 + Value*(544-0)/100, 1)";
mostCurrent._label9.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(0+_value*(544-0)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 227;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar4_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 217;BA.debugLine="Private Sub SeekBar4_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 218;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 219;BA.debugLine="Label12.Text=Round2(105 + Value*(295-105)/100, 1";
mostCurrent._label12.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(105+_value*(295-105)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar5_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 211;BA.debugLine="Private Sub SeekBar5_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 212;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 213;BA.debugLine="Label15.Text=Round2(0 + Value*(35.1-0)/100, 1)";
mostCurrent._label15.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(0+_value*(35.1-0)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar6_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 205;BA.debugLine="Private Sub SeekBar6_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 206;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 207;BA.debugLine="Label18.Text=Round2(723 + Value*(1218-723)/100,";
mostCurrent._label18.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(723+_value*(1218-723)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 209;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar7_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 199;BA.debugLine="Private Sub SeekBar7_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 200;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 201;BA.debugLine="Label21.Text=Round2(280 + Value*(992.6-280)/100,";
mostCurrent._label21.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(280+_value*(992.6-280)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return "";
}
public static String  _seekbar8_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Private Sub SeekBar8_ValueChanged (Value As Int, U";
 //BA.debugLineNum = 194;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 195;BA.debugLine="Label24.Text=Round2(1 + Value*(365-1)/100, 1)";
mostCurrent._label24.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Round2(1+_value*(365-1)/(double)100,(int) (1))));
 };
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
}
