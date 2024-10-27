import java.util.ArrayList;
import java.util.Scanner;
public class StudentGradeTracker{
    public static void main(String[]args){
        ArrayList<String>names=new ArrayList<>();
        ArrayList<ArrayList<Double>>grades=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("/n1. Add a student");
            System.out.println("2. Enter grades for a student");
            System.out.println("3. Compute statistics for a student");
            System.out.println("4. Compute overall highest and lowest marks");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent(names,grades,sc);
                    break;
                case 2:
                    enterGrades(names,grades,sc);
                    break;
                case 3:
                    computeStatistics(names,grades,sc);
                    break;
                case 4:
                    computeOverallStatistics(names,grades);
                    break;
                case 5:
                    System.out.println("Exiting the program.Goodbye!");
                    sc.close();
                    System.exit(0);
            
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void addStudent(ArrayList<String>names,ArrayList<ArrayList<Double>>grades,Scanner sc){
        System.out.print("Enter student name: ");
        String name=sc.nextLine();
        names.add(name);
        grades.add(new ArrayList<>());
        System.out.println("Student added successfully.");
    }
    private static void enterGrades(ArrayList<String>names,ArrayList<ArrayList<Double>>grades,Scanner sc){
        System.out.print("Enter student name: ");
        String name=sc.nextLine();
        int index=names.indexOf(name);
        if(index==-1){
            System.out.println("Student not found.");
            return;
        }
        ArrayList<Double>studentGrades=grades.get(index);
        while(true){
            System.out.print("Enter grade (or -1 to finish): ");
            double grade=sc.nextDouble();
            if(grade==-1)
            break;
            studentGrades.add(grade);
        }
        System.out.println("Grades entered successfully.");
    }
    private static void computeStatistics(ArrayList<String>names, ArrayList<ArrayList<Double>>grades, Scanner sc){
        System.out.print("Enter student name: ");
        String name=sc.nextLine();
        int index=names.indexOf(name);
        if(index==-1){
            System.out.println("Student not found.");
            return;
        }
        ArrayList<Double>studentGrades=grades.get(index);
        if(studentGrades.isEmpty()){
            System.out.println("No grades entered for this student.");
            return;
        }
        double sum=0;
        double max=studentGrades.get(0);
        double min=studentGrades.get(0);
        for(double grade:studentGrades){
            sum+=grade;
            if(grade>max)
            max=grade;
            if(grade<min)
            min=grade;
        }
        double average=sum/studentGrades.size();
        System.out.printf("Statistics for %s:\n",name);
        System.out.printf("Average: %2f\n",average);
        System.out.printf("Highest: %2f\n",max);
        System.out.printf("Lowest: %2f\n",min);
    }
    private static void computeOverallStatistics(ArrayList<String>names,ArrayList<ArrayList<Double>>grades){
        if(grades.isEmpty()){
            System.out.println("No students or grades in the system.");
            return;
        }
        double overallHighest=Double.MIN_VALUE;
        double overallLowest=Double.MAX_VALUE;
        String highestStudent="";
        String lowestStudent="";
        for(int i=0;i<grades.size();i++){
            ArrayList<Double>studentGrades=grades.get(i);
            if(!studentGrades.isEmpty()){
                for(double grade:studentGrades){
                    if(grade>overallHighest){
                        overallHighest=grade;
                        highestStudent=names.get(i);
                    }
                    if(grade<overallLowest){
                        overallLowest=grade;
                        lowestStudent=names.get(i);
                    }
                }
            }
        }
        if(overallHighest==Double.MIN_VALUE||overallLowest==Double.MAX_VALUE){
            System.out.println("No grades found in the system.");
        }
        else{
            System.out.println("Overall Statistics:");
            System.out.printf("Highest mark: %2f (Student: %s)\n",overallHighest,highestStudent);
            System.out.printf("Lowest mark: %2f (Student: %s)\n",overallLowest,lowestStudent);
        }
    }
}