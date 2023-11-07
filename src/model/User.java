package model;


import java.util.List;

/**
 *
 * @author ASUS
 */
public class User extends Person{
    private boolean hasParents;
    private List<Parent> pList;
    private boolean hasChildren;
    private List<Child> cList;
    private boolean siblingsRegister;
    private double income;

    public User() {
    }

    public User(boolean hasParents, List<Parent> pList, boolean hasChildren, List<Child> cList, boolean siblingsRegister, double income, int age, int gender) {
        super(age, gender);
        this.hasParents = hasParents;
        this.pList = pList;
        this.hasChildren = hasChildren;
        this.cList = cList;
        this.siblingsRegister = siblingsRegister;
        this.income = income;
    }

    public boolean isHasParents() {
        return hasParents;
    }

    public void setHasParents(boolean hasParents) {
        this.hasParents = hasParents;
    }

    public List<Parent> getpList() {
        return pList;
    }

    public void setpList(List<Parent> pList) {
        this.pList = pList;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<Child> getcList() {
        return cList;
    }

    public void setcList(List<Child> cList) {
        this.cList = cList;
    }

    public boolean isSiblingsRegister() {
        return siblingsRegister;
    }

    public void setSiblingsRegister(boolean siblingsRegister) {
        this.siblingsRegister = siblingsRegister;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

}
