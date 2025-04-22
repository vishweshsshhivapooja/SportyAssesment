package org.sporty.dto;

import java.util.List;

public class PurchaseRequest {
    private Long customerId;
    private List<Long> bookIds;
    private boolean redeemLoyalty;

    public PurchaseRequest() {}

    public PurchaseRequest(Long customerId, List<Long> bookIds, boolean redeemLoyalty) {
        this.customerId = customerId;
        this.bookIds = bookIds;
        this.redeemLoyalty = redeemLoyalty;
    }

    // Getters and setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public List<Long> getBookIds() { return bookIds; }
    public void setBookIds(List<Long> bookIds) { this.bookIds = bookIds; }

    public boolean isRedeemLoyalty() { return redeemLoyalty; }
    public void setRedeemLoyalty(boolean redeemLoyalty) { this.redeemLoyalty = redeemLoyalty; }
}
