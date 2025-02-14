package demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roll;
	private String name;

	public Student() {
		
		// TODO Auto-generated constructor stub
	}

	public Student(int roll, String name) {
		this.roll = roll;
		this.name = name;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}