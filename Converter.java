//package JavaSwing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*







*/
class ConversionFunction {

//returning AtoF in numbers 
public static String AtoF(String ch){
		if((ch.charAt(0)=='a') || (ch.charAt(0)=='A')) return "10";
		else if((ch.charAt(0)=='b') || (ch.charAt(0)=='B')) return "11";
		else if((ch.charAt(0)=='c') || (ch.charAt(0)=='C')) return "12";
		else if((ch.charAt(0)=='d') || (ch.charAt(0)=='D')) return "13";
		else if((ch.charAt(0)=='e') || (ch.charAt(0)=='E')) return "14";
		else if((ch.charAt(0)=='f') || (ch.charAt(0)=='F')) return "15";	
		else if(Integer.parseInt(ch)==10) return "A";
		else if(Integer.parseInt(ch)==11) return "B";
		else if(Integer.parseInt(ch)==12) return "C";
		else if(Integer.parseInt(ch)==13) return "D";
		else if(Integer.parseInt(ch)==14) return "E";
		else if(Integer.parseInt(ch)==15) return "F";	
		else  return ch;
}

	
//All Systems to Decimal
	public static long AlltoDecimal(String num,int base,int p){ 
		
		if(num.length()>0) {
			long store=Math.round(Math.pow(base,p)*Long.parseLong(AtoF(num.substring(num.length()-1,num.length()))));
			return (store+AlltoDecimal(num.substring(0,num.length()-1),base,++p));
		}
		else return 0;
	}


//Decimal to All Systems
	public static String DecimaltoAll(long num,int base) {
		if(num>0) return DecimaltoAll(num/base,base)+AtoF(String.valueOf(num%base));
		else return "";
	}

//All to Binary
	public static String AlltoBinary(String str,int base) {
		String num="",rev="",bn="";
		int n,j=1,p=4;
		if(base==16) p=5;
		while(str.length()!=0){
			n=Integer.parseInt(AtoF(String.valueOf(str.substring(0,j).charAt(0))));
			for(int i=1;i<p;i++) {
				num+=Integer.toString(n%2);
				n/=2;
			}
		for(int i=num.length()-1;i>=0;i--) rev+=num.charAt(i);
		bn+=rev;
		num=rev="";
		str=str.substring(j,str.length());
		}
		return bn;
	}


//Binary to all
	public static String BinarytoAll(String str,int base) {
	int n;
	if(base==16) base=4;
	if(base==8) base=3;
	if(base==10) return String.valueOf(AlltoDecimal(str,2,0));
	if(str.length()<base) n=str.length();
	else n=base;
	if(str.length()>0) {
	String num=String.valueOf(AlltoDecimal(str.substring(str.length()-n,str.length()),2,0));
	num=AtoF(num);
	return BinarytoAll(str.substring(0,str.length()-n),base)+num;
	}
	else return "";
	}
}
/*

















*/
class Converter {
public static void main(String... argc) {

a obj=new a();


}
}
/*


















GUI class overloading Jframe
*/
class a extends JFrame implements TextListener,ActionListener {

private String[] str={"Decimal(0-9)","Binary(0-1)","Octal(0-7)","Hexa-Decimal(0-F)"};
private int sm1=80,sm2=40;
private JComboBox cb1,cb2;
private TextField t1,t2;
private JLabel l1;
private JButton b2,b3;
private ConversionFunction fun;

public a() {
  
 	
	setVisible(true);
	setTitle("Number System Converter");
	setBounds(600,200,400,180);
  	setLayout(null);
  	


l1=new JLabel("Number System Converter");

cb1=new JComboBox();
cb2=new JComboBox();
t1=new TextField();
t2=new TextField();
b2=new JButton("Swap");
b3=new JButton("Clear");
fun=new ConversionFunction();
l1.setBounds(120,0,150,30);
cb1.setBounds(20,sm2,100,20);
b2.setBounds(150,sm2,80,20);
cb2.setBounds(260,sm2,100,20);
t1.setBounds(20,sm1,100,20);
b3.setBounds(150,sm1,80,20);
t2.setBounds(260,sm1,100,20);
t1.addTextListener(this);
b2.addActionListener(this);
b3.addActionListener(this);

add(l1);
add(t1);
add(b2);
add(t2);
add(cb1);
add(cb2);
add(b3);
cb1.addItem(str[0]);
cb1.addItem(str[1]);
cb1.addItem(str[2]);
cb1.addItem(str[3]);
cb2.addItem(str[0]);
cb2.addItem(str[1]);
cb2.addItem(str[2]);
cb2.addItem(str[3]);



t1.requestFocus();
setResizable(false);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}



	public void actionPerformed(ActionEvent e) {
	String u1,u2;
	if(e.getSource() == b2)	{
		u1=cb1.getSelectedItem().toString();
		u2=cb2.getSelectedItem().toString();
		cb2.removeAllItems();
		cb1.removeAllItems();
		cb1.addItem(u2);
		cb2.addItem(u1);
		for(int i=0;i<4;i++) {
		if(!u1.equals(str[i])) cb2.addItem(str[i]);
		if(!u2.equals(str[i])) cb1.addItem(str[i]); 
		//t1.requestFocus();
		}
		u1=t2.getText();
		t2.setText(t1.getText());
		t1.setText(u1);
	}
	if(e.getSource() == b3)	{
		t1.setText("");
		t2.setText(t1.getText());
		//t1.requestFocus();
	}
	t1.requestFocus();
	}
	public void textValueChanged(TextEvent te) {
	char ch1,ch2;
	ch1=cb1.getSelectedItem().toString().charAt(0);
	ch2=cb2.getSelectedItem().toString().charAt(0);
	t1.requestFocus();
		if(t1.getText().equals("")) t2.setText("");
		else if(ch1=='D' && ch2=='B') t2.setText(fun.DecimaltoAll(Long.parseLong(t1.getText()),2));
		else if(ch1=='D' && ch2=='O') t2.setText(fun.DecimaltoAll(Long.parseLong(t1.getText()),8));
		else if(ch1=='D' && ch2=='H') t2.setText(fun.DecimaltoAll(Long.parseLong(t1.getText()),16));
		else if(ch1=='B' && ch2=='D') t2.setText(String.valueOf(fun.AlltoDecimal(t1.getText(),2,0)));
		else if(ch1=='O' && ch2=='D') t2.setText(String.valueOf(fun.AlltoDecimal(t1.getText(),8,0)));
		else if(ch1=='H' && ch2=='D') t2.setText(String.valueOf(fun.AlltoDecimal(t1.getText(),16,0)));
		else if(ch1=='O' && ch2=='B') t2.setText(fun.AlltoBinary(t1.getText(),8));
		else if(ch1=='H' && ch2=='B') t2.setText(fun.AlltoBinary(t1.getText(),16));
		else if(ch1=='B' && ch2=='O') t2.setText(fun.BinarytoAll(t1.getText(),8));
		else if(ch1=='B' && ch2=='H') t2.setText(fun.BinarytoAll(t1.getText(),16));
		else if(ch1=='O' && ch2=='H') t2.setText(fun.BinarytoAll(fun.AlltoBinary(t1.getText(),8),16));
		else if(ch1=='H' && ch2=='O') t2.setText(fun.BinarytoAll(fun.AlltoBinary(t1.getText(),16),8));
		else t2.setText(t1.getText());
	//t2.setText(txt);
	}
	

}
/*






*/

