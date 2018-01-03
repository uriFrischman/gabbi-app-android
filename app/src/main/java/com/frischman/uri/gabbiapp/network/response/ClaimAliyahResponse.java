package com.frischman.uri.gabbiapp.network.response;


import com.frischman.uri.gabbiapp.model.Aliyah;

public class ClaimAliyahResponse {
    

    Aliyah aliyah;
    String message;
    boolean succesfullClaim;

    public ClaimAliyahResponse(Aliyah aliyah, String message, boolean succesfullClaim) {
        this.aliyah = aliyah;
        this.message = message;
        this.succesfullClaim = succesfullClaim;
    }

    public Aliyah getAliyah() {
        return aliyah;
    }

    public void setAliyah(Aliyah aliyah) {
        this.aliyah = aliyah;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccesfullClaim() {
        return succesfullClaim;
    }

    public void setSuccesfullClaim(boolean succesfullClaim) {
        this.succesfullClaim = succesfullClaim;
    }
}
