import java.io.*;
import java.util.ArrayList;
import java.util.List;


import java.io.Serializable;
 class Chimpanzee implements Serializable {
     private static final long serialVersionUID = 2L;
     private transient String name;
     private transient int age = 10;
     private static char type = 'B';
     { this.age = 14; }
     public String toString() {
         return "Chimpanzee{" +
                 "name='" + name + '\'' +
                 ", age=" + age +
                 ", type=" + type +
                 '}';
     }

    { this.age = 14; }
    public Chimpanzee() {
        this.name = "Unknown";
        this.age = 12;
        this.type = 'Q';
    }
    public Chimpanzee(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }



 }


public class Main {
    public static void main(String[] azrgs) throws IOException, ClassNotFoundException {
        var chimpanzees = new ArrayList<Chimpanzee>();
        chimpanzees.add(new Chimpanzee("Ham", 2, 'A'));
        chimpanzees.add(new Chimpanzee("Enos", 4, 'B'));
        File dataFile = new File("chimpanzee.data");
        saveToFile(chimpanzees, dataFile);
        var chimpanzeesFromDisk = readFromFile(dataFile);
        System.out.println(chimpanzeesFromDisk);


    }

    public static List<Chimpanzee> readFromFile(File dataFile) throws IOException,
            ClassNotFoundException {
        var gorillas = new ArrayList<Chimpanzee>();
        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Chimpanzee)
                    gorillas.add((Chimpanzee) object);
            }
        } catch (EOFException e) {
// File end reached
        }
        return gorillas;
    }
    public static void saveToFile(List<Chimpanzee> gorillas, File dataFile)
            throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            for (Chimpanzee gorilla : gorillas)
                out.writeObject(gorilla);
        }
    }
}
