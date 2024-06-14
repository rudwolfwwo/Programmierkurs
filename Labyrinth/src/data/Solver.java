package data;

import java.util.Random;

public class Solver {
    static LabyrinthCell start;
    static LabyrinthCell end;
    public Solver (Labyrinth l) {
        Random r = new Random();
        //start = l.getLabyrinthCell(r.nextInt(0,l.getSize()*l.getSize()));
        start = l.getLabyrinthCell(0);
        //end = l.getLabyrinthCell(r.nextInt(0,l.getSize()*l.getSize()));
        end = l.getLabyrinthCell(l.getSize()*l.getSize()-1);
        System.out.println(l.getIndexOfCell(start));
        System.out.println(l.getIndexOfCell(end));
        System.out.println("Eine Seite des Labyrinths hat " + l.getSize() + " Kästchen!");
    }
    public static LabyrinthCell getStart() {
        return start;
    }
    public static LabyrinthCell getEnd() {
        return end;
    }
    public boolean solveLabyrinth (Labyrinth l, LabyrinthCell i) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        l.setCurrent(i);
        l.addToPath(i);
        if (i.equals(end)) return true;
        if (!LabyrinthWallContainer.getInstance().contains(i.getTopWall()) && i.getTopWall() != null) {
            if(!l.getPath().contains(i.getTopWall().getTopNeighbor())) {
                if (solveLabyrinth(l, i.getTopWall().getTopNeighbor())) return true;
            }
        }
        if (!LabyrinthWallContainer.getInstance().contains(i.getRightWall()) && i.getRightWall() != null) {
            if (!l.getPath().contains(i.getRightWall().getRightNeighbor())) {
                if (solveLabyrinth(l, i.getRightWall().getRightNeighbor())) return true;
            }
        }
        if (!LabyrinthWallContainer.getInstance().contains(i.getBottomWall()) && i.getBottomWall() != null) {
            if (!l.getPath().contains(i.getBottomWall().getBottomNeighbor())) {
                if (solveLabyrinth(l, i.getBottomWall().getBottomNeighbor())) return true;
            }
        }
        if (!LabyrinthWallContainer.getInstance().contains(i.getLeftWall()) && i.getLeftWall() != null) {
            if (!l.getPath().contains(i.getLeftWall().getLeftNeighbor())) {
                if (solveLabyrinth(l, i.getLeftWall().getLeftNeighbor())) return true;
            }
        }
        l.removeFromPath(i);
        return false;
    }
}
