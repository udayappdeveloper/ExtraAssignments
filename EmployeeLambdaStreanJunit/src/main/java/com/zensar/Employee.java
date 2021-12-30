package com.zensar;

import java.time.LocalDate;

public class Employee {

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", doj=" + doj + "]";
	}

	private int id;

	public Employee() {
	}

	public Employee(int id, String name, Double salary, LocalDate doj) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.doj = doj;
	}

	private String name;
	private Double salary;
	private LocalDate doj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

}
