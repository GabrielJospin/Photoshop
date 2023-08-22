package photoshop;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double distEuclidian(Point that){
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    public double distManhathan(Point that){
        return  Math.abs(this.x - that.x) + Math.abs(this.y - that.y)  ;
    }


}
