B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13.3
@EndOfDesignText@

'========================================================
' BodyFatXGBoost.bas
' Offline XGBoost regression for B4A
' Model file: bodyfat_xgboost.json
'========================================================

Sub Process_Globals
	Private XGBModel As Map
	Private Trees As List
	Private BaseScore As Double
	Private NumFeatures As Int
	Private IsLoaded As Boolean
End Sub

'--------------------------------------------------------
' Load native XGBoost JSON model from DirAssets
'--------------------------------------------------------
Public Sub InitializeModel
	If IsLoaded Then Return

	Dim Parser As JSONParser
	Dim ModelText As String

	ModelText = File.ReadString( _
        File.DirAssets, "xgb_model.json")

	Parser.Initialize(ModelText)
	Dim Root As Map = Parser.NextObject

	Dim Learner As Map = Root.Get("learner")
	Dim LearnerParams As Map = Learner.Get("learner_model_param")

	NumFeatures = LearnerParams.Get("num_feature")

	' XGBoost may store base_score as "[35.0]"
	' or as a scalar string such as "35.0".
	Dim BaseText As String
	BaseText = LearnerParams.Get("base_score")
	BaseText = BaseText.Replace("[", "")
	BaseText = BaseText.Replace("]", "")
	BaseText = BaseText.Trim

	BaseScore = BaseText

	Dim Booster As Map = Learner.Get("gradient_booster")
	Dim BoosterModel As Map = Booster.Get("model")

	Trees = BoosterModel.Get("trees")

	If Trees.IsInitialized = False Then
		Log("ERROR: XGBoost trees were not loaded.")
		Return
	End If

	IsLoaded = True
	Log("XGBoost model loaded. Trees: " & Trees.Size)
End Sub

'--------------------------------------------------------
' Predict target from a List of numeric input features.
' Values must be in exactly the training feature order.
'--------------------------------------------------------
Public Sub PredictBodyFat(Values As List) As Double
	If IsLoaded = False Then InitializeModel

	If IsLoaded = False Then
		Log("ERROR: Model is not initialized.")
		Return 0
	End If

	If Values.Size <> NumFeatures Then
		Log("ERROR: Expected " & NumFeatures & _
            " inputs; received " & Values.Size)
		Return 0
	End If

	Dim Prediction As Double = BaseScore

	For t = 0 To Trees.Size - 1
		Dim Tree As Map = Trees.Get(t)

		Dim LeftChildren As List = Tree.Get("left_children")
		Dim RightChildren As List = Tree.Get("right_children")
		Dim SplitIndices As List = Tree.Get("split_indices")
		Dim SplitConditions As List = Tree.Get("split_conditions")

		Dim Node As Int = 0
		Dim MaxSteps As Int = LeftChildren.Size + 1
		Dim Steps As Int = 0

		Do While Steps < MaxSteps
			Steps = Steps + 1

			Dim LeftNode As Int = LeftChildren.Get(Node)
			Dim RightNode As Int = RightChildren.Get(Node)

			' A leaf has no left or right child.
			If LeftNode = -1 And RightNode = -1 Then
				Prediction = Prediction + _
                    SplitConditions.Get(Node)
				Exit
			End If

			Dim FeatureIndex As Int = SplitIndices.Get(Node)
			Dim Threshold As Double = SplitConditions.Get(Node)
			Dim FeatureValue As Double = Values.Get(FeatureIndex)

			' Standard numerical XGBoost split:
			' values below threshold go left.
			If FeatureValue < Threshold Then
				Node = LeftNode
			Else
				Node = RightNode
			End If

			If Node < 0 Or Node >= LeftChildren.Size Then
				Log("ERROR: Invalid tree node.")
				Return 0
			End If
		Loop

		If Steps >= MaxSteps Then
			Log("ERROR: Tree traversal limit reached.")
			Return 0
		End If
	Next

	Return Prediction
End Sub
