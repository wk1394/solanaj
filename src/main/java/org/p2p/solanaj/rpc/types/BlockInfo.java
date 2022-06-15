package org.p2p.solanaj.rpc.types;

import com.squareup.moshi.Json;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

public class BlockInfo extends RpcResultObject{

    public static class Transaction{

    }

    @Json(name = "blockHeight")
    public BigInteger blockHeight;

    @Json(name = "blockhash")
    public String blockhash;

    @Json(name = "parentSlot")
    public BigInteger parentSlot;

    @Json(name = "previousBlockhash")
    public String previousBlockhash;

    @Json(name = "transactions")
    public ArrayList<Transaction> transactions;
}
