package resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializeData {
	private static ObjectOutputStream out;
	private static ObjectInputStream in;

	public void writeData(InvertedIndex obj) throws IOException {
		
		FileOutputStream fout = new FileOutputStream("test1.txt");
		out = new ObjectOutputStream(fout);
		out.writeObject(obj);
		System.out.println("success");
	}
	
	public void readData() throws ClassNotFoundException, IOException {
		FileInputStream fin = new FileInputStream("test1.txt");
		in = new ObjectInputStream(fin);
		InvertedIndex obj = (InvertedIndex)in.readObject();
		System.out.println(obj.invertedIdxArray);		
	}
	

}
