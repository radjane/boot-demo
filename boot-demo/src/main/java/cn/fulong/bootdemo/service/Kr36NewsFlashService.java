package cn.fulong.bootdemo.service;

import cn.fulong.bootdemo.entity.Kr36NewsFlash;

import java.util.List;

public interface Kr36NewsFlashService {

    String save36KrNewsFlash(Integer size);

    List<Kr36NewsFlash> getKr36NewsFlashes1(String startYmd);

    List<Kr36NewsFlash> getKr36NewsFlashes2(String startYmd);

    String update36KrNewsFlash(String yyyymmdd);

    List<Kr36NewsFlash> getKr36NewsFlashes3(String startYmd, String endYmd);
}
