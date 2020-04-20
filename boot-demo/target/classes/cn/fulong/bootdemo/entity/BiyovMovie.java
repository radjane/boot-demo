package cn.fulong.bootdemo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BiyovMovie implements Serializable {

    int id;
    String movieId;
    String movieName;
    String movieSubName;
    String movieDesc;
    String movieType;
    String movieUrl;
    String moviePlayUrl;

}
