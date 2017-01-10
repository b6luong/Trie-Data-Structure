import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] arg) throws FileNotFoundException{
		//Set up Frame
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		
		try{
			JPanel panel = new AutoCompletePanel();
			frame.add(panel);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
