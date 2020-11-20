package practice.serialization;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDetailsClass implements Serializable {
	
	private String mName;
	private String mAddress;
	
	//this is declared transient, hence will not be serialized
	//If we try to read this value from consumer code, 0 will be returned
	private transient int mLength;

	public UserDetailsClass(String name, String address) {

		mName = name;
		mAddress = address;
		mLength = mName.length();
	}

	public String getmName() {
		return mName;
	}

	public String getmAddress() {
		return mAddress;
	}

	public int getmLength() {
		return mLength;
	}

}