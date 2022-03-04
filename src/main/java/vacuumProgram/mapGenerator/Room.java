package vacuumProgram.mapGenerator;

import java.util.Arrays;

import static java.lang.Boolean.TRUE;

public class Room {

    private int sizeX;
    private int sizeY;

    private boolean[][] blockedPoints; // is point at coordinates x,y blocked?
    // buffer rows and columns included to represent the wall

    public Room(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.blockedPoints = new boolean[sizeX+2][sizeY+2];
        blockWalls();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean[][] getBlockedPoints() {
        return blockedPoints;
    }


    public boolean isPointBlocked(int x, int y) {
        return blockedPoints[x][y];
    }


    public void blockPoint(int x, int y) {
        blockedPoints[x][y] = true;
    }

    private void blockWalls() {
        Arrays.fill(blockedPoints[0],true);
        Arrays.fill(blockedPoints[sizeX+1],true);

        for(int i = 1; i <= sizeX; i++) {
            blockedPoints[i][0] = true;
            blockedPoints[i][sizeY+1] = true;
        }
    }
}
