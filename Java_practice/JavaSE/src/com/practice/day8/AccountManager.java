package com.practice.day8;

public class AccountManager {
    private Double balance;

    public  AccountManager(Double balance) {
        this.balance = balance;
    }

    public void deposit(Double money) {
        balance += money;
    }

    //在可能有问题的地方抛出声明要抛出异常
    public void withdraw(Double money) throws NoMoneyException{
        if (balance >= money) {
            balance -= money;
        } else {
            double needMoney = money - balance;
            throw new NoMoneyException("余额不足还差:" + needMoney);
        }
    }
}
