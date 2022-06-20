package org.p2p.solanaj.core;

import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.utils.TweetNaclFast;

import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class SendToTest {
    @Test
    public void sendTo(){
        PublicKey fromPublicKey = new PublicKey("7uPBmpy71p4WvZ5h8jop1ssFTNnu3HiJDMTmhPy9BBvd");
        PublicKey toPublickKey = new PublicKey("EXck5CaurgMU6HYSWMHeeEwMj253FgaXnBxGmfBL1dvX");
        int lamports = 120000000;

        byte[] secret= Hex.decode("cddc33a24ec730cfb456c6f19a10d1f3f8542b1aa0ba64187a91e2fe20399cc5");
        byte[] pubkey = fromPublicKey.toByteArray();
//        TweetNaclFast.Signature.KeyPair keyPair = TweetNaclFast.Signature.keyPair_fromSeed(secret);

        //私钥格式，私钥在前，公钥在后，合起来为64位。
        byte[] secretkey = new byte[64];
        for (int i = 0; i < 32; i++){
            secretkey[i] = secret[i];
        }
        for (int i = 0; i < 32; i++){
            secretkey[i+32] = pubkey[i];
        }

        if (secretkey.length != 64){
            System.out.println("secretkey length:"+secretkey.length);
            return;
        }

        Account signer = new Account(secretkey);

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
