package controller;

import model.User;
import repository.TaxRepository;

/**
 *
 * @author ASUS
 */
public class TaxController {
    TaxRepository tr;
    User user;
    
    public TaxController(){
        tr = new TaxRepository();
        user = new User();
    }
    
    public void run(){
        tr.detailDeduction(user);
    }
}
