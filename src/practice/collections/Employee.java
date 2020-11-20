package practice.collections;

import java.util.Calendar;

public class Employee implements Comparable<Employee>{
	
	private EmployeeName eName;
	private Integer eNumber; 
	private Calendar hireDate;

	public Integer geteNumber() {
		return eNumber;
	}

	public EmployeeName geteName() {
		return eName;
	}

	public Calendar getHireDate() {
		return hireDate;
	}

	public Employee(EmployeeName name, int number, Calendar hireDate) {

		this.eName = new EmployeeName(name.getFirstName(), name.getLastName());
		this.eNumber = number;
		this.hireDate = hireDate;
	}

	//natural ordering of the Employee
	@Override
	public int compareTo(Employee o) {
		return this.eName.compareTo(o.eName);
	}
	
	@Override
	public String toString() {
		String date = getHireDate().get(Calendar.DAY_OF_MONTH) + "/" 
		+ getHireDate().get(Calendar.MONTH) + "/" + (1900+getHireDate().get(Calendar.YEAR));
		return eName.toString() + "; " + geteNumber() + "; " + date;
	}
}
