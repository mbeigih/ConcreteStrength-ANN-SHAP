B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=8
@EndOfDesignText@

'========================================================
' RandomForest.bas
' Offline RandomForestRegressor prediction
' Model: random_forest.json
'========================================================

Sub Process_Globals
	Private Trees As List
	Private NumFeatures As Int
	Private IsLoaded As Boolean
End Sub

'--------------------------------------------------------
' Load JSON model
'--------------------------------------------------------
Public Sub InitializeModel
	If IsLoaded Then Return

	Dim Parser As JSONParser
	Dim ModelText As String

	ModelText = File.ReadString( _
        File.DirAssets, "random_forest.json")

	Parser.Initialize(ModelText)
	Dim Model As Map = Parser.NextObject

	Trees = Model.Get("trees")
	NumFeatures = Model.Get("n_features")

	If Trees.IsInitialized = False Or Trees.Size = 0 Then
		Log("ERROR: Random Forest model has no trees.")
		Return
	End If

	IsLoaded = True
	Log("Random Forest loaded. Trees: " & Trees.Size)
End Sub

'--------------------------------------------------------
' Predict one tree
'--------------------------------------------------------
Private Sub PredictTree(Tree As Map, Values As List) As Double
	Dim LeftChildren As List = Tree.Get("children_left")
	Dim RightChildren As List = Tree.Get("children_right")
	Dim Features As List = Tree.Get("feature")
	Dim Thresholds As List = Tree.Get("threshold")
	Dim LeafValues As List = Tree.Get("value")

	Dim Node As Int = 0
	Dim MaxSteps As Int = LeftChildren.Size + 1
	Dim Steps As Int = 0

	Do While Steps < MaxSteps
		Steps = Steps + 1

		Dim LeftNode As Int = LeftChildren.Get(Node)
		Dim RightNode As Int = RightChildren.Get(Node)

		' Leaf node
		If LeftNode = -1 And RightNode = -1 Then
			Return LeafValues.Get(Node)
		End If

		Dim FeatureIndex As Int = Features.Get(Node)
		Dim Threshold As Double = Thresholds.Get(Node)
		Dim FeatureValue As Double = Values.Get(FeatureIndex)

		If FeatureValue <= Threshold Then
			Node = LeftNode
		Else
			Node = RightNode
		End If

		If Node < 0 Or Node >= LeftChildren.Size Then
			Log("ERROR: Invalid Random Forest tree node.")
			Return 0
		End If
	Loop

	Log("ERROR: Tree traversal limit reached.")
	Return 0
End Sub

'--------------------------------------------------------
' Predict target using all trees
'--------------------------------------------------------
Public Sub Predict(Values As List) As Double
	If IsLoaded = False Then InitializeModel
	If IsLoaded = False Then Return 0

	If Values.Size <> NumFeatures Then
		Log("ERROR: Expected " & NumFeatures & _
            " features, received " & Values.Size)
		Return 0
	End If

	Dim TotalPrediction As Double = 0

	For Each Tree As Map In Trees
		TotalPrediction = TotalPrediction + _
            PredictTree(Tree, Values)
	Next

	Return TotalPrediction / Trees.Size
End Sub
