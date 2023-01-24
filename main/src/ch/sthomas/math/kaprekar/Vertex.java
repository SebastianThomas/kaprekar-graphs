package ch.sthomas.math.kaprekar;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int value;
    private Vertex followingVertex;
    private List<Vertex> ingoingVerteces;

    public Vertex(int value) {
        this.value = value;
        this.ingoingVerteces = new ArrayList<>(1);
    }

    public Vertex(int value, Vertex followingVertex) {
        this(value);
        this.followingVertex = followingVertex;
    }

    public void setFollowingVertex(Vertex followingVertex) {
        this.followingVertex = followingVertex;
    }

    public void addIngoingVertex(Vertex ingoingVertex) {
        this.ingoingVerteces.add(ingoingVertex);
    }

    public Vertex getFollowingVertex() {
        return followingVertex;
    }

    public List<Vertex> getIngoingVerteces() {
        return ingoingVerteces;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "V=" + this.getValue() + "+F=" + this.getFollowingVertex().getValue();
    }
}
