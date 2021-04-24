public class NBody {
    public static String imageToDraw = "./images/starfield.jpg";

    public static double readRadius(String filename) {
        In in = new In(filename);
        int firstItemInFile = in.readInt();
		double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int number = in.readInt();
        in.readDouble();
        Body[] allBodies = new Body[number];
        for(int i = 0; i < number; i ++) {
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvel = in.readDouble();
            double yvel = in.readDouble();
            double mass = in.readDouble();
            String imgname = in.readString();
            allBodies[i] = new Body(xpos, ypos, xvel, yvel, mass, imgname); 
        }
        return allBodies;
    }

    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] allbodies = readBodies(filename);
        double radius = readRadius(filename);
        //all drawing takes place at the offscreen canvas
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        double time_var = 0;
        double[] xForces = new double[allbodies.length];
        double[] yForces = new double[allbodies.length];
        while(time_var <= T) {
            for(int i = 0; i < allbodies.length; i++) {
                double xF = allbodies[i].calcNetForceExertedByX(allbodies);
                double yF = allbodies[i].calcNetForceExertedByY(allbodies);
                xForces[i] = xF;
                yForces[i] = yF;
            }
            for(int i = 0; i < allbodies.length; i++) {
                allbodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, imageToDraw);
            for(Body b : allbodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time_var = time_var + dt;
        }
        StdOut.printf("%d\n", allbodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allbodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allbodies[i].xxPos, allbodies[i].yyPos, allbodies[i].xxVel,
            allbodies[i].yyVel, allbodies[i].mass, allbodies[i].imgFileName);   
        }
    }
}
