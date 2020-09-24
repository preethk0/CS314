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
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleHuffProcessor implements IHuffProcessor {
    private static final int NO_MORE_BITS = -1;
    private static final int ERROR = -1;

    private IHuffViewer myViewer;
    private HuffmanTree tree;
    private int totalBitsSaved;
    private int headerFormat;
    private Parser parser = new Parser();

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     *
     * @param in           is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     *                     header to use, standard count format, standard tree format, or
     *                     possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        this.headerFormat = headerFormat;
        BitInputStream inputStream = new BitInputStream(in);
        tree = new HuffmanTree(count(inputStream));
        parser = new Parser(tree);
        // this is the total bits saved irrespective of the format
        totalBitsSaved = tree.bitsSaved() - binLength(MAGIC_NUMBER) - binLength(headerFormat);

        // this takes into account the header format
        totalBitsSaved -= headerFormat == STORE_COUNTS ? (ALPH_SIZE * BITS_PER_INT) : tree.getSTFString(tree.getRoot(), new StringBuilder()).length() + BITS_PER_INT;
        inputStream.close();
        return totalBitsSaved;
    }

    // utility function to return the length of a number in binary
    public static int binLength(int num) {
        return Integer.toBinaryString(num).length();
    }

    // helper method to count frequencies
    private int[] count(BitInputStream in) throws IOException {
        int[] freqs = new int[ALPH_SIZE + 1];
        int input = in.readBits(BITS_PER_WORD);
        // while there is input left to read in, increment that input's freq
        while (input != NO_MORE_BITS) {
            freqs[input]++;
            input = in.readBits(BITS_PER_WORD);
        }

        freqs[PSEUDO_EOF] = 1;
        return freqs;
    }

    /**
     * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     *
     * @param in    is the stream being compressed (NOT a BitInputStream)
     * @param out   is bound to a file/stream to which bits are written
     *              for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     *              If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     *                     writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        // check precon
        if (tree == null) {
            throw new IllegalStateException("Pre-processing required before compression.");
        }

        int outputLength = 0;
        if (force || totalBitsSaved > 0) {
            BitOutputStream outputStream = new BitOutputStream(out);
            outputStream.writeBits(BITS_PER_INT, MAGIC_NUMBER);
            outputStream.writeBits(BITS_PER_INT, headerFormat);
            outputLength += BITS_PER_INT + BITS_PER_INT;
            // we increment output length depending on what the header format is
            outputLength += headerFormat == STORE_COUNTS ? parser.writeSCF(outputStream) : parser.writeSTF(outputStream);

            BitInputStream inputStream = new BitInputStream(in);
            outputLength = writeCompressedFile(outputLength, outputStream, inputStream);
            // note that we subtract one because of the leading 1 that we insert in our implementation of the compression
            int eofEncodingLength = binLength(tree.getEncodings().get(PSEUDO_EOF)) - 1;
            int eofEncoding = tree.getEncodings().get(PSEUDO_EOF);
            outputStream.writeBits(eofEncodingLength, eofEncoding);
            outputLength += eofEncodingLength;

            outputStream.close();
            inputStream.close();
        }
        return outputLength;
    }

    // this helper method gets the encodings from our tree and writes them to our compressed file
    private int writeCompressedFile(int outputLength, BitOutputStream outputStream, BitInputStream inputStream) throws IOException {
        int input = inputStream.readBits(BITS_PER_WORD);
        while (input != NO_MORE_BITS) {
            // get the encoding and its length for each input and write it to our output
            int encodingLength = binLength(tree.getEncodings().get(input)) - 1;
            int encoding = tree.getEncodings().get(input);
            outputStream.writeBits(encodingLength, encoding);
            outputLength += encodingLength;

            input = inputStream.readBits(BITS_PER_WORD);
        }
        return outputLength;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     *
     * @param in  is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     *                     writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
        BitInputStream inputStream = new BitInputStream(in);


        if (throwError(inputStream.readBits(BITS_PER_INT) != MAGIC_NUMBER, "Error reading compressed file. \nFile did not start with the huff magic number.")) {
            return ERROR;
        }
        int headerFormat = inputStream.readBits(BITS_PER_INT);
        if (throwError(headerFormat == NO_MORE_BITS, "Error reading compressed file.\nFile did not have a valid header format number.")) {
            return ERROR;
        }

        // we initialize the treeNode root depending on the header format
        TreeNode root = headerFormat == STORE_COUNTS ? parser.readSCF(inputStream) : parser.readSTF(inputStream);
        if (root == null) {
            myViewer.showError("Error parsing " + (headerFormat == STORE_COUNTS ? "SCF" : "STF") + " encoding.");
            return ERROR;
        }
        int input = inputStream.readBits(1);
        if (throwError(input == NO_MORE_BITS, "Error reading compressed file. \nFile's content not found.")) {
            return ERROR;
        }

        TreeNode current = input == 0 ? root.getLeft() : root.getRight();
        // we keep track of the bits written and read RESPECTIVELY with this int array
        int[] bitsWrittenRead = {0, 1 + parser.getStfHeaderLength()};

        BitOutputStream outputStream = new BitOutputStream(out);
        if (writeUncompressedFile(inputStream, outputStream, root, current, bitsWrittenRead)) {
            return ERROR;
        }
        // this is where we read in the remaining bits (if any) and check if it is valid
        input = inputStream.readBits(BITS_PER_WORD - bitsWrittenRead[1] % BITS_PER_WORD);
        if (throwError(input != 0 && bitsWrittenRead[1] % BITS_PER_WORD != 0, "Error reading compressed file.\nFile has content after pseudo-EOF encoding")) {
            return ERROR;
        }

        inputStream.close();
        outputStream.close();
        return bitsWrittenRead[0];
    }


    // this helper method just throws an error message contingent upon the invalid boolean
    private boolean throwError(boolean invalid, String errorMessage) {
        if (invalid) {
            myViewer.showError(errorMessage);
            return true;
        }
        return false;
    }

    // this helper method writes out the uncompressed file based on the tree we made while keeping track of bits written and read
    private boolean writeUncompressedFile(BitInputStream inputStream, BitOutputStream outputStream, TreeNode root, TreeNode current, int[] bitsWrittenRead) throws IOException {
        // iterate through the tree until we reach the pseudo eof value
        while (current != null && current.getValue() != PSEUDO_EOF) {
            // when we reach a leaf we want to write its value to our file
            if (current.isLeaf()) {
                outputStream.writeBits(BITS_PER_WORD, current.getValue());
                bitsWrittenRead[0] += BITS_PER_WORD;
            }

            int input = inputStream.readBits(1);
            bitsWrittenRead[1]++;
            if (throwError(input == NO_MORE_BITS, "Error reading compressed file. \nFile's content ended unexpectedly."))
                return true;
            if (current.isLeaf()) {
                current = root;
            }
            // move onto the appropriate node: 0 for left and 1 for right
            current = input == 0 ? current.getLeft() : current.getRight();
        }
        return false;
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s) {
        if (myViewer != null)
            myViewer.update(s);
    }
}