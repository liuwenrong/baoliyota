package com.android.baoliyota.model.bean;

import java.util.List;

/**
 * Bean类,服务器获取的锁屏和图书推荐的封装类
 *
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */

public class EpdScreenBean {

    /**
     * code : 1
     * data : [{"bookId":1,"picUrl":"测试内容q74t","reourceId":1,"resourceType":1,"resourceUrl":"测试内容5b6q"}]
     * msg : 测试内容tyh6
     */
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bookId : 1
         * picUrl : 测试内容q74t
         * resourceId : 1
         * resourceType : 1
         * resourceUrl : 测试内容5b6q
         * invalidTime : 14935...
         * validTime : 149...
         */

        private long bookId;
        private String picUrl;
        private long resourceId;
        private int resourceType;
        private String resourceUrl;
        private long validTime;
        private long invalidTime;

        public long getValidTime() {
            return validTime;
        }

        public void setValidTime(long validTime) {
            this.validTime = validTime;
        }

        public long getInvalidTime() {
            return invalidTime;
        }

        public void setInvalidTime(long invalidTime) {
            this.invalidTime = invalidTime;
        }

        public long getBookId() {
            return bookId;
        }

        public void setBookId(long bookId) {
            this.bookId = bookId;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public long getResourceId() {
            return resourceId;
        }

        public void setResourceId(long resourceId) {
            this.resourceId = resourceId;
        }

        public int getResourceType() {
            return resourceType;
        }

        public void setResourceType(int resourceType) {
            this.resourceType = resourceType;
        }

        public String getResourceUrl() {
            return resourceUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }
    }
}
