package com.android.baoliyota.model.bean;

import java.util.List;

/**
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
         * reourceId : 1
         * resourceType : 1
         * resourceUrl : 测试内容5b6q
         */

        private long bookId;
        private String picUrl;
        private long resourceId;
        private int resourceType;
        private String resourceUrl;

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

        public long getReourceId() {
            return resourceId;
        }

        public void setReourceId(long resourceId) {
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
