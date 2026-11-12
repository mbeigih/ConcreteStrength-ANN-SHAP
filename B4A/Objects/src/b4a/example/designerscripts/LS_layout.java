package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layout{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Layout/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 5;BA.debugLine="Label1.Left=0"[Layout/General script]
views.get("label1").vw.setLeft((int)(0d));
//BA.debugLineNum = 6;BA.debugLine="Label1.Top=0"[Layout/General script]
views.get("label1").vw.setTop((int)(0d));
//BA.debugLineNum = 7;BA.debugLine="Label1.Height=10%y"[Layout/General script]
views.get("label1").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 8;BA.debugLine="Label1.Width=30%x"[Layout/General script]
views.get("label1").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 9;BA.debugLine="Label1.Text=\"C(kg/m^3)\""[Layout/General script]
((TextViewWrapper)views.get("label1").vw).setText("C(kg/m^3)");
//BA.debugLineNum = 10;BA.debugLine="Label1.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label1").vw).setTextSize((float)(20d));
//BA.debugLineNum = 19;BA.debugLine="SeekBar1.Top=Label1.Top"[Layout/General script]
views.get("seekbar1").vw.setTop((int)((views.get("label1").vw.getTop())));
//BA.debugLineNum = 20;BA.debugLine="SeekBar1.Left=Label1.Right"[Layout/General script]
views.get("seekbar1").vw.setLeft((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth())));
//BA.debugLineNum = 21;BA.debugLine="SeekBar1.Height=Label1.Height"[Layout/General script]
views.get("seekbar1").vw.setHeight((int)((views.get("label1").vw.getHeight())));
//BA.debugLineNum = 22;BA.debugLine="SeekBar1.Width=50%x"[Layout/General script]
views.get("seekbar1").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 24;BA.debugLine="Label3.Top=Label1.Top"[Layout/General script]
views.get("label3").vw.setTop((int)((views.get("label1").vw.getTop())));
//BA.debugLineNum = 25;BA.debugLine="Label3.Left=SeekBar1.Right"[Layout/General script]
views.get("label3").vw.setLeft((int)((views.get("seekbar1").vw.getLeft() + views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 26;BA.debugLine="Label3.Height=Label1.Height"[Layout/General script]
views.get("label3").vw.setHeight((int)((views.get("label1").vw.getHeight())));
//BA.debugLineNum = 27;BA.debugLine="Label3.Width=20%x"[Layout/General script]
views.get("label3").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 28;BA.debugLine="Label3.Text=540"[Layout/General script]
((TextViewWrapper)views.get("label3").vw).setText("540");
//BA.debugLineNum = 29;BA.debugLine="Label3.TextSize=Label1.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label3").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label1").vw).getTextSize())));
//BA.debugLineNum = 32;BA.debugLine="Label4.Left=0"[Layout/General script]
views.get("label4").vw.setLeft((int)(0d));
//BA.debugLineNum = 33;BA.debugLine="Label4.Top=Label1.Bottom"[Layout/General script]
views.get("label4").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())));
//BA.debugLineNum = 34;BA.debugLine="Label4.Height=10%y"[Layout/General script]
views.get("label4").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 35;BA.debugLine="Label4.Width=30%x"[Layout/General script]
views.get("label4").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 36;BA.debugLine="Label4.Text=\"BFS(kg/m^3)\""[Layout/General script]
((TextViewWrapper)views.get("label4").vw).setText("BFS(kg/m^3)");
//BA.debugLineNum = 37;BA.debugLine="Label4.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label4").vw).setTextSize((float)(20d));
//BA.debugLineNum = 46;BA.debugLine="SeekBar2.Top=Label4.Top"[Layout/General script]
views.get("seekbar2").vw.setTop((int)((views.get("label4").vw.getTop())));
//BA.debugLineNum = 47;BA.debugLine="SeekBar2.Left=Label4.Right"[Layout/General script]
views.get("seekbar2").vw.setLeft((int)((views.get("label4").vw.getLeft() + views.get("label4").vw.getWidth())));
//BA.debugLineNum = 48;BA.debugLine="SeekBar2.Height=Label4.Height"[Layout/General script]
views.get("seekbar2").vw.setHeight((int)((views.get("label4").vw.getHeight())));
//BA.debugLineNum = 49;BA.debugLine="SeekBar2.Width=SeekBar1.Width"[Layout/General script]
views.get("seekbar2").vw.setWidth((int)((views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 51;BA.debugLine="Label6.Top=Label4.Top"[Layout/General script]
views.get("label6").vw.setTop((int)((views.get("label4").vw.getTop())));
//BA.debugLineNum = 52;BA.debugLine="Label6.Left=SeekBar2.Right"[Layout/General script]
views.get("label6").vw.setLeft((int)((views.get("seekbar2").vw.getLeft() + views.get("seekbar2").vw.getWidth())));
//BA.debugLineNum = 53;BA.debugLine="Label6.Height=Label4.Height"[Layout/General script]
views.get("label6").vw.setHeight((int)((views.get("label4").vw.getHeight())));
//BA.debugLineNum = 54;BA.debugLine="Label6.Width=20%x"[Layout/General script]
views.get("label6").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 55;BA.debugLine="Label6.Text=0"[Layout/General script]
((TextViewWrapper)views.get("label6").vw).setText("0");
//BA.debugLineNum = 56;BA.debugLine="Label6.TextSize=Label4.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label6").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label4").vw).getTextSize())));
//BA.debugLineNum = 59;BA.debugLine="Label7.Left=0"[Layout/General script]
views.get("label7").vw.setLeft((int)(0d));
//BA.debugLineNum = 60;BA.debugLine="Label7.Top=Label4.Bottom"[Layout/General script]
views.get("label7").vw.setTop((int)((views.get("label4").vw.getTop() + views.get("label4").vw.getHeight())));
//BA.debugLineNum = 61;BA.debugLine="Label7.Height=10%y"[Layout/General script]
views.get("label7").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 62;BA.debugLine="Label7.Width=30%x"[Layout/General script]
views.get("label7").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 63;BA.debugLine="Label7.Text=\"FA(kg/m^3)\""[Layout/General script]
((TextViewWrapper)views.get("label7").vw).setText("FA(kg/m^3)");
//BA.debugLineNum = 64;BA.debugLine="Label7.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label7").vw).setTextSize((float)(20d));
//BA.debugLineNum = 73;BA.debugLine="SeekBar3.Top=Label7.Top"[Layout/General script]
views.get("seekbar3").vw.setTop((int)((views.get("label7").vw.getTop())));
//BA.debugLineNum = 74;BA.debugLine="SeekBar3.Left=Label7.Right"[Layout/General script]
views.get("seekbar3").vw.setLeft((int)((views.get("label7").vw.getLeft() + views.get("label7").vw.getWidth())));
//BA.debugLineNum = 75;BA.debugLine="SeekBar3.Height=Label7.Height"[Layout/General script]
views.get("seekbar3").vw.setHeight((int)((views.get("label7").vw.getHeight())));
//BA.debugLineNum = 76;BA.debugLine="SeekBar3.Width=SeekBar1.Width"[Layout/General script]
views.get("seekbar3").vw.setWidth((int)((views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 78;BA.debugLine="Label9.Top=Label7.Top"[Layout/General script]
views.get("label9").vw.setTop((int)((views.get("label7").vw.getTop())));
//BA.debugLineNum = 79;BA.debugLine="Label9.Left=SeekBar3.Right"[Layout/General script]
views.get("label9").vw.setLeft((int)((views.get("seekbar3").vw.getLeft() + views.get("seekbar3").vw.getWidth())));
//BA.debugLineNum = 80;BA.debugLine="Label9.Height=Label7.Height"[Layout/General script]
views.get("label9").vw.setHeight((int)((views.get("label7").vw.getHeight())));
//BA.debugLineNum = 81;BA.debugLine="Label9.Width=20%x"[Layout/General script]
views.get("label9").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 82;BA.debugLine="Label9.Text=0"[Layout/General script]
((TextViewWrapper)views.get("label9").vw).setText("0");
//BA.debugLineNum = 83;BA.debugLine="Label9.TextSize=Label7.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label9").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label7").vw).getTextSize())));
//BA.debugLineNum = 86;BA.debugLine="Label10.Left=0"[Layout/General script]
views.get("label10").vw.setLeft((int)(0d));
//BA.debugLineNum = 87;BA.debugLine="Label10.Top=Label7.Bottom"[Layout/General script]
views.get("label10").vw.setTop((int)((views.get("label7").vw.getTop() + views.get("label7").vw.getHeight())));
//BA.debugLineNum = 88;BA.debugLine="Label10.Height=10%y"[Layout/General script]
views.get("label10").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 89;BA.debugLine="Label10.Width=30%x"[Layout/General script]
views.get("label10").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 90;BA.debugLine="Label10.Text=\"W(kg/m^3)\""[Layout/General script]
((TextViewWrapper)views.get("label10").vw).setText("W(kg/m^3)");
//BA.debugLineNum = 91;BA.debugLine="Label10.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label10").vw).setTextSize((float)(20d));
//BA.debugLineNum = 100;BA.debugLine="SeekBar4.Top=Label10.Top"[Layout/General script]
views.get("seekbar4").vw.setTop((int)((views.get("label10").vw.getTop())));
//BA.debugLineNum = 101;BA.debugLine="SeekBar4.Left=Label10.Right"[Layout/General script]
views.get("seekbar4").vw.setLeft((int)((views.get("label10").vw.getLeft() + views.get("label10").vw.getWidth())));
//BA.debugLineNum = 102;BA.debugLine="SeekBar4.Height=Label10.Height"[Layout/General script]
views.get("seekbar4").vw.setHeight((int)((views.get("label10").vw.getHeight())));
//BA.debugLineNum = 103;BA.debugLine="SeekBar4.Width=SeekBar1.Width"[Layout/General script]
views.get("seekbar4").vw.setWidth((int)((views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 105;BA.debugLine="Label12.Top=Label10.Top"[Layout/General script]
views.get("label12").vw.setTop((int)((views.get("label10").vw.getTop())));
//BA.debugLineNum = 106;BA.debugLine="Label12.Left=SeekBar4.Right"[Layout/General script]
views.get("label12").vw.setLeft((int)((views.get("seekbar4").vw.getLeft() + views.get("seekbar4").vw.getWidth())));
//BA.debugLineNum = 107;BA.debugLine="Label12.Height=Label10.Height"[Layout/General script]
views.get("label12").vw.setHeight((int)((views.get("label10").vw.getHeight())));
//BA.debugLineNum = 108;BA.debugLine="Label12.Width=20%x"[Layout/General script]
views.get("label12").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 109;BA.debugLine="Label12.Text=162"[Layout/General script]
((TextViewWrapper)views.get("label12").vw).setText("162");
//BA.debugLineNum = 110;BA.debugLine="Label12.TextSize=Label10.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label12").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label10").vw).getTextSize())));
//BA.debugLineNum = 113;BA.debugLine="Label13.Left=0"[Layout/General script]
views.get("label13").vw.setLeft((int)(0d));
//BA.debugLineNum = 114;BA.debugLine="Label13.Top=Label10.Bottom"[Layout/General script]
views.get("label13").vw.setTop((int)((views.get("label10").vw.getTop() + views.get("label10").vw.getHeight())));
//BA.debugLineNum = 115;BA.debugLine="Label13.Height=10%y"[Layout/General script]
views.get("label13").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 116;BA.debugLine="Label13.Width=30%x"[Layout/General script]
views.get("label13").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 117;BA.debugLine="Label13.Text=\"SP(kg/m^3)\""[Layout/General script]
((TextViewWrapper)views.get("label13").vw).setText("SP(kg/m^3)");
//BA.debugLineNum = 118;BA.debugLine="Label13.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label13").vw).setTextSize((float)(20d));
//BA.debugLineNum = 127;BA.debugLine="SeekBar5.Top=Label13.Top"[Layout/General script]
views.get("seekbar5").vw.setTop((int)((views.get("label13").vw.getTop())));
//BA.debugLineNum = 128;BA.debugLine="SeekBar5.Left=Label13.Right"[Layout/General script]
views.get("seekbar5").vw.setLeft((int)((views.get("label13").vw.getLeft() + views.get("label13").vw.getWidth())));
//BA.debugLineNum = 129;BA.debugLine="SeekBar5.Height=Label13.Height"[Layout/General script]
views.get("seekbar5").vw.setHeight((int)((views.get("label13").vw.getHeight())));
//BA.debugLineNum = 130;BA.debugLine="SeekBar5.Width=SeekBar1.Width"[Layout/General script]
views.get("seekbar5").vw.setWidth((int)((views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 132;BA.debugLine="Label15.Top=Label13.Top"[Layout/General script]
views.get("label15").vw.setTop((int)((views.get("label13").vw.getTop())));
//BA.debugLineNum = 133;BA.debugLine="Label15.Left=SeekBar5.Right"[Layout/General script]
views.get("label15").vw.setLeft((int)((views.get("seekbar5").vw.getLeft() + views.get("seekbar5").vw.getWidth())));
//BA.debugLineNum = 134;BA.debugLine="Label15.Height=Label13.Height"[Layout/General script]
views.get("label15").vw.setHeight((int)((views.get("label13").vw.getHeight())));
//BA.debugLineNum = 135;BA.debugLine="Label15.Width=20%x"[Layout/General script]
views.get("label15").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 136;BA.debugLine="Label15.Text=2.5"[Layout/General script]
((TextViewWrapper)views.get("label15").vw).setText("2.5");
//BA.debugLineNum = 137;BA.debugLine="Label15.TextSize=Label13.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label15").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label13").vw).getTextSize())));
//BA.debugLineNum = 140;BA.debugLine="Label16.Left=0"[Layout/General script]
views.get("label16").vw.setLeft((int)(0d));
//BA.debugLineNum = 141;BA.debugLine="Label16.Top=Label13.Bottom"[Layout/General script]
views.get("label16").vw.setTop((int)((views.get("label13").vw.getTop() + views.get("label13").vw.getHeight())));
//BA.debugLineNum = 142;BA.debugLine="Label16.Height=10%y"[Layout/General script]
views.get("label16").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 143;BA.debugLine="Label16.Width=30%x"[Layout/General script]
views.get("label16").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 144;BA.debugLine="Label16.Text=\"CA(kg/m^3)\""[Layout/General script]
((TextViewWrapper)views.get("label16").vw).setText("CA(kg/m^3)");
//BA.debugLineNum = 145;BA.debugLine="Label16.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label16").vw).setTextSize((float)(20d));
//BA.debugLineNum = 154;BA.debugLine="SeekBar6.Top=Label16.Top"[Layout/General script]
views.get("seekbar6").vw.setTop((int)((views.get("label16").vw.getTop())));
//BA.debugLineNum = 155;BA.debugLine="SeekBar6.Left=Label16.Right"[Layout/General script]
views.get("seekbar6").vw.setLeft((int)((views.get("label16").vw.getLeft() + views.get("label16").vw.getWidth())));
//BA.debugLineNum = 156;BA.debugLine="SeekBar6.Height=Label16.Height"[Layout/General script]
views.get("seekbar6").vw.setHeight((int)((views.get("label16").vw.getHeight())));
//BA.debugLineNum = 157;BA.debugLine="SeekBar6.Width=SeekBar1.Width"[Layout/General script]
views.get("seekbar6").vw.setWidth((int)((views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 159;BA.debugLine="Label18.Top=Label16.Top"[Layout/General script]
views.get("label18").vw.setTop((int)((views.get("label16").vw.getTop())));
//BA.debugLineNum = 160;BA.debugLine="Label18.Left=SeekBar6.Right"[Layout/General script]
views.get("label18").vw.setLeft((int)((views.get("seekbar6").vw.getLeft() + views.get("seekbar6").vw.getWidth())));
//BA.debugLineNum = 161;BA.debugLine="Label18.Height=Label16.Height"[Layout/General script]
views.get("label18").vw.setHeight((int)((views.get("label16").vw.getHeight())));
//BA.debugLineNum = 162;BA.debugLine="Label18.Width=20%x"[Layout/General script]
views.get("label18").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 163;BA.debugLine="Label18.Text=1040"[Layout/General script]
((TextViewWrapper)views.get("label18").vw).setText("1040");
//BA.debugLineNum = 164;BA.debugLine="Label18.TextSize=Label16.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label18").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label16").vw).getTextSize())));
//BA.debugLineNum = 167;BA.debugLine="Label19.Left=0"[Layout/General script]
views.get("label19").vw.setLeft((int)(0d));
//BA.debugLineNum = 168;BA.debugLine="Label19.Top=Label16.Bottom"[Layout/General script]
views.get("label19").vw.setTop((int)((views.get("label16").vw.getTop() + views.get("label16").vw.getHeight())));
//BA.debugLineNum = 169;BA.debugLine="Label19.Height=10%y"[Layout/General script]
views.get("label19").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 170;BA.debugLine="Label19.Width=30%x"[Layout/General script]
views.get("label19").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 171;BA.debugLine="Label19.Text=\"FAg(kg/m^3)\""[Layout/General script]
((TextViewWrapper)views.get("label19").vw).setText("FAg(kg/m^3)");
//BA.debugLineNum = 172;BA.debugLine="Label19.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label19").vw).setTextSize((float)(20d));
//BA.debugLineNum = 181;BA.debugLine="SeekBar7.Top=Label19.Top"[Layout/General script]
views.get("seekbar7").vw.setTop((int)((views.get("label19").vw.getTop())));
//BA.debugLineNum = 182;BA.debugLine="SeekBar7.Left=Label19.Right"[Layout/General script]
views.get("seekbar7").vw.setLeft((int)((views.get("label19").vw.getLeft() + views.get("label19").vw.getWidth())));
//BA.debugLineNum = 183;BA.debugLine="SeekBar7.Height=Label19.Height"[Layout/General script]
views.get("seekbar7").vw.setHeight((int)((views.get("label19").vw.getHeight())));
//BA.debugLineNum = 184;BA.debugLine="SeekBar7.Width=SeekBar1.Width"[Layout/General script]
views.get("seekbar7").vw.setWidth((int)((views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 186;BA.debugLine="Label21.Top=Label19.Top"[Layout/General script]
views.get("label21").vw.setTop((int)((views.get("label19").vw.getTop())));
//BA.debugLineNum = 187;BA.debugLine="Label21.Left=SeekBar7.Right"[Layout/General script]
views.get("label21").vw.setLeft((int)((views.get("seekbar7").vw.getLeft() + views.get("seekbar7").vw.getWidth())));
//BA.debugLineNum = 188;BA.debugLine="Label21.Height=Label19.Height"[Layout/General script]
views.get("label21").vw.setHeight((int)((views.get("label19").vw.getHeight())));
//BA.debugLineNum = 189;BA.debugLine="Label21.Width=20%x"[Layout/General script]
views.get("label21").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 190;BA.debugLine="Label21.Text=676"[Layout/General script]
((TextViewWrapper)views.get("label21").vw).setText("676");
//BA.debugLineNum = 191;BA.debugLine="Label21.TextSize=Label19.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label21").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label19").vw).getTextSize())));
//BA.debugLineNum = 194;BA.debugLine="Label22.Left=0"[Layout/General script]
views.get("label22").vw.setLeft((int)(0d));
//BA.debugLineNum = 195;BA.debugLine="Label22.Top=Label19.Bottom"[Layout/General script]
views.get("label22").vw.setTop((int)((views.get("label19").vw.getTop() + views.get("label19").vw.getHeight())));
//BA.debugLineNum = 196;BA.debugLine="Label22.Height=10%y"[Layout/General script]
views.get("label22").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 197;BA.debugLine="Label22.Width=30%x"[Layout/General script]
views.get("label22").vw.setWidth((int)((30d / 100 * width)));
//BA.debugLineNum = 198;BA.debugLine="Label22.Text=\"Age(days)\""[Layout/General script]
((TextViewWrapper)views.get("label22").vw).setText("Age(days)");
//BA.debugLineNum = 199;BA.debugLine="Label22.TextSize=20"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label22").vw).setTextSize((float)(20d));
//BA.debugLineNum = 208;BA.debugLine="SeekBar8.Top=Label22.Top"[Layout/General script]
views.get("seekbar8").vw.setTop((int)((views.get("label22").vw.getTop())));
//BA.debugLineNum = 209;BA.debugLine="SeekBar8.Left=Label22.Right"[Layout/General script]
views.get("seekbar8").vw.setLeft((int)((views.get("label22").vw.getLeft() + views.get("label22").vw.getWidth())));
//BA.debugLineNum = 210;BA.debugLine="SeekBar8.Height=Label22.Height"[Layout/General script]
views.get("seekbar8").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 211;BA.debugLine="SeekBar8.Width=SeekBar1.Width"[Layout/General script]
views.get("seekbar8").vw.setWidth((int)((views.get("seekbar1").vw.getWidth())));
//BA.debugLineNum = 213;BA.debugLine="Label24.Top=Label22.Top"[Layout/General script]
views.get("label24").vw.setTop((int)((views.get("label22").vw.getTop())));
//BA.debugLineNum = 214;BA.debugLine="Label24.Left=SeekBar8.Right"[Layout/General script]
views.get("label24").vw.setLeft((int)((views.get("seekbar8").vw.getLeft() + views.get("seekbar8").vw.getWidth())));
//BA.debugLineNum = 215;BA.debugLine="Label24.Height=Label22.Height"[Layout/General script]
views.get("label24").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 216;BA.debugLine="Label24.Width=20%x"[Layout/General script]
views.get("label24").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 217;BA.debugLine="Label24.Text=28"[Layout/General script]
((TextViewWrapper)views.get("label24").vw).setText("28");
//BA.debugLineNum = 218;BA.debugLine="Label24.TextSize=Label22.TextSize"[Layout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label24").vw).setTextSize((float)((((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("label22").vw).getTextSize())));
//BA.debugLineNum = 221;BA.debugLine="Label2.Top=Label22.Bottom"[Layout/General script]
views.get("label2").vw.setTop((int)((views.get("label22").vw.getTop() + views.get("label22").vw.getHeight())));
//BA.debugLineNum = 222;BA.debugLine="Label2.Left=0"[Layout/General script]
views.get("label2").vw.setLeft((int)(0d));
//BA.debugLineNum = 223;BA.debugLine="Label2.Height=Label22.Height"[Layout/General script]
views.get("label2").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 224;BA.debugLine="Label2.Width=20%x"[Layout/General script]
views.get("label2").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 225;BA.debugLine="Label2.Text = \"Samples\""[Layout/General script]
((TextViewWrapper)views.get("label2").vw).setText("Samples");
//BA.debugLineNum = 226;BA.debugLine="RadioButton1.Left = Label2.Right"[Layout/General script]
views.get("radiobutton1").vw.setLeft((int)((views.get("label2").vw.getLeft() + views.get("label2").vw.getWidth())));
//BA.debugLineNum = 227;BA.debugLine="RadioButton1.Top = Label22.Bottom"[Layout/General script]
views.get("radiobutton1").vw.setTop((int)((views.get("label22").vw.getTop() + views.get("label22").vw.getHeight())));
//BA.debugLineNum = 228;BA.debugLine="RadioButton1.Height=Label22.Height"[Layout/General script]
views.get("radiobutton1").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 229;BA.debugLine="RadioButton1.Width =14%x"[Layout/General script]
views.get("radiobutton1").vw.setWidth((int)((14d / 100 * width)));
//BA.debugLineNum = 230;BA.debugLine="RadioButton1.Text=25"[Layout/General script]
((TextViewWrapper)views.get("radiobutton1").vw).setText("25");
//BA.debugLineNum = 232;BA.debugLine="RadioButton2.Left = RadioButton1.Right"[Layout/General script]
views.get("radiobutton2").vw.setLeft((int)((views.get("radiobutton1").vw.getLeft() + views.get("radiobutton1").vw.getWidth())));
//BA.debugLineNum = 233;BA.debugLine="RadioButton2.Top = Label22.Bottom"[Layout/General script]
views.get("radiobutton2").vw.setTop((int)((views.get("label22").vw.getTop() + views.get("label22").vw.getHeight())));
//BA.debugLineNum = 234;BA.debugLine="RadioButton2.Height=Label22.Height"[Layout/General script]
views.get("radiobutton2").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 235;BA.debugLine="RadioButton2.Width =14%x"[Layout/General script]
views.get("radiobutton2").vw.setWidth((int)((14d / 100 * width)));
//BA.debugLineNum = 236;BA.debugLine="RadioButton2.Text=50"[Layout/General script]
((TextViewWrapper)views.get("radiobutton2").vw).setText("50");
//BA.debugLineNum = 238;BA.debugLine="RadioButton3.Left = RadioButton2.Right"[Layout/General script]
views.get("radiobutton3").vw.setLeft((int)((views.get("radiobutton2").vw.getLeft() + views.get("radiobutton2").vw.getWidth())));
//BA.debugLineNum = 239;BA.debugLine="RadioButton3.Top = Label22.Bottom"[Layout/General script]
views.get("radiobutton3").vw.setTop((int)((views.get("label22").vw.getTop() + views.get("label22").vw.getHeight())));
//BA.debugLineNum = 240;BA.debugLine="RadioButton3.Height=Label22.Height"[Layout/General script]
views.get("radiobutton3").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 241;BA.debugLine="RadioButton3.Width =16%x"[Layout/General script]
views.get("radiobutton3").vw.setWidth((int)((16d / 100 * width)));
//BA.debugLineNum = 242;BA.debugLine="RadioButton3.Text=100"[Layout/General script]
((TextViewWrapper)views.get("radiobutton3").vw).setText("100");
//BA.debugLineNum = 244;BA.debugLine="RadioButton4.Left = RadioButton3.Right"[Layout/General script]
views.get("radiobutton4").vw.setLeft((int)((views.get("radiobutton3").vw.getLeft() + views.get("radiobutton3").vw.getWidth())));
//BA.debugLineNum = 245;BA.debugLine="RadioButton4.Top = Label22.Bottom"[Layout/General script]
views.get("radiobutton4").vw.setTop((int)((views.get("label22").vw.getTop() + views.get("label22").vw.getHeight())));
//BA.debugLineNum = 246;BA.debugLine="RadioButton4.Height=Label22.Height"[Layout/General script]
views.get("radiobutton4").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 247;BA.debugLine="RadioButton4.Width =16%x"[Layout/General script]
views.get("radiobutton4").vw.setWidth((int)((16d / 100 * width)));
//BA.debugLineNum = 248;BA.debugLine="RadioButton4.Text=250"[Layout/General script]
((TextViewWrapper)views.get("radiobutton4").vw).setText("250");
//BA.debugLineNum = 250;BA.debugLine="RadioButton5.Left = RadioButton4.Right"[Layout/General script]
views.get("radiobutton5").vw.setLeft((int)((views.get("radiobutton4").vw.getLeft() + views.get("radiobutton4").vw.getWidth())));
//BA.debugLineNum = 251;BA.debugLine="RadioButton5.Top = Label22.Bottom"[Layout/General script]
views.get("radiobutton5").vw.setTop((int)((views.get("label22").vw.getTop() + views.get("label22").vw.getHeight())));
//BA.debugLineNum = 252;BA.debugLine="RadioButton5.Height=Label22.Height"[Layout/General script]
views.get("radiobutton5").vw.setHeight((int)((views.get("label22").vw.getHeight())));
//BA.debugLineNum = 253;BA.debugLine="RadioButton5.Width =20%x"[Layout/General script]
views.get("radiobutton5").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 254;BA.debugLine="RadioButton5.Text=2171"[Layout/General script]
((TextViewWrapper)views.get("radiobutton5").vw).setText("2171");
//BA.debugLineNum = 256;BA.debugLine="Button1.Top=RadioButton1.Bottom"[Layout/General script]
views.get("button1").vw.setTop((int)((views.get("radiobutton1").vw.getTop() + views.get("radiobutton1").vw.getHeight())));
//BA.debugLineNum = 257;BA.debugLine="Button1.Width=100%x"[Layout/General script]
views.get("button1").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 258;BA.debugLine="Button1.Text=\"Predict & Interpret\""[Layout/General script]
((TextViewWrapper)views.get("button1").vw).setText("Predict & Interpret");
//BA.debugLineNum = 259;BA.debugLine="Button1.Left=0"[Layout/General script]
views.get("button1").vw.setLeft((int)(0d));
//BA.debugLineNum = 260;BA.debugLine="Button1.Height=10%y"[Layout/General script]
views.get("button1").vw.setHeight((int)((10d / 100 * height)));

}
}