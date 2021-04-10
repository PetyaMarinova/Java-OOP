package p01ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setHeight(double height) {
        if (isPositiveAndNotZero(height)) {
            this.height = height;
        } else {
            throw new IllegalStateException("Height cannot be zero or negative.");
        }
    }

    private void setWidth(double width) {
        if (isPositiveAndNotZero(width)) {
            this.width = width;
        } else {
            throw new IllegalStateException("Width cannot be zero or negative.");
        }
    }

    private void setLength(double length) {
        if (isPositiveAndNotZero(length)) {
            this.length = length;
        } else {
            throw new IllegalStateException("Length cannot be zero or negative.");
        }
    }

    private boolean isPositiveAndNotZero(double length) {
        return length > 0;
    }

    public double calculateSurfaceArea() {
        return 2 * this.length * this.width + 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }
}
