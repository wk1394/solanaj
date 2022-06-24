package org.p2p.solanaj.core;

import org.junit.Test;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.AccountInfo;

import static org.p2p.solanaj.core.MainTest.myAddress;

public class ScanBlockTest {
    @Test
    public void scanBlock() {
        try {
            // Get account Info
            final RpcClient client = new RpcClient(Cluster.MAINNET);
            System.out.println("block height:"+client.getApi().getBlock(138541876L));
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }
}
