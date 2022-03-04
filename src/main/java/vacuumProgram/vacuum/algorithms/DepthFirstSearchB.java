package vacuumProgram.vacuum.algorithms;

import vacuumProgram.Display;
import vacuumProgram.mapGenerator.Room;
import vacuumProgram.vacuum.Direction;
import vacuumProgram.vacuum.VacuumCleaner;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class DepthFirstSearchB {

    Room room;
    VacuumCleaner vacuumCleaner;

    Graph knownMap;
    Vertex root;
    Stack<Vertex> pathFromRoot;

    public DepthFirstSearchB(Room room, VacuumCleaner vacuumCleaner) {
        this.room = room;
        this.vacuumCleaner = vacuumCleaner;
        knownMap = new Graph();
        pathFromRoot = new Stack<>();
    }

    public void start() {

        root = new Vertex(vacuumCleaner.getXCoord(), vacuumCleaner.getYCoord());
        knownMap.addVertex(root);
        pathFromRoot.add(root);

        while (areAnyUndiscovered()) {

            Display.display(room, vacuumCleaner, knownMap.getVertices());

            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                doDFS();
            } catch (Exception e) {
                System.out.println(pathFromRoot.toString());
            }
        }


    }

    private boolean areAnyUndiscovered() {

        for (Vertex vertex : knownMap.getVertices()) {
            if (!vertex.isDiscovered()) {
                return true;
            }
        }

        return false;
    }

    private void doDFS() {

        Vertex currentVertex = pathFromRoot.peek();
        if (!currentVertex.isDiscovered()) {
            discoverEdges(currentVertex);
        }
        Vertex unvisitedNeighbor = findUnvisitedNeighbor(currentVertex);
        if (unvisitedNeighbor != null) {
            vacuumCleaner.moveTo(unvisitedNeighbor.getXCoord(), unvisitedNeighbor.getYCoord());
            pathFromRoot.add(unvisitedNeighbor);
        } else {
            pathFromRoot.pop();
            vacuumCleaner.moveTo(pathFromRoot.peek().getXCoord(), pathFromRoot.peek().getYCoord());
        }
    }

    private Vertex findUnvisitedNeighbor(Vertex vertex) {

        Vertex unvisitedNeighbor;
        Boolean[] accessible = vertex.getAccessible();

        for (int i = 0; i < 4; i++) {

            int targetXCoord = vertex.getXCoord() + Direction.indexToDirectionArray(i)[0];
            int targetYCoord = vertex.getYCoord() + Direction.indexToDirectionArray(i)[1];

            if (accessible[i]) {
                unvisitedNeighbor = vertexFinder(targetXCoord, targetYCoord);
                if (!unvisitedNeighbor.isDiscovered()) {
                    return unvisitedNeighbor;
                }
            }
        }

        return null;

    }

    private void discoverEdges(Vertex vertex) {

        Boolean[] canMoveArray = new Boolean[4];

        for (int i = 0; i < 4; i++) {

            int targetXCoord = vertex.getXCoord() + Direction.indexToDirectionArray(i)[0];
            int targetYCoord = vertex.getYCoord() + Direction.indexToDirectionArray(i)[1];

            if (!room.isPointBlocked(targetXCoord, targetYCoord)) {
                canMoveArray[i] = true;
                Vertex tempVertex = new Vertex(targetXCoord, targetYCoord);
                if(!knownMap.getVertices().contains(tempVertex)) {
                    knownMap.addVertex(tempVertex);
                }
            } else {
                canMoveArray[i] = false;
            }
        }

        vertex.setAccessible(canMoveArray);
        vertex.setDiscovered(true);

    }

    private Vertex vertexFinder(int x, int y) {

        for (Vertex vertex : knownMap.getVertices()) {
            if (vertex.getXCoord() == x && vertex.getYCoord() == y) {
                return vertex;
            }
        }

        return null;
    }


}
