package org.p2p.solanaj.programs;


import org.p2p.solanaj.core.AccountMeta;
import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.core.TransactionInstruction;

import java.util.ArrayList;

public class AssociatedTokenProgram {
    public static final PublicKey programId = new PublicKey("ATokenGPvbdGVxr1b2hvZbsiqW5xWH25efTNsLJA8knL");
    public static TransactionInstruction transfer(PublicKey funding_address,
                                                  PublicKey associated_account_address,
                                                  PublicKey wallet_address,
                                                  PublicKey spl_token_mint_address,
                                                  PublicKey system_program,
                                                  PublicKey spl_token,
                                                  PublicKey rent) {

        ArrayList<AccountMeta> keys = new ArrayList<AccountMeta>();
        keys.add(new AccountMeta(funding_address, false, true));
        keys.add(new AccountMeta(associated_account_address, false, true));
        keys.add(new AccountMeta(wallet_address,false,true));
        keys.add(new AccountMeta(spl_token_mint_address,false,false));
        keys.add(new AccountMeta(system_program,false,false));
        keys.add(new AccountMeta(spl_token,false,false));
        keys.add(new AccountMeta(rent,false,false));
        return new TransactionInstruction(programId, keys, new byte[]{});
    }

}
