import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Cell[][] world = new Cell[9][9];
    public static PriorityQueue<Cell> queue = new PriorityQueue<>(Cell::compareTo);
    public static int res = -1;
    public static Cord[] moves = {new Cord(1, 0), new Cord(0, 1), new Cord(-1, 0), new Cord(0, -1)};

    public static void main(String[] args) {
        // read the first input data
        // var - variant of thanos zone
        // (xInf, yInf) - coordinate of infinity stone
        int var = scanner.nextInt();
        int xInf = scanner.nextInt();
        int yInf = scanner.nextInt();

        // the beginning of A* algorithm
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                world[i][j] = new Cell(i, j);
            }
        world[0][0].g = 0;
        world[0][0].h = calcH(0, 0, xInf, yInf);
        world[0][0].type = CellType.EMPTY;
        queue.add(world[0][0]);
        Cord current = new Cord(0, 0);

        // main part of the A* algorithm
        while (!queue.isEmpty()) {
            Cell temp = queue.peek();
            makeTransition(current, temp.cord);
            current = temp.cord;
            if ((temp.cord.x == xInf) && (temp.cord.y == yInf)) {
                res = (world[temp.cord.x][temp.cord.y].g + world[temp.cord.x][temp.cord.y].h);
                break;
            }
            queue.remove(temp);
            world[temp.cord.x][temp.cord.y].used = true;

            for (var move: moves) {
                Cord v = new Cord(temp.cord.x + move.x, temp.cord.y + move.y);
                if (v.x < 0 || v.y < 0 || v.x > 8 || v.y > 8)
                    continue;
                if (world[v.x][v.y].used && world[v.x][v.y].g + world[v.x][v.y].h >= world[temp.cord.x][temp.cord.y].g)
                    continue;
                if (!world[v.x][v.y].used || world[v.x][v.y].g + world[v.x][v.y].h < world[temp.cord.x][temp.cord.y].g) {
                    queue.remove(world[v.x][v.y]);
                    world[v.x][v.y].parent = temp.cord;
                    world[v.x][v.y].g = world[temp.cord.x][temp.cord.y].g + 1;
                    world[v.x][v.y].h = calcH(v.x, v.y, xInf, yInf);
                    if (!world[v.x][v.y].used && isValidCell(new Cord(v.x, v.y)))
                        queue.add(world[v.x][v.y]);
                }
            }
        }
        System.out.println("e " + res);
    }

    /**
     * this function is necessary to avoid teleportation on the map, in fact, in order to make any transition,
     * we perform a return to the starting point and a transition from it to the desired one
     * @param from coordinate of cell from which we want to make transition
     * @param to coordinate of cell we want to come to
     */
    static void makeTransition(Cord from, Cord to) {
        if (!((from.x == 0) && (from.y == 0))) {
            while (true) {
                from = world[from.x][from.y].parent;
                query(from);
                if (from.x == 0 && from.y == 0)
                    break;
            }
        }
        Cord cur = null;
        int mn = 10000000;
        for (var move: moves) {
            Cord temp = new Cord(to.x + move.x, to.y + move.y);
            if (temp.x < 0 || temp.y < 0 || temp.x > 8 || temp.y > 8)
                continue;
            if (world[temp.x][temp.y].used && world[temp.x][temp.y].h + world[temp.x][temp.y].g < mn) {
                cur = temp;
                mn = world[temp.x][temp.y].h + world[temp.x][temp.y].g;
            }
        }
        ArrayList<Cord>cells = new ArrayList<>();
        cells.add(to);
        if (cur != null) {
            while (cur.x != 0 || cur.y != 0) {
                cells.add(cur);
                cur = world[cur.x][cur.y].parent;
            }
        }
        for (int i = cells.size() - 1; i >= 0; i--)
            query(cells.get(i));
    }

    /**
     * check the valid cell or not by type
     * @param cord - coordinate of the cell
     * @return true - if cell is valid and false - otherwise
     */
    static boolean isValidCell(Cord cord){
        return (world[cord.x][cord.y].type == CellType.SHIELD || world[cord.x][cord.y].type == CellType.EMPTY || world[cord.x][cord.y].type == CellType.INFINITY);
    }

    /**
     * A function that executes a request sent to the descriptor
     * @param cord - coordinates of the cell where we make the transition
     */
    static void query(Cord cord) {
        System.out.println("m " + cord.x + " " + cord.y);
        int quantity = scanner.nextInt();
        for (int i = 0; i < quantity; i++) {
            int xx = scanner.nextInt();
            int yy = scanner.nextInt();
            char type = scanner.next().charAt(0);
            boolean b = false;
            if (queue.contains(world[xx][yy])) {
                queue.remove(world[xx][yy]);
                b = true;
            }
            world[xx][yy].type = CellType.howType(type);
            if (b)
                queue.add(world[xx][yy]);
        }
    }

    /**
     * function which calculates h function for cell
     * @param x x-coordinate of cell
     * @param y y-coordinate of cell
     * @param x1 x-coordinate of infinity stone cell
     * @param y1 y-coordinate of infinity stone cell
     * @return manhattan distance between these two coordinates
     */
    static int calcH(int x, int y, int x1, int y1) {
        return Math.abs(x - x1) + Math.abs(y - y1);
    }

    /**
     * class for coordinates
     */
    static class Cord {
        public int x;
        public int y;

        public Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Class for table cells
     * cord - coordinates of cell
     * parent - coordinate of from which we came
     * used - boolean flag, used cell or not
     * type - type of cell
     */
    static class Cell implements Comparable<Cell> {
        public Cord cord = new Cord(0, 0);
        public Cord parent = new Cord(-1, -1);
        public boolean used = false;
        public CellType type = CellType.EMPTY;
        public int h = 100000, g = 100000;

        public Cell(int x, int y) {
            this.cord = new Cord(x, y);
        }

        /**
         * The function needed to compare two cells
         * @param o the object to be compared.
         * @return a numeric value that reflects the priority of cells
         */
        @Override
        public int compareTo(Cell o) {
            if (this.h + this.g < o.h + o.g)
                return -1;
            if (this.h + this.g > o.h + o.g)
                return 1;
            return 0;
        }
    }

    /**
     * classification of cells by type
     */
    public enum CellType {
        PERCEPTION,
        HULK,
        INFINITY,
        THOR,
        MARVEL,
        SHIELD,
        EMPTY;

        /**
         * A function that returns the tile type by the received letter
         * Example: 'P' -> CellType.PERCEPTION
         */
        static CellType howType(char c) {
            switch (c) {
                case 'P':
                    return PERCEPTION;
                case 'I':
                    return INFINITY;
                case 'M':
                    return MARVEL;
                case 'T':
                    return THOR;
                case 'S':
                    return SHIELD;
                case 'H':
                    return HULK;
                default:
                    throw new IllegalStateException("Unexpected value: " + c);
            }
        }
    }
}
