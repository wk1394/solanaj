package org.p2p.solanaj.rpc.types;

import com.squareup.moshi.Json;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

public class BlockInfo{
    @Json(name = "blockhash")
    public String blockhash;

    public String getBlockHash() {
        return blockhash;
    }

    //    @Json(name = "transactions")
//    public ArrayList<Transaction> transactions;
}
