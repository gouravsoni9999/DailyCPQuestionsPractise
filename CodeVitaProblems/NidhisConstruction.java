package CodeVitaProblems;

import java.util.*;

class Command {
    int existingCube;
    int newCube;
    String dir; // the first letter of direction given : left, right, up, down

    Command(int existingCube, int newCube, String dir) {
        this.existingCube = existingCube;
        this.newCube = newCube;
        this.dir = dir;
    }
}

class Cube {
    int cubeNo; // the cube no. assigned
    Cube left;
    Cube right;
    Cube down;
    Cube top;

    Cube(int cubeNo) {
        this.cubeNo = cubeNo;
    }

    Cube(int cubeNo, Cube left, Cube right, Cube down, Cube top) {
        this.cubeNo = cubeNo;
        this.left = left;
        this.right = right;
        this.down = down;
        this.top = top;
    }

    void insertAtDir(String dir, Cube cube) {
        if (dir.equals("left")) {
            this.left = cube;
        } else if (dir.equals("right")) {
            this.right = cube;
        } else if (dir.equals("up")) {
            this.top = cube;
        } else {
            this.down = cube;
        }
    }

}

public class NidhisConstruction {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();

        // store commands in cmds
        Command[] cmds = new Command[N];

        for (int i = 0; i < N; i++) {
            int existingCube = scn.nextInt();
            int newCube = scn.nextInt();
            String dir = scn.next();
            scn.nextLine();

            cmds[i] = new Command(existingCube, newCube, dir);
        }

        Arrays.sort(cmds, (a, b) -> {
            if (a.existingCube != b.existingCube) {
                return Integer.compare(a.existingCube, b.existingCube);
            } else {
                // if existingCube values are same, sort acc. to newCube values
                return Integer.compare(a.newCube, b.newCube);
            }
        });

        // we make a cube hashmap, containing cubeNo, Cube relation
        Map<Integer, Cube> map = new HashMap<>();

        // now, we can process commands
        for (int i = 0; i < N; i++) {
            Command cmd = cmds[i];
            int existingCube = cmd.existingCube;
            int newCube = cmd.newCube;
            String dir = cmd.dir;

            if (!map.containsKey(existingCube)) {
                map.put(existingCube, new Cube(existingCube));
            }

            if (!map.containsKey(newCube)) {
                map.put(newCube, new Cube(newCube));
            }

            map.get(existingCube).insertAtDir(dir, map.get(newCube));

        }

        int targetCube = scn.nextInt();
        scn.close();

        Cube cube = map.get(targetCube);

        System.out.print(((cube.top == null) ? -1 : cube.top.cubeNo) + " ");
        System.out.print(((cube.down == null) ? -1 : cube.down.cubeNo) + " ");
        System.out.print(((cube.left == null) ? -1 : cube.left.cubeNo) + " ");
        System.out.print(((cube.right == null) ? -1 : cube.right.cubeNo) + " ");
    }
}
