package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class ann {
private static ann mostCurrent = new ann();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static int _n = 0;
public b4a.example.main _main = null;
public b4a.example.plot _plot = null;
public b4a.example.shap _shap = null;
public b4a.example.adaboost _adaboost = null;
public b4a.example.randomforest _randomforest = null;
public b4a.example.svr _svr = null;
public b4a.example.xgboost _xgboost = null;
public b4a.example.catboost _catboost = null;
public static double  _predict(anywheresoftware.b4a.BA _ba,double[] _x1) throws Exception{
double[] _xp1 = null;
int _i = 0;
double[] _a1 = null;
int _j = 0;
double[] _a2 = null;
double _a3 = 0;
 //BA.debugLineNum = 9;BA.debugLine="Sub Predict(x1() As Double) As Double";
 //BA.debugLineNum = 11;BA.debugLine="Dim xp1 (8) As Double";
_xp1 = new double[(int) (8)];
;
 //BA.debugLineNum = 12;BA.debugLine="For i=0 To 7";
{
final int step2 = 1;
final int limit2 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 13;BA.debugLine="xp1 (i) = (x1 (i) - Main.scaler_mean_ (i)) / Mai";
_xp1[_i] = (_x1[_i]-mostCurrent._main._scaler_mean_ /*double[]*/ [_i])/(double)mostCurrent._main._scaler_scale_ /*double[]*/ [_i];
 }
};
 //BA.debugLineNum = 16;BA.debugLine="Dim a1 (187) As Double";
_a1 = new double[(int) (187)];
;
 //BA.debugLineNum = 17;BA.debugLine="For i=0 To 186";
{
final int step6 = 1;
final int limit6 = (int) (186);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 18;BA.debugLine="For j=0 To 7";
{
final int step7 = 1;
final int limit7 = (int) (7);
_j = (int) (0) ;
for (;_j <= limit7 ;_j = _j + step7 ) {
 //BA.debugLineNum = 19;BA.debugLine="a1 (i) = a1 (i) + Main.coef_0 (i,j) * xp1 (j)";
_a1[_i] = _a1[_i]+mostCurrent._main._coef_0 /*double[][]*/ [_i][_j]*_xp1[_j];
 }
};
 //BA.debugLineNum = 21;BA.debugLine="a1 (i) = a1 (i) + Main.intercepts_0 (i)";
_a1[_i] = _a1[_i]+mostCurrent._main._intercepts_0 /*double[]*/ [_i];
 //BA.debugLineNum = 22;BA.debugLine="a1 (i) = 2 / (1 + Power(cE,-2 * a1 (i)))-1";
_a1[_i] = 2/(double)(1+anywheresoftware.b4a.keywords.Common.Power(anywheresoftware.b4a.keywords.Common.cE,-2*_a1[_i]))-1;
 }
};
 //BA.debugLineNum = 25;BA.debugLine="Dim a2 (71) As Double";
_a2 = new double[(int) (71)];
;
 //BA.debugLineNum = 26;BA.debugLine="For i=0 To 70";
{
final int step14 = 1;
final int limit14 = (int) (70);
_i = (int) (0) ;
for (;_i <= limit14 ;_i = _i + step14 ) {
 //BA.debugLineNum = 27;BA.debugLine="For j=0 To 186";
{
final int step15 = 1;
final int limit15 = (int) (186);
_j = (int) (0) ;
for (;_j <= limit15 ;_j = _j + step15 ) {
 //BA.debugLineNum = 28;BA.debugLine="a2 (i) = a2 (i) + Main.coef_1 (i,j) * a1 (j)";
_a2[_i] = _a2[_i]+mostCurrent._main._coef_1 /*double[][]*/ [_i][_j]*_a1[_j];
 }
};
 //BA.debugLineNum = 30;BA.debugLine="a2 (i) = a2 (i) + Main.intercepts_1 (i)";
_a2[_i] = _a2[_i]+mostCurrent._main._intercepts_1 /*double[]*/ [_i];
 //BA.debugLineNum = 31;BA.debugLine="a2 (i) = 2 / (1 + Power(cE,-2 * a2 (i)))-1";
_a2[_i] = 2/(double)(1+anywheresoftware.b4a.keywords.Common.Power(anywheresoftware.b4a.keywords.Common.cE,-2*_a2[_i]))-1;
 }
};
 //BA.debugLineNum = 34;BA.debugLine="Dim a3 As Double";
_a3 = 0;
 //BA.debugLineNum = 35;BA.debugLine="For i = 0 To 70";
{
final int step22 = 1;
final int limit22 = (int) (70);
_i = (int) (0) ;
for (;_i <= limit22 ;_i = _i + step22 ) {
 //BA.debugLineNum = 36;BA.debugLine="a3 =a3 + Main.coef_2 (i) * a2 (i)";
_a3 = _a3+mostCurrent._main._coef_2 /*double[]*/ [_i]*_a2[_i];
 }
};
 //BA.debugLineNum = 38;BA.debugLine="a3 = a3 + Main.intercepts_2";
_a3 = _a3+mostCurrent._main._intercepts_2 /*double*/ ;
 //BA.debugLineNum = 40;BA.debugLine="Return a3";
if (true) return _a3;
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim n As Int";
_n = 0;
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
