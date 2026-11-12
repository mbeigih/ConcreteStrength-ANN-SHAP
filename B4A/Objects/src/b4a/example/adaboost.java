package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class adaboost {
private static adaboost mostCurrent = new adaboost();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.List _trees = null;
public static anywheresoftware.b4a.objects.collections.List _estimatorweights = null;
public static int _numfeatures = 0;
public b4a.example.main _main = null;
public b4a.example.plot _plot = null;
public b4a.example.shap _shap = null;
public b4a.example.randomforest _randomforest = null;
public b4a.example.svr _svr = null;
public b4a.example.xgboost _xgboost = null;
public b4a.example.ann _ann = null;
public b4a.example.catboost _catboost = null;
public static String  _initializemodel(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
String _jsontext = "";
 //BA.debugLineNum = 7;BA.debugLine="Public Sub InitializeModel";
 //BA.debugLineNum = 8;BA.debugLine="Dim Parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 9;BA.debugLine="Dim Root As Map";
_root = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 11;BA.debugLine="Dim JSONText As String";
_jsontext = "";
 //BA.debugLineNum = 12;BA.debugLine="JSONText = File.ReadString(File.DirAssets, \"adabo";
_jsontext = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"adaboost.json");
 //BA.debugLineNum = 14;BA.debugLine="Parser.Initialize(JSONText)";
_parser.Initialize(_jsontext);
 //BA.debugLineNum = 15;BA.debugLine="Root = Parser.NextObject";
_root = _parser.NextObject();
 //BA.debugLineNum = 17;BA.debugLine="Trees = Root.Get(\"trees\")";
_trees = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("trees"))));
 //BA.debugLineNum = 18;BA.debugLine="EstimatorWeights = Root.Get(\"estimator_weights\")";
_estimatorweights = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_root.Get((Object)("estimator_weights"))));
 //BA.debugLineNum = 19;BA.debugLine="NumFeatures = Root.Get(\"n_features\")";
_numfeatures = (int)(BA.ObjectToNumber(_root.Get((Object)("n_features"))));
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static double  _predict(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _features) throws Exception{
anywheresoftware.b4a.objects.collections.List _predictions = null;
anywheresoftware.b4a.objects.collections.List _weights = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _tree = null;
double _prediction = 0;
 //BA.debugLineNum = 22;BA.debugLine="Public Sub Predict(Features As List) As Double";
 //BA.debugLineNum = 23;BA.debugLine="If Features.Size <> NumFeatures Then";
if (_features.getSize()!=_numfeatures) { 
 //BA.debugLineNum = 24;BA.debugLine="Log(\"Error: Incorrect number of input features\")";
anywheresoftware.b4a.keywords.Common.LogImpl("31966082","Error: Incorrect number of input features",0);
 //BA.debugLineNum = 25;BA.debugLine="Return 0";
if (true) return 0;
 };
 //BA.debugLineNum = 28;BA.debugLine="Dim Predictions As List";
_predictions = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 29;BA.debugLine="Predictions.Initialize";
_predictions.Initialize();
 //BA.debugLineNum = 31;BA.debugLine="Dim Weights As List";
_weights = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 32;BA.debugLine="Weights.Initialize";
_weights.Initialize();
 //BA.debugLineNum = 34;BA.debugLine="For i = 0 To Trees.Size - 1";
{
final int step9 = 1;
final int limit9 = (int) (_trees.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit9 ;_i = _i + step9 ) {
 //BA.debugLineNum = 35;BA.debugLine="Dim Tree As Map";
_tree = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 36;BA.debugLine="Tree = Trees.Get(i)";
_tree = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_trees.Get(_i)));
 //BA.debugLineNum = 38;BA.debugLine="Dim Prediction As Double";
_prediction = 0;
 //BA.debugLineNum = 39;BA.debugLine="Prediction = PredictTree(Tree, Features)";
_prediction = _predicttree(_ba,_tree,_features);
 //BA.debugLineNum = 41;BA.debugLine="Predictions.Add(Prediction)";
_predictions.Add((Object)(_prediction));
 //BA.debugLineNum = 42;BA.debugLine="Weights.Add(EstimatorWeights.Get(i))";
_weights.Add(_estimatorweights.Get(_i));
 }
};
 //BA.debugLineNum = 45;BA.debugLine="Return WeightedMedian(Predictions, Weights)";
if (true) return _weightedmedian(_ba,_predictions,_weights);
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return 0;
}
public static double  _predicttree(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.Map _tree,anywheresoftware.b4a.objects.collections.List _features) throws Exception{
anywheresoftware.b4a.objects.collections.List _leftnodes = null;
anywheresoftware.b4a.objects.collections.List _rightnodes = null;
anywheresoftware.b4a.objects.collections.List _featureindices = null;
anywheresoftware.b4a.objects.collections.List _thresholds = null;
anywheresoftware.b4a.objects.collections.List _values = null;
int _node = 0;
int _featureindex = 0;
double _threshold = 0;
double _featurevalue = 0;
 //BA.debugLineNum = 48;BA.debugLine="Private Sub PredictTree(Tree As Map, Features As L";
 //BA.debugLineNum = 49;BA.debugLine="Dim LeftNodes As List = Tree.Get(\"children_left\")";
_leftnodes = new anywheresoftware.b4a.objects.collections.List();
_leftnodes = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("children_left"))));
 //BA.debugLineNum = 50;BA.debugLine="Dim RightNodes As List = Tree.Get(\"children_right";
_rightnodes = new anywheresoftware.b4a.objects.collections.List();
_rightnodes = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("children_right"))));
 //BA.debugLineNum = 51;BA.debugLine="Dim FeatureIndices As List = Tree.Get(\"feature\")";
_featureindices = new anywheresoftware.b4a.objects.collections.List();
_featureindices = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("feature"))));
 //BA.debugLineNum = 52;BA.debugLine="Dim Thresholds As List = Tree.Get(\"threshold\")";
_thresholds = new anywheresoftware.b4a.objects.collections.List();
_thresholds = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("threshold"))));
 //BA.debugLineNum = 53;BA.debugLine="Dim Values As List = Tree.Get(\"value\")";
_values = new anywheresoftware.b4a.objects.collections.List();
_values = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("value"))));
 //BA.debugLineNum = 55;BA.debugLine="Dim Node As Int = 0";
_node = (int) (0);
 //BA.debugLineNum = 57;BA.debugLine="Do While LeftNodes.Get(Node) <> -1";
while ((_leftnodes.Get(_node)).equals((Object)(-1)) == false) {
 //BA.debugLineNum = 58;BA.debugLine="Dim FeatureIndex As Int";
_featureindex = 0;
 //BA.debugLineNum = 59;BA.debugLine="FeatureIndex = FeatureIndices.Get(Node)";
_featureindex = (int)(BA.ObjectToNumber(_featureindices.Get(_node)));
 //BA.debugLineNum = 61;BA.debugLine="Dim Threshold As Double";
_threshold = 0;
 //BA.debugLineNum = 62;BA.debugLine="Threshold = Thresholds.Get(Node)";
_threshold = (double)(BA.ObjectToNumber(_thresholds.Get(_node)));
 //BA.debugLineNum = 64;BA.debugLine="Dim FeatureValue As Double";
_featurevalue = 0;
 //BA.debugLineNum = 65;BA.debugLine="FeatureValue = Features.Get(FeatureIndex)";
_featurevalue = (double)(BA.ObjectToNumber(_features.Get(_featureindex)));
 //BA.debugLineNum = 67;BA.debugLine="If FeatureValue <= Threshold Then";
if (_featurevalue<=_threshold) { 
 //BA.debugLineNum = 68;BA.debugLine="Node = LeftNodes.Get(Node)";
_node = (int)(BA.ObjectToNumber(_leftnodes.Get(_node)));
 }else {
 //BA.debugLineNum = 70;BA.debugLine="Node = RightNodes.Get(Node)";
_node = (int)(BA.ObjectToNumber(_rightnodes.Get(_node)));
 };
 }
;
 //BA.debugLineNum = 74;BA.debugLine="Return Values.Get(Node)";
if (true) return (double)(BA.ObjectToNumber(_values.Get(_node)));
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Private Trees As List";
_trees = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 3;BA.debugLine="Private EstimatorWeights As List";
_estimatorweights = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 4;BA.debugLine="Private NumFeatures As Int";
_numfeatures = 0;
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public static double  _weightedmedian(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _predictions,anywheresoftware.b4a.objects.collections.List _weights) throws Exception{
int _n = 0;
int _i = 0;
int _j = 0;
double _currentvalue = 0;
double _previousvalue = 0;
double _currentweight = 0;
double _previousweight = 0;
double _totalweight = 0;
double _cumulativeweight = 0;
double _cutoff = 0;
 //BA.debugLineNum = 77;BA.debugLine="Private Sub WeightedMedian(Predictions As List, We";
 //BA.debugLineNum = 78;BA.debugLine="Dim N As Int = Predictions.Size";
_n = _predictions.getSize();
 //BA.debugLineNum = 80;BA.debugLine="For i = 1 To N - 1";
{
final int step2 = 1;
final int limit2 = (int) (_n-1);
_i = (int) (1) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 81;BA.debugLine="Dim j As Int = i";
_j = _i;
 //BA.debugLineNum = 83;BA.debugLine="Do While j > 0";
while (_j>0) {
 //BA.debugLineNum = 84;BA.debugLine="Dim CurrentValue As Double = Predictions.Get(j)";
_currentvalue = (double)(BA.ObjectToNumber(_predictions.Get(_j)));
 //BA.debugLineNum = 85;BA.debugLine="Dim PreviousValue As Double = Predictions.Get(j";
_previousvalue = (double)(BA.ObjectToNumber(_predictions.Get((int) (_j-1))));
 //BA.debugLineNum = 87;BA.debugLine="If CurrentValue >= PreviousValue Then Exit";
if (_currentvalue>=_previousvalue) { 
if (true) break;};
 //BA.debugLineNum = 89;BA.debugLine="Predictions.Set(j, PreviousValue)";
_predictions.Set(_j,(Object)(_previousvalue));
 //BA.debugLineNum = 90;BA.debugLine="Predictions.Set(j - 1, CurrentValue)";
_predictions.Set((int) (_j-1),(Object)(_currentvalue));
 //BA.debugLineNum = 92;BA.debugLine="Dim CurrentWeight As Double = Weights.Get(j)";
_currentweight = (double)(BA.ObjectToNumber(_weights.Get(_j)));
 //BA.debugLineNum = 93;BA.debugLine="Dim PreviousWeight As Double = Weights.Get(j -";
_previousweight = (double)(BA.ObjectToNumber(_weights.Get((int) (_j-1))));
 //BA.debugLineNum = 95;BA.debugLine="Weights.Set(j, PreviousWeight)";
_weights.Set(_j,(Object)(_previousweight));
 //BA.debugLineNum = 96;BA.debugLine="Weights.Set(j - 1, CurrentWeight)";
_weights.Set((int) (_j-1),(Object)(_currentweight));
 //BA.debugLineNum = 98;BA.debugLine="j = j - 1";
_j = (int) (_j-1);
 }
;
 }
};
 //BA.debugLineNum = 102;BA.debugLine="Dim TotalWeight As Double = 0";
_totalweight = 0;
 //BA.debugLineNum = 103;BA.debugLine="For i = 0 To N - 1";
{
final int step18 = 1;
final int limit18 = (int) (_n-1);
_i = (int) (0) ;
for (;_i <= limit18 ;_i = _i + step18 ) {
 //BA.debugLineNum = 104;BA.debugLine="TotalWeight = TotalWeight + Weights.Get(i)";
_totalweight = _totalweight+(double)(BA.ObjectToNumber(_weights.Get(_i)));
 }
};
 //BA.debugLineNum = 107;BA.debugLine="Dim CumulativeWeight As Double = 0";
_cumulativeweight = 0;
 //BA.debugLineNum = 108;BA.debugLine="Dim Cutoff As Double = TotalWeight / 2";
_cutoff = _totalweight/(double)2;
 //BA.debugLineNum = 110;BA.debugLine="For i = 0 To N - 1";
{
final int step23 = 1;
final int limit23 = (int) (_n-1);
_i = (int) (0) ;
for (;_i <= limit23 ;_i = _i + step23 ) {
 //BA.debugLineNum = 111;BA.debugLine="CumulativeWeight = CumulativeWeight + Weights.Ge";
_cumulativeweight = _cumulativeweight+(double)(BA.ObjectToNumber(_weights.Get(_i)));
 //BA.debugLineNum = 113;BA.debugLine="If CumulativeWeight >= Cutoff Then";
if (_cumulativeweight>=_cutoff) { 
 //BA.debugLineNum = 114;BA.debugLine="Return Predictions.Get(i)";
if (true) return (double)(BA.ObjectToNumber(_predictions.Get(_i)));
 };
 }
};
 //BA.debugLineNum = 118;BA.debugLine="Return Predictions.Get(N - 1)";
if (true) return (double)(BA.ObjectToNumber(_predictions.Get((int) (_n-1))));
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return 0;
}
}
