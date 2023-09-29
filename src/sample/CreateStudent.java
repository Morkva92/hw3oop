package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateStudent {

    public List<Student> newStudents() {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.println("Введите количество студентов для добавления:");
        int numOfStudents = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < numOfStudents; i++) {
            Student newStudent = new Student();
            System.out.println("Введите имя студента: ");
            newStudent.setName(sc.nextLine());
            System.out.println("Введите фамилию студента: ");
            newStudent.setLastName(sc.nextLine());
            System.out.println("Введите пол студента (MALE - мужской, FEMALE - женский): ");
            String gender = sc.nextLine();

            if (gender.equals("MALE")) {
                newStudent.setGender(Gender.MALE);
            } else if (gender.equals("FEMALE")) {
                newStudent.setGender(Gender.FEMALE);
            } else {
                System.out.println("Ошибка, некорректный ввод");
            }

            System.out.println("Введите ID студента: ");
            newStudent.setId(sc.nextInt());
            sc.nextLine(); 
            System.out.println("Введите название группы студента: ");
            newStudent.setGroupName(sc.nextLine());

            students.add(newStudent);
        }

        return students;
    }

    public static void addStudentsToGroup(List<Student> students, Group group) throws GroupOverflowException {
        for (Student student : students) {
            group.addStudent(student);
        }
    }
}