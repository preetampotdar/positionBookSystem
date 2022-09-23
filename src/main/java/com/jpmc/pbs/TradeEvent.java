package com.jpmc.pbs;

public class TradeEvent {

    private int id;
    private TradeOperations tradeOperations;
    private Account account;
    private Securities securities;
    private int numberOfSecuritiesToTrade;

    public TradeEvent(int id, TradeOperations tradeOperations, Account account, Securities securities, int numberOfSecuritiesToTrade) {
        this.id = id;
        this.tradeOperations = tradeOperations;
        this.account = account;
        this.securities = securities;
        this.numberOfSecuritiesToTrade = numberOfSecuritiesToTrade;
    }

    public int getId() {
        return id;
    }

    public TradeOperations getTradeOperations() {
        return tradeOperations;
    }

    public Account getAccount() {
        return account;
    }

    public Securities getSecurities() {
        return securities;
    }

    public int getNumberOfSecuritiesToTrade() {
        return numberOfSecuritiesToTrade;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "," + tradeOperations +
                "," + account.getAccountName() +
                "," + securities.getSecurityName() +
                "," + numberOfSecuritiesToTrade;
    }
}
