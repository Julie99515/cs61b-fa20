public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
                  xxPos = xP;
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = img;
              }
    public Body(Body b) {
        /** understand constructor! */
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double xxDis = xxPos - b.xxPos;
        double yyDis = yyPos - b.yyPos;
        double Distance = Math.sqrt(xxDis * xxDis + yyDis * yyDis);
        return Distance;
    }

    public double calcForceExertedBy(Body b) {
        double distance = this.calcDistance(b);
        double force = 6.67e-11 * mass * b.mass / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double distance = this.calcDistance(b);
        double dx = b.xxPos - xxPos;
        double force = calcForceExertedBy(b);
        double forcex = force * dx / distance;
        return forcex;
    }

    public double calcForceExertedByY(Body b) {
        double distance = this.calcDistance(b);
        double dy = b.yyPos - yyPos;
        double force = calcForceExertedBy(b);
        double forcey = force * dy / distance;
        return forcey;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        double ForceXX = 0;
        for(Body p: allBodys) {
            if(p != this) {
                ForceXX = ForceXX + calcForceExertedByX(p);
            } 
        }
        return ForceXX;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        double ForceYY = 0;
        for(Body p: allBodys) {
            if(p != this) {
                ForceYY = ForceYY + calcForceExertedByY(p);
            } 
        }
        return ForceYY; 
    }

    public void update(double dt, double xforce, double yforce) {
        double accel_x = xforce / mass;
        double accel_y = yforce / mass;
        xxVel = xxVel + accel_x * dt;
        yyVel = yyVel + accel_y * dt;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}