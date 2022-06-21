package org.p2p.solanaj.core.block;

import java.util.List;

public class InternalTransaction {
    private MessageInfo message;
    private List<String> signatures;

    public MessageInfo getMessage() {
        return message;
    }

    public List<String> getSignatures(){
        return signatures;
    };
}
