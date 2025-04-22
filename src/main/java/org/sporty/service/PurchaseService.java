package org.sporty.service;

import org.sporty.dto.PurchaseRequest;
import org.sporty.dto.PurchaseResponse;
import org.sporty.model.Book;
import org.sporty.model.BookType;
import org.sporty.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    public PurchaseResponse processPurchase(PurchaseRequest request) {
        Optional<Customer> customerOpt = customerService.getCustomerById(request.getCustomerId());
        if (!customerOpt.isPresent()) {
            return new PurchaseResponse(0, "Customer not found");
        }
        Customer customer = customerOpt.get();
        List<Book> purchasedBooks = new ArrayList<>();
        for (Long bookId : request.getBookIds()) {
            Optional<Book> bookOpt = bookService.getBookById(bookId);
            bookOpt.ifPresent(purchasedBooks::add);
        }
        if (purchasedBooks.isEmpty()) {
            return new PurchaseResponse(0, "No valid books found in purchase");
        }

        // Count books by type for bundle discount eligibility.
        int regularCount = 0;
        int oldEditionCount = 0;
        for (Book book : purchasedBooks) {
            if (book.getBookType() == BookType.REGULAR) {
                regularCount++;
            } else if (book.getBookType() == BookType.OLD_EDITION) {
                oldEditionCount++;
            }
        }

        double totalPrice = 0;
        // Calculate discounted price for each book.
        List<Double> bookPrices = new ArrayList<>();
        for (Book book : purchasedBooks) {
            double price = book.getBasePrice();
            if (book.getBookType() == BookType.NEW_RELEASE) {
                // No discount.
            } else if (book.getBookType() == BookType.REGULAR) {
                if (regularCount >= 3) {
                    price *= 0.90; // 10% discount.
                }
            } else if (book.getBookType() == BookType.OLD_EDITION) {
                if (oldEditionCount >= 3) {
                    price *= 0.75; // 25% discount (20% + additional 5%).
                } else {
                    price *= 0.80; // 20% discount.
                }
            }
            bookPrices.add(price);
            totalPrice += price;
        }

        boolean redeemed = false;
        // Process loyalty redemption: if requested and at least 10 points.
        if (request.isRedeemLoyalty() && customer.getLoyaltyPoints() >= 10) {
            // Redeem the first eligible book (Regular or Old Edition).
            for (int i = 0; i < purchasedBooks.size(); i++) {
                Book book = purchasedBooks.get(i);
                if (book.getBookType() == BookType.REGULAR || book.getBookType() == BookType.OLD_EDITION) {
                    double discountAmount = bookPrices.get(i);
                    totalPrice -= discountAmount; // Make the book free.
                    redeemed = true;
                    break;
                }
            }
        }

        // Update loyalty points.
        if (redeemed) {
            customer.setLoyaltyPoints(0);
        } else {
            customer.setLoyaltyPoints(customer.getLoyaltyPoints() + purchasedBooks.size());
        }
        customerService.updateCustomer(customer);

        String message = "Purchase successful.";
        if (redeemed) {
            message += " Loyalty points redeemed for one free book.";
        }

        return new PurchaseResponse(totalPrice, message);
    }
}
