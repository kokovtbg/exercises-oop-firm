package streamsGenerics;

import java.io.*;

public class P_08_SerializeCustomObject {
    public static void main(String[] args) {
        String path = "src/streamsGenerics/output_shape_p08.txt";
        P_08_Shape shape = new P_08_Shape("green", 15.3, 12.4);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            oos.writeObject(shape);
            P_08_Shape shapeFromFile = (P_08_Shape) ois.readObject();
            System.out.println(shapeFromFile.getColor());
            System.out.println(shapeFromFile.getWidth());
            System.out.println(shapeFromFile.getHeight());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
