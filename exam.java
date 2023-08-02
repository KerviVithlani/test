import java.util.HashMap;
import java.util.Map;

class Spacecraft {
    private int x, y, z;
    private String direction;
    private final Map<String, int[]> directions = new HashMap<>();

    public Spacecraft(int initialX, int initialY, int initialZ, String initialDirection) {
        x = initialX;
        y = initialY;
        z = initialZ;
        direction = initialDirection;

        directions.put("N", new int[]{0, 1, 0});
        directions.put("S", new int[]{0, -1, 0});
        directions.put("E", new int[]{1, 0, 0});
        directions.put("W", new int[]{-1, 0, 0});
        directions.put("Up", new int[]{0, 0, 1});
        directions.put("Down", new int[]{0, 0, -1});
    }

    // Helper function to rotate the spacecraft
    private void rotate(int angle) {
        String[] directionList = {"N", "E", "S", "W", "Up", "Down"};
        int currentIndex = getIndex(directionList, direction);
        int newIndex = (currentIndex + angle + directionList.length) % directionList.length;
        direction = directionList[newIndex];
    }

    private int getIndex(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public void move(String[] commands) {
        for (String command : commands) {
            switch (command) {
                case "f":
                    int[] forward = directions.get(direction);
                    x += forward[0];
                    y += forward[1];
                    z += forward[2];
                    break;
                case "b":
                    int[] backward = directions.get(direction);
                    x -= backward[0];
                    y -= backward[1];
                    z -= backward[2];
                    break;
                case "l":
                    rotate(-1);
                    break;
                case "r":
                    rotate(1);
                    break;
                case "u":
                    rotate(1); // Reversed rotation
                    break;
                case "d":
                    rotate(-1); // Reversed rotation
                    break;
                default:
                    System.out.println("Invalid command: " + command);
            }
        }
    }

    public int[] getFinalPosition() {
        return new int[]{x, y, z};
    }

    public String getFinalDirection() {
        return direction;
    }

    public static void main(String[] args) {
        Spacecraft spacecraft = new Spacecraft(0, 0, 0, "N");
        String[] commands = {"f", "r", "u", "b", "l"};
        spacecraft.move(commands);
        int[] finalPos = spacecraft.getFinalPosition();
        String finalDir = spacecraft.getFinalDirection();
        System.out.println("Final Position: (" + finalPos[0] + ", " + finalPos[1] + ", " + finalPos[2] + ")");
        System.out.println("Final Direction: " + finalDir);
    }
}
