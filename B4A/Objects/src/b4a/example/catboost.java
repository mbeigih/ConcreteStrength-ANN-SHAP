package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class catboost {
private static catboost mostCurrent = new catboost();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.Map _model = null;
public static boolean _isloaded = false;
public b4a.example.main _main = null;
public b4a.example.plot _plot = null;
public b4a.example.shap _shap = null;
public b4a.example.adaboost _adaboost = null;
public b4a.example.randomforest _randomforest = null;
public b4a.example.svr _svr = null;
public b4a.example.xgboost _xgboost = null;
public b4a.example.ann _ann = null;
public static boolean  _initializemodel(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
String _jsontext = "";
 //BA.debugLineNum = 16;BA.debugLine="Public Sub InitializeModel As Boolean";
 //BA.debugLineNum = 17;BA.debugLine="Try";
try { //BA.debugLineNum = 18;BA.debugLine="Dim Parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 19;BA.debugLine="Dim JsonText As String";
_jsontext = "";
 //BA.debugLineNum = 21;BA.debugLine="JsonText = File.ReadString( _             File.D";
_jsontext = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"catboost.json");
 //BA.debugLineNum = 24;BA.debugLine="Parser.Initialize(JsonText)";
_parser.Initialize(_jsontext);
 //BA.debugLineNum = 25;BA.debugLine="Model = Parser.NextObject";
_model = _parser.NextObject();
 //BA.debugLineNum = 26;BA.debugLine="Log(Model.Get(\"n_features\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("32949130",BA.ObjectToString(_model.Get((Object)("n_features"))),0);
 //BA.debugLineNum = 28;BA.debugLine="If Model.Get(\"n_features\") <> 8 Then";
if ((_model.Get((Object)("n_features"))).equals((Object)(8)) == false) { 
 //BA.debugLineNum = 29;BA.debugLine="IsLoaded = False";
_isloaded = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 30;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 33;BA.debugLine="IsLoaded = True";
_isloaded = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 34;BA.debugLine="Log(\"CatBoost model loaded successfully\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32949138","CatBoost model loaded successfully",0);
 //BA.debugLineNum = 35;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e16) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e16); //BA.debugLineNum = 38;BA.debugLine="IsLoaded = False";
_isloaded = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 39;BA.debugLine="Log(\"Error loading catboost.json\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32949143","Error loading catboost.json",0);
 //BA.debugLineNum = 40;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return false;
}
public static double  _predict(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _inputvalues) throws Exception{
double[] _x = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.List _trees = null;
double _total = 0;
int _t = 0;
anywheresoftware.b4a.objects.collections.Map _tree = null;
anywheresoftware.b4a.objects.collections.List _splits = null;
anywheresoftware.b4a.objects.collections.List _leafvalues = null;
int _leafindex = 0;
int _depth = 0;
anywheresoftware.b4a.objects.collections.Map _split = null;
int _featureindex = 0;
double _border = 0;
double _modelscale = 0;
double _modelbias = 0;
 //BA.debugLineNum = 47;BA.debugLine="Public Sub Predict(InputValues As List) As Double";
 //BA.debugLineNum = 49;BA.debugLine="If IsLoaded = False Then";
if (_isloaded==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 50;BA.debugLine="Log(\"CatBoost model is not loaded\")";
anywheresoftware.b4a.keywords.Common.LogImpl("33014659","CatBoost model is not loaded",0);
 //BA.debugLineNum = 51;BA.debugLine="Return 0";
if (true) return 0;
 };
 //BA.debugLineNum = 54;BA.debugLine="If InputValues.Size <> 8 Then";
if (_inputvalues.getSize()!=8) { 
 //BA.debugLineNum = 55;BA.debugLine="Log(\"Exactly 8 inputs are required\")";
anywheresoftware.b4a.keywords.Common.LogImpl("33014664","Exactly 8 inputs are required",0);
 //BA.debugLineNum = 56;BA.debugLine="Return 0";
if (true) return 0;
 };
 //BA.debugLineNum = 59;BA.debugLine="Dim X(8) As Double";
_x = new double[(int) (8)];
;
 //BA.debugLineNum = 61;BA.debugLine="For i = 0 To 7";
{
final int step10 = 1;
final int limit10 = (int) (7);
_i = (int) (0) ;
for (;_i <= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 62;BA.debugLine="X(i) = InputValues.Get(i)";
_x[_i] = (double)(BA.ObjectToNumber(_inputvalues.Get(_i)));
 }
};
 //BA.debugLineNum = 65;BA.debugLine="Dim Trees As List = Model.Get(\"trees\")";
_trees = new anywheresoftware.b4a.objects.collections.List();
_trees = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_model.Get((Object)("trees"))));
 //BA.debugLineNum = 66;BA.debugLine="Dim Total As Double = 0";
_total = 0;
 //BA.debugLineNum = 69;BA.debugLine="For t = 0 To Trees.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (_trees.getSize()-1);
_t = (int) (0) ;
for (;_t <= limit15 ;_t = _t + step15 ) {
 //BA.debugLineNum = 71;BA.debugLine="Dim Tree As Map = Trees.Get(t)";
_tree = new anywheresoftware.b4a.objects.collections.Map();
_tree = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_trees.Get(_t)));
 //BA.debugLineNum = 72;BA.debugLine="Dim Splits As List = Tree.Get(\"splits\")";
_splits = new anywheresoftware.b4a.objects.collections.List();
_splits = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("splits"))));
 //BA.debugLineNum = 73;BA.debugLine="Dim LeafValues As List = Tree.Get(\"leaf_values\")";
_leafvalues = new anywheresoftware.b4a.objects.collections.List();
_leafvalues = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("leaf_values"))));
 //BA.debugLineNum = 75;BA.debugLine="Dim LeafIndex As Int = 0";
_leafindex = (int) (0);
 //BA.debugLineNum = 78;BA.debugLine="For depth = 0 To Splits.Size - 1";
{
final int step20 = 1;
final int limit20 = (int) (_splits.getSize()-1);
_depth = (int) (0) ;
for (;_depth <= limit20 ;_depth = _depth + step20 ) {
 //BA.debugLineNum = 80;BA.debugLine="Dim Split As Map = Splits.Get(depth)";
_split = new anywheresoftware.b4a.objects.collections.Map();
_split = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_splits.Get(_depth)));
 //BA.debugLineNum = 82;BA.debugLine="Dim FeatureIndex As Int";
_featureindex = 0;
 //BA.debugLineNum = 83;BA.debugLine="FeatureIndex = Split.Get(\"feature\")";
_featureindex = (int)(BA.ObjectToNumber(_split.Get((Object)("feature"))));
 //BA.debugLineNum = 85;BA.debugLine="Dim Border As Double";
_border = 0;
 //BA.debugLineNum = 86;BA.debugLine="Border = Split.Get(\"border\")";
_border = (double)(BA.ObjectToNumber(_split.Get((Object)("border"))));
 //BA.debugLineNum = 88;BA.debugLine="If X(FeatureIndex) > Border Then";
if (_x[_featureindex]>_border) { 
 //BA.debugLineNum = 89;BA.debugLine="LeafIndex = LeafIndex + Power(2, depth)";
_leafindex = (int) (_leafindex+anywheresoftware.b4a.keywords.Common.Power(2,_depth));
 };
 }
};
 //BA.debugLineNum = 94;BA.debugLine="Total = Total + LeafValues.Get(LeafIndex)";
_total = _total+(double)(BA.ObjectToNumber(_leafvalues.Get(_leafindex)));
 }
};
 //BA.debugLineNum = 99;BA.debugLine="Dim ModelScale As Double = Model.Get(\"scale\")";
_modelscale = (double)(BA.ObjectToNumber(_model.Get((Object)("scale"))));
 //BA.debugLineNum = 100;BA.debugLine="Dim ModelBias As Double = Model.Get(\"bias\")";
_modelbias = (double)(BA.ObjectToNumber(_model.Get((Object)("bias"))));
 //BA.debugLineNum = 102;BA.debugLine="Return ModelScale * Total + ModelBias";
if (true) return _modelscale*_total+_modelbias;
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Public Model As Map";
_model = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 10;BA.debugLine="Public IsLoaded As Boolean = False";
_isloaded = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
}
