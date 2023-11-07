package model;

/**
 *
 * @author ASUS
 */
public class Child extends Person {

    private boolean isStudying;

    public Child() {
    }

    public Child(boolean isStudying, int age, int gender) {
        super(age, gender);
        this.isStudying = isStudying;
    }

    public boolean isIsStudying() {
        return isStudying;
    }

    public void setIsStudying(boolean isStudying) {
        this.isStudying = isStudying;
    }
}
