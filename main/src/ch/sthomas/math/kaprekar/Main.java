package ch.sthomas.math.kaprekar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static final int BASE = 10;
    public static final int PLACES = 4;

    public static void main(String[] args) {
        int[] ints = generateAllNumbers();
        Vertex[] vs = new Vertex[(int) Math.pow(BASE, PLACES)];

        doKaprekarAlgorithmFor(ints, vs);

        int[] directCycles = getDirectCycles(vs);
        System.out.println("Direct cycles (Kaprekar-constants): " + collectIntArray(directCycles));

        int[] multipleIngoingVertices = getMultipleIngoingVertices(vs);
        System.out.println("Multiple ingoing Vertices have: " + collectIntArray(multipleIngoingVertices));
    }

    /**
     * Get the numbers which are pointing to themselves.
     *
     * @param vs the Vertices to check
     * @return the number values of the vertices pointing to themselves
     */
    public static int[] getDirectCycles(Vertex[] vs) {
        List<Vertex> directCycles = new ArrayList<>();
        for (Vertex v : vs) {
            if (v.getFollowingVertex() == v) directCycles.add(v);
        }

        return directCycles.stream().mapToInt(Vertex::getValue).toArray();
    }

    /**
     * Get the numbers which have multiple ingoing vertices.
     *
     * @param vs the vertices to check
     * @return the number values of the vertices that have multiple ingoing vertices.
     */
    public static int[] getMultipleIngoingVertices(Vertex[] vs) {
        List<Vertex> multipleIngoingVertices = new ArrayList<>();
        for (Vertex v : vs) {
            if (v.getIngoingVerteces().size() > 1) multipleIngoingVertices.add(v);
        }

        return multipleIngoingVertices.stream().mapToInt(Vertex::getValue).toArray();
    }

    /**
     * Loop over all the ints, calculate their respective kaprekar-number, create Vertices for the number i and the next
     * number and store them at their respective place in vs.
     *
     * @param ints the numbers (preferably continuous) to create Vertices for
     * @param vs   the array into which all newly created vertices are being stored, should have the same size as {@code ints}
     */
    public static void doKaprekarAlgorithmFor(int[] ints, Vertex[] vs) {
        for (int i : ints) {
            int next = calcNextKaprekar(i);

            if (vs[i] == null) vs[i] = new Vertex(i);
            if (vs[next] == null) vs[next] = new Vertex(next);

            vs[i].setFollowingVertex(vs[next]);
            vs[next].addIngoingVertex(vs[i]);
        }
    }

    /**
     * Calculate the next kaprekar-number, so {@code ascending ordered} - {@code descending ordered}
     *
     * @param i the number to calculate kaprekar-number for
     * @return the next kaprekar-number
     */
    public static int calcNextKaprekar(int i) {
        return toInt(sortDesc(toString(i))) - toInt(sortAsc(toString(i)));
    }

    /**
     * Generate all continuous numbers between 0 and {@code BASE}^{@code PLACES}-1.
     *
     * @return an array containing all the numbers
     */
    public static int[] generateAllNumbers() {
        int maxNr = (int) Math.pow(BASE, PLACES);
        int[] ints = new int[maxNr];

        for (int i = 0; i < maxNr; i++) {
            ints[i] = i;
        }

        return ints;
    }

    /**
     * Return the string of value {@code i}
     *
     * @param i the number to convert to string
     * @return the string
     */
    public static String toString(int i) {
        return String.format("%0" + PLACES + "d", i);
    }

    /**
     * Sort a string in ascending order.
     *
     * @param s the String to sort
     * @return the sorted string
     */
    public static String sortAsc(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    /**
     * Sort a string in descending order.
     *
     * @param s the String to sort
     * @return the sorted String
     */
    public static String sortDesc(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        s = new String(c);
        return reverse(s);
    }

    /**
     * Reverse the given String.
     *
     * @param s the String to reverse
     * @return the reversed String
     */
    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Convert an int to a String.
     *
     * @param s the String to convert
     * @return the converted String
     * @throws NumberFormatException if {@code s} is not a number
     */
    public static int toInt(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }

    /**
     * Collect ints to a String joined with a comma.
     *
     * @param ints the ints to join
     * @return the aggregated String
     */
    public static String collectIntArray(int[] ints) {
        return Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(", "));
    }
}