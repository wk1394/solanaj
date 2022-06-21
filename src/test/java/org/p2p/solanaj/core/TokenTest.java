package org.p2p.solanaj.core;

import jdk.nashorn.internal.ir.Block;
import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.p2p.solanaj.core.block.BlockChain;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.programs.TokenProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;

public class TokenTest {
    @Test
    public void getBlock(){
        PublicKey fromPublicKey = new PublicKey("CGJ3B6nGoFSQaQxwbv57AjPTfCtBnFso2rhjD2jwgi8B");
        PublicKey toPublickKey = new PublicKey("9ffTvptgwpdmMUjMK5TR1qVvvcvXKN1jYoppWYR36H4E");
        PublicKey ownerKey = new PublicKey("5QdAuczJVtJYqDLdSyuwikB5dV7km1NRMKtUziBrbALj");
        int lamports = 1000000;

        byte[] secret= Hex.decode("52e684b8880a4d226414426bf0aeab8ba8ffc62d66073cdda720a7b6804c7ab8");
        byte[] pubkey = ownerKey.toByteArray();
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
        transaction.addInstruction(TokenProgram.transfer(fromPublicKey, toPublickKey, ownerKey,lamports));
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
