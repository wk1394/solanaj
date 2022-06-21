package org.p2p.solanaj.core.block;

public class RecentBlockHash extends RpcResultObject {

    public static class FeeCalculator {
        private long lamportsPerSignature;
        public long getLamportsPerSignature() {
            return lamportsPerSignature;
        }

    }
    public static class Value{
        private String blockhash;
        private FeeCalculator feeCalculator;
        public String getBlockhash() {
            return blockhash;
        }
        public FeeCalculator getFeeCalculator() {
            return feeCalculator;
        }
    }

    public Value value;
    public Value getValue() {
        return value;
    }

    public String getRecentBlockhash() {
        return getValue().getBlockhash();
    }
}
