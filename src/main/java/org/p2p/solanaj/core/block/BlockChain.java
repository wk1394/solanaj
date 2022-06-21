package org.p2p.solanaj.core.block;


import java.util.List;

public class BlockChain {
    private Long blockHeight;
    private Long blockTime;
    private String blockhash;
    private Long parentSlot;
    private String previousBlockhash;
    private List<TransactionInfo> transactions;

    public List<TransactionInfo> getTransactions() {
        return transactions;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public String getBlockhash() {
        return blockhash;
    }
}
