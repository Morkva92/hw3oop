package sample;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CreateStudent createStudent = new CreateStudent();
        List<Student> students = createStudent.newStudents();

        Group group = new Group("Group A");

        try {
            // Добавляем студентов в группу
            CreateStudent.addStudentsToGroup(students, group);
        } catch (GroupOverflowException e) {
            System.out.println("Ошибка: Превышен лимит студентов в группе!");
            return; // Завершаем программу, так как группа переполнена
        }

        // Выводим список студентов в группе
        System.out.println("Студенты в группе:");
        System.out.println(group);

        // Пытаемся найти студента по фамилии
        String searchLastName = "Иванов";
        try {
            Student foundStudent = group.searchStudentByLastName(searchLastName);
            System.out.println("Студент с фамилией " + searchLastName + " найден: " + foundStudent);
        } catch (StudentNotFoundException e) {
            System.out.println("Студент с фамилией " + searchLastName + " не найден в группе " + group.getGroupName());
        }

        // Сортируем студентов по фамилии и выводим отсортированный список
        group.sortStudentsByLastName();
        System.out.println("Отсортированные студенты в группе:");
        System.out.println(group);

        // Сохраняем группу в CSV файл
        GroupFileStorage fileStorage = new GroupFileStorage();
        fileStorage.saveGroupToCSV(group);

        // Загружаем группу из CSV файла и выводим список студентов
        File csvFile = fileStorage.findFileByGroupName(group.getGroupName(), new File("."));
        if (csvFile != null) {
            Group loadedGroup = fileStorage.loadGroupFromCSV(csvFile);
            System.out.println("Загруженная группа из файла:");
            System.out.println(loadedGroup);
        }
    }
}