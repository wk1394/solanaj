package org.p2p.solanaj.core;

import org.bitcoinj.core.Base58;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;
import org.p2p.solanaj.api.Client;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.utils.TweetNaclFast;

import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SendToTest {
    @Test
    public void sendTo(){
        String signture = Client.sendTo("5QdAuczJVtJYqDLdSyuwikB5dV7km1NRMKtUziBrbALj",
                "52e684b8880a4d226414426bf0aeab8ba8ffc62d66073cdda720a7b6804c7ab8",
                "EXck5CaurgMU6HYSWMHeeEwMj253FgaXnBxGmfBL1dvX",
                "0.49");

        assertTrue(signture != "");
    }
}
