package practice.collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @author VinodAkkepalli
 *
 *	Program to demonstrate the object natural ordering by implementing Comparable interface
 *	https://docs.oracle.com/javase/tutorial/collections/interfaces/order.html
 *
 */


public class EmployeeCollectionsSort {

	//using comparator to sort the employee in seniority order least senior --> most senior
	static final Comparator<Employee> SENIORITY_ORDER =  (e1, e2) -> {
				int dateCmp = e2.getHireDate().compareTo(e1.getHireDate());
				if(dateCmp !=0)
					return dateCmp;

				return (e1.geteNumber().compareTo(e2.geteNumber()));
			};

	//using comparator to sort the employee on employee number
	static final Comparator<Employee> EMPLOYEE_ID = (e1, e2) -> (e2.geteNumber().compareTo(e1.geteNumber()));
			
	public static void main(String[] args) {

		/**
		 * This part is to manipulate only Employee name related lists
		 */
		
		EmployeeName[] nameArray = {
	            new EmployeeName("John", "Smith"),
	            new EmployeeName("Karl", "Ng"),
	            new EmployeeName("Jeff", "Smith"),
	            new EmployeeName("Tom", "Rich"),
	            new EmployeeName("Vinod", "Akkepalli"),
	        };
		
		System.out.println("Employee names in creation order");
		List<EmployeeName> eNames = Arrays.asList(nameArray);
		System.out.println(eNames);
		
		System.out.println("Employee names in sorted order");
		Collections.sort(eNames);
		System.out.println(eNames);
		
		/**
		 * This part is to manipulate lists containing all details Employees
		 */
		
		Employee[] empDetails = {
				new Employee(new EmployeeName("John", "Smith"), 1000, new GregorianCalendar(96, Calendar.NOVEMBER, 25)/*new Date(96, 10, 25)*/),
				new Employee(new EmployeeName("Karl", "Ng"), 1001, new GregorianCalendar(96, Calendar.NOVEMBER, 25)),
				new Employee(new EmployeeName("Jeff", "Smith"), 1005, new GregorianCalendar(100, Calendar.DECEMBER, 30)),
				new Employee(new EmployeeName("Tom", "Rich"), 1004, new GregorianCalendar(92, Calendar.SEPTEMBER, 10)),
				new Employee(new EmployeeName("Vinod", "Akkepalli"), 1002, new GregorianCalendar(115, Calendar.MARCH, 15)),
		};
		
		System.out.println("Employee details in order of storage");
		List<Employee> empList = Arrays.asList(empDetails);
		System.out.println(empList);
		
		System.out.println("Employee details sorted on its natural ordering(By Name)");
		Collections.sort(empList);
		System.out.println(empList);
		
		System.out.println("Employee details sorted on seniority(hireDate)");
		Collections.sort(empList, SENIORITY_ORDER);
		System.out.println(empList);

		System.out.println("Employee details sorted on seniority(hireDate) most senior -> lease senior");
		Collections.sort(empList, (e1, e2) -> e1.getHireDate().compareTo(e2.getHireDate()) );
		System.out.println(empList);

		//same sort as above in short format
		Collections.sort(empList, Comparator.comparing(Employee::getHireDate));
		System.out.println(empList);

		System.out.println("Employee details sorted on employee number");		
		empList.sort(EMPLOYEE_ID);
		System.out.println(empList);
		Collections.sort(empList, (first,second) -> first.geteNumber().compareTo(second.geteNumber()));
		System.out.println(empList);


		streamOperationsPractice(empDetails);
	}

	private static void streamOperationsPractice(Employee[] empDetails) {

		System.out.println("print employees whose first name starts with 'J'");
		Arrays.stream(empDetails).filter(e -> e.geteName().getFirstName().startsWith("J")).forEach(System.out::println);

		System.out.println("print employee names whose emp number is greater than 1002");
		List<EmployeeName> names = Arrays.stream(empDetails).filter(e -> e.geteNumber() > 1002).map(Employee::geteName).collect(Collectors.toList());
		System.out.println(names);

		System.out.println("Group employees by employee last name");
		Map<String, List<Employee>> groupedEmployees = Arrays.stream(empDetails).collect(Collectors.groupingBy(employee -> employee.geteName().getLastName()));
		System.out.println(groupedEmployees);

		System.out.println("flatmap usage");
//		groupedEmployees.entrySet().forEach(System.out::println);
		groupedEmployees.entrySet().stream().flatMap(e -> e.getValue().stream()).forEach(System.out::println);
	}

}