package vacuumProgram;

import vacuumProgram.mapGenerator.Room;
import vacuumProgram.vacuum.VacuumCleaner;
import vacuumProgram.vacuum.algorithms.Vertex;

import java.util.Arrays;
import java.util.List;

public class DisplayB {

    public static void display(Room room, VacuumCleaner vacuumCleaner, List<Vertex> discoveries) {

        int sizeX = room.getSizeX();
        int sizeY = room.getSizeY();
        boolean[][] blockedPoints = room.getBlockedPoints();

        int vacuumX = vacuumCleaner.getXCoord();
        int vacuumY = vacuumCleaner.getYCoord();
        int[] direction = vacuumCleaner.getDirection().getDirectionArray();
        char vacuum = vacuumDirectionToUnicode(direction);


        System.out.println();
        for (int i = sizeY + 1; i >= 0; i--) {
            System.out.println();
            for (int j = 0; j <= sizeX + 1; j++) {
                char aChar = '.';
                if (blockedPoints[j][i]) {
                    aChar = 'X';
                } else {
                    Vertex tempVertex = new Vertex(j, i);
                    if (discoveries.contains(tempVertex)) {
                        Vertex vertex = discoveries.get(discoveries.indexOf(tempVertex));
                        aChar = vertex.isDiscovered() ? ' ' : '.';
                    }
                    if (j == vacuumX && i == vacuumY) {
                         aChar = vacuum;
                    }
                }
                System.out.print(aChar);
            }
        }
    }

    private static char vacuumDirectionToUnicode(int[] direction) {

//        circle: u25CF
//        triangle: u25B2, u25B6, u25C0, u25BC
        char vacuumDisplayChar = ' ';
        if (Arrays.equals(direction, new int[]{0, 1})) {
            vacuumDisplayChar = '\u25B2';
        } else if (Arrays.equals(direction, new int[]{1, 0})) {
            vacuumDisplayChar = '\u25CF'; // triangle: u25B6, circle: u25CF
        } else if (Arrays.equals(direction, new int[]{0, -1})) {
            vacuumDisplayChar = '\u25C0';
        } else if (Arrays.equals(direction, new int[]{-1, 0})) {
            vacuumDisplayChar = '\u25BC';
        } else {
            System.out.println("Direction error! Direction" + direction[0] + "," + direction[1]);
            System.exit(0);
        }

        return vacuumDisplayChar;
    }


}
