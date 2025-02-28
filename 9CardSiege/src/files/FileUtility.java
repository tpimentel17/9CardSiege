package files;

import java.io.*;

public class FileUtility {

    public static final String FILE_TO_SAVE_GAME = "savedgame.bin";

        // <editor-fold desc="TEXT UI">
    public static void saveGameToFile(Object o) {
        ObjectOutputStream oout = null;

        try {

            oout = new ObjectOutputStream(new FileOutputStream(new File(FILE_TO_SAVE_GAME)));
            oout.writeObject(o);

        } catch (FileNotFoundException ex) {
            System.out.println("[EXCEPTION] File not found!");
        } catch (IOException ex) {
            System.out.println("[EXCEPTION] Problem saving file!");
        } finally {
            if (oout != null) {
                try {
                    oout.close();
                } catch (IOException ex) {
                    System.out.println("[EXCEPTION] Problem closing object output stream!");
                }
            }
        }
    }

    public static Object loadGameFromFile() throws ClassNotFoundException, FileNotFoundException, IOException {
        ObjectInputStream oin = null;
        
        try{
            
        oin = new ObjectInputStream(new FileInputStream(new File(FILE_TO_SAVE_GAME)));
        return oin.readObject();

        }finally{
            if(oin != null)
                oin.close();
        }

    }
    
        // </editor-fold>
    
    public static void saveGameToFile(File file, Object o) throws IOException
    {
        ObjectOutputStream oout = null;

        try{

            oout = new ObjectOutputStream(new FileOutputStream(file));        
            oout.writeObject(o);

        }finally{
            if(oout != null)
                oout.close();
        }
    }

    
    public static Object loadGameFromFile(File file) throws IOException, ClassNotFoundException
    {
        ObjectInputStream oin = null;

        try{

            oin = new ObjectInputStream(new FileInputStream(file));        
            return oin.readObject();

        }finally{
            if(oin != null)
                oin.close();
        }
    }
}
