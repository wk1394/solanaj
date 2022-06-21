package org.p2p.solanaj.core.block;


public class RpcResultObject {
    public static class Context {
        private long slot;
        public long getSlot() {
            return slot;
        }

    }

    protected Context context;
    public Context gContext() {
        return context;
    }

}
