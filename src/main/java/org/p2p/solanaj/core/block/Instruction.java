package org.p2p.solanaj.core.block;

import java.util.List;

public class Instruction {
    private List<Integer> accounts;
    private String data;
    private Integer programIdIndex;

    public Integer getProgramIdIndex() {
        return programIdIndex;
    }

    public String getData() {
        return data;
    }

    public List<Integer> getAccounts() {
        return accounts;
    }
}
