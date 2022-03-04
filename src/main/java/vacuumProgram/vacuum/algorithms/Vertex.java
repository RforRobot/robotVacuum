package vacuumProgram.vacuum.algorithms;

public class Vertex {

    private int xCoord;
    private int yCoord;
    private boolean discovered;
    private Boolean[] accessible;

    public Vertex(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        discovered = false;
    }

    @Override
    public boolean equals(Object obj) {
        if(!obj.getClass().equals(this.getClass())) {return false;}
        Vertex otherVertex = (Vertex) obj;
        if(otherVertex.getXCoord()==xCoord && otherVertex.getYCoord()==yCoord) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String coords = xCoord + "," + yCoord;
        return coords.hashCode();
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public Boolean[] getAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean[] accessible) {
        this.accessible = accessible;
    }
}
