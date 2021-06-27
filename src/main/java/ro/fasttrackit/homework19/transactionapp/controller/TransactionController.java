package ro.fasttrackit.homework19.transactionapp.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework19.transactionapp.model.Transaction;
import ro.fasttrackit.homework19.transactionapp.model.TransactionType;
import ro.fasttrackit.homework19.transactionapp.service.TransactionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/products")
    public List<Transaction> getMultipleFilters(@RequestParam(required = false) String productName,
                                                @RequestParam(required = false) TransactionType productType,
                                                @RequestParam(required = false) Double minAmount,
                                                @RequestParam(required = false) Double maxAmount
    ) {
        return transactionService.getAllTransactionsFilterable(productName, productType, minAmount, maxAmount);
    }

    @GetMapping("/id")
    public Optional<Transaction> getById(@RequestParam int productId) {
        return transactionService.getById(productId);
    }

    @PostMapping
    Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @SuppressWarnings("unused")
    @DeleteMapping("{transactionId}")
    Transaction deleteTransaction(@PathVariable int transactionId) {
        return transactionService.deleteTransaction(transactionId)
                .orElse(null);
    }
}
