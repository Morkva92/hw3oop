package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupFileStorage {

    public void saveGroupToCSV(Group group) {
        try (PrintWriter writer = new PrintWriter("group.csv")) {
            for (Student student : group.getStudents()) {
                if (student != null) {
                    writer.println(
                            student.getId() + "," +
                                    student.getGroupName() + "," +
                                    student.getName() + "," +
                                    student.getLastName() + "," +
                                    student.getGender()
                    );
                }
            }
            System.out.println("Группа успешно сохранена в CSV файл.");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при сохранении группы в CSV файл: " + e.getMessage());
        }
    }

    public Group loadGroupFromCSV(File file) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String groupName = parts[1].trim();
                    String name = parts[2].trim();
                    String lastName = parts[3].trim();
                    Gender gender = Gender.valueOf(parts[4].trim());

                    Student student = new Student(name, lastName, gender, id, groupName);
                    students.add(student);
                }
            }
            System.out.println("Группа успешно загружена из CSV файла.");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке группы из CSV файла: " + e.getMessage());
        }
        Group group = new Group(""); // Create an empty group
        group.setStudents(students); // Set the list of students
        return group;
    }

    public File findFileByGroupName(String groupName, File workFolder) {
        File[] files = workFolder.listFiles();
        for (File file : files) {
            if (file.getName().equals(groupName + ".csv")) {
                System.out.println("Файл найден: " + file.getAbsolutePath());
                return file;
            }
        }
        System.out.println("Файл с названием группы не найден.");
        return null;
    }
}