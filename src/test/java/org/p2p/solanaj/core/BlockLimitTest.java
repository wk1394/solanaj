package org.p2p.solanaj.core;

import org.junit.Test;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;

import java.math.BigInteger;

public class BlockLimitTest {

    @Test
    public void getBlockLimit() throws Throwable {
        final RpcClient client = new RpcClient(Cluster.MAINNET);
        System.out.println("getBlockLimit:"+client.getApi().getBlockLimit(new BigInteger("138799752"),new BigInteger("1")));
    }
}
