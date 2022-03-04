package vacuumProgram.mapGenerator;

public class Generator {

    public static Room generate(int sizeX, int sizeY) {

        Room room = new Room(sizeX, sizeY);

        int failedFurnitureGenerations = 0;
        int counter = 0;

        while (failedFurnitureGenerations < 9 && counter < 99) {
            Furniture furniture = generateFurniture(sizeX,sizeY);
            if(furniture.doesThisFit(room)) {
                furniture.blockSpace(room);
            } else {
                failedFurnitureGenerations++;
            }
            counter++;
        }

        return room;
    }

    private static Furniture generateFurniture(int sizeX, int sizeY) {

        int leftX = (int) (Math.random()*sizeX);
        int rightX = Math.min(sizeX,leftX + (int) (Math.random()*sizeX/3));
        int topY = (int) (Math.random()*sizeY);
        int bottomY = Math.max(1,topY - (int) (Math.random()*sizeY/3));

//        System.out.println("(" + leftX + "," + topY + "),(" + rightX + "," + bottomY + ")");

        Furniture furniture = new Furniture(leftX,rightX,topY,bottomY);

        return furniture;
    }

}
