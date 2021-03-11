package primitives;

public class Ray {
    Point3D p0;
    Vector dir;

    public Point3D getP0() {
        return p0;
    }

    public void setP0(Point3D p0) {
        this.p0 = p0;
    }

    public Vector getDir() {
        return dir;
    }

    public void setDir(Vector dir) {
        this.dir = dir;
    }

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Ray)) return false;
        if (!super.equals(object)) return false;
        Ray ray = (Ray) object;
        return java.util.Objects.equals(p0, ray.p0) && java.util.Objects.equals(dir, ray.dir);
    }

}
