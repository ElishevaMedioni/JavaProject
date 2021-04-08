package primitives;

import java.util.Objects;

/**
 * Ray Class has a direction and a starting point
 */
public class Ray {
    /**
     * point3D and vector field
     */
    Point3D p0;
    Vector dir;

    /**
     * get the starting point of the ray
     * @return the point3D value
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * set the point3D value
     * @param p0 point3D value
     */
    public void setP0(Point3D p0) {
        this.p0 = p0;
    }

    /**
     * get the vector value
     * @return the vector value
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * set the vector value of the ray
     * @param dir vector value
     */
    public void setDir(Vector dir) {
        this.dir = dir;
    }

    /**
     * ray constructor receiving the point3D and the vector
     * @param p0 the point3D value
     * @param dir the vector value
     */
    public Ray(Point3D p0, Vector dir) {
        if (dir.length()==1)
            this.dir = dir;
        else
            this.dir = dir.normalized();

        this.p0 = p0;

    }


    /*************** Admin *****************/

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Ray)) return false;
        if (!super.equals(object)) return false;
        Ray ray = (Ray) object;
        return java.util.Objects.equals(p0, ray.p0) && java.util.Objects.equals(dir, ray.dir);
    }

    @Override
    public java.lang.String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

}
