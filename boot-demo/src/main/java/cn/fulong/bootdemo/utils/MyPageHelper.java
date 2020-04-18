package cn.fulong.bootdemo.utils;


import java.util.ArrayList;
import java.util.List;


/**
 * @Author:GHB
 * @Date:2019-07-25
 * Mysql 分页 工具类
 */
public class MyPageHelper<T> {

    /**
     * 总条数
     */
    private int totalCount = 0;

    /***
     * 总页数
     */
    private int totalPage = 0;

    /***
     * 每页显示行数  ①如果要调整每页显示行数，必须第一个设置
     */
    private int pageSize = 2;

    /***
     * 当前页
     */
    private int currentPage = 1;

    /***
     *  当前页之前和之后显示的页数个数 如：假设当前页是 6 共有11页 那么 显示分页条会显示 1 2 3 4 5 [6] 7 8 9 10 11
     */
    private int num = 3;

    private List<T> listItems = new ArrayList<>();





    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            if (this.totalCount % this.pageSize == 0) {
                this.totalPage = totalCount / pageSize;
            } else if (this.totalCount % this.pageSize > 0) {
                this.totalPage = totalCount / pageSize + 1;
            } else {
                this.totalPage = 0;
            }
        } else {
            this.totalCount = 0;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<T> getListItems() {
        return listItems;
    }

    public void setListItems(List<T> listItems) {
        this.listItems = listItems;
    }

    /**
     * 获取前一页
     *
     * @return
     */
    public Integer getPrev() {
        if (currentPage==1) {
            return 1;
        }
        return currentPage - 1;

    }

    /**
     * 获取后一页
     *
     * @return
     */
    public Integer getNext() {
        return currentPage + 1;
    }

    /**
     * 获取最后一页
     *
     * @return
     */
    public Integer getLast() {
        return totalPage;
    }

    /**
     * 判断是否有前一页
     *
     * @return
     */
    public boolean getIsPrev() {
        if (currentPage > 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有后一页
     *
     * @return
     */
    public boolean getIsNext() {

        if (currentPage < totalPage) {
            return true;
        }
        return false;
    }
    /**
     * 当前页的前num条页 假设当前页是 6 共有11页 如：1 2 3 4 5
     *
     * @return
     */
    public List<Integer> getPrevPages() {
        List<Integer> list = new ArrayList<Integer>();
        Integer _frontStart = 1;
        if (currentPage > num) {
            _frontStart = currentPage - num;
        }
        for (Integer i = _frontStart; i < currentPage; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 当前页的后num条页 假设当前页是 6 共有11页 如：7 8 9 10 11
     *
     * @return
     */
    public List<Integer> getNextPages() {
        List<Integer> list = new ArrayList<Integer>();
        Integer _endCount = num;
        if (num < totalPage && (currentPage + num) < totalPage) {
            _endCount = currentPage + _endCount;
        } else {
            _endCount = totalPage;
        }
        for (Integer i = currentPage + 1; i <= _endCount; i++) {
            list.add(i);
        }
        return list;
    }
}