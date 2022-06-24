package org.p2p.solanaj.core;

import jdk.nashorn.internal.ir.Block;
import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.p2p.solanaj.api.Client;
import org.p2p.solanaj.core.block.BlockChain;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.programs.TokenProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;

public class TokenTest {
    @Test
    public void sendToken(){
        String signature = Client.sendUSDTToken("5QdAuczJVtJYqDLdSyuwikB5dV7km1NRMKtUziBrbALj",
                "52e684b8880a4d226414426bf0aeab8ba8ffc62d66073cdda720a7b6804c7ab8",
                "CGJ3B6nGoFSQaQxwbv57AjPTfCtBnFso2rhjD2jwgi8B",
                "CS6vCis9xGpz82ro9ZCgnsaNfEmez47Pr79iKhM1JvkL",
                "0.44");

        Assert.assertTrue(signature != "");
    }
}
