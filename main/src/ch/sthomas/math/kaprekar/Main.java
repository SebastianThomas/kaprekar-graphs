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
        System.out.println(Arrays.stream(directCycles).mapToObj(String::valueOf).collect(Collectors.joining(", ")));
    }

    public static int[] getDirectCycles(Vertex[] vs) {
        List<Vertex> directCycles = new ArrayList<>();
        for (Vertex v : vs) {
            if (v.getFollowingVertex() == v) directCycles.add(v);
        }

        return directCycles.stream().mapToInt(Vertex::getValue).toArray();
    }

    public static void doKaprekarAlgorithmFor(int[] ints, Vertex[] vs) {
        for (int i : ints) {
            int next = calcNextKaprekar(i);

            if (vs[i] == null) vs[i] = new Vertex(i);
            if (vs[next] == null) vs[next] = new Vertex(next);

            vs[i].setFollowingVertex(vs[next]);
            vs[next].addIngoingVertex(vs[i]);
        }
    }

    public static int calcNextKaprekar(int i) {
        return toInt(sortDesc(toString(i))) - toInt(sortAsc(toString(i)));
    }

    public static int[] generateAllNumbers() {
        int maxNr = (int) Math.pow(BASE, PLACES);
        int[] ints = new int[maxNr];

        for (int i = 0; i < maxNr; i++) {
            ints[i] = i;
        }

        return ints;
    }

    public static String toString(int i) {
        return String.format("%0" + PLACES + "d", i);
    }

    public static String sortAsc(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public static String sortDesc(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        s = new String(c);
        return reverse(s);
    }

    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static int toInt(String s) throws NumberFormatException {
        return Integer.parseInt(s);
    }
}