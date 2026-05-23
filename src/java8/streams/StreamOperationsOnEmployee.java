package java8.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationsOnEmployee {

    static void main() {


        List<Employee> employees = EmployeeDatabase.getAllEmployees();

        // forEach
        employees.forEach(e-> System.out.println(e.getName()+" : "+e.getSalary()));

        employees.stream().forEach(System.out::println);

        //filter
        //.collect

        Map<Integer, String> developmentEmployees = employees.stream()
                .filter(e -> e.getDept().equals("Development") && e.getSalary() > 80000)
                .collect(Collectors.toMap(Employee::getId, Employee::getName));

        System.out.println("Development dept employees with salary > 80000 are: ");
         System.out.println(developmentEmployees);

        //map
        //distinct
        List<String> ListOfDistinctDepts = employees.stream()
                .map(Employee::getDept)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(ListOfDistinctDepts);

        List<Stream<String>> projectNames = employees.stream()
                .map(e -> e.getProjects()
                        .stream().map(p -> p.getName())).collect(Collectors.toList());

        //flatMap

        List<String> projects = employees.stream()
                .flatMap(e -> e.getProjects().stream())
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(projects);


        //sorted
        //asc
        List<Employee> ascSortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());

//        ascSortedEmployees.get(0);

        //ascSortedEmployees.forEach(System.out::println);

        //desc
        List<Employee> descSortedEmployees = employees.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Employee::getSalary)))
                .collect(Collectors.toList());

//        descSortedEmployees.get(0);

        //descSortedEmployees.forEach(System.out::println);

        //min & max
        Optional<Employee> highestPaidEmployees = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        // System.out.println("Highest paid employee : "+highestPaidEmployees);

        Optional<Employee> lowestPaidEmployees = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary));

        //System.out.println("Lowest paid employee : "+lowestPaidEmployees);

        //groupingBy

        Map<String, List<Employee>> employeeGroup = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender));

        //System.out.println(employeeGroup);

        //Gender -> [names]
        Map<String, List<String>> employeeGroupNames = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));

        //System.out.println(employeeGroupNames);

        //Gender -> [count]
        Map<String, Long> employeeGroupCountMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(employeeGroupCountMap);

        //findFirst

        Employee findFirstElement = employees.stream()
                .filter(e -> e.getDept().equals("Development"))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Employee not found "));

//        System.out.println(findFirstElement.get());//NPE
//
//        if(findFirstElement.isPresent()){
//            System.out.println(findFirstElement.get());
//        }
//
//        findFirstElement.ifPresent(e-> System.out.println(e.getName()));

        //System.out.println(findFirstElement);

        //findAny

        Employee findAnyElement = employees.stream()
                .filter(e -> e.getDept().equals("Development"))
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("Employee not found "));

        // System.out.println(findAnyElement);

        //anyMatch(Predicate) , allMatch(Predicate) , noneMatch(Predicate)

        boolean developmentEmpAnyMatch = employees.stream()
                .anyMatch(e -> e.getDept().equals("Development"));
        //System.out.println("is there any employee match from development dept "+developmentEmpAnyMatch);


        boolean developmentEmpAllMatch = employees.stream()
                .allMatch(e -> e.getSalary()>50000);//55000
        //System.out.println(developmentEmpAllMatch); //false


        boolean isNoneMatch = employees.stream()
                .noneMatch(e -> e.getDept().equals("abc"));
        //System.out.println(isNoneMatch);

        //limit(long)

        List<Employee> topPaidEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(4)
                .collect(Collectors.toList());

        topPaidEmployees.forEach(e-> System.out.println(e.getName()));

        //skip(long)
        List<Employee> skipEmployees = employees.stream().skip(10)
                .collect(Collectors.toList());


        /**
         *
         * You are given a list of Employee objects. Find the highest-paid employee in each department.
         * Return a Map where the key is the department name and the value is the Employee (or Optional<Employee>) with the top salary.
         *
         * Assume this class: ```java
         * class Employee {
         * String name;
         * String department;
         * double salary;
         * // Assume getters exist
         * }
         *
         * Goal: Return Map<String, Optional<Employee>> (or Map<String, Employee>).
         *
         *
         */

        Map<String, List<Employee>> collect = employees.stream()
//                .map(e -> Map.entry(e.getDept(), e))
                .collect(Collectors.groupingBy(Employee::getDept));
        System.out.println(collect);

        Map<String, Optional<Employee>> collect2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept,
                                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(collect2);

        Map<String, Employee> collect3 = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDept,
                        e->e,
                        (existingEmp, newEmp) ->
                                existingEmp.getSalary() >= newEmp.getSalary() ? existingEmp : newEmp
                ));
        collect3.forEach((s, employee) -> System.out.println( s + " = " + employee.getName()));
//
//        forEach(Consumer)
//        filter(Predicate)
//        collect(Collector)
//        map(Function)
//        distinct()
//        flatMap(Function)
//        sorted(Comparator both ASC and DESC)
//        min() & max()
//        GroupBy
//        findFirst()
//        findAny()
//        anyMatch(Predicate)
//        allMatch(Predicate)
//        noneMatch(Predicate)
//        limit(long maxSize)
//        skip(long n)


    }
}