package vacuumProgram.vacuum;

import java.util.Arrays;

public class Direction {

    private int[] directionArray;

    public Direction(int[] directionArray) {
        this.directionArray = directionArray;
    }

    public int[] getDirectionArray() {
        return directionArray;
    }

    public void setDirectionArray(int[] directionArray) {
        this.directionArray = directionArray;
    }

    public int[] left() {

        int[] newDirection = new int[2];

        // Applying 90 degree rotation matrix
        newDirection[0] = -directionArray[1];
        newDirection[1] = directionArray[0];

        return newDirection;

    }

    public int[] right() {

        int[] newDirection = new int[2];

        // Applying -90 degree rotation matrix
        newDirection[0] = directionArray[1];
        newDirection[1] = -directionArray[0];

        return newDirection;

    }


    public static int directionArrayToIndex(int[] directionArray) {

        int index = 5;
        if (Arrays.equals(directionArray, new int[]{1, 0})) {
            index = 0;
        } else if (Arrays.equals(directionArray, new int[]{0, 1})) {
            index = 1;
        } else if (Arrays.equals(directionArray, new int[]{-1, 0})) {
            index = 2;
        } else if (Arrays.equals(directionArray, new int[]{0, -1})) {
            index = 3;
        } else {
            System.out.println("Direction error! Direction" + directionArray[0] + "," + directionArray[1]);
            System.exit(0);
        }

        return index;
    }

    public static int[] indexToDirectionArray(int index) {

        int[] directionArray;
        switch (index) {
            case 0:
                directionArray = new int[]{1, 0};
                break;
            case 1:
                directionArray = new int[]{0, 1};
                break;
            case 2:
                directionArray = new int[]{-1, 0};
                break;
            case 3:
                directionArray = new int[]{0, -1};
                break;
            default:
                directionArray = new int[]{2,2};
                System.out.println("Index to Direction conversion error! index" + index);
                System.exit(0);
        }

        return directionArray;
    }

}
