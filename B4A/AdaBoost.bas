B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13.3
@EndOfDesignText@
Sub Process_Globals
	Private Trees As List
	Private EstimatorWeights As List
	Private NumFeatures As Int
End Sub

Public Sub InitializeModel
	Dim Parser As JSONParser
	Dim Root As Map

	Dim JSONText As String
	JSONText = File.ReadString(File.DirAssets, "adaboost.json")

	Parser.Initialize(JSONText)
	Root = Parser.NextObject

	Trees = Root.Get("trees")
	EstimatorWeights = Root.Get("estimator_weights")
	NumFeatures = Root.Get("n_features")
End Sub

Public Sub Predict(Features As List) As Double
	If Features.Size <> NumFeatures Then
		Log("Error: Incorrect number of input features")
		Return 0
	End If

	Dim Predictions As List
	Predictions.Initialize

	Dim Weights As List
	Weights.Initialize

	For i = 0 To Trees.Size - 1
		Dim Tree As Map
		Tree = Trees.Get(i)

		Dim Prediction As Double
		Prediction = PredictTree(Tree, Features)

		Predictions.Add(Prediction)
		Weights.Add(EstimatorWeights.Get(i))
	Next

	Return WeightedMedian(Predictions, Weights)
End Sub

Private Sub PredictTree(Tree As Map, Features As List) As Double
	Dim LeftNodes As List = Tree.Get("children_left")
	Dim RightNodes As List = Tree.Get("children_right")
	Dim FeatureIndices As List = Tree.Get("feature")
	Dim Thresholds As List = Tree.Get("threshold")
	Dim Values As List = Tree.Get("value")

	Dim Node As Int = 0

	Do While LeftNodes.Get(Node) <> -1
		Dim FeatureIndex As Int
		FeatureIndex = FeatureIndices.Get(Node)

		Dim Threshold As Double
		Threshold = Thresholds.Get(Node)

		Dim FeatureValue As Double
		FeatureValue = Features.Get(FeatureIndex)

		If FeatureValue <= Threshold Then
			Node = LeftNodes.Get(Node)
		Else
			Node = RightNodes.Get(Node)
		End If
	Loop

	Return Values.Get(Node)
End Sub

Private Sub WeightedMedian(Predictions As List, Weights As List) As Double
	Dim N As Int = Predictions.Size

	For i = 1 To N - 1
		Dim j As Int = i

		Do While j > 0
			Dim CurrentValue As Double = Predictions.Get(j)
			Dim PreviousValue As Double = Predictions.Get(j - 1)

			If CurrentValue >= PreviousValue Then Exit

			Predictions.Set(j, PreviousValue)
			Predictions.Set(j - 1, CurrentValue)

			Dim CurrentWeight As Double = Weights.Get(j)
			Dim PreviousWeight As Double = Weights.Get(j - 1)

			Weights.Set(j, PreviousWeight)
			Weights.Set(j - 1, CurrentWeight)

			j = j - 1
		Loop
	Next

	Dim TotalWeight As Double = 0
	For i = 0 To N - 1
		TotalWeight = TotalWeight + Weights.Get(i)
	Next

	Dim CumulativeWeight As Double = 0
	Dim Cutoff As Double = TotalWeight / 2

	For i = 0 To N - 1
		CumulativeWeight = CumulativeWeight + Weights.Get(i)

		If CumulativeWeight >= Cutoff Then
			Return Predictions.Get(i)
		End If
	Next

	Return Predictions.Get(N - 1)
End Sub