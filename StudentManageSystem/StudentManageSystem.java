package com.practice.test3;
import java.util.Scanner;
import java.util.ArrayList;
public class StudentManageSystem{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> studentList = new ArrayList<>();
		consoleLoop: while (true){
			System.out.println("--------Menu List--------");
			System.out.println("1.Add Student\n2.Delete Student\n3.Modify Student\n4.Show All Student\n5.Exit\nPlease Input Your Option Number");
			String choose = sc.next(); 
			switch(choose){
				case "1" -> addStudent(studentList);
				case "2" -> deleteStudent(studentList);
				case "3" -> updateStudent(studentList);
				case "4" -> queryStudent(studentList);
				case "5" -> {System.out.println("Exited!");break consoleLoop;}
				default -> System.out.println("Wrong Option!");
			}
		}	
	}
	public static void addStudent(ArrayList<Student> studentList){
		Scanner sc = new Scanner(System.in);
		Student stu = new Student();
		String id = null;
		while(true){
			System.out.println("Please input student id:");
			id = sc.next();
			if(contains(studentList,id)){
			System.out.println("Id exists!Please reinput.");
			}else{stu.setId(id);break;}
		}
		System.out.println("Please input student name:");
		stu.setName(sc.next());
		System.out.println("Please input student age:");
		stu.setAge(sc.nextInt());
		System.out.println("Please input student address:");
		stu.setAddress(sc.next());
		studentList.add(stu);
		System.out.println("Added successfull\n");
	}
	
	public static void deleteStudent(ArrayList<Student> studentList){
		System.out.println("Please enter the id you want to delete:");
		String id = new Scanner(System.in).next();
		int index = getIndex(studentList,id);
		if(index >= 0){
			studentList.remove(index);
			System.out.println("Id:"+id+" deleted successfull\n");
		}else{System.out.println("Id does not exist,deletion failed\n");}
	}
	
	public static void updateStudent(ArrayList<Student> studentList){
		System.out.println("Please enter the id you want to modify:");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		int index = getIndex(studentList,id);
		if(index == -1){
			System.out.println("Id does not exist!\n");
			return;
		}
		Student stu = studentList.get(index);
		System.out.println("Please input new student name:");
		stu.setName(sc.next());
		System.out.println("Please input new student age:");
		stu.setAge(sc.nextInt());
		System.out.println("Please input new student address:");
		stu.setAddress(sc.next());
		System.out.println("Id:"+id+" Modified successfull\n");	
	}
	
	public static void queryStudent(ArrayList<Student> studentList){
		System.out.println("Show All Student:");
		if(studentList.size() == 0){System.out.println("No students! Please add first.\n");return;}
		System.out.printf("%-10s %-15s %-8s %-20s%n", "id", "name", "age", "address");
		for (int i=0;i<studentList.size();i++){
			Student stu = studentList.get(i);
			System.out.printf("%-10s %-15s %-8s %-20s%n",stu.getId(),stu.getName(),stu.getAge(),stu.getAddress());
		}
		System.out.println();
	}
	
	public static boolean contains(ArrayList<Student> studentList,String id){
		return getIndex(studentList,id) >= 0;
	}
	
	public static int getIndex(ArrayList<Student> studentList,String id){
		for(int i=0;i<studentList.size();i++){
			if(studentList.get(i).getId().equals(id)){
				return i;
			}
		}
		return -1;
	}
}