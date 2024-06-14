package data;

import java.util.*;


public class AldousBroder implements LabyrinthCreator {
    private HashSet<LabyrinthCell> visitedCells = new java.util.HashSet<>();
    @Override
    public void createLabyrinth(Labyrinth l) {
        Random r = new Random();
        l.setCurrent(l.getLabyrinthCell(r.nextInt(0,l.getSize()*l.getSize())));
        visitedCells.add(l.getCurrent());
        while (visitedCells.size() != l.getSize()*l.getSize()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                switch (r.nextInt(0, 4)) {
                    case (0) -> {
                        l.setCurrent(l.getCurrent().getTopWall().getTopNeighbor());
                        if (!visitedCells.contains(l.getCurrent())) {
                            visitedCells.add(l.getCurrent());
                            l.getWallContainer().unlinkLabyrinthWall(l.getCurrent().getBottomWall());
                        }
                    }
                    case (1) -> {
                        l.setCurrent(l.getCurrent().getRightWall().getRightNeighbor());
                        if (!visitedCells.contains(l.getCurrent())) {
                            visitedCells.add(l.getCurrent());
                            l.getWallContainer().unlinkLabyrinthWall(l.getCurrent().getLeftWall());
                        }
                    }
                    case (2) -> {
                        l.setCurrent(l.getCurrent().getBottomWall().getBottomNeighbor());
                        if (!visitedCells.contains(l.getCurrent())) {
                            visitedCells.add(l.getCurrent());
                            l.getWallContainer().unlinkLabyrinthWall(l.getCurrent().getTopWall());
                        }
                    }
                    case (3) -> {
                        l.setCurrent(l.getCurrent().getLeftWall().getLeftNeighbor());
                        if (!visitedCells.contains(l.getCurrent())) {
                            visitedCells.add(l.getCurrent());
                            l.getWallContainer().unlinkLabyrinthWall(l.getCurrent().getRightWall());
                        }
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }
    public ArrayList<LabyrinthCell> getVisited() {
        return new ArrayList<LabyrinthCell>(visitedCells);
    }
}
