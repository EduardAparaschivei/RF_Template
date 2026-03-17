package ro.usv.rf.utils;

public class Pair <P, Q>{
    private P a;//weights
    private Q b;///iclass

    public Pair(P a, Q b) {
        this.a = a;
        this.b = b;
    }

    public void setA(P a) {
        this.a = a;
    }

    public void setB(Q b) {
        this.b = b;
    }

    public P getA() {
        return a;
    }

    public Q getB() {
        return b;
    }
}
