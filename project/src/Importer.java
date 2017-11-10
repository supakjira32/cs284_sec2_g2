import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Importer {
	private String line = new String();
	private ArrayList<String> id = new ArrayList<>();
	private JFileChooser chooser = new JFileChooser();
	private File file;

	public Importer() {
		
		chooser = new JFileChooser();
		chooser.setDialogTitle("Choose file to import");
		chooser.setApproveButtonText("Import");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	public boolean importFromFile() {

		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return false;
		}
		file = chooser.getSelectedFile();
		System.out.println(file);

		Scanner scanner;
		try {
			scanner = new Scanner(file);
			boolean valid = false;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();

				if (line.matches(".*\\d{10}.*")) {
					valid = true;
				}
			}
			
			scanner.close();
			
			if (valid == false) {
				return false;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.matches(".*\\d{10}.*")) {
					break;
				}
			}

		} catch (FileNotFoundException e) {
			return false;
		}
		{
			StringTokenizer tokenizer = new StringTokenizer(line, ",");
			tokenizer.nextToken();
			id.add(tokenizer.nextToken());
			
		}
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			StringTokenizer tokenizer = new StringTokenizer(line, ",");
			tokenizer.nextToken();
			id.add(tokenizer.nextToken());
		}

		

//		StringTokenizer tokenizer = null;
//		do {
//			tokenizer = new StringTokenizer(line, ",");
//			tokenizer.nextToken();
//			id.add(tokenizer.nextToken());
//			line = scanner.nextLine();
//		}while (scanner.hasNextLine());
//		
//		scanner.close();
//		for(String tmp:id) {
//			System.out.println(tmp);
//		}
		return true;
	}

	public ArrayList<String> getArrayList() {
		return id;
	}

}
