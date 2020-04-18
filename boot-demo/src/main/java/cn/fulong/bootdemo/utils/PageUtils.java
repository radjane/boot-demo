package cn.fulong.bootdemo.utils;

public class PageUtils {


    /**
     *
     * @param pageNum
     * @param pageSize
     * @param totalCount
     * @return
     */
    public static MyPageHelper getMyPageHelper(int pageNum,int pageSize,int totalCount){
        MyPageHelper pageHelper=new MyPageHelper();
        pageHelper.setPageSize(pageSize);
        pageHelper.setCurrentPage(pageNum);
        pageHelper.setTotalCount(totalCount);
        int totalPage=0;
        if(totalCount%pageSize==0 && totalCount>0){
            totalPage=totalCount/pageSize;
        }else{
            totalPage=totalCount/pageSize+1;
        }
        pageHelper.setTotalPage(totalPage);

        return pageHelper;
    }
}
