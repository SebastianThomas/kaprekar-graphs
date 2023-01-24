package ch.sthomas.math.kaprekar;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int value;
    private Vertex followingVertex;
    private List<Vertex> ingoingVerteces;

    /**
     * Create a new Vertex with a value
     *
     * @param value the Vertex' value
     */
    public Vertex(int value) {
        this.value = value;
        this.ingoingVerteces = new ArrayList<>(1);
    }

    /**
     * Create a new Vertex with a value and a following Vertex
     *
     * @param value           the Vertex' value
     * @param followingVertex the following Vertex
     */
    public Vertex(int value, Vertex followingVertex) {
        this(value);
        this.followingVertex = followingVertex;
    }

    /**
     * Set the following Vertex.
     *
     * @param followingVertex the following Vertex
     */
    public void setFollowingVertex(Vertex followingVertex) {
        this.followingVertex = followingVertex;
    }

    /**
     * Add an ingoing Vertex to the list.
     *
     * @param ingoingVertex the next Vertex to add
     */
    public void addIngoingVertex(Vertex ingoingVertex) {
        this.ingoingVerteces.add(ingoingVertex);
    }

    /**
     * @return the following Vertex
     */
    public Vertex getFollowingVertex() {
        return followingVertex;
    }

    /**
     * @return a {@code List} with all ingoing vertices
     */
    public List<Vertex> getIngoingVerteces() {
        return ingoingVerteces;
    }

    /**
     * @return the own value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return an adequate String representation of the Vertex
     */
    @Override
    public String toString() {
        return "V=" + this.getValue() + "+F=" + this.getFollowingVertex().getValue();
    }
}
