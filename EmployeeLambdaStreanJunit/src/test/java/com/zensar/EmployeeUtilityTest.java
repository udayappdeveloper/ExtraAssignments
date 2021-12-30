package com.zensar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class EmployeeUtilityTest {

	private List<Employee> empList = new ArrayList<>();

	private Map<Object, Long> yearWiseCountMap = new HashMap();

	@Test
	public void test() {
		EmployeeUtility employeeUtilityTest = mock(EmployeeUtility.class);
		when(employeeUtilityTest.findOutLongTenureEmployees()).thenReturn(empList);
		when(employeeUtilityTest.tenureWiseCount()).thenReturn(yearWiseCountMap);
	}

}
