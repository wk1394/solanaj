package org.p2p.solanaj.programs;

import org.p2p.solanaj.core.AccountMeta;
import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.core.TransactionInstruction;

import java.util.ArrayList;

import static org.bitcoinj.core.Utils.int64ToByteArrayLE;

public class TokenProgram {

    public static final PublicKey programId = new PublicKey("TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA");
    public static final int PROGRAM_INDEX_TRANSFER = 3;
    public static TransactionInstruction transfer(PublicKey fromPublicKey, PublicKey toPublickKey, PublicKey ownerKey, long lamports) {
        ArrayList<AccountMeta> keys = new ArrayList<AccountMeta>();
        keys.add(new AccountMeta(fromPublicKey, false, true));
        keys.add(new AccountMeta(toPublickKey, false, true));
        keys.add(new AccountMeta(ownerKey,true,false));

        // 4 byte instruction index + 8 bytes lamports
        byte[] data = new byte[1 + 8];
        data[0] = (byte)((int)(255L & PROGRAM_INDEX_TRANSFER));
        int64ToByteArrayLE(lamports, data, 1);

        return new TransactionInstruction(programId, keys, data);
    }
}
