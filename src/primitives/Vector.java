package primitives;

public class Vector {
    Point3D head;
    public Point3D getHead() {
        return head;
    }

    public void setHead(Point3D head) {
        this.head = head;
    }
    public Vector(Point3D head) {
        this.head = head;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Vector)) return false;
        if (!super.equals(object)) return false;
        Vector vector = (Vector) object;
        return java.util.Objects.equals(head, vector.head);
    }

    public Vector add(Vector vector){};
    public Vector substract(Vector vector){};
    public Vector scale(double doub){};
    public Vector crossProduct(Vector vector){};
    public double dotProduct(Vector vector){};
    public double lengthSquared(){};
    public double length(){};
    public Vector normalize(){};
    public Vector normalized(){};
}
