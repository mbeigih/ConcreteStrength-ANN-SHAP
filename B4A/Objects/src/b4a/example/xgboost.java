package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class xgboost {
private static xgboost mostCurrent = new xgboost();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.Map _xgbmodel = null;
public static anywheresoftware.b4a.objects.collections.List _trees = null;
public static double _basescore = 0;
public static int _numfeatures = 0;
public static boolean _isloaded = false;
public b4a.example.main _main = null;
public b4a.example.plot _plot = null;
public b4a.example.shap _shap = null;
public b4a.example.adaboost _adaboost = null;
public b4a.example.randomforest _randomforest = null;
public b4a.example.svr _svr = null;
public b4a.example.ann _ann = null;
public b4a.example.catboost _catboost = null;
public static String  _initializemodel(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
String _modeltext = "";
anywheresoftware.b4a.objects.collections.Map _root = null;
anywheresoftware.b4a.objects.collections.Map _learner = null;
anywheresoftware.b4a.objects.collections.Map _learnerparams = null;
String _basetext = "";
anywheresoftware.b4a.objects.collections.Map _booster = null;
anywheresoftware.b4a.objects.collections.Map _boostermodel = null;
 //BA.debugLineNum = 18;BA.debugLine="Public Sub InitializeModel";
 //BA.debugLineNum = 19;BA.debugLine="If IsLoaded Then Return";
if (_isloaded) { 
if (true) return "";};
 //BA.debugLineNum = 21;BA.debugLine="Dim Parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 22;BA.debugLine="Dim ModelText As String";
_modeltext = "";
 //BA.debugLineNum = 24;BA.debugLine="ModelText = File.ReadString( _         File.DirAs";
_modeltext = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"xgb_model.json");
 //BA.debugLineNum = 27;BA.debugLine="Parser.Initialize(ModelText)";
_parser.Initialize(_modeltext);
 //BA.debugLineNum = 28;BA.debugLine="Dim Root As Map = Parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
 //BA.debugLineNum = 30;BA.debugLine="Dim Learner As Map = Root.Get(\"learner\")";
_learner = new anywheresoftware.b4a.objects.collections.Map();
_learner = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_root.Get((Object)("learner"))));
 //BA.debugLineNum = 31;BA.debugLine="Dim LearnerParams As Map = Learner.Get(\"learner_m";
_learnerparams = new anywheresoftware.b4a.objects.collections.Map();
_learnerparams = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_learner.Get((Object)("learner_model_param"))));
 //BA.debugLineNum = 33;BA.debugLine="NumFeatures = LearnerParams.Get(\"num_feature\")";
_numfeatures = (int)(BA.ObjectToNumber(_learnerparams.Get((Object)("num_feature"))));
 //BA.debugLineNum = 37;BA.debugLine="Dim BaseText As String";
_basetext = "";
 //BA.debugLineNum = 38;BA.debugLine="BaseText = LearnerParams.Get(\"base_score\")";
_basetext = BA.ObjectToString(_learnerparams.Get((Object)("base_score")));
 //BA.debugLineNum = 39;BA.debugLine="BaseText = BaseText.Replace(\"[\", \"\")";
_basetext = _basetext.replace("[","");
 //BA.debugLineNum = 40;BA.debugLine="BaseText = BaseText.Replace(\"]\", \"\")";
_basetext = _basetext.replace("]","");
 //BA.debugLineNum = 41;BA.debugLine="BaseText = BaseText.Trim";
_basetext = _basetext.trim();
 //BA.debugLineNum = 43;BA.debugLine="BaseScore = BaseText";
_basescore = (double)(Double.parseDouble(_basetext));
 //BA.debugLineNum = 45;BA.debugLine="Dim Booster As Map = Learner.Get(\"gradient_booste";
_booster = new anywheresoftware.b4a.objects.collections.Map();
_booster = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_learner.Get((Object)("gradient_booster"))));
 //BA.debugLineNum = 46;BA.debugLine="Dim BoosterModel As Map = Booster.Get(\"model\")";
_boostermodel = new anywheresoftware.b4a.objects.collections.Map();
_boostermodel = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_booster.Get((Object)("model"))));
 //BA.debugLineNum = 48;BA.debugLine="Trees = BoosterModel.Get(\"trees\")";
_trees = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_boostermodel.Get((Object)("trees"))));
 //BA.debugLineNum = 50;BA.debugLine="If Trees.IsInitialized = False Then";
if (_trees.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 51;BA.debugLine="Log(\"ERROR: XGBoost trees were not loaded.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32621473","ERROR: XGBoost trees were not loaded.",0);
 //BA.debugLineNum = 52;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 55;BA.debugLine="IsLoaded = True";
_isloaded = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 56;BA.debugLine="Log(\"XGBoost model loaded. Trees: \" & Trees.Size)";
anywheresoftware.b4a.keywords.Common.LogImpl("32621478","XGBoost model loaded. Trees: "+BA.NumberToString(_trees.getSize()),0);
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static double  _predict(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _values) throws Exception{
double _prediction = 0;
int _t = 0;
anywheresoftware.b4a.objects.collections.Map _tree = null;
anywheresoftware.b4a.objects.collections.List _leftchildren = null;
anywheresoftware.b4a.objects.collections.List _rightchildren = null;
anywheresoftware.b4a.objects.collections.List _splitindices = null;
anywheresoftware.b4a.objects.collections.List _splitconditions = null;
int _node = 0;
int _maxsteps = 0;
int _steps = 0;
int _leftnode = 0;
int _rightnode = 0;
int _featureindex = 0;
double _threshold = 0;
double _featurevalue = 0;
 //BA.debugLineNum = 63;BA.debugLine="Public Sub Predict(Values As List) As Double";
 //BA.debugLineNum = 64;BA.debugLine="If IsLoaded = False Then InitializeModel";
if (_isloaded==anywheresoftware.b4a.keywords.Common.False) { 
_initializemodel(_ba);};
 //BA.debugLineNum = 66;BA.debugLine="If IsLoaded = False Then";
if (_isloaded==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 67;BA.debugLine="Log(\"ERROR: Model is not initialized.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32686980","ERROR: Model is not initialized.",0);
 //BA.debugLineNum = 68;BA.debugLine="Return 0";
if (true) return 0;
 };
 //BA.debugLineNum = 71;BA.debugLine="If Values.Size <> NumFeatures Then";
if (_values.getSize()!=_numfeatures) { 
 //BA.debugLineNum = 72;BA.debugLine="Log(\"ERROR: Expected \" & NumFeatures & _";
anywheresoftware.b4a.keywords.Common.LogImpl("32686985","ERROR: Expected "+BA.NumberToString(_numfeatures)+" inputs; received "+BA.NumberToString(_values.getSize()),0);
 //BA.debugLineNum = 74;BA.debugLine="Return 0";
if (true) return 0;
 };
 //BA.debugLineNum = 77;BA.debugLine="Dim Prediction As Double = BaseScore";
_prediction = _basescore;
 //BA.debugLineNum = 79;BA.debugLine="For t = 0 To Trees.Size - 1";
{
final int step11 = 1;
final int limit11 = (int) (_trees.getSize()-1);
_t = (int) (0) ;
for (;_t <= limit11 ;_t = _t + step11 ) {
 //BA.debugLineNum = 80;BA.debugLine="Dim Tree As Map = Trees.Get(t)";
_tree = new anywheresoftware.b4a.objects.collections.Map();
_tree = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_trees.Get(_t)));
 //BA.debugLineNum = 82;BA.debugLine="Dim LeftChildren As List = Tree.Get(\"left_childr";
_leftchildren = new anywheresoftware.b4a.objects.collections.List();
_leftchildren = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("left_children"))));
 //BA.debugLineNum = 83;BA.debugLine="Dim RightChildren As List = Tree.Get(\"right_chil";
_rightchildren = new anywheresoftware.b4a.objects.collections.List();
_rightchildren = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("right_children"))));
 //BA.debugLineNum = 84;BA.debugLine="Dim SplitIndices As List = Tree.Get(\"split_indic";
_splitindices = new anywheresoftware.b4a.objects.collections.List();
_splitindices = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("split_indices"))));
 //BA.debugLineNum = 85;BA.debugLine="Dim SplitConditions As List = Tree.Get(\"split_co";
_splitconditions = new anywheresoftware.b4a.objects.collections.List();
_splitconditions = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_tree.Get((Object)("split_conditions"))));
 //BA.debugLineNum = 87;BA.debugLine="Dim Node As Int = 0";
_node = (int) (0);
 //BA.debugLineNum = 88;BA.debugLine="Dim MaxSteps As Int = LeftChildren.Size + 1";
_maxsteps = (int) (_leftchildren.getSize()+1);
 //BA.debugLineNum = 89;BA.debugLine="Dim Steps As Int = 0";
_steps = (int) (0);
 //BA.debugLineNum = 91;BA.debugLine="Do While Steps < MaxSteps";
while (_steps<_maxsteps) {
 //BA.debugLineNum = 92;BA.debugLine="Steps = Steps + 1";
_steps = (int) (_steps+1);
 //BA.debugLineNum = 94;BA.debugLine="Dim LeftNode As Int = LeftChildren.Get(Node)";
_leftnode = (int)(BA.ObjectToNumber(_leftchildren.Get(_node)));
 //BA.debugLineNum = 95;BA.debugLine="Dim RightNode As Int = RightChildren.Get(Node)";
_rightnode = (int)(BA.ObjectToNumber(_rightchildren.Get(_node)));
 //BA.debugLineNum = 98;BA.debugLine="If LeftNode = -1 And RightNode = -1 Then";
if (_leftnode==-1 && _rightnode==-1) { 
 //BA.debugLineNum = 99;BA.debugLine="Prediction = Prediction + _";
_prediction = _prediction+(double)(BA.ObjectToNumber(_splitconditions.Get(_node)));
 //BA.debugLineNum = 101;BA.debugLine="Exit";
if (true) break;
 };
 //BA.debugLineNum = 104;BA.debugLine="Dim FeatureIndex As Int = SplitIndices.Get(Node";
_featureindex = (int)(BA.ObjectToNumber(_splitindices.Get(_node)));
 //BA.debugLineNum = 105;BA.debugLine="Dim Threshold As Double = SplitConditions.Get(N";
_threshold = (double)(BA.ObjectToNumber(_splitconditions.Get(_node)));
 //BA.debugLineNum = 106;BA.debugLine="Dim FeatureValue As Double = Values.Get(Feature";
_featurevalue = (double)(BA.ObjectToNumber(_values.Get(_featureindex)));
 //BA.debugLineNum = 110;BA.debugLine="If FeatureValue < Threshold Then";
if (_featurevalue<_threshold) { 
 //BA.debugLineNum = 111;BA.debugLine="Node = LeftNode";
_node = _leftnode;
 }else {
 //BA.debugLineNum = 113;BA.debugLine="Node = RightNode";
_node = _rightnode;
 };
 //BA.debugLineNum = 116;BA.debugLine="If Node < 0 Or Node >= LeftChildren.Size Then";
if (_node<0 || _node>=_leftchildren.getSize()) { 
 //BA.debugLineNum = 117;BA.debugLine="Log(\"ERROR: Invalid tree node.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32687030","ERROR: Invalid tree node.",0);
 //BA.debugLineNum = 118;BA.debugLine="Return 0";
if (true) return 0;
 };
 }
;
 //BA.debugLineNum = 122;BA.debugLine="If Steps >= MaxSteps Then";
if (_steps>=_maxsteps) { 
 //BA.debugLineNum = 123;BA.debugLine="Log(\"ERROR: Tree traversal limit reached.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("32687036","ERROR: Tree traversal limit reached.",0);
 //BA.debugLineNum = 124;BA.debugLine="Return 0";
if (true) return 0;
 };
 }
};
 //BA.debugLineNum = 128;BA.debugLine="Return Prediction";
if (true) return _prediction;
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private XGBModel As Map";
_xgbmodel = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 9;BA.debugLine="Private Trees As List";
_trees = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 10;BA.debugLine="Private BaseScore As Double";
_basescore = 0;
 //BA.debugLineNum = 11;BA.debugLine="Private NumFeatures As Int";
_numfeatures = 0;
 //BA.debugLineNum = 12;BA.debugLine="Private IsLoaded As Boolean";
_isloaded = false;
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
}
