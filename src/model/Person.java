package model;

/**
 *
 * @author ASUS
 */
public class Person {
    protected int age;
    protected int gender;

    public Person() {
    }

    public Person(int age, int gender) {
        this.age = age;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
