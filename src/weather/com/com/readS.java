package weather.com.com;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//////////////////////////////////////////////////////////////////////////////////////////////

public class readS {
	 String adr;
	 String cur;
	 String mn;
	 String mx;
	 String td;
	 String this_marker = "MyWidgetProvider"; //** зададим имя маркера для логов
	 
	 //////////////////////////////////////////////////////////////////////////////////////////////
	 
	 readS (String adr) {
		 this.adr  = adr;
	 }
	 
	 //////////////////////////////////////////////////////////////////////////////////////////////
	 
	 void getDt() {
		 try {
			 URL url = new URL(adr);
			 URLConnection conn = url.openConnection();
			 InputStreamReader rd = new InputStreamReader(conn.getInputStream());
			 StringBuilder allpage = new StringBuilder();
			 int n = 0;
			 char[] buffer = new char[40000];
			 while (n >= 0) {
				 n = rd.read(buffer, 0, buffer.length);
				 if (n > 0) {
					 allpage.append(buffer, 0, n);                    
				 }
			 }
			 String dat = allpage.toString();
			 String[] tokens = dat.split("<br>");
			 cur 	= tokens[0];
			 mn		= tokens[2];
			 mx		= tokens[4];
			 td		= tokens[6];
		 }
		 catch (Exception e) {   
			 cur 	= "-";
			 mn		= "-";
			 mx		= "-";
		 }
	 }//void getDt()
	 
	 //////////////////////////////////////////////////////////////////////////////////////////////
	 
	 public static void main(String[] args) {
		 readS y = new readS("http://star003.dlinkddns.com/03.php");
		 y.getDt();
		 System.out.println("cur:"+y.cur);
		 System.out.println("mn:"+y.mn);
		 System.out.println("mx:"+y.mx);
		 System.out.println("td:"+y.td);
	 }//public static void main(String[] args)
	 
}//public class readS
