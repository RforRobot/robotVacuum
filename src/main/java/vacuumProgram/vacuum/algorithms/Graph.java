package vacuumProgram.vacuum.algorithms;

import java.util.*;

public class Graph {

    private List<Vertex> vertices = new ArrayList<>();

    public Graph() {
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }


}
