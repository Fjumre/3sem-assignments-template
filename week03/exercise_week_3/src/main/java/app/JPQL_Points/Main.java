package app.JPQL_Points;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        PointDAO pointDAO= new PointDAO();


        List<Point> points = pointDAO.retrieveAllPoints();
        for (Point point : points) {
            System.out.println(point);
        }

        double averageX= pointDAO.getAverageX();
        System.out.println("Average x is "+ averageX);

        long totalPoints= pointDAO.getTotalPoints();
        System.out.println("Total points are "+ totalPoints);




        pointDAO.close();

    }


}

