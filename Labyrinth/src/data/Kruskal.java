package data;

import java.util.ArrayList;

public class Kruskal implements LabyrinthCreator {
    @Override
    public void createLabyrinth(Labyrinth l) {
        ArrayList<ArrayList<LabyrinthCell>> all = new ArrayList<>();
        for (LabyrinthCell cell : l.getLabyrinthCells()) {
            ArrayList<LabyrinthCell> h = new ArrayList<>();
            h.add(cell);
            all.add(h);
        }
        ArrayList<LabyrinthWall> visitedWalls = new  ArrayList<>();

        while (all.size() > 1) {
            LabyrinthWall w = l.getWallContainer().getRandomWall();
            if (!visitedWalls.contains(w)) {
                visitedWalls.add(w);

                LabyrinthCell first;
                LabyrinthCell second;
                if (w.isHorizontal()) {
                    first = w.getTopNeighbor();
                    second = w.getBottomNeighbor();
                } else {
                    first = w.getRightNeighbor();
                    second = w.getLeftNeighbor();
                }
                ArrayList<LabyrinthCell> menge1 = null;
                ArrayList<LabyrinthCell> menge2 = null;

                for (ArrayList<LabyrinthCell> a : all) {
                    if (a.contains(first)) {
                        menge1 = a;
                        break;
                    }
                }
                for (ArrayList<LabyrinthCell> b : all) {
                    if (b.contains(second)) {
                        menge2 = b;
                        break;
                    }
                }
                if (menge1 != menge2 && menge1 != null && menge2 != null) {
                    l.getWallContainer().unlinkLabyrinthWall(w);
                    menge1.addAll(menge2);
                    all.remove(menge2);
                }
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
