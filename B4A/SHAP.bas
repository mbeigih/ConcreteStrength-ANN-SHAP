B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13.3
@EndOfDesignText@
'====================================================
' EXACT SHAP IMPLEMENTATION
' USING EXPECTED MODEL OUTPUT

'====================================================

Sub Process_Globals

	Private BackgroundData As List
	Private BaseValue As Double

End Sub

'====================================================
' Load dataset
'====================================================
Public Sub LoadData(Dir As String, FileName As String)

	BackgroundData.Initialize

	Dim su As StringUtils
	Dim rows As List = su.LoadCSV(Dir, FileName, ",")

	Dim sumPred As Double = 0
	Dim cnt As Int = 0

	For i = 0 To rows.Size - 1

		Dim row() As String = rows.Get(i)

		'skip header
		If IsNumber(row(0)) = False Then Continue

		Dim x(8) As Double

		For j = 0 To 7
			x(j) = row(j)
		Next

		BackgroundData.Add(x)

		'========================================
		' expected model output
		'========================================

		Dim pred As Double = ANN.Predict(x)

		sumPred = sumPred + pred

		cnt = cnt + 1

	Next

	BaseValue = sumPred / cnt

End Sub

'====================================================
' EXACT SHAP
'====================================================
Public Sub CalculateExactSHAP(x() As Double) As Double()
'	Log("Calculate1 " & DateTime.Now)
	
	Dim M As Int = 8

	Dim shap(M) As Double

	'========================================
	' loop over each feature
	'========================================

	For i = 0 To M - 1

		Dim phi As Double = 0

		'====================================
		' all subsets excluding feature i
		'====================================

		For subset = 0 To Bit.ShiftLeft(1,M)-1

			'skip subsets containing i
			If Bit.And(subset, _
                Bit.ShiftLeft(1,i)) <> 0 Then Continue

			Dim s As Int = CountBits(subset)

			'Shapley coefficient
			Dim weight As Double = Factorial(s) * Factorial(M-s-1) / Factorial(M)

			'====================================
			' average over background dataset
			'====================================

			Dim marginalAvg As Double = 0

			For b = 0 To BackgroundData.Size - 1

				Dim bg() As Double = _
                    BackgroundData.Get(b)

				Dim xS(8) As Double
				Dim xSi(8) As Double

				'============================
				' build coalition S
				'============================

				For j = 0 To M - 1

					If Bit.And(subset, _
                        Bit.ShiftLeft(1,j)) <> 0 Then

						xS(j) = x(j)
						xSi(j) = x(j)

					Else

						xS(j) = bg(j)
						xSi(j) = bg(j)

					End If

				Next

				'add feature i
				xSi(i) = x(i)

				'============================
				' predictions
				'============================

				Dim predS As Double = _
                    ANN.Predict(xS)

				Dim predSi As Double = _
                    ANN.Predict(xSi)

				marginalAvg = _
                    marginalAvg + _
                    (predSi - predS)

			Next

			'average marginal contribution
			marginalAvg = marginalAvg / BackgroundData.Size

			phi = phi + weight * marginalAvg

		Next

		shap(i) = phi

	Next

	Return shap

End Sub

'====================================================
' Count bits
'====================================================
Private Sub CountBits(x As Int) As Int

	Dim cnt As Int = 0

	Do While x > 0

		If Bit.And(x,1)=1 Then
			cnt = cnt + 1
		End If
		x = Bit.UnsignedShiftRight(x,1)

	Loop

	Return cnt

End Sub

'====================================================
' Factorial
'====================================================
Private Sub Factorial(n As Int) As Double

	If n <= 1 Then Return 1

	Dim f As Double = 1

	For i = 2 To n
		f = f * i
	Next

	Return f

End Sub

'====================================================
' Explain prediction
'====================================================
Public Sub ExplainPrediction(x() As Double) As Map
'	Log("Explain1 " & DateTime.Now)

	Dim result As Map
	result.Initialize

	Dim shap() As Double = CalculateExactSHAP(x)

	Dim pred As Double = ANN.Predict(x)

	Dim sumPred As Double = BaseValue

	For i = 0 To 7
		sumPred = sumPred + shap(i)
	Next

	result.Put("base_value", BaseValue)

	result.Put("prediction", pred)

	result.Put("sum_prediction", sumPred)

	result.Put("shap_values", shap)

	Return result

End Sub