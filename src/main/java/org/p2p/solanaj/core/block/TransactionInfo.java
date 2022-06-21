package org.p2p.solanaj.core.block;

public class TransactionInfo {
    private Meta meta;
    private InternalTransaction transaction;

    public InternalTransaction getTransaction() {
        return transaction;
    }

    public Meta getMeta() {
        return meta;
    }
}
