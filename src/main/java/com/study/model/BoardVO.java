package com.study.model;

import java.sql.Date;

public class BoardVO {
    private int bId, viewCnt, delYn;
    private String category, writer, pw, title, content;
    private String file1 = "";
    private String file2 = "";
    private String file3 = "";
    private Date delDt, modDt, regDt;

    public BoardVO() {}

    public BoardVO(int bId, int viewCnt, int delYn, String category, String writer, String pw, String title, String content, String file1, String file2, String file3, Date delDt, Date modDt, Date regDt) {
        super();
        this.bId = bId;
        this.viewCnt = viewCnt;
        this.delYn = delYn;
        this.category = category;
        this.writer = writer;
        this.pw = pw;
        this.title = title;
        this.content = content;
        if (file1 != null)
            this.file1 = file1;
        if (file2 != null)
            this.file2 = file2;
        if (file1 != null)
        this.file3 = file3;
        this.delDt = delDt;
        this.modDt = modDt;
        this.regDt = regDt;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public int getDelYn() {
        return delYn;
    }

    public void setDelYn(int delYn) {
        this.delYn = delYn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getFile3() {
        return file3;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public Date getDelDt() {
        return delDt;
    }

    public void setDelDt(Date delDt) {
        this.delDt = delDt;
    }

    public Date getModDt() {
        return modDt;
    }

    public void setModDt(Date modDt) {
        this.modDt = modDt;
    }

    public Date getRegDt() {
        return regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }
}
