package org.p2p.solanaj.core;

import org.bitcoinj.core.Base58;
import org.junit.Test;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;

import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class SendToTest {
    @Test
    public void sendTo(){
        PublicKey fromPublicKey = new PublicKey("BQWWFhzBdw2vKKBUX17NHeFbCoFQHfRARpdztPE2tDJ");
        PublicKey toPublickKey = new PublicKey("EXck5CaurgMU6HYSWMHeeEwMj253FgaXnBxGmfBL1dvX");
        int lamports = 320000000;

        Account signer = new Account(Base58
                .decode("4VY8wkk8XuMmr8gDERT7xT2VRL5UboyCNGYvAXbuSkYU8FnsPwneMoBVzyM37ehiSdzp3EaYsoPNfhbpGrvxa9L8"));

        Transaction transaction = new Transaction();
        transaction.addInstruction(SystemProgram.transfer(fromPublicKey, toPublickKey, lamports));
        final RpcClient client = new RpcClient(Cluster.MAINNET);
        try
        {
//            String recentHash = client.getApi().getRecentBlockhash();
//            System.out.println("recentHash:"+recentHash);
//            transaction.setRecentBlockHash(recentHash);
            //transaction.sign(signer);
            //byte[] serializedTransaction = transaction.serialize();

            String signature = client.getApi().sendTransaction(transaction,signer);
            System.out.println("signature:"+signature);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
