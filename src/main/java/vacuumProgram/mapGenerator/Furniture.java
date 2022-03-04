package vacuumProgram.mapGenerator;

public class Furniture {

    // furniture is rectangular
    private int leftX; // leftmost X coordinate
    private int rightX; // leftmost X coordinate
    private int topY;  // top Y coordinate
    private int bottomY;  // top Y coordinate

    public Furniture(int leftX, int rightX, int topY, int bottomY) {
        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.bottomY = bottomY;
    }

    public boolean doesThisFit(Room room) {

        int sizeX = room.getSizeX();
        int sizeY = room.getSizeY();
        boolean[][] blockedPoints = room.getBlockedPoints();

        boolean fits = true;
        // Adjecency is checked, except for walls

        outerloop:
        for (int i = Math.max(leftX - 1, 1); i <= Math.min(rightX + 1, sizeX); i++) {
            for (int j = Math.min(topY + 1, sizeY); j >= Math.max(bottomY - 1, 1); j--) {
                if (blockedPoints[i][j]) {
                    fits = false;
                    break outerloop;
                }
            }
        }

        return fits;
    }

    public void blockSpace(Room room) {
        for (int i = leftX; i <= rightX; i++) {
            for (int j = topY; j >= bottomY; j--) {
                room.blockPoint(i, j);
            }
        }
    }
}
