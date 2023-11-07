package repository;

import dataAccess.TaxDAO;

import model.User;

/**
 *
 * @author ASUS
 */
public class TaxRepository implements ITax {

    @Override
    public void detailDeduction(User user) {
        TaxDAO.Instance().deductionDetail(user);
    }
}
