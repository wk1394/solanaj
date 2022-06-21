package org.p2p.solanaj.core.block;

import java.util.List;

public class Meta {
    private Object err;
    private long fee;
    private List<Object> innerInstructions;
    private List<Long> postBalances;
    private List<Long> preBalances;
    private Status status;

    public Object getErr() {
        return err;
    }

    public long getFee() {
        return fee;
    }

    public List<Object> getInnerInstructions() {
        return innerInstructions;
    }

    public List<Long> getPostBalances() {
        return postBalances;
    }

    public List<Long> getPreBalances() {
        return preBalances;
    }

    public Status getStatus() {
        return status;
    }
}
