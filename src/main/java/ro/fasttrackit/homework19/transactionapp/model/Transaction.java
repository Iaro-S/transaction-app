package ro.fasttrackit.homework19.transactionapp.model;

public record Transaction(
        int id,
        String product,
        TransactionType type,
        double amount
) {
}
