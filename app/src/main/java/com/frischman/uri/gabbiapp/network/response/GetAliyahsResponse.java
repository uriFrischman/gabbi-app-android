package com.frischman.uri.gabbiapp.network.response;


import com.frischman.uri.gabbiapp.model.Aliyah;

import java.util.List;

public class GetAliyahsResponse {

    List<Aliyah> aliyahList;

    public GetAliyahsResponse(List<Aliyah> aliyahList) {
        this.aliyahList = aliyahList;
    }

    public List<Aliyah> getAliyahList() {
        return aliyahList;
    }

    public void setAliyahList(List<Aliyah> aliyahList) {
        this.aliyahList = aliyahList;
    }
}
