package org.p2p.solanaj.core;

import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.p2p.solanaj.programs.AssociatedTokenProgram;
import org.p2p.solanaj.programs.TokenProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;

import java.util.Arrays;

public class AssociatedAccountTest {
    @Test
    public void AssociatedAccountCreate(){
//        byte[] data = Base58.decode("11119os1e9qSs2u7TsThXqkBSRVFxhmYaFKFZ1waB2X7armDmvK3p5GmLdUxYdg3h7QSrL");
//        System.out.println("data:"+Hex.toHexString(data));
//
//        String pubKey = Base58.encode(Hex.decode("06ddf6e1d765a193d9cbe146ceeb79ac1cb485ed5f5b37913a8cf5857eff00a9"));
//        System.out.println("pubKey:"+pubKey);
//        //TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA
//
//        byte[] init3 = Base58.decode("6RYKqqpX6njaP6hS9Y97HCicU8NhaAke84F4LVHsftJmh");
//        System.out.println("init3:"+Hex.toHexString(init3));
//        String pubkey3 = Base58.encode(Hex.decode("417c0139910c28f027925719f0ae7193c1da0111cae12eb3422c79613f31941c"));
//        System.out.println("pubkey3:"+pubkey3);
//        //5QdAuczJVtJYqDLdSyuwikB5dV7km1NRMKtUziBrbALj


        PublicKey funding_address = new PublicKey("5QdAuczJVtJYqDLdSyuwikB5dV7km1NRMKtUziBrbALj");
        PublicKey wallet_address = new PublicKey("Ccrjhj1Ww8HZ74y6xY1QPWp4VDQ8m7hpk8BN3Xrm5EnN");
        PublicKey spl_token_mint_address = new PublicKey("Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB");
        PublicKey system_program = new PublicKey("11111111111111111111111111111111");
        PublicKey spl_token = new PublicKey("TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA");
        PublicKey rent = new PublicKey("SysvarRent111111111111111111111111111111111");
        PublicKey associated_account_address = null;
        try {
            associated_account_address = PublicKey.findProgramAddress(Arrays.asList(
                    wallet_address.toByteArray(),
                    spl_token.toByteArray(),
                    spl_token_mint_address.toByteArray()
            ),AssociatedTokenProgram.programId).getAddress();
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println("associated_account_address:"+associated_account_address.toBase58());

        byte[] secret= Hex.decode("52e684b8880a4d226414426bf0aeab8ba8ffc62d66073cdda720a7b6804c7ab8");
        byte[] pubkey = funding_address.toByteArray();
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
        transaction.addInstruction(AssociatedTokenProgram.transfer(funding_address,
                associated_account_address,wallet_address,spl_token_mint_address,system_program,spl_token,rent));
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
