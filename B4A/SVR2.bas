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
	
	
End Sub



Sub Predict(x1() As Double) As Double
'	Dim HPCSVR As ReadableWorkbook
'	Dim scaler_mean_ (8) As Double
'	Dim scaler_scale_ (8) As Double
'	Dim gamma As Double
'	Dim support_vectors(1623,8) As Double
'	Dim dual_coef(1623) As Double
'	Dim intercept As Double
	
'	#region Constants
'	HPCSVR.Initialize(File.DirAssets, "SVR.xls")
'	For i=0 To 7
'		scaler_mean_ (i) =HPCSVR.GetSheet(1).GetCellValue(0,i)
'		scaler_scale_ (i) = HPCSVR.GetSheet(2).GetCellValue(0,i)
'	Next
'	gamma = HPCSVR.GetSheet(3).GetCellValue(0,0)
'	
'	For i=0 To 1622
'		For j=0 To 7
'			support_vectors(i,j) = HPCSVR.GetSheet(4).GetCellValue(j,i)
'		Next
'	Next
'	
'	For i=0 To 1622
'		dual_coef (i) = HPCSVR.GetSheet(5).GetCellValue(0,i)
'	Next
'	
'	intercept =HPCSVR.GetSheet(6).GetCellValue(0,0)
'	
'	#end region
'	
	
	Dim xp1 (8) As Double
	For i=0 To 7
		xp1 (i) = (x1 (i) - Main.scaler_mean_ (i)) / Main.scaler_scale_ (i)
	Next
	
	Dim kernel (1623) As Double
	For i=0 To 1622
		For j=0 To 7
			kernel (i) = kernel (i) + Power(Main.support_vectors (i,j) - xp1 (j), 2)
		Next
		kernel (i) = Power(cE,-Main.gamma * kernel (i))
	Next
	
	Dim p As Double
	For i = 0 To 1622
		p =p + Main.dual_coef (i) * kernel (i)
	Next
	p = p + Main.intercept
		
	Return p
End Sub





