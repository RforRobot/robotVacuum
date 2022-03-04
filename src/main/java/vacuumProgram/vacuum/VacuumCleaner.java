package vacuumProgram.vacuum;

import vacuumProgram.mapGenerator.Room;

public class VacuumCleaner {

    private int xCoord;
    private int yCoord;

    private Direction direction;

    public VacuumCleaner() {
    }

    public VacuumCleaner(int xCoord, int yCoord, int[] directionArray) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        direction = new Direction(directionArray);
    }


    public boolean isPathAheadClear(Room room) {
        int xAhead = xCoord + direction.getDirectionArray()[0];
        int yAhead = yCoord + direction.getDirectionArray()[1];
        return !room.isPointBlocked(xAhead, yAhead);
    }


    public void move() {
        xCoord += direction.getDirectionArray()[0];
        yCoord += direction.getDirectionArray()[1];
    }

    public void turnLeft() {

        direction.setDirectionArray(direction.left());

    }

    public void turnRight() {

        direction.setDirectionArray(direction.right());

    }

    public void moveTo(int x, int y) {
        orient(x,y);
        move();
    }

    public void moveToB(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    private void orient(int targetX, int targetY) {

        int[] targetDirectionArray = new int[2];
        targetDirectionArray[0] = targetX - xCoord;
        targetDirectionArray[1] = targetY - yCoord;

        // draw to directionArrayToIndex mapping to understand the next line
        int rightTurnsNeeded = (Direction.directionArrayToIndex(direction.getDirectionArray()) - Direction.directionArrayToIndex(targetDirectionArray) + 4) % 4;

        switch (rightTurnsNeeded) {
            case 1:
                turnRight();
                return;
            case 2:
                turnRight();
                turnRight();
                return;
            case 3:
                turnLeft();
                return;
            default:
                return;
        }

    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public Direction getDirection() {
        return direction;
    }

}
