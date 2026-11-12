package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class shap {
private static shap mostCurrent = new shap();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.List _backgrounddata = null;
public static double _basevalue = 0;
public b4a.example.main _main = null;
public b4a.example.plot _plot = null;
public b4a.example.adaboost _adaboost = null;
public b4a.example.randomforest _randomforest = null;
public b4a.example.svr _svr = null;
public b4a.example.xgboost _xgboost = null;
public b4a.example.ann _ann = null;
public b4a.example.catboost _catboost = null;
public static double[]  _calculateexactshap(anywheresoftware.b4a.BA _ba,double[] _x) throws Exception{
int _m = 0;
double[] _shap = null;
int _i = 0;
double _phi = 0;
int _subset = 0;
int _s = 0;
double _weight = 0;
double _marginalavg = 0;
int _b = 0;
double[] _bg = null;
double[] _xs = null;
double[] _xsi = null;
int _j = 0;
double _preds = 0;
double _predsi = 0;
 //BA.debugLineNum = 61;BA.debugLine="Public Sub CalculateExactSHAP(x() As Double) As Do";
 //BA.debugLineNum = 64;BA.debugLine="Dim M As Int = 8";
_m = (int) (8);
 //BA.debugLineNum = 66;BA.debugLine="Dim shap(M) As Double";
_shap = new double[_m];
;
 //BA.debugLineNum = 72;BA.debugLine="For i = 0 To M - 1";
{
final int step3 = 1;
final int limit3 = (int) (_m-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 74;BA.debugLine="Dim phi As Double = 0";
_phi = 0;
 //BA.debugLineNum = 80;BA.debugLine="For subset = 0 To Bit.ShiftLeft(1,M)-1";
{
final int step5 = 1;
final int limit5 = (int) (anywheresoftware.b4a.keywords.Common.Bit.ShiftLeft((int) (1),_m)-1);
_subset = (int) (0) ;
for (;_subset <= limit5 ;_subset = _subset + step5 ) {
 //BA.debugLineNum = 83;BA.debugLine="If Bit.And(subset, _                 Bit.ShiftL";
if (anywheresoftware.b4a.keywords.Common.Bit.And(_subset,anywheresoftware.b4a.keywords.Common.Bit.ShiftLeft((int) (1),_i))!=0) { 
if (true) continue;};
 //BA.debugLineNum = 86;BA.debugLine="Dim s As Int = CountBits(subset)";
_s = _countbits(_ba,_subset);
 //BA.debugLineNum = 89;BA.debugLine="Dim weight As Double = Factorial(s) * Factorial";
_weight = _factorial(_ba,_s)*_factorial(_ba,(int) (_m-_s-1))/(double)_factorial(_ba,_m);
 //BA.debugLineNum = 95;BA.debugLine="Dim marginalAvg As Double = 0";
_marginalavg = 0;
 //BA.debugLineNum = 97;BA.debugLine="For b = 0 To BackgroundData.Size - 1";
{
final int step10 = 1;
final int limit10 = (int) (_backgrounddata.getSize()-1);
_b = (int) (0) ;
for (;_b <= limit10 ;_b = _b + step10 ) {
 //BA.debugLineNum = 99;BA.debugLine="Dim bg() As Double = _                     Bac";
_bg = (double[])(_backgrounddata.Get(_b));
 //BA.debugLineNum = 102;BA.debugLine="Dim xS(8) As Double";
_xs = new double[(int) (8)];
;
 //BA.debugLineNum = 103;BA.debugLine="Dim xSi(8) As Double";
_xsi = new double[(int) (8)];
;
 //BA.debugLineNum = 109;BA.debugLine="For j = 0 To M - 1";
{
final int step14 = 1;
final int limit14 = (int) (_m-1);
_j = (int) (0) ;
for (;_j <= limit14 ;_j = _j + step14 ) {
 //BA.debugLineNum = 111;BA.debugLine="If Bit.And(subset, _";
if (anywheresoftware.b4a.keywords.Common.Bit.And(_subset,anywheresoftware.b4a.keywords.Common.Bit.ShiftLeft((int) (1),_j))!=0) { 
 //BA.debugLineNum = 114;BA.debugLine="xS(j) = x(j)";
_xs[_j] = _x[_j];
 //BA.debugLineNum = 115;BA.debugLine="xSi(j) = x(j)";
_xsi[_j] = _x[_j];
 }else {
 //BA.debugLineNum = 119;BA.debugLine="xS(j) = bg(j)";
_xs[_j] = _bg[_j];
 //BA.debugLineNum = 120;BA.debugLine="xSi(j) = bg(j)";
_xsi[_j] = _bg[_j];
 };
 }
};
 //BA.debugLineNum = 127;BA.debugLine="xSi(i) = x(i)";
_xsi[_i] = _x[_i];
 //BA.debugLineNum = 133;BA.debugLine="Dim predS As Double = _                     AN";
_preds = mostCurrent._ann._predict /*double*/ (_ba,_xs);
 //BA.debugLineNum = 136;BA.debugLine="Dim predSi As Double = _                     A";
_predsi = mostCurrent._ann._predict /*double*/ (_ba,_xsi);
 //BA.debugLineNum = 139;BA.debugLine="marginalAvg = _                     marginalAv";
_marginalavg = _marginalavg+(_predsi-_preds);
 }
};
 //BA.debugLineNum = 146;BA.debugLine="marginalAvg = marginalAvg / BackgroundData.Size";
_marginalavg = _marginalavg/(double)_backgrounddata.getSize();
 //BA.debugLineNum = 148;BA.debugLine="phi = phi + weight * marginalAvg";
_phi = _phi+_weight*_marginalavg;
 }
};
 //BA.debugLineNum = 152;BA.debugLine="shap(i) = phi";
_shap[_i] = _phi;
 }
};
 //BA.debugLineNum = 156;BA.debugLine="Return shap";
if (true) return _shap;
 //BA.debugLineNum = 158;BA.debugLine="End Sub";
return null;
}
public static int  _countbits(anywheresoftware.b4a.BA _ba,int _x) throws Exception{
int _cnt = 0;
 //BA.debugLineNum = 163;BA.debugLine="Private Sub CountBits(x As Int) As Int";
 //BA.debugLineNum = 165;BA.debugLine="Dim cnt As Int = 0";
_cnt = (int) (0);
 //BA.debugLineNum = 167;BA.debugLine="Do While x > 0";
while (_x>0) {
 //BA.debugLineNum = 169;BA.debugLine="If Bit.And(x,1)=1 Then";
if (anywheresoftware.b4a.keywords.Common.Bit.And(_x,(int) (1))==1) { 
 //BA.debugLineNum = 170;BA.debugLine="cnt = cnt + 1";
_cnt = (int) (_cnt+1);
 };
 //BA.debugLineNum = 172;BA.debugLine="x = Bit.UnsignedShiftRight(x,1)";
_x = anywheresoftware.b4a.keywords.Common.Bit.UnsignedShiftRight(_x,(int) (1));
 }
;
 //BA.debugLineNum = 176;BA.debugLine="Return cnt";
if (true) return _cnt;
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return 0;
}
public static anywheresoftware.b4a.objects.collections.Map  _explainprediction(anywheresoftware.b4a.BA _ba,double[] _x) throws Exception{
anywheresoftware.b4a.objects.collections.Map _result = null;
double[] _shap = null;
double _pred = 0;
double _sumpred = 0;
int _i = 0;
 //BA.debugLineNum = 200;BA.debugLine="Public Sub ExplainPrediction(x() As Double) As Map";
 //BA.debugLineNum = 203;BA.debugLine="Dim result As Map";
_result = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 204;BA.debugLine="result.Initialize";
_result.Initialize();
 //BA.debugLineNum = 206;BA.debugLine="Dim shap() As Double = CalculateExactSHAP(x)";
_shap = _calculateexactshap(_ba,_x);
 //BA.debugLineNum = 208;BA.debugLine="Dim pred As Double = ANN.Predict(x)";
_pred = mostCurrent._ann._predict /*double*/ (_ba,_x);
 //BA.debugLineNum = 210;BA.debugLine="Dim sumPred As Double = BaseValue";
_sumpred = _basevalue;
 //BA.debugLineNum = 212;BA.debugLine="For i = 0 To 7";
{
final int step6 = 1;
final int limit6 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 213;BA.debugLine="sumPred = sumPred + shap(i)";
_sumpred = _sumpred+_shap[_i];
 }
};
 //BA.debugLineNum = 216;BA.debugLine="result.Put(\"base_value\", BaseValue)";
_result.Put((Object)("base_value"),(Object)(_basevalue));
 //BA.debugLineNum = 218;BA.debugLine="result.Put(\"prediction\", pred)";
_result.Put((Object)("prediction"),(Object)(_pred));
 //BA.debugLineNum = 220;BA.debugLine="result.Put(\"sum_prediction\", sumPred)";
_result.Put((Object)("sum_prediction"),(Object)(_sumpred));
 //BA.debugLineNum = 222;BA.debugLine="result.Put(\"shap_values\", shap)";
_result.Put((Object)("shap_values"),(Object)(_shap));
 //BA.debugLineNum = 224;BA.debugLine="Return result";
if (true) return _result;
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return null;
}
public static double  _factorial(anywheresoftware.b4a.BA _ba,int _n) throws Exception{
double _f = 0;
int _i = 0;
 //BA.debugLineNum = 183;BA.debugLine="Private Sub Factorial(n As Int) As Double";
 //BA.debugLineNum = 185;BA.debugLine="If n <= 1 Then Return 1";
if (_n<=1) { 
if (true) return 1;};
 //BA.debugLineNum = 187;BA.debugLine="Dim f As Double = 1";
_f = 1;
 //BA.debugLineNum = 189;BA.debugLine="For i = 2 To n";
{
final int step3 = 1;
final int limit3 = _n;
_i = (int) (2) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 190;BA.debugLine="f = f * i";
_f = _f*_i;
 }
};
 //BA.debugLineNum = 193;BA.debugLine="Return f";
if (true) return _f;
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return 0;
}
public static String  _loaddata(anywheresoftware.b4a.BA _ba,String _dir,String _filename) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.collections.List _rows = null;
double _sumpred = 0;
int _cnt = 0;
int _i = 0;
String[] _row = null;
double[] _x = null;
int _j = 0;
double _pred = 0;
 //BA.debugLineNum = 17;BA.debugLine="Public Sub LoadData(Dir As String, FileName As Str";
 //BA.debugLineNum = 19;BA.debugLine="BackgroundData.Initialize";
_backgrounddata.Initialize();
 //BA.debugLineNum = 21;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 22;BA.debugLine="Dim rows As List = su.LoadCSV(Dir, FileName, \",\")";
_rows = new anywheresoftware.b4a.objects.collections.List();
_rows = _su.LoadCSV(_dir,_filename,BA.ObjectToChar(","));
 //BA.debugLineNum = 24;BA.debugLine="Dim sumPred As Double = 0";
_sumpred = 0;
 //BA.debugLineNum = 25;BA.debugLine="Dim cnt As Int = 0";
_cnt = (int) (0);
 //BA.debugLineNum = 27;BA.debugLine="For i = 0 To rows.Size - 1";
{
final int step6 = 1;
final int limit6 = (int) (_rows.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 29;BA.debugLine="Dim row() As String = rows.Get(i)";
_row = (String[])(_rows.Get(_i));
 //BA.debugLineNum = 32;BA.debugLine="If IsNumber(row(0)) = False Then Continue";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_row[(int) (0)])==anywheresoftware.b4a.keywords.Common.False) { 
if (true) continue;};
 //BA.debugLineNum = 34;BA.debugLine="Dim x(8) As Double";
_x = new double[(int) (8)];
;
 //BA.debugLineNum = 36;BA.debugLine="For j = 0 To 7";
{
final int step10 = 1;
final int limit10 = (int) (7);
_j = (int) (0) ;
for (;_j <= limit10 ;_j = _j + step10 ) {
 //BA.debugLineNum = 37;BA.debugLine="x(j) = row(j)";
_x[_j] = (double)(Double.parseDouble(_row[_j]));
 }
};
 //BA.debugLineNum = 40;BA.debugLine="BackgroundData.Add(x)";
_backgrounddata.Add((Object)(_x));
 //BA.debugLineNum = 46;BA.debugLine="Dim pred As Double = ANN.Predict(x)";
_pred = mostCurrent._ann._predict /*double*/ (_ba,_x);
 //BA.debugLineNum = 48;BA.debugLine="sumPred = sumPred + pred";
_sumpred = _sumpred+_pred;
 //BA.debugLineNum = 50;BA.debugLine="cnt = cnt + 1";
_cnt = (int) (_cnt+1);
 }
};
 //BA.debugLineNum = 54;BA.debugLine="BaseValue = sumPred / cnt";
_basevalue = _sumpred/(double)_cnt;
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private BackgroundData As List";
_backgrounddata = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 10;BA.debugLine="Private BaseValue As Double";
_basevalue = 0;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
}
