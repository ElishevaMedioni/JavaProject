package primitives;

/**
 * Class to store some material for the Phong Reflectance Model
 * kD and kS are the are coefficients of reflection
 * nShininess - the object shininess
 */
public class Material {
    public double kD=0;
    public double kS=0;
    public int nShininess=0;
    public double kT=0.0;
    public double kR=0.0;

    /**
     * setKD, setKS, setNShininess - builder design pattern
     */
    public Material setKD(double kD){
        this.kD=kD;
        return this;
    }

    public Material setKS(double kS){
        this.kS=kS;
        return this;
    }

    public Material setNShininess(int nShininess){
        this.nShininess=nShininess;
        return this;
    }

    public Material setKT(double kT){
        this.kT=kT;
        return this;
    }

    public Material setKR(double kR){
        this.kR=kR;
        return this;
    }
}
