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

import java.util.HashMap;

public class HuffmanTree implements IHuffConstants {
    //if node has no value, is just a link between two other nodes
    private static final int NO_VAL = -1;
    private static final int RADIX = 2; //base two int encoding
    private static final int EOF_LENGTH = 9;

    private HashMap<Integer, Integer> encodings;
    private int[] freqs;
    private int bitsSaved;
    private String stfHeader;
    private TreeNode root;

    public HuffmanTree(int[] freqs) {
        //init internal freq array
        this.freqs = freqs;

        //fill priority queue with all the frequencies
        PriorityQueue<TreeNode> queue = new PriorityQueue<>();
        for (int i = 0; i <= ALPH_SIZE; i++) {
            if (freqs[i] > 0) {
                queue.enqueue(new TreeNode(i, freqs[i]));
            }
        }

        //build huffman tree by dequeuing two TreeNodes, linking them together, and putting it back in the priority queue
        //until only the root of the huffman tree is in the queue (queue.size() == 1 then)
        while (queue.size() > 1) {
            queue.enqueue(new TreeNode(queue.dequeue(), NO_VAL, queue.dequeue()));
        }

        //init tree root
        root = queue.dequeue();

        //init and fill encodings map with each byte and its new encoding using helper method traverseTree()
        encodings = new HashMap<>();
        traverseTree(root, "");
    }

    public HashMap<Integer, Integer> getEncodings() {
        return encodings;
    }

    public int[] getFreqs() {
        return freqs;
    }

    //method to fill encodings map
    //can use String and not StringBuilder in parameter path because there are a maximum of
    //256 leaves in the huffman tree, and the huffman tree is a full tree, so the maximum height
    //of the huffman tree is not large enough to warrant a StringBuilder
    //basically, string concatenation being O(N) doesn't matter in this case because N is so small, it's negligible
    private void traverseTree(TreeNode node, String path) {
        if (node != null) {
            //if current node is a leaf, put its encoding (stored in path) into the map
            if (node.isLeaf()) {
                //add a leading 1 to the path so leading zeroes are preserved
                encodings.put(node.getValue(), Integer.valueOf("1" + path, RADIX));

                //EOF encoding actually adds length to compressed file because init file did not have EOF val
                if (SimpleHuffProcessor.binLength(node.getValue()) == EOF_LENGTH) {
                    bitsSaved -= path.length();
                } else { //every other encoding saves frequency * (difference in init length and encoding length)
                    bitsSaved += node.getFrequency() * (BITS_PER_WORD - path.length());
                }
            } else {
                //if current node is not a leaf, keep recursing down left and right subtrees to traverse tree fully
                traverseTree(node.getLeft(), path + "0");
                traverseTree(node.getRight(), path + "1");
            }
        }
    }

    public int bitsSaved() {
        return bitsSaved;
    }

    //recursive helper method to write STF tree encoding and store it in a string
    //so later in compress() we can simply call the STF string to write the encoding
    public String getSTFString(TreeNode node, StringBuilder soFar) {
        if (node != null) {
            //encoding is a preorder traversal, so visit current node and then its left and right subtrees
            //if current node is a leaf, add a 1 and a 9-bit integer containing encoding; otherwise, add a 0
            soFar.append(node.isLeaf() ? ("1" + padToNine(Integer.toBinaryString(node.getValue()))) : "0");
            getSTFString(node.getLeft(), soFar);
            getSTFString(node.getRight(), soFar);
        }

        stfHeader = soFar.toString();
        return soFar.toString();
    }

    public String getSTFHeader() {
        return stfHeader;
    }

    //helper method that takes in a binary string and pads it with leading 0s until it is length 9
    //useful for writing specifically 9-bit integers, necessary for writing STF tree encoding
    private String padToNine(String s) {
        //EOF already 9 bits, so just return it
        if (s.length() == EOF_LENGTH) {
            return s;
        }

        return "0" + "0".repeat(Math.max(0, BITS_PER_WORD - s.length())) + s;
    }

    public TreeNode getRoot() {
        return root;
    }
}

