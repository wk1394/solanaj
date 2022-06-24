package org.p2p.solanaj.core;

import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.p2p.solanaj.api.Client;
import org.p2p.solanaj.programs.AssociatedTokenProgram;
import org.p2p.solanaj.programs.TokenProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class AssociatedAccountTest {
    @Test
    public void AssociatedAccountCreate(){
        String signature = Client.createUSDTAssociateAccount("5QdAuczJVtJYqDLdSyuwikB5dV7km1NRMKtUziBrbALj",
                "EXck5CaurgMU6HYSWMHeeEwMj253FgaXnBxGmfBL1dvX",
                "EXck5CaurgMU6HYSWMHeeEwMj253FgaXnBxGmfBL1dvX");
        assertTrue(signature != "");
    }
}
