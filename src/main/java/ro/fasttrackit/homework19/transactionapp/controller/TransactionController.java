package ro.fasttrackit.homework19.transactionapp.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework19.transactionapp.model.Transaction;
import ro.fasttrackit.homework19.transactionapp.model.TransactionType;
import ro.fasttrackit.homework19.transactionapp.service.TransactionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //http://localhost:8080/transactions?productName=audi&productType=BUY&minAmount=0.01&maxAmount=57550.99
    @GetMapping()
    public List<Transaction> getMultipleFilters(@RequestParam(required = false) String productName,
                                                @RequestParam(required = false) TransactionType productType,
                                                @RequestParam(required = false) Double minAmount,
                                                @RequestParam(required = false) Double maxAmount
    ) {
        return transactionService.getAllTransactionsFilterable(productName, productType, minAmount, maxAmount);
    }

    //http://localhost:8080/transactions/id?productId=19 -asa l-am facut initial
    //http://localhost:8080/transactions/19

    @GetMapping("/{productId}")
    public Transaction getById(@PathVariable int productId) {
        return transactionService.getById(productId)
                .orElseThrow(()->new TransactionNotFoundException("Id " + productId + " not find"));
    }

    @PostMapping
    Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("{transactionId}")
    Transaction replaceTransaction(@PathVariable int transactionId, @RequestBody Transaction newTransaction) {
        return transactionService.replaceTransaction(transactionId, newTransaction)
                .orElse(null);
    }

    @PatchMapping("{transactionId}")
    Transaction patchTransaction(@PathVariable int transactionId, @RequestBody Transaction transaction) {
        return transactionService.patchTransaction(transactionId, transaction)
                .orElse(null);
    }

    @DeleteMapping("{transactionId}")
    Transaction deleteTransaction(@PathVariable int transactionId) {
        return transactionService.deleteTransaction(transactionId)
                .orElse(null);
    }

    //http://localhost:8080/transactions/reports/type
    @GetMapping("/reports/type")
    Map<TransactionType, List<Double>> typeReport() {
        return transactionService.typeReport();
    }

    //http://localhost:8080/transactions/reports/product
    @GetMapping("/reports/product")
    Map<String, List<Double>> productReport() {
        return transactionService.productReport();
    }

    //http://localhost:8080/transactions/reports/type/sum
    @GetMapping("/reports/type/sum")
    Map<TransactionType, Double> sumTypeAmount() {
        return transactionService.sumTypeAmount();
    }

    //http://localhost:8080/transactions/reports/product/sum
    @GetMapping("/reports/product/sum")
    Map<String, Double> sumProductAmount() {
        return transactionService.sumProductAmount();
    }
}

