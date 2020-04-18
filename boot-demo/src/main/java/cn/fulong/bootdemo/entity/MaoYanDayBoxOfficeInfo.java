package cn.fulong.bootdemo.entity;

import lombok.Data;

import java.util.List;

/**
 * @author black猫
 * @date 2020/4/12
 * @time 12:36 上午
 * @desc 猫眼日票房数据信息
 */
@Data
public class MaoYanDayBoxOfficeInfo {

    MaoYanDayBoxOffice maoYanDayBoxOffice;

    List<MaoYanDayBoxOfficeMovie> maoYanDayBoxOfficeMovie;


}
