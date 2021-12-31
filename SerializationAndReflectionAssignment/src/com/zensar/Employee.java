package com.zensar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

public class Employee implements Serializable {

	private int Id;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, double salary, String name) {
		Id = id;
		this.salary = salary;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", salary=" + salary + ", name=" + name + "]";
	}

	private double salary;
	private String name;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@EmployeeAnnotation(name = "uday")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws Exception {

		// Employee e = new Employee(101, 15000.00, "");
		Employee e = new Employee();

		e.setId(101);
		e.setName(null);
		e.setSalary(100.99);

		System.out.println("Before serialization");

		System.out.println(e.getName());
		System.out.println(e.getId());

		try {

			Method m = e.getClass().getMethod("getName");

			EmployeeAnnotation empAnnotattion = m.getAnnotation(EmployeeAnnotation.class);
			System.out.println("value is: " + empAnnotattion.name());
		} catch (Exception ex1) {

			throw new AnnotationNotPersistedException("Annotation not defined");
		}

		// serialization

		FileOutputStream fout = new FileOutputStream("serial.txt");
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		oout.writeObject(e);
		oout.flush();
		oout.close();

		FileInputStream fis = new FileInputStream("serial.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Employee obj = (Employee) ois.readObject();
		ois.close();
		System.out.println("Employee Object: " + obj);

		System.out.println(e.getName());
		System.out.println(e.getId());

		System.out.println("After serialization");

		System.out.println(obj.getName());
		System.out.println(obj.getId());

		try {
			Method m1 = obj.getClass().getMethod("getName");

			EmployeeAnnotation empAnnotattion1 = m1.getAnnotation(EmployeeAnnotation.class);
			System.out.println("value is: " + empAnnotattion1.name());

		} catch (Exception ex) {

			throw new AnnotationNotPersistedException("Annotation not defined");
		}

	}

	/*
	 * ##OBSERVERVATIONS### 
	 * RetentionPolicy.SOURCE:- The annotations annotated using the SOURCE retention policy are discarded at runtime. 
	 * 
	 * RetentionPolicy.CLASS:- The annotations annotated using the CLASS retention policy
	 *  are recorded in the  .class file but are discarded during runtime. CLASS is the default retention
	 *  policy in Java. 
	 *  
	 * RetentionPolicy.RUNTIME:- The annotations annotated using the
	 * RUNTIME retention policy are retained during runtime and can be accessed in
	 * our program during runtime.
	 */
}
