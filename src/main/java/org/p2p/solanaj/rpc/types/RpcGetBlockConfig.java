package org.p2p.solanaj.rpc.types;

import com.squareup.moshi.Json;

public class RpcGetBlockConfig {
    public static enum Encoding {
        json("json");

        private String enc;

        Encoding(String enc) {
            this.enc = enc;
        }

        public String getEncoding() {
            return enc;
        }

    }

    @Json(name = "encoding")
    private Encoding encoding = Encoding.json;
}
