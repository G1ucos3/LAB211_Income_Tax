package dataAccess;

import common.Validate;
import java.util.ArrayList;
import java.util.List;
import model.Child;
import model.Parent;
import model.User;

/**
 *
 * @author ASUS
 */
public class TaxDAO {

    private static TaxDAO instance;
    Validate validate = new Validate();

    public static TaxDAO Instance() {
        if (instance == null) {
            synchronized (TaxDAO.class) {
                if (instance == null) {
                    instance = new TaxDAO();
                }
            }
        }
        return instance;
    }
    
    public void deductionDetail(User user){
        userInformation(user.getpList(), user.getcList(), user);
        double parentDeduction = parentDeduction(user.getpList(), user);
        double childDeduction = childDeduction(user.getcList(), user);
        double totalDeduction = 11000000 + parentDeduction + childDeduction;
        double taxIncome = taxIncome(user.getIncome(), totalDeduction);
        double tax = totalTax(taxIncome, user);
        System.out.println("\n=== Tax Calculate ===");
        System.out.printf("Income: %.2f\n", user.getIncome());
        if (user.isSiblingsRegister()) {
            System.out.println("Parent Deduction: 0(Siblings already register)");
        } else if (!user.isHasParents()) {
            System.out.println("Parent Deduction: 0(No parents)");
        } else {
            System.out.printf("Parent Deduction: %.2f\n",parentDeduction);
        }
        if (!user.isHasChildren()) {
            System.out.println("Child Deduction: 0(No children)");
        } else {
            System.out.printf("Child Deduction: %.2f\n",childDeduction);
        }
        System.out.println("Self deduction: 11000000");
        System.out.printf("Total Deduction: %.2f\n",totalDeduction);
        System.out.printf("Tax income: %.2f\n",taxIncome);
        if (taxIncome <= 0) {
            System.out.println("Tax(0%): 0");
        }
        else if (taxIncome < 4000000) {
            System.out.printf("Tax(5%%): %.2f\n",tax);
        }
        else if (taxIncome >= 4000000 && taxIncome < 6000000) {
            System.out.printf("Tax(8%%): %.2f\n" ,tax);
        }
        else if (taxIncome >= 6000000 && taxIncome < 10000000) {
            System.out.printf("Tax(10%%): %.2f\n" ,tax);
        } else {
            System.out.printf("Tax(20%%): %.2f\n" ,tax);
        }
    }
    
    public void userInformation(List<Parent> pList, List<Child> cList, User user) {
        user.setAge(validate.checkLimit("Enter user age", 0, 100));
        user.setGender(validate.checkLimit("Enter user gender(1.Male/0.Female)", 0, 1));
        user.setIncome(validate.checkIncome("Enter user income"));
        user.setHasParents(validate.inputYN("Does user have parents?(Y/N)"));
        if (user.isHasParents()) {
            user.setSiblingsRegister(validate.inputYN("Does user siblings already register parents?(Y/N)"));
            if(!user.isSiblingsRegister()){
                user.setpList(parentInfo());
            }
        }
        user.setHasChildren(validate.inputYN("Does user have children?(Y/N)"));
        if (user.isHasChildren()) {
            user.setcList(childrenInfo());
        }

    }

    private List<Parent> parentInfo() {
        List<Parent> pList = new ArrayList<>();
        do {
            int age = validate.checkLimit("Enter parent age", 0, 100);
            int gender = validate.checkLimit("Enter parent gender (1.Male/0.Female)", 0, 1);
            Parent parent = new Parent(age, gender);
            pList.add(parent);
        } while (validate.inputYN("Do you want to add more?(Y/N)"));
        return pList;
    }

    private List<Child> childrenInfo() {
        List<Child> cList = new ArrayList<>();
        do {
            boolean isStudying = validate.inputYN("Does your child still study?(Y/N)");
            int age = validate.checkLimit("Enter child age", 0, 100);
            int gender = validate.checkLimit("Enter child gender(1.Male/0.Female)", 0, 1);
            Child child = new Child(isStudying, age, gender);
            cList.add(child);
        } while (validate.inputYN("Do you want to add more?(Y/N)"));
        return cList;
    }

    public double childDeduction(List<Child> cList, User user) {
        if (!user.isHasChildren()) {
            return 0;
        }
        double maxDeduction_1 = 0;
        double maxDeduction_2 = 0;
        for (Child child : cList) {
            if (child.getAge() < 18) {
                if (maxDeduction_1 < 4400000) {
                    maxDeduction_1 = 4400000;
                    continue;
                }
                if (maxDeduction_2 < 4400000) {
                    maxDeduction_2 = 4400000;
                }
            } else {
                if (child.isIsStudying() && child.getAge() <= 22) {
                    if (maxDeduction_2 < 6000000) {
                        maxDeduction_2 = 6000000;
                        continue;
                    }
                    if (maxDeduction_1 < 6000000) {
                        maxDeduction_1 = 6000000;
                    }
                }
            }
        }
        return maxDeduction_1 + maxDeduction_2;
    }

    public double parentDeduction(List<Parent> pList, User user) {
        if (user.isSiblingsRegister()|| !user.isHasParents()) {
            return 0;
        }
        double deduction = 0;
        for (Parent parent : pList) {
            if (parent.getGender() == 1) {
                if (parent.getAge() > 60) {
                    deduction += 4400000;
                }
            } else {
                if (parent.getAge() > 55) {
                    deduction += 4400000;
                }
            }
        }
        return deduction;
    }

    public double taxIncome(double income, double deduction) {
        if (income - deduction <= 0) {
            return 0;
        }
        return income - deduction;
    }

    public double totalTax(double taxIncome, User user) {
        if (taxIncome <= 0) {
            return 0;
        }
        if (taxIncome < 4000000) {
            return user.getIncome() * 0.05;
        }
        if (taxIncome >= 4000000 && taxIncome < 6000000) {
            return user.getIncome() * 0.08;
        }
        if (taxIncome >= 6000000 && taxIncome < 10000000) {
            return user.getIncome() * 0.1;
        }
        return user.getIncome() * 0.2;
    }
}
