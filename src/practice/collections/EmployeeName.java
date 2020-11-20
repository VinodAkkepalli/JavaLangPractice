package practice.collections;

class EmployeeName implements Comparable<EmployeeName>{

	private final String firstName, lastName;

	public EmployeeName(String firstName, String lastName) {

		if(firstName == null || lastName == null){
			throw new NullPointerException();
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	//Eclipse IDE auto-generated hashCode method 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	//Eclipse IDE auto-generated equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeName other = (EmployeeName) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	//This compareTo method defines natural ordering that is used for sorting
	@Override
	public int compareTo(EmployeeName o) {

		int lastCmp =  lastName.compareTo(o.getLastName());
		return (lastCmp != 0 ? lastCmp : firstName.compareTo(o.getLastName()));
	}
	
}