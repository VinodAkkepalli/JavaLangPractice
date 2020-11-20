package practice.serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MySerializationProducer {

	public static void main(String[] args) {
		
		UserDetailsClass obj = new UserDetailsClass("Vinod", "Bellampalli");

		try (ObjectOutputStream aFile = new ObjectOutputStream(new FileOutputStream(new File("serialized.ser")))) {
			aFile.writeObject(obj);
			System.out.println("User details serialized to a serialized.ser file!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
