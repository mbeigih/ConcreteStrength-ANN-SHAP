package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class randomforest {
private static randomforest mostCurrent = new randomforest();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.List _trees = null;
public static int _numfeatures = 0;
public static boolean _isloaded = false;
public b4a.example.main _main = null;
public b4a.example.plot _plot = null;
public b4a.example.shap _shap = null;
public b4a.example.adaboost _adaboost = null;
public b4a.example.svr _svr = null;
public b4a.example.xgboost _xgboost = null;
public b4a.example.ann _ann = null;
public b4a.example.catboost _catboost = null;
public static String  _initializemodel(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
String _modeltext = "";
anywheresoftware.b4a.objects.collections.Map _model = null;
 //BA.debugLineNum = 17;BA.debugLine="Public Sub InitializeModel";
 //BA.debugLineNum = 18;BA.debugLine="If IsLoaded Then Return";
if (_isloaded) { 
if (true) return "";};
 //BA.debugLineNum = 20;BA.debugLine="Dim Parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 21;BA.debugLine="Dim ModelText As String";
_modeltext = "";
 //BA.debugLineNum = 23;BA.debugLine="ModelText = File.ReadString( _         File.DirAs";
_modeltext = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"random_forest.json");
 //BA.debugLineNum = 26;BA.debugLine="Parser.Initialize(ModelText)";
_parser.Initialize(_modeltext);
 //BA.debugLineNum = 27;BA.debugLine="Dim Model As Map = Parser.NextObject";
_model = new anywheresoftware.b4a.objects.collections.Map();
_model = _parser.NextObject();
 //BA.debugLineNum = 29;BA.debugLine="Trees = Model.Get(\"trees\")";
_trees = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_model.Get((Object)("trees"))));
 //BA.debugLineNum = 30;BA.debugLine="NumFeatures = Model.Get(\"n_features\")";
_numfeatures = (int)(BA.ObjectToNumber(_model.Get((Object)("n_features"))));
 //BA.debugLineNum = 32;BA.debugLine="If Trees.IsInitialized = False Or Trees.Size = 0";
if (_trees.IsInitialized()==anywheresoftware.b4a.keywords.Common.False || _trees.getSize()==0) { 
 //BA.debugLineNum = 33;BA.debugLine="Log(\"ERROR: Random Forest model has no trees.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32228240","ERROR: Random Forest model has no trees.",0);
 //BA.debugLineNum = 34;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 37;BA.debugLine="IsLoaded = True";
_isloaded = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 38;BA.debugLine="Log(\"Random Forest loaded. Trees: \" & Trees.Size)";
anywheresoftware.b4a.keywords.Common.LogImpl("32228245","Random Forest loaded. Trees: "+BA.NumberToString(_trees.getSize()),0);
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static double  _predict(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _values) throws Exception{
double _totalprediction = 0;
anywheresoftware.b4a.objects.collections.Map _tree = null;
 //BA.debugLineNum = 89;BA.debugLine="Public Sub Predict(Values As List) As Double";
 //BA.debugLineNum = 90;BA.debugLine="If IsLoaded = False Then InitializeModel";
if (_isloaded==anywheresoftware.b4a.keywords.Common.False) { 
_initializemodel(_ba);};
 //BA.debugLineNum = 91;BA.debugLine="If IsLoaded = False Then Return 0";
if (_isloaded==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return 0;};
 //BA.debugLineNum = 93;BA.debugLine="If Values.Size <> NumFeatures Then";
if (_values.getSize()!=_numfeatures) { 
 //BA.debugLineNum = 94;BA.debugLine="Log(\"ERROR: Expected \" & NumFeatures & _";
anywheresoftware.b4a.keywords.Common.LogImpl("32359301","ERROR: Expected "+BA.NumberToString(_numfeatures)+" features, received "+BA.NumberToString(_values.getSize()),0);
 //BA.debugLineNum = 96;BA.debugLine="Return 0";
if (true) return 0;
 };
 //BA.debugLineNum = 99;BA.debugLine="Dim TotalPrediction As Double = 0";
_totalprediction = 0;
 //BA.debugLineNum = 101;BA.debugLine="For Each Tree As Map In Trees";
_tree = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group8 = _trees;
final int groupLen8 = group8.getSize()
;int index8 = 0;
;
for (; index8 < groupLen8;index8++){
_tree = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group8.Get(index8)));
 //BA.debugLineNum = 102;BA.debugLine="TotalPrediction = TotalPrediction + _";
_totalprediction = _totalprediction+_predicttree(_ba,_tree,_values);
 }
};
 //BA.debugLineNum = 106;BA.debugLine="Return TotalPrediction / Trees.Size";
if (true) return _totalprediction/(double)_trees.getSize();
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return 0;
}
public static double  _predicttree(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.Map _tree,anywheresoftware.b4a.objects.collections.List _values) throws Exception{
anywheresoftware.b4a.objects.collections.List _leftchildren = null;
anywheresoftware.b4a.objects.collections.List _rightchildren = null;
anywheresoftware.b4a.objects.collections.List _features = null;
anywheresoftware.b4a.objects.collections.List _thresholds = null;
anywheresoftware.b4a.objects.collections.List _leafvalues = null;
int _node = 0;
int _maxsteps = 0;
int _steps = 0;
int _leftnode = 0;
int _rightnode = 0;
int _featureindex = 0;
double _threshold = 0;
double _featurevalue = 0;
 //BA.debugLineNum = 44;BA.debugLine="Private Sub PredictTree(Tree As Map, Values As Lis";
 //BA.debugLineNum = 45;BA.debugLine="Dim LeftChildren As List = Tree.Get(\"children_lef";
_leftchildren = new anywheresoftware.b4a.objects.collections.List();
_leftchildren = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("children_left"))));
 //BA.debugLineNum = 46;BA.debugLine="Dim RightChildren As List = Tree.Get(\"children_ri";
_rightchildren = new anywheresoftware.b4a.objects.collections.List();
_rightchildren = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("children_right"))));
 //BA.debugLineNum = 47;BA.debugLine="Dim Features As List = Tree.Get(\"feature\")";
_features = new anywheresoftware.b4a.objects.collections.List();
_features = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("feature"))));
 //BA.debugLineNum = 48;BA.debugLine="Dim Thresholds As List = Tree.Get(\"threshold\")";
_thresholds = new anywheresoftware.b4a.objects.collections.List();
_thresholds = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("threshold"))));
 //BA.debugLineNum = 49;BA.debugLine="Dim LeafValues As List = Tree.Get(\"value\")";
_leafvalues = new anywheresoftware.b4a.objects.collections.List();
_leafvalues = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("value"))));
 //BA.debugLineNum = 51;BA.debugLine="Dim Node As Int = 0";
_node = (int) (0);
 //BA.debugLineNum = 52;BA.debugLine="Dim MaxSteps As Int = LeftChildren.Size + 1";
_maxsteps = (int) (_leftchildren.getSize()+1);
 //BA.debugLineNum = 53;BA.debugLine="Dim Steps As Int = 0";
_steps = (int) (0);
 //BA.debugLineNum = 55;BA.debugLine="Do While Steps < MaxSteps";
while (_steps<_maxsteps) {
 //BA.debugLineNum = 56;BA.debugLine="Steps = Steps + 1";
_steps = (int) (_steps+1);
 //BA.debugLineNum = 58;BA.debugLine="Dim LeftNode As Int = LeftChildren.Get(Node)";
_leftnode = (int)(BA.ObjectToNumber(_leftchildren.Get(_node)));
 //BA.debugLineNum = 59;BA.debugLine="Dim RightNode As Int = RightChildren.Get(Node)";
_rightnode = (int)(BA.ObjectToNumber(_rightchildren.Get(_node)));
 //BA.debugLineNum = 62;BA.debugLine="If LeftNode = -1 And RightNode = -1 Then";
if (_leftnode==-1 && _rightnode==-1) { 
 //BA.debugLineNum = 63;BA.debugLine="Return LeafValues.Get(Node)";
if (true) return (double)(BA.ObjectToNumber(_leafvalues.Get(_node)));
 };
 //BA.debugLineNum = 66;BA.debugLine="Dim FeatureIndex As Int = Features.Get(Node)";
_featureindex = (int)(BA.ObjectToNumber(_features.Get(_node)));
 //BA.debugLineNum = 67;BA.debugLine="Dim Threshold As Double = Thresholds.Get(Node)";
_threshold = (double)(BA.ObjectToNumber(_thresholds.Get(_node)));
 //BA.debugLineNum = 68;BA.debugLine="Dim FeatureValue As Double = Values.Get(FeatureI";
_featurevalue = (double)(BA.ObjectToNumber(_values.Get(_featureindex)));
 //BA.debugLineNum = 70;BA.debugLine="If FeatureValue <= Threshold Then";
if (_featurevalue<=_threshold) { 
 //BA.debugLineNum = 71;BA.debugLine="Node = LeftNode";
_node = _leftnode;
 }else {
 //BA.debugLineNum = 73;BA.debugLine="Node = RightNode";
_node = _rightnode;
 };
 //BA.debugLineNum = 76;BA.debugLine="If Node < 0 Or Node >= LeftChildren.Size Then";
if (_node<0 || _node>=_leftchildren.getSize()) { 
 //BA.debugLineNum = 77;BA.debugLine="Log(\"ERROR: Invalid Random Forest tree node.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32293793","ERROR: Invalid Random Forest tree node.",0);
 //BA.debugLineNum = 78;BA.debugLine="Return 0";
if (true) return 0;
 };
 }
;
 //BA.debugLineNum = 82;BA.debugLine="Log(\"ERROR: Tree traversal limit reached.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32293798","ERROR: Tree traversal limit reached.",0);
 //BA.debugLineNum = 83;BA.debugLine="Return 0";
if (true) return 0;
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private Trees As List";
_trees = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 10;BA.debugLine="Private NumFeatures As Int";
_numfeatures = 0;
 //BA.debugLineNum = 11;BA.debugLine="Private IsLoaded As Boolean";
_isloaded = false;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
}
