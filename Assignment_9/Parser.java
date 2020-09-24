package Assignment_9;
/*  Student information for assignment:
 *
 *  On OUR honor, Pranav Eswaran and Preeth Kanamangala, this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: PVE84
 *  email address: pranave@utexas.edu
 *  Grader name: Amir
 *
 *  Student 2
 *  UTEID: PK9297
 *  email address: preeth@utexas.edu
 *
 */

import java.io.IOException;

public class Parser implements IHuffConstants {
    private static final int EOF = 256;
    private static final int STF_LENGTH = 9;
    private static final int NO_VAL = -1;
    private static final int NO_MORE_BITS = -1;

    private HuffmanTree tree;
    private int stfHeaderLength;
    private int stfBitsToRead;

    //zero argument default constructor required because implementing IHuffConstants
    //zero argument constructor used when parser is used to decompress
    public Parser() {

    }

    //constructor that uses HuffmanTree as param
    //used in compressing and writing SCF/STF
    public Parser(HuffmanTree tree) {
        this.tree = tree;
    }

    //method to write SCF tree encoding
    public int writeSCF(BitOutputStream outputStream) {
        //write freq of every possible byte
        for (int i = 0; i < ALPH_SIZE; i++) {
            outputStream.writeBits(BITS_PER_INT, tree.getFreqs()[i]);
        }

        //return number of bits written
        return BITS_PER_INT * ALPH_SIZE;
    }

    //method to write STF tree encoding
    public int writeSTF(BitOutputStream outputStream) {
        //write 32-bit number representing length of following tree encoding
        outputStream.writeBits(BITS_PER_INT, tree.getSTFHeader().length());
        for (char bit : tree.getSTFHeader().toCharArray()) {
            outputStream.writeBits(1, bit == '0' ? 0 : 1);
        }

        //return length of STF tree encoding, which equals 32 bit int + tree encoding
        return BITS_PER_INT + tree.getSTFHeader().length();
    }

    //method to read SCF when decompressing
    public TreeNode readSCF(BitInputStream inputStream) throws IOException {
        //read every 32-bit number which represents the frequency of a byte
        int[] freqs = new int[ALPH_SIZE + 1];
        for (int i = 0; i < ALPH_SIZE; i++) {
            int freq = inputStream.readBits(BITS_PER_INT);
            //if input ends unexpectedly, return null (other methods catch this and will throw the appropriate error)
            if (freq == -1) {
                return null;
            }

            freqs[i] = freq;
        }

        //EOF frequency is 1
        freqs[EOF] = 1;

        //return root of a huffman tree created from the frequency map
        return (new HuffmanTree(freqs)).getRoot();
    }

    //method to read STF when decompressing
    public TreeNode readSTF(BitInputStream inputStream) throws IOException {
        //get length of following tree encoding, return null (which other methods handle) if input terminates unexpectedly
        stfHeaderLength = inputStream.readBits(BITS_PER_INT);
        stfBitsToRead = stfHeaderLength;
        if (stfHeaderLength == -1) {
            return null;
        }

        //readTree() throws IOException if input terminates unexpectedly; this try-catch then returns null, which another method handles to show the error appropriately
        TreeNode output;
        try {
            output = readTree(inputStream);
        } catch (IOException e) {
            return null;
        }

        if (stfBitsToRead > 0) {
            return null;
        }

        //return root of huffman tree from encoding
        return output;
    }

    //recursive STF parser
    private TreeNode readTree(BitInputStream inputStream) throws IOException {
        //read bit indicating if current node is a leaf or internal node
        int bit = inputStream.readBits(1);
        stfBitsToRead--;
        //throw IOException if input terminates unexpectedly
        if (bit == NO_MORE_BITS || stfBitsToRead < 0) {
            throw new IOException();
        }

        //if internal node, set current node to internal node, recurse on left and right children
        TreeNode root;
        if (bit == 0) {
            root = new TreeNode(NO_VAL, NO_VAL);
            root.setLeft(readTree(inputStream));
            root.setRight(readTree(inputStream));
        } else { //if leaf, read in value of leaf, set current node to this leaf
            int val = inputStream.readBits(STF_LENGTH);
            stfBitsToRead -= STF_LENGTH;
            //throw IOException if input terminates unexpectedly
            if (val == -1 || stfBitsToRead < 0) {
                throw new IOException();
            }

            root = new TreeNode(val, NO_VAL);
        }

        //return root of huffman tree created from encoding
        return root;
    }


    public int getStfHeaderLength() {
        return stfHeaderLength;
    }
}

