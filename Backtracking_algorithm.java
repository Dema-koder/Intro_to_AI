import java.util.*;
public class Main {
    public static int[][] used = new int[10][10];
    public static int[][] table = new int[10][10];
    public static int variant;
    public static Point infinityStone;
    public static Scanner scanner = new Scanner(System.in);
    public static Point[] ThanosZone1 = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, -1), new Point(-1, 1),
            new Point(1, -1), new Point(1, 1)};
    public static Point[] ThanosZone2 = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, -1), new Point(-1, 1),
            new Point(1, -1), new Point(1, 1), new Point(-2, -2), new Point(2, 2), new Point(-2, 2), new Point(2, -2)};
    public static Point[] HulkZones = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1)};
    public static Point[] ThorZones = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, -1), new Point(-1, 1),
            new Point(1, -1), new Point(1, 1)};
    public static Point[] MarvelZones = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, -1), new Point(-1, 1),
            new Point(1, -1), new Point(1, 1), new Point(-2, 0), new Point(2, 0), new Point(0, -2), new Point(0, 2)};
    public static Point[] moves = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1)};

    record Point(int x, int y) {
        boolean isValidPoint() {
            return (x >= 0 && x < 9 && y >= 0 && y < 9);
        }
    }

    static class Pair<L, R> {
        private final L first;
        private final R second;

        public Pair(L x, R y) {
            this.first = x;
            this.second = y;
        }
    }

    static void printAnswer() {
        int[][] dist = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;
        Set<Pair<Integer, Pair<Integer, Integer>>> set = new HashSet<>();
        set.add(new Pair<>(0, new Pair<>(0, 0)));
        while (!set.isEmpty()) {
            Pair<Integer, Pair<Integer, Integer>> v = set.iterator().next();
            set.remove(v);
            for (Point i : moves) {
                Point p = new Point(v.second.first + i.x, v.second.second + i.y);
                if (p.isValidPoint() && canGo(p)) {
                    if (dist[p.x][p.y] > dist[v.second.first][v.second.second] + 1) {
                        set.remove(new Pair<>(dist[p.x][p.y], new Pair<>(p.x, p.y)));
                        dist[p.x][p.y] = dist[v.second.first][v.second.second] + 1;
                        set.add(new Pair<>(dist[p.x][p.y], new Pair<>(p.x, p.y)));
                    }
                }
            }
        }
        if (dist[infinityStone.x][infinityStone.y] == Integer.MAX_VALUE) {
            System.out.println("e -1");
        } else {
            System.out.println("e " + dist[infinityStone.x][infinityStone.y]);
        }
    }


    static void getMapInfo(Point now, Point par) {
        used[now.x][now.y] = 1;
        for (var i: moves) {
            Point next = new Point(now.x + i.x, now.y + i.y);
            if (next.isValidPoint() && canGo(next) && used[next.x][next.y] == 0) { /////
                move(next);
                getMapInfo(next, now);
            }
        }
        if (!(par.x == -1 && par.y == -1)) {
            move(par);
        }
    }

    static boolean canGo(Point cur) {
        return (table[cur.x][cur.y] == 0 || table[cur.x][cur.y] == 1);
    }

    public static void BackTracking() {
        variant = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        infinityStone = new Point(x, y);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                used[i][j] = 0;
                table[i][j] = -1;
            }
        }
        table[infinityStone.x()][infinityStone.y()] = 1;
        table[0][0] = 0;
        move(new Point(0, 0));
    }

    static void defineZones(Point p, Point[] zone, int type) {
        for (var i: zone) {
            Point cur = new Point(p.x + i.x, p.y + i.y);
            if (cur.isValidPoint()) {
                if (table[cur.x][cur.y] != 7)
                    table[cur.x][cur.y] = type;
            }
        }
    }

    static void move(Point pos) {
        System.out.println("m " + pos.x + " " + pos.y);
        int quantity = scanner.nextInt();
        for (int i = 0; i < quantity; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            char type = scanner.next().charAt(0);
            if (type == 'P')
                table[x][y] = 8;
            else if (type == 'H') {
                table[x][y] = 2;
                defineZones(new Point(x, y), HulkZones, 6);
            } else if (type == 'T') {
                table[x][y] = 3;
                defineZones(new Point(x, y), ThorZones, 6);
            } else if (type == 'M') {
                table[x][y] = 4;
                defineZones(new Point(x, y), MarvelZones, 7);
            }
        }
        for (var i : (variant == 1 ? ThanosZone1 : ThanosZone2)) {
            Point cur = new Point(pos.x + i.x, pos.y + i.y);
            if (cur.isValidPoint() && table[cur.x][cur.y] == -1) {
                table[cur.x][cur.y] = 0;
            }
        }
    }

    public static void main(String[] args) {
        BackTracking();
        getMapInfo(new Point(0, 0), new Point(-1, -1));
        printAnswer();
    }

}
