package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class svr {
private static svr mostCurrent = new svr();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example.main _main = null;
public b4a.example.plot _plot = null;
public b4a.example.shap _shap = null;
public b4a.example.adaboost _adaboost = null;
public b4a.example.randomforest _randomforest = null;
public b4a.example.xgboost _xgboost = null;
public b4a.example.ann _ann = null;
public b4a.example.catboost _catboost = null;
public static double  _predict(anywheresoftware.b4a.BA _ba,double[] _x1) throws Exception{
double[] _xp1 = null;
int _i = 0;
double[] _kernel = null;
int _j = 0;
double _p = 0;
 //BA.debugLineNum = 12;BA.debugLine="Sub Predict(x1() As Double) As Double";
 //BA.debugLineNum = 44;BA.debugLine="Dim xp1 (8) As Double";
_xp1 = new double[(int) (8)];
;
 //BA.debugLineNum = 45;BA.debugLine="For i=0 To 7";
{
final int step2 = 1;
final int limit2 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 46;BA.debugLine="xp1 (i) = (x1 (i) - Main.scaler_mean_ (i)) / Mai";
_xp1[_i] = (_x1[_i]-mostCurrent._main._scaler_mean_ /*double[]*/ [_i])/(double)mostCurrent._main._scaler_scale_ /*double[]*/ [_i];
 }
};
 //BA.debugLineNum = 49;BA.debugLine="Dim kernel (1623) As Double";
_kernel = new double[(int) (1623)];
;
 //BA.debugLineNum = 50;BA.debugLine="For i=0 To 1622";
{
final int step6 = 1;
final int limit6 = (int) (1622);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 51;BA.debugLine="For j=0 To 7";
{
final int step7 = 1;
final int limit7 = (int) (7);
_j = (int) (0) ;
for (;_j <= limit7 ;_j = _j + step7 ) {
 //BA.debugLineNum = 52;BA.debugLine="kernel (i) = kernel (i) + Power(Main.support_ve";
_kernel[_i] = _kernel[_i]+anywheresoftware.b4a.keywords.Common.Power(mostCurrent._main._support_vectors /*double[][]*/ [_i][_j]-_xp1[_j],2);
 }
};
 //BA.debugLineNum = 54;BA.debugLine="kernel (i) = Power(cE,-Main.gamma * kernel (i))";
_kernel[_i] = anywheresoftware.b4a.keywords.Common.Power(anywheresoftware.b4a.keywords.Common.cE,-mostCurrent._main._gamma /*double*/ *_kernel[_i]);
 }
};
 //BA.debugLineNum = 57;BA.debugLine="Dim p As Double";
_p = 0;
 //BA.debugLineNum = 58;BA.debugLine="For i = 0 To 1622";
{
final int step13 = 1;
final int limit13 = (int) (1622);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 59;BA.debugLine="p =p + Main.dual_coef (i) * kernel (i)";
_p = _p+mostCurrent._main._dual_coef /*double[]*/ [_i]*_kernel[_i];
 }
};
 //BA.debugLineNum = 61;BA.debugLine="p = p + Main.intercept";
_p = _p+mostCurrent._main._intercept /*double*/ ;
 //BA.debugLineNum = 63;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
