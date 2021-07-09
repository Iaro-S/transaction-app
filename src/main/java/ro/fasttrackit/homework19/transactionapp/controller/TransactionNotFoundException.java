package ro.fasttrackit.homework19.transactionapp.controller;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String msg) {
        super(msg);
    }
}
