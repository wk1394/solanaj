package org.p2p.solanaj.core;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.programs.TokenProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;

public class CreateAccount {
    @Test
    public void CreateTokenAccount(){

        PublicKey fromPublicKey = new PublicKey("Ccrjhj1Ww8HZ74y6xY1QPWp4VDQ8m7hpk8BN3Xrm5EnN");

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
        TransactionInstruction instruction = SystemProgram.createAccount(SystemProgram.PROGRAM_ID,
                SystemProgram.PROGRAM_ID, 2039280, 165, TokenProgram.programId);
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
