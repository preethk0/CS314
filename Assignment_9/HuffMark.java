package Assignment_9;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class HuffMark {
    protected static JFileChooser ourOpenChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));
    static {
        ourOpenChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    private PrintWriter pw;
    private double myTotalCompressTime;
    private long myTotalUncompressedBytes;
    private long myTotalCompressedBytes;
    
    private IHuffProcessor myHuffer;
    private static String SUFFIX = ".hf";
    private static boolean FAST_READER = true;
    
    public void compress(File f) throws IOException{

        if (f.getName().endsWith(SUFFIX)) return;  // don't read .hf files!
        if (f.isDirectory()) return; // don't read directories
        
        double start = System.currentTimeMillis();
        myHuffer.preprocessCompress(getFastByteReader(f), IHuffProcessor.STORE_COUNTS);
        File outFile = new File(getCompressedName(f));
        FileOutputStream out = new FileOutputStream(outFile);
        pw.println("compressing to: "+outFile.getCanonicalPath());
        myHuffer.compress(getFastByteReader(f), out,true);
        double end = System.currentTimeMillis();
        double time = (end-start)/1000.0;
        
        myTotalUncompressedBytes += f.length();
        myTotalCompressedBytes += outFile.length();
        myTotalCompressTime += time;
        
        pw.printf("%s from\t %d to\t %d in\t %.3f\n",f.getName(),f.length(),outFile.length(),time);
        double percentCompression = (double)(f.length() - outFile.length()) / f.length();
        pw.println("percent compressed: " + percentCompression + "%");
    }
    
    public void doMark() throws IOException{
        if (myHuffer == null){
            myHuffer = new SimpleHuffProcessor();
        }
        int action = ourOpenChooser.showOpenDialog(null);
        if (action == JFileChooser.APPROVE_OPTION){
            File dir = ourOpenChooser.getSelectedFile();
            pw = new PrintWriter(new File(dir.getName() + ".txt"));
            File[] list = dir.listFiles();
            for(File f : list){
                compress(f);
            }
            pw.println("--------");
            pw.printf("total bytes read: %d\n",myTotalUncompressedBytes);
            pw.printf("total compressed bytes %d\n", myTotalCompressedBytes);
            pw.printf("total percent compression %.3f\n",100.0* (1.0 - 1.0*myTotalCompressedBytes/myTotalUncompressedBytes));
            pw.printf("compression time: %.3f\n",myTotalCompressTime);
            pw.close();
        }
    }
    
    public static void main(String[] args) throws IOException{
        HuffMark hf = new HuffMark();
        hf.doMark();
    }
    
    
    private String getCompressedName(File f){
        String name = f.getName();
        String path = null;
        try {
            path = f.getCanonicalPath();
        } catch (IOException e) {
            System.err.println("trouble with file canonicalizing "+f);
            return null;
        }
        int pos = path.lastIndexOf(name);
        String newName = path.substring(0, pos) + name + SUFFIX;
        return newName;
    }
    
    
    private InputStream getFastByteReader(File f) throws FileNotFoundException{
        
        if (!FAST_READER){
            return new FileInputStream(f);
        }
        
        ByteBuffer buffer = null;
         try {
             FileChannel channel = new FileInputStream(f).getChannel();
             buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
             byte[] barray = new byte[buffer.limit()];
           
             if (barray.length != channel.size()){               
                 System.err.println(String.format("Reading %s error: lengths differ %d %ld\n",f.getName(),barray.length,channel.size()));
             }
             buffer.get(barray);
             return new ByteArrayInputStream(barray);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
    }
    
}
