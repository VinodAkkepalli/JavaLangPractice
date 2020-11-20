package practice.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MySerializationConsumer {

	public static void main(String[] args) {

		try (ObjectInputStream cFile = new ObjectInputStream(new FileInputStream(new File("serialized.ser")))) {

			UserDetailsClass consumer = (UserDetailsClass) cFile.readObject();
			System.out.println(consumer.getClass().getSimpleName());
			System.out.println(consumer.getmName());
			System.out.println(consumer.getmAddress());
			System.out.println(consumer.getmLength());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
