
package studentmanagmentsystem;
import java.util.ArrayList;
import java.util.Scanner;
public class StudentManagmentSystem {
public static Scanner kbd = new Scanner(System.in);

    public static ArrayList<User> allUser = new ArrayList();
    public static ArrayList<Student> allStudent = new ArrayList();

    public static boolean checkAccount(String number, String password) {
        for (int i = 0; i < allUser.size(); i++) {
            if (number.equals(allUser.get(i).getUN()) && password.equals(allUser.get(i).getPW())) {
                return true;
            }
        }
        return false;
    }

    public static int admin_menu() {
        int menuChoice;
        do {
            System.out.print("\nPlease Choose From the Following Options:"
                    + "\n 1. add ccountant  2. all accountant 3. edit accountant 4. delete accountant  3. Log out\n\n");
            menuChoice = kbd.nextInt();

            if (menuChoice < 1 || menuChoice > 5) {
                System.out.println("error");
            }

        } while (menuChoice < 1 || menuChoice > 5);

        return menuChoice;
    }

    public static void editAccountant() {
        String inputNumber, newPW, isAdmin;
        System.out.println("write accountant number you want to edit password :");
        inputNumber = kbd.next();
        for (int i = 0; i < allUser.size(); i++) {
            if (inputNumber.equals(allUser.get(i).getUN())) {
                System.out.println("write a new password :");
                newPW = kbd.next();
                System.out.println("you want to make this user admin <yes - no> : ");
                isAdmin = kbd.next();
                allUser.set(i, new User(inputNumber, newPW, isAdmin.equals("Yes") || isAdmin.equals("yes") ? true : false));
                break;
            }
        }
    }

    public static void editStudent() {
        String inputNumber, oldPW, newName, du;
        System.out.println("write student number you want to edit :");
        inputNumber = kbd.next();
        for (int i = 0; i < allStudent.size(); i++) {
            if (inputNumber.equals(allStudent.get(i).getID())) {
                System.out.println("write a new name :");
                newName = kbd.next();
                System.out.println("write a new du fee :");
                du = kbd.next();
                allStudent.set(i, new Student(inputNumber, newName, du));
                break;
            }
        }
    }

    public static boolean checkAvailable(String number) {
        for (int i = 0; i < allUser.size(); i++) {
            if (number.equals(allUser.get(i).getUN())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkStudentIfEcist(String number) {
        for (int i = 0; i < allStudent.size(); i++) {
            if (number.equals(allStudent.get(i).getID())) {
                return true;
            }
        }
        return false;
    }

    public static void addNewStudent() {
        boolean flag;
        String stu_num, stu_name, dufee;
        do {
            System.out.println("Student Number: ");
            stu_num = kbd.next();
            System.out.println("Student Name: ");
            stu_name = kbd.next();
            System.out.println("Du feec $: ");
            dufee = kbd.next();
            flag = checkStudentIfEcist(stu_num);
            if (flag == false) {
                allStudent.add(new Student(stu_num, stu_name, dufee));
            } else {
                System.out.println("This student already exist, try again!");
            }
        } while (flag == true);
    }

    public static void addNewAccountant() {
        boolean flag;
        String account_num, account_pw, account_role;
        do {
            System.out.println("Account Number: ");
            account_num = kbd.next();
            System.out.println("Account Passwrds: ");
            account_pw = kbd.next();
            System.out.println("Do you want to make user's is admin <yes or no> : ");
            account_role = kbd.next();
            flag = checkAvailable(account_num);
            if (flag == false) {
                allUser.add(new User(account_num, account_pw, account_role.equals("YES") || account_role.equals("yes") ? true : false));
            } else {
                System.out.println("This account already exist, try again!");
            }
        } while (flag == true);
    }

    public static void deleteStudent() {
        String InputNumber;
        System.out.println("Enter student id you want to delete : ");
        InputNumber = kbd.next();
        for (int i = 0; i < allStudent.size(); i++) {
            if (InputNumber.equals(allStudent.get(i).getID())) {
                allStudent.remove(i);
            }
        }
    }

    public static void deleteAccountant() {
        String InputNumber;
        System.out.println("Enter accountant number you want to delete : ");
        InputNumber = kbd.next();
        for (int i = 0; i < allUser.size(); i++) {
            if (InputNumber.equals(allUser.get(i).getUN())) {
                allUser.remove(i);
            }
        }
    }

    public static int accountant_menu() {
        int menuChoice;
        do {
            System.out.print("\nPlease Choose From the Following Options:"
                    + "\n 1. add students  2. edit students 3. delete students 4. all students  5. Log out\n\n");
            menuChoice = kbd.nextInt();

            if (menuChoice < 1 || menuChoice > 5) {
                System.out.println("error");
            }

        } while (menuChoice < 1 || menuChoice > 5);

        return menuChoice;
    }

    public static void main(String[] args) {
        allUser.add(new User("1111-5", "noor", true));
        allUser.add(new User("2222-5", "noor", false));
        String accountNumber, accountPassword;
        int menuOption = 0;

        boolean isAccountAccepted = false;
        boolean AccountIsAdmin = false;

        do {
            System.out.println("Please Enter Your Account Number: ");
            accountNumber = kbd.next();

            System.out.println("Enter Your Password: ");
            accountPassword = kbd.next();

            isAccountAccepted = checkAccount(accountNumber, accountPassword);

            if (isAccountAccepted) {
                System.out.println("Hello -> " + accountNumber);
            } else {
                System.out.println("error");
            }
        } while (isAccountAccepted == false);

        for (int i = 0; i < allUser.size(); i++) {
            if (accountNumber.equals(allUser.get(i).getUN()) && allUser.get(i).getROLE() == true) {
                AccountIsAdmin = true;
                break;
            } else {
                AccountIsAdmin = false;
            }
        }

        if (AccountIsAdmin) {
            while (menuOption != 5) {
                menuOption = admin_menu();
                switch (menuOption) {
                    case 1:
                        addNewAccountant();
                        break;
                    case 2:
                        for (int i = 0; i < allUser.size(); i++) {
                            System.out.println(allUser.get(i).getUN() + " " + allUser.get(i).getPW() + " " + " " + allUser.get(i).getROLE());
                        }
                        break;
                    case 3:
                        editAccountant();
                        break;
                    case 4:
                        deleteAccountant();
                        break;
                    case 5:
                        System.out.print("\nHave a Nice Day.  Good-Bye!");
                        System.exit(0);
                        break;
                }
            }
        } else {
            while (menuOption != 5) {
                menuOption = accountant_menu();
                switch (menuOption) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        editStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        for (int i = 0; i < allStudent.size(); i++) {
                            System.out.println(allStudent.get(i).getID() + " " + allStudent.get(i).getNAME() + " " + allStudent.get(i).getDUFEE());
                        }
                        break;
                    case 5:
                        System.out.print("\nThank For Using My ATM.  Have a Nice Day.  Good-Bye!");
                        System.exit(0);
                        break;
                }
            }
        }

    }

}

class Student {

    private String STUID, STUNAME, DUFEE;

    public Student(String id, String name, String du) {
        this.STUID = id;
        this.STUNAME = name;
        this.DUFEE = du;
    }

    public String getID() {
        return STUID;
    }

    public void setID(String newID) {
        this.STUID = newID;
    }

    public String getNAME() {
        return STUNAME;
    }

    public void setNAME(String newNAME) {
        this.STUNAME = newNAME;
    }

    public String getDUFEE() {
        return DUFEE;
    }

    public void setDUFEE(String newDUFEE) {
        this.DUFEE = newDUFEE;
    }
}

class User {

    private String UN, PW;
    private boolean ROLE;

    public User(String un, String pw, boolean role) {
        this.UN = un;
        this.PW = pw;
        this.ROLE = role;
    }

    public String getUN() {
        return UN;
    }

    public void setUN(String newUN) {
        UN = newUN;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String newPW) {
        PW = newPW;
    }

    public boolean getROLE() {
        return ROLE;
    }

}


