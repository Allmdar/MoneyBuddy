//Do I really need this class???

/* 

// This class is responsible for saving and loading `User` objects to and from a file
// Handles file I/O operations for Money Buddy

import java.io.*; //Imports necessary I/O classes


public class DataManager {
    //Instance Variables

    // This variable stores the name of the file where the user data will be saved.
    private String filename;


    //Initializes the DataManager object with a specific file name
    // The passed filename is assigned to the filename instance variable.
    public DataManager(String filename) {
        this.filename = filename;
    }

    //method takes a User object as an argument and saves it to a file.
    //The method creates an ObjectOutputStream wrapped around a FileOutputStream to write the user object to a file. 
    //If any IOException occurs during the save operation, the method prints the stack trace but does not throw an exception.
    public void saveUserData(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //This method reads a User object from a file and returns it. 
    //The method initializes a User reference to null and creates an ObjectInputStream wrapped around a FileInputStream to read the user object from the file. 
    //The method casts the read object to a User type and assigns it to the user reference.
    //If an IOException or ClassNotFoundException occurs during the load operation, the method prints the stack trace but does not throw an exception. Finally, the user object is returned.

    public User loadUserData() {
        User user = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            user = (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
*/