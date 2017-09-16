package com.android.baoliyota.model.bean;

import java.util.List;

/**
 * Bean类,服务器获取的锁屏和图书推荐的封装类
 * @author liuwenrong@coolpad.com
 * @version 1.0, 2017/3/20
 */

public class EpdScreenBean {

    /**
     * code : 1
     * data : [{"bookId":1,"picUrl":"测试内容q74t","reourceId":1,"resourceType":1,"coverUrl":"测试内容5b6q"}]
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
         * author : "xx"
         * bookId : 1
         * bookName : "芈月传"
         * briefIntroduction : "..."
         * coverUrl : xx.jpg
         * downUrl : xx
         * fileFormat : 2
         * resourceDescription : xx
         * picUrl : http://...jpg
         * resourceId : 1
         * resourceType : 1
         * invalidTime : 14935...
         * validTime : 149...
         */

        private String author;
        private long bookId;
        private String bookName;
        private String briefIntroduction;
        private String coverUrl;
        private String downUrl;
        private String picUrl;
        private int fileFormat;
        private String resourceDescription;
        private long resourceId;
        private int resourceType;
        private long validTime;
        private long invalidTime;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBriefIntroduction() {
            return briefIntroduction;
        }

        public void setBriefIntroduction(String briefIntroduction) {
            this.briefIntroduction = briefIntroduction;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public int getFileFormat() {
            return fileFormat;
        }

        public void setFileFormat(int fileFormat) {
            this.fileFormat = fileFormat;
        }

        public String getResourceDescription() {
            return resourceDescription;
        }

        public void setResourceDescription(String resourceDescription) {
            this.resourceDescription = resourceDescription;
        }

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

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }
    }
}
