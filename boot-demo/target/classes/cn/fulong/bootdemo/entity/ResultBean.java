package cn.fulong.bootdemo.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @Author:GHB
 * @Date:2019-06-23
 *
 * 其中正确返回值:
 *  {state:0, data:返回数据, message:错误消息}
 * 错误返回值:
 *  {state:1, data:null, message:错误消息}
 */

@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = -3644950655568598241L;
    public static final  int SUCCESS=0;
    public static final  int ERROR=1;

    /**
     * 返回是否成功的状态, 0表示成功,
     *  1或其他值 表示失败
     */
    private int state;

    /**
     * 成功时候,返回的JSON数据
     */
    private T data;

    /**
     * 是错误时候的错误消息
     */
    private String message;

    public ResultBean (int state,T data,String message){
        this.state=state;
        this.data=data;
        this.message=message;
    }

    public ResultBean(){

    }

}
