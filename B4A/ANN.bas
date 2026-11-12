B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13.3
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim n As Int
End Sub

Sub Predict(x1() As Double) As Double
		
	Dim xp1 (8) As Double
	For i=0 To 7
		xp1 (i) = (x1 (i) - Main.scaler_mean_ (i)) / Main.scaler_scale_ (i)
	Next
	
	Dim a1 (187) As Double
	For i=0 To 186
		For j=0 To 7
			a1 (i) = a1 (i) + Main.coef_0 (i,j) * xp1 (j)
		Next
		a1 (i) = a1 (i) + Main.intercepts_0 (i)
		a1 (i) = 2 / (1 + Power(cE,-2 * a1 (i)))-1
	Next
	
	Dim a2 (71) As Double
	For i=0 To 70
		For j=0 To 186
			a2 (i) = a2 (i) + Main.coef_1 (i,j) * a1 (j)
		Next
		a2 (i) = a2 (i) + Main.intercepts_1 (i)
		a2 (i) = 2 / (1 + Power(cE,-2 * a2 (i)))-1
	Next
	
	Dim a3 As Double
	For i = 0 To 70
		a3 =a3 + Main.coef_2 (i) * a2 (i)
	Next
	a3 = a3 + Main.intercepts_2
	
	Return a3
End Sub
