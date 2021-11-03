package math;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(double size, double angle, boolean polar) {
        this.x = size * Math.cos(angle);
        this.y = size * Math.sin(angle);
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    /** Return the module of the vector*/
    public double size() {
        if (x == 0) {
            if (y == 0)
                return 0;
            else
                return Math.abs(y);
        }
        else if (y == 0)
            return Math.abs(x);

        return Math.sqrt(x*x + y*y);
    }

    /** Rotate the vector to the specific vector*/
    public void setDirection(double angle) {
        final double module = size();
        this.x = module * Math.cos(angle);
        this.y = module * Math.sin(angle);
    }

    /** Return the argument of the vector*/
    public double getDirection() {
        return Math.atan2(y, x);
    }

    /** Return the summation of the vectors*/
    public Vector2D plus(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    /** Return the subtraction of the vectors*/
    public Vector2D minus(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    /** Return the product of the vector with the scalar*/
    public Vector2D scale(double value) {
        return new Vector2D(value * x, value * y);
    }

    /** Return the vector normalized*/
    public Vector2D normalize() {
        final double size = size();
        assert (size != 0);
        return new Vector2D(x / size, y / size);
    }

    /** Restricts the components of the vector*/
    public void limit(double limit) {
        if (x > limit)
            x = limit;
        if (x < -limit)
            x = -limit;
        if (y > limit)
            y = limit;
        if (y < -limit)
            y = -limit;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }
}
