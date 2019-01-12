package exercises;

public class Circle {
	int x = 0;
	int y = 0;
	double radius = 0;

	public Circle(int x, int y, double radius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public Circle() {
		x = 1;
		y = 1;
	}

	public double calculateArea() {
		return Math.PI * radius * radius;
	}

	public double calculatePerimeter() {
		return 2 * Math.PI * radius;
	}

	public boolean overlapsWith(Circle c) {
		double abstand = Math.sqrt((x - c.getX()) * (x - c.getX())
				+ (y - c.getY()) * (y - c.getY()));
		if (abstand <= radius + c.getRadius()) {
			return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Circle [x=" + x + ", y=" + y + ", radius=" + radius + "]";
	}

}
