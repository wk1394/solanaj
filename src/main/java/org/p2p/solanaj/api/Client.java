package org.p2p.solanaj.api;

import com.sun.deploy.util.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.p2p.solanaj.core.Account;
import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.core.Transaction;
import org.p2p.solanaj.programs.AssociatedTokenProgram;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.programs.TokenProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.utils.Convert;

import java.math.BigInteger;
import java.util.Arrays;

public class Client {

    public static final int SOL_DECIMAL = 9;
    public static final int USDT_DECIMAL = 6;
    public static String sendTo(String from, String fromPrivateKey, String to,String amount){
        PublicKey fromPublicKey = new PublicKey(from);
        PublicKey toPublickKey = new PublicKey(to);

        //SOL decimal 9
        BigInteger value = Convert.toDecimal(amount,SOL_DECIMAL);
        int lamports = value.intValue();

        byte[] secretKey= PublicKey.getKeyPairsFromPrivKey(from,fromPrivateKey);
        Account signer = new Account(secretKey);
        Transaction transaction = new Transaction();
        transaction.addInstruction(SystemProgram.transfer(fromPublicKey, toPublickKey, lamports));
        String signature = "";
        try
        {
            final RpcClient client = new RpcClient(Cluster.MAINNET);
            signature = client.getApi().sendTransaction(transaction,signer);
            System.out.println("signature:"+signature);
        }catch (Exception e){
            e.printStackTrace();
        }
        return signature;
    }

    public static String createUSDTAssociateAccount(String fundingAddress, String fundingPrivateKey, String walletAddress){
        String associatedAddress = "";

        PublicKey funding_address = new PublicKey(fundingAddress);
        PublicKey wallet_address = new PublicKey(walletAddress);

        //USDT contract
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
            ), AssociatedTokenProgram.programId).getAddress();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

        associatedAddress = associated_account_address.toBase58();
        System.out.println("associated_account_address:"+associatedAddress);

        byte[] secretkey = PublicKey.getKeyPairsFromPrivKey(fundingAddress, fundingPrivateKey);
        Account signer = new Account(secretkey);
        Transaction transaction = new Transaction();
        transaction.addInstruction(AssociatedTokenProgram.transfer(funding_address,
                associated_account_address,wallet_address,spl_token_mint_address,system_program,spl_token,rent));
        try
        {
            final RpcClient client = new RpcClient(Cluster.MAINNET);
            String signature = client.getApi().sendTransaction(transaction,signer);
            System.out.println("signature:"+signature);
            if (signature != null && signature.length() > 0) return associatedAddress;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String sendUSDTToken(String ownerAddress, String ownerPrivateKey,String fromAssociatedAddress, String toAssociateAddress,String amount){
        PublicKey fromPublicKey = new PublicKey(fromAssociatedAddress);
        PublicKey toPublickKey = new PublicKey(toAssociateAddress);
        PublicKey ownerKey = new PublicKey(ownerAddress);

        //USDT decimal 6
        BigInteger value = Convert.toDecimal(amount,USDT_DECIMAL);
        int lamports = value.intValue();

        byte[] secretkey= PublicKey.getKeyPairsFromPrivKey(ownerAddress,ownerPrivateKey);
        Account signer = new Account(secretkey);

        Transaction transaction = new Transaction();
        transaction.addInstruction(TokenProgram.transfer(fromPublicKey, toPublickKey, ownerKey, lamports));
        try
        {
            final RpcClient client = new RpcClient(Cluster.MAINNET);
            String signature = client.getApi().sendTransaction(transaction,signer);
            System.out.println("signature:"+signature);
            return signature;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
