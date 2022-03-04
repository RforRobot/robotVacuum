package vacuumProgram;

import vacuumProgram.mapGenerator.Generator;
import vacuumProgram.mapGenerator.Room;
import vacuumProgram.vacuum.VacuumCleaner;
import vacuumProgram.vacuum.algorithms.DepthFirstSearch;


public class Main {

    public static void main(String[] args) {

//        Room room = Generator.generate(30,10);
        Room room = Generator.generate(10,10);
        VacuumCleaner vacuumCleaner = placeVacuum(room);

        System.out.println("Starting DFS");

        DepthFirstSearch dfs = new DepthFirstSearch(room,vacuumCleaner);
        dfs.start();

    }

    private static VacuumCleaner placeVacuum(Room room) {

        int sizeX = room.getSizeX();
        int sizeY = room.getSizeY();

        VacuumCleaner vacuumCleaner = new VacuumCleaner();

        outerloop:
        for (int i = 1; i <= sizeX; i++) {
            for (int j = 1; j <= sizeY; j++) {
                if (!room.isPointBlocked(i, j)) {
                    vacuumCleaner = new VacuumCleaner(i, j, new int[]{1, 0});
                    break outerloop;
                }
            }
        }

        return vacuumCleaner;
    }

}
