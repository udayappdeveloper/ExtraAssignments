package com.zensar;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EmployeeUtility {

	private List<Employee> empList = new ArrayList<>();
	// Map<Integer, Integer> tenureCountMap = new HashMap<>();

	public EmployeeUtility() {

		Employee e1 = new Employee(101, "ROCK", 250000.00, LocalDate.of(2021, 1, 10));
		empList.add(e1);
		Employee e2 = new Employee(102, "DON", 50000.00, LocalDate.of(2015, 11, 8));
		empList.add(e2);

		Employee e3 = new Employee(103, "GEORGE", 20000.00, LocalDate.of(2014, 1, 10));
		empList.add(e3);
		Employee e4 = new Employee(104, "JERRY", 502000.00, LocalDate.of(2013, 11, 8));
		empList.add(e4);

		Employee e5 = new Employee(105, "TOM", 250000.00, LocalDate.of(2021, 1, 10));
		empList.add(e5);
		Employee e6 = new Employee(106, "SHENOY", 50000.00, LocalDate.of(2020, 11, 8));
		empList.add(e6);

		Employee e7 = new Employee(107, "BANTU", 20000.00, LocalDate.of(2019, 1, 10));
		empList.add(e7);
		Employee e8 = new Employee(108, "AJAY", 502000.00, LocalDate.of(2019, 11, 8));
		empList.add(e8);

		Employee e9 = new Employee(109, "AMAR", 2000.00, LocalDate.of(2019, 10, 4));
		empList.add(e9);

		Employee e10 = new Employee(110, "ANTONY", 22000.00, LocalDate.of(2019, 10, 4));
		empList.add(e10);

		Employee e11 = new Employee(111, "ATOM", 35000.00, LocalDate.of(2020, 1, 4));
		empList.add(e11);

	}

	public List<Employee> findOutLongTenureEmployees() {

		List<Employee> list = empList.stream().filter(e -> Period.between(e.getDoj(), LocalDate.now()).getYears() > 5)
				.collect(Collectors.toList());

		return list;

	}

	public Map<Integer, Long> tenureWiseCount() {

		// Approach #1
		/*
		 * Consumer<Employee> action = e -> { int years = Period.between(e.getDoj(),
		 * LocalDate.now()).getYears();
		 * 
		 * if (!tenureCountMap.containsKey(years)) { tenureCountMap.put(years, 1); }
		 * else { tenureCountMap.put(years, tenureCountMap.get(years).intValue() + 1); }
		 * };
		 * 
		 * empList.stream().forEach(action);
		 */

		// System.out.println(tenureCountMap);

		// #Approach2

		Map<Integer, Long> yearWiseCountMap = empList.stream().collect(Collectors
				.groupingBy(e -> Period.between(e.getDoj(), LocalDate.now()).getYears(), Collectors.counting()));

		return yearWiseCountMap;

	}

	public static void main(String[] args) {

		EmployeeUtility employeeUtility = new EmployeeUtility();
		System.out.println(employeeUtility.findOutLongTenureEmployees().toString());
		System.out.println("Year wise count is:" + employeeUtility.tenureWiseCount());
		;

	}

}
