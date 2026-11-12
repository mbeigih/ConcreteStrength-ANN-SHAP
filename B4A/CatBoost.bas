B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=8
@EndOfDesignText@

' ============================================
' CatBoost.bas
' CatBoostRegressor - Offline prediction
' 13 numerical input features
' ============================================

Sub Process_Globals
	Public Model As Map
	Public IsLoaded As Boolean = False
End Sub

' --------------------------------------------
' Load model JSON
' --------------------------------------------
Public Sub InitializeModel As Boolean
	Try
		Dim Parser As JSONParser
		Dim JsonText As String

		JsonText = File.ReadString( _
            File.DirAssets, "catboost.json")

		Parser.Initialize(JsonText)
		Model = Parser.NextObject
		Log(Model.Get("n_features"))

		If Model.Get("n_features") <> 8 Then
			IsLoaded = False
			Return False
		End If

		IsLoaded = True
		Log("CatBoost model loaded successfully")
		Return True

	Catch
		IsLoaded = False
		Log("Error loading catboost.json")
		Return False
	End Try
End Sub

' --------------------------------------------
' Predict using 8 input values
' --------------------------------------------
Public Sub Predict(InputValues As List) As Double

	If IsLoaded = False Then
		Log("CatBoost model is not loaded")
		Return 0
	End If

	If InputValues.Size <> 8 Then
		Log("Exactly 8 inputs are required")
		Return 0
	End If

	Dim X(8) As Double

	For i = 0 To 7
		X(i) = InputValues.Get(i)
	Next

	Dim Trees As List = Model.Get("trees")
	Dim Total As Double = 0

	' Evaluate all symmetric trees
	For t = 0 To Trees.Size - 1

		Dim Tree As Map = Trees.Get(t)
		Dim Splits As List = Tree.Get("splits")
		Dim LeafValues As List = Tree.Get("leaf_values")

		Dim LeafIndex As Int = 0

		' Each split contributes one bit to the leaf index.
		For depth = 0 To Splits.Size - 1

			Dim Split As Map = Splits.Get(depth)

			Dim FeatureIndex As Int
			FeatureIndex = Split.Get("feature")

			Dim Border As Double
			Border = Split.Get("border")

			If X(FeatureIndex) > Border Then
				LeafIndex = LeafIndex + Power(2, depth)
			End If

		Next

		Total = Total + LeafValues.Get(LeafIndex)

	Next

	' Apply CatBoost model scale and bias
	Dim ModelScale As Double = Model.Get("scale")
	Dim ModelBias As Double = Model.Get("bias")

	Return ModelScale * Total + ModelBias

End Sub
