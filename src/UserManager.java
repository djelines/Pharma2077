import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class UserManager implements Serializable {
    private static final String USER_FILE = "user.ser";
    private List<User> users = new ArrayList<>();
    private User currentUser = null;

    public void addUser(User user) {
        users.add(user);
    }
    public void removeUser(String username) {
        users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
    }
    public List<User> getUsers() {
        return users;
    }
    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
            System.out.println(Colors.NEON_BLUE + "Users successfully saved." + Colors.RESET);
        } catch (IOException e) {
            System.err.println(Colors.NEON_PINK + "Error saving users: " + e.getMessage() + Colors.RESET);
        }
    }

    @Override
    public void loadData() {
        File userFile = new File(USER_FILE);
        if (!userFile.exists()) {
            System.out.println(Colors.NEON_PINK + "No saved users found, creating default users." + Colors.RESET);
            users.add(new Admin("admin1", "ad"));
            users.add(new Employee("employee1", "em"));
            users.add(new Client("client1", "cl"));
            saveData();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            users = (List<User>) ois.readObject();
            System.out.println(Colors.NEON_BLUE + "Users successfully loaded from saved file." + Colors.RESET);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(Colors.NEON_PINK + "Error loading users: " + e.getMessage() + Colors.RESET);
        }
    }




//    public void authenticateUser() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome! Choose an option:");
//        System.out.println("1. Login");
//        System.out.println("2. Sign up");
//        System.out.print("Enter your choice: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        if (choice == 2) {
//            System.out.print("Enter new username: ");
//            String username = scanner.nextLine();
//            System.out.print("Enter new password: ");
//            String password = scanner.nextLine();
//            Client newUser = new Client(username, password);
//            addUser(newUser);
//            saveData();
//            currentUser = newUser;
//            System.out.println("Sign-up successful! Logged in as " + username);
//        } else {
//            System.out.print("Enter username: ");
//            String username = scanner.nextLine();
//            System.out.print("Enter password: ");
//            String password = scanner.nextLine();
//
//            for (User user : users) {
//                if (user.login(username, password)) {
//                    currentUser = user;
//                    System.out.println("Login successful! Welcome " + username);
//                    return;
//                }
//            }
//            System.out.println("Invalid credentials");
//
//            //need clear
//            PharmacyMenu.displayLogo();
//            authenticateUser();
//        }
//    }


}