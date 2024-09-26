/**
 * @author Vanshdeep Singh
 * Stonybrook ID: 116535948
 * Recitation: 01
 */

import com.sun.jdi.InvalidTypeException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menu used to use and navigate HiringTable
 * Commands:
 *(A) Add Applicant <Company> <Applicant Name> <GPA> <College> <Skills> -Adds a new applicant to the end of HiringTable
 *(R) Remove Applicant <Applicant Name> -Removes applicant based on name
 *(G) Get applicant <Name> -Displays the information of the applicant based on the name entered
 *(P) Print List- Prints all of the applicants from HiringTable in table form
 *(RS) Refine Search <Company> <Skill> <College> <GPA>- Returns a list of applicants that meet the search criteria
 *(S) Size- Displays the amount of applicants in the list
 *(B) Backup- Creates a backup of the HiringTable
 *(CB) Compare Backup- Checks if the current HiringTable and the backup are the same
 *(RB) Revert Backup- Reverts HiringTable to backup
 *(Q) Quit -Ends the program
 */
public class HiringSystem {
    static int MAX_COMPANIES = 3;
    static int MAX_SKILLS = 3;
    static HiringTable backup = new HiringTable();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean q = false;
        HiringTable ht = new HiringTable();
        while(q == false) {
            System.out.println("Enter a command: ");
            String command = input.nextLine();
            switch (command.toUpperCase()) {
                case "A":
                    System.out.println("Enter Applicant Name: ");
                    String name = input.nextLine();
                    System.out.println("Enter a GPA: ");
                    boolean valid = false;
                    double gpa = 0;
                    /**
                     * @throws InputMismatchException
                     * If input is not a double, says invalid input and requests new input
                     */
                    do {
                       try {
                           gpa = input.nextDouble();
                           valid = true;
                       }catch (InputMismatchException e){
                           System.out.println("Invalid Input. Enter a double.");
                           valid = false;
                           input.next();
                       }
                    }while(valid == false);
                    System.out.println("Enter Applicant College: ");
                    String college = input.nextLine();
                    college = input.nextLine();
                    String[] companies;
                    companies = new String[MAX_COMPANIES];

                    for(int i = MAX_COMPANIES; i>0; i--){
                        System.out.println("Enter up to " + i +" companies: ");
                        companies[i-1] = input.nextLine();
                        if(companies[i-1].equals("")){
                            break;
                        }
                    }
                    String[] skills;
                    skills = new String[MAX_SKILLS];

                    for(int j = MAX_SKILLS; j>0; j--){
                        System.out.println("Enter up to " + j +" skills: ");
                        skills[j-1] = input.nextLine();
                        if(skills[j-1].equals("")){
                            break;
                        }
                    }
                    Applicant applicant = new Applicant(companies,name,gpa,college,skills);
                    try{
                        ht.addApplicant(applicant);
                    } catch (FullTableException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Applicant "+applicant.getApplicantName() +" has been successfully added to the hiring system.");
                    break;
                case "R":
                    System.out.println("Enter Applicant Name: ");
                    String rName = input.nextLine();
                    try{
                        ht.removeApplicant(rName);
                    }catch(ApplicantNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Applicant "+ rName +" has been successfully removed from the hiring system.");

                    break;
                case "G":
                    System.out.println("Enter Applicant Name: ");
                    String gName = input.nextLine();
                    try{
                        System.out.println(ht.getApplicant(gName));
                    } catch (ApplicantNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "P":
                    ht.printApplicantTable();
                    break;
                case "RS":
                    System.out.println("Enter a company to filter for: ");
                    String company = input.nextLine();
                    System.out.println("Enter a skill to filter for: ");
                    String skillR = input.nextLine();
                    System.out.println("Enter a college to filter for: ");
                    String collegeR = input.nextLine();
                    System.out.println("Enter the minimum GPA to filter for: ");
                    String min = input.nextLine();
                    double minGPA = 0;
                    if(min == null){
                        minGPA = 0;
                    } else if (min.equals("")) {
                        minGPA = 0;
                    } else{
                        minGPA = Double.parseDouble(min);
                    }
                    HiringTable.refineSearch(ht,company,skillR,collegeR,minGPA);

                    break;
                case "S":
                    System.out.println("There are " +ht.size()+ " applicants in the hiring system.");
                    break;
                case "B":
                    backup = new HiringTable();
                    backup = (HiringTable) ht.clone();
                    System.out.println("Successfully created backup");
                    break;
                case "CB":
                    if (backup == null) {
                        System.out.println("No backup exists.");
                    } else {
                        boolean isEqual = true;
                        if(backup.size()!=ht.size()){
                            isEqual = false;
                        } else{
                            for (int i = 0; i < backup.size(); i++) {
                                if (ht.returnsApp(i).equals(backup.returnsApp(i)) == false) {
                                    isEqual = false;
                                }
                                if (isEqual == false) {
                                    break;
                                }
                            }
                        }
                        System.out.println("Current list is " + (isEqual ? "the same" : "not the same ") + " as the backup copy");
                    }

                    break;
                case "RB":
                    if (backup == null) {
                        System.out.println("No backup exists.");
                    } else {
                        ht = backup;
                        System.out.println("Successfully reverted to the backup copy");
                    }
                    break;
                case "Q":
                    q=true;
                    break;
            }
        }
    }
}

