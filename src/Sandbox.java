import java.util.ArrayList;

public class Sandbox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "\r\nHello\r\nWorld!\r\n";
		
		for (String s : convertTextToArrayList(test)) {
			System.out.println(s);
		}
			
	}
	
	public static ArrayList<String> convertTextToArrayList(String text) {
		ArrayList<String> lines = new ArrayList<String>();
		
		String divOpen = "<div class='line'>";
		String divClose = "</div>";
		String buffer = divOpen;
		for(char c : text.toCharArray()) {
			switch (c) {
				case '\r':
					lines.add(buffer + divClose);
					buffer = divOpen;
					break;
				case '\n':
					break;
				default:
					buffer += c;					
					break;
			}
		}
		
		// handle the last line if it hasn't been added 
		if (buffer != "")
			lines.add(buffer + divClose);
		
		return lines;
	}
}
