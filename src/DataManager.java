import java.io.*;


public class DataManager {
    private String filename;

    public DataManager(String filename) {
        this.filename = filename;
    }

    public void saveUserData(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
