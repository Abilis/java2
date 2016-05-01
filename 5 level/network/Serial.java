package arhangel.dim.network;

import java.io.*;


public class Serial {

    static class Sample implements Serializable {
        private int version;
        private String name;

        transient int f;

        Sample(int version, String name) {
            this.version = version;
            this.name = name;

            f = 99;
        }

        @Override
        public String toString() {
            return "Sample{" +
                    "version=" + version +
                    ", name='" + name + '\'' +
                    ", f=" + f +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception  {
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Sample ts = new Sample(1, "Test");
        oos.writeObject(ts);
        oos.flush();
        oos.close();


        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);

        Sample restored = (Sample) oin.readObject();
        System.out.println("restored: " + restored.toString());
    }


}
