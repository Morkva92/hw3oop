package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Group {

    private String groupName;
    private List<Student> students = new ArrayList<>();

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean hasNoEquivalentStudents() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i) != null && students.get(j) != null && students.get(i).equals(students.get(j))) {
                    return false; 
                }
            }
        }
        return true; 
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (Student student : students) {
            if (student != null && student.getLastName().equals(lastName)) {
                return student;
            }
        }
        throw new StudentNotFoundException("There is no student with last name " + lastName + " in the Group " + groupName + ".");
    }

    public boolean removeStudentByID(int id) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student != null && student.getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public void sortStudentsByLastName() {
        students.sort((student1, student2) -> student1.getLastName().compareTo(student2.getLastName()));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Group " + groupName + " students list: \n");
        for (Student student : students) {
            result.append(student).append("\n");
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, students);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return Objects.equals(groupName, other.groupName) && Objects.equals(students, other.students);
    }
}