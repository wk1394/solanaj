package org.p2p.solanaj.core;

import org.bitcoinj.core.Base58;
import org.junit.Test;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.AccountInfo;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MainTest {
    public static final String myAddress = "BQWWFhzBdw2vKKBUX17NHeFbCoFQHfRARpdztPE2tDJ";
    @Test
    public void createAddress() {
        Account acc = Account.fromMnemonic(Arrays.asList("spider", "federal", "bleak", "unable", "ask", "weasel",
                "diamond", "electric", "illness", "wheat", "uphold", "mind"), "");
        String myAddress = acc.getPublicKey().toBase58();
        String secretKey = Base58.encode(acc.getSecretKey());
        System.out.println("createAddress: address:"+myAddress);
        System.out.println("createAddress: key:"+secretKey);
    }


    @Test
    public void getBlock(){
        try {
            // Get account Info
            final RpcClient client = new RpcClient(Cluster.MAINNET);
            System.out.println("block height:"+client.getApi().getBlockHeight());
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBalance(){
        final RpcClient client = new RpcClient(Cluster.MAINNET);
        final PublicKey publicKey = new PublicKey(myAddress);

        try {
            // Get account Info
            final AccountInfo accountInfo = client.getApi().getAccountInfo(publicKey);
            final double balance = (double) accountInfo.getValue().getLamports()/ 100000000;
            System.out.println("balance:"+balance);

            // Account data list
            final List<String> accountData = accountInfo.getValue().getData();

            // Verify "base64" string in accountData
            assertTrue(accountData.stream().anyMatch(s -> s.equalsIgnoreCase("base64")));
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void confirm(){

    }

}
