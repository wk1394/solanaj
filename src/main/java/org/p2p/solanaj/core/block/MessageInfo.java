package org.p2p.solanaj.core.block;

import java.util.List;

public class MessageInfo {
    private List<String> accountKeys;
    private List<Integer> header;
    private List<Instruction> instructions;
    private String recentBlockhash;

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public List<String> getAccountKeys() {
        return accountKeys;
    }

}
