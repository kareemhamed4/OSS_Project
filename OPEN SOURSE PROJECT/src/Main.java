import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> data = new ArrayList();
    static ArrayList<String> IDs = new ArrayList();
    static ArrayList<String> names = new ArrayList();
    static ArrayList<String> grades = new ArrayList();
    static ArrayList<String> departments = new ArrayList();
    static ArrayList<String> GPA = new ArrayList();
    static String fileName = "DataFile.csv";

    public static void main(String[] args) {
        try {
            fetchData(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome");
        loop:
        while (true) {
            System.out.println("Please select service number : ");
            System.out.println("Press 1 to add new Student.");
            System.out.println("Press 2 to search by ID.");
            System.out.println("Press 3 to update record by ID.");
            System.out.println("Press 4 to display record by ID.");
            System.out.println("Press 5 to display column by headers.");
            System.out.println("Press 6 to delete record by ID.");
            System.out.println("Press 7 to create new File.");
            System.out.println("Press 0 to exit.");
            Scanner input = new Scanner(System.in).useDelimiter("\n");
            int serviceNumber = input.nextInt();
            try {
                switch (serviceNumber) {
                    case 1: {
                        //add
                        System.out.println("Please enter File name : ");
                        fileName = input.next();
                        System.out.println("Please enter Student ID : ");
                        String id = input.next();
                        System.out.println("Please enter Student name : ");
                        String name = input.next();
                        System.out.println("Please enter Student Grade : ");
                        String grade = input.next();
                        System.out.println("Please enter Student department : ");
                        String department = input.next();
                        System.out.println("Please enter Student GPA : ");
                        String gpa = input.next();
                        if (insertRecordToFile(id, name, grade, department, gpa, fileName))
                            System.out.println("New Student Was Added!");
                        else
                            System.out.println("Can not add, because ID is already exist try another service.");
                        break;
                    }
                    case 2: {
                        //search
                        System.out.println("Please enter file name : ");
                        fileName = input.next();
                        System.out.println("Please enter Student ID : ");
                        String IDToSearch = input.next();
                        searchDataFromFile(IDToSearch, fileName);
                        break;
                    }
                    case 3: {
                        //update
                        System.out.println("Please enter file name : ");
                        fileName = input.next();
                        System.out.println("Please enter Student ID : ");
                        String IDToUpdate = input.next();
                        System.out.println("Please enter new Student name : ");
                        String name = input.next();
                        System.out.println("Please enter new Student Grade : ");
                        String age = input.next();
                        System.out.println("Please enter new Student department : ");
                        String department = input.next();
                        System.out.println("Please enter new Student GPA : ");
                        String gpa = input.next();
                        updateDataFromFile(IDToUpdate, name, age, department, gpa, fileName);
                        break;
                    }
                    case 4: {
                        //display record
                        System.out.println("Enter File Name : ");
                        fileName = input.next();
                        System.out.println("Enter Student ID to display his info : ");
                        String ID = input.next();
                        displayRecordFromFile(ID,fileName);
                        break;
                    }
                    case 5:{
                        //display column
                        System.out.println("Please enter file name : ");
                        fileName = input.next();
                        fetchData(fileName);
                        if(data.size()<2) {
                            System.out.println("File is empty");
                        }
                        else {
                            System.out.println("Please select service number : ");
                            System.out.println("Press 1 to display Students Names.");
                            System.out.println("Press 2 to display Students IDs.");
                            System.out.println("Press 3 to display Students Grades.");
                            System.out.println("Press 4 to display Students Departments.");
                            System.out.println("Press 5 to display Students GPA.");
                            System.out.println("Press 0 to exit.");
                            int serviceNumberToDisplayColumn = input.nextInt();
                            switch (serviceNumberToDisplayColumn) {
                                case 1: {
                                    displayNames();
                                    break;
                                }
                                case 2: {
                                    displayIDs();
                                    break;
                                }
                                case 3: {
                                    displayGrades();
                                    break;
                                }
                                case 4: {
                                    displayDepartments();
                                    break;
                                }
                                case 5: {
                                    displayGPA();
                                    break;
                                }
                                case 0: {
                                    break;
                                }
                                default:
                                    System.out.println("Service number not found");
                            }
                        }
                        break;
                    }
                    case 6: {
                        //delete
                        System.out.println("Please enter file name : ");
                        fileName = input.next();
                        System.out.println("Enter Student ID to delete record : ");
                        String IDToDeleted = input.next();
                        deleteDataFromFile(IDToDeleted, fileName);
                        break;
                    }
                    case 7:{
                        //create new file
                        System.out.println("Enter file name : ");
                        String newFile = input.next();
                        createFile(newFile);
                        break ;
                    }
                    case 0: {
                        break loop;
                    }
                    default:
                        System.out.println("Service number not found");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Done
    public static void fetchData(String fileName) throws IOException {
        String line;
        String[] toFetchData;
        data.clear();
        IDs.clear();
        names.clear();
        departments.clear();
        grades.clear();
        GPA.clear();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        for (int i = 1; i < data.size(); i++) {
            toFetchData = data.get(i).split(",");
            Student student = new Student(toFetchData[0], toFetchData[1], toFetchData[2], toFetchData[3], toFetchData[4]);
            IDs.add(student.getId());
            names.add(student.getName());
            grades.add(student.getGrade());
            departments.add(student.getDepartment());
            GPA.add(student.getGpa());
        }
    }
    //Done
    public static int checkID(String id) {
        int index = 1;
        for (int i = 0; i < IDs.size(); i++, index++) {
            if (IDs.get(i).compareTo(id) == 0)
                break;
        }
        if (index > IDs.size())
            index = -1;
        return index;
    }
    //Done
    public static void createFile(String fileName) throws IOException {
        File databaseFile = new File(fileName);
        if (databaseFile.createNewFile()) {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            printWriter.println(new Student("name", "id", "age", "collage", "address").writeAllData());
            printWriter.flush();
            printWriter.close();
            System.out.println("File created");
        } else
            System.out.println("File already exist");
    }
    //Done
    public static boolean insertRecordToFile(String id, String name, String grade, String department, String gpa, String fileName) throws IOException {
        if (checkID(id) == -1) {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            printWriter.println(new Student(id, name, grade, department, gpa).writeAllData());
            printWriter.flush();
            printWriter.close();
            data.add(new Student(id, name, grade, department, gpa).writeAllData());
            return true;
        }
        return false;
    }
    //Done
    public static void updateDataFromFile(String id, String name, String grade, String department, String gpa, String fileName) throws IOException {
        fetchData(fileName);
        int index = checkID(id);
        if (index >= 0) {
            data.set(index, new Student(id, name, grade, department, gpa).writeAllData());
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < data.size(); i++) {
                writer.append(data.get(i));
                writer.append("\n");
            }
            writer.close();
            System.out.println("Updated.");
        }
        else
            System.out.println("Student ID not exist");
    }
    //Done
    public static void searchDataFromFile(String id, String fileName) throws IOException {
        fetchData(fileName);
        int index = checkID(id);
        if (index >= 0)
            displayRecordFromFile(id,fileName);
        else
            System.out.println("Student ID not exist");
    }
    //Done
    public static void deleteDataFromFile(String id, String fileName) throws IOException {
        fetchData(fileName);
        int index = checkID(id);
        if(index>=0){
            data.remove(checkID(id));
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < data.size(); i++) {
                writer.append(data.get(i));
                writer.append("\n");
            }
            writer.close();
            System.out.println("Deleted.");
        }
        else
            System.out.println("Student ID not found.");
    }
    //Done
    public static void displayRecordFromFile(String id,String fileName) throws IOException {
        fetchData(fileName);
        int index = checkID(id);
        if (index >= 0&&data.size()>=2) {
            String[] displayData = data.get(index).split(",");
            System.out.println(new Student(displayData[0], displayData[1], displayData[2], displayData[3], displayData[4]).getStudentsData());
        } else
            System.out.println("Student ID not found");

        /*File file = new File(fileName);
        Scanner input = new Scanner(file);
        int counter = 1;
        do {
            if(counter==index){
                String[] s;
                s = input.nextLine().split(",");
                for (int i =0;i<s.length;i++)
                    System.out.println(s[i]);
            }
            counter++;
            input.nextLine();
        }while (input.hasNext());
        input.close();*/

    }

    //Done
    public static void displayIDs() {
        for (int i = 0; i < IDs.size(); i++)
            System.out.println(IDs.get(i));
    }
    //Done
    public static void displayNames() {
        for (int i = 0; i < names.size(); i++)
            System.out.println(names.get(i));
    }
    //Done
    public static void displayGrades() {
        for (int i = 0; i < grades.size(); i++)
            System.out.println(grades.get(i));
    }
    //Done
    public static void displayDepartments() {
        for (int i = 0; i < departments.size(); i++)
            System.out.println(departments.get(i));
    }
    //Done
    public static void displayGPA() {
        for (int i = 0; i < GPA.size(); i++)
            System.out.println(GPA.get(i));
    }

}
