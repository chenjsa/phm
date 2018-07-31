package com.pms.base.common; 
import java.util.List;

public class PageBean
{
  private int pageSize;
  private int pageNo;
  private int totalRecords;
  private int totalPages;
  private int firstNo;
  private List<?> data;
  private String orderBy;
  private String order;

  public PageBean()
  {
    this.pageSize = 10;
    this.pageNo = 1;
    this.totalRecords = 0;
    this.data = null;
    this.totalPages = 0;
    this.firstNo = 1;
  }

  public PageBean(int pageSize, int pageNo, int totalRecords, List<?> data) {
    this.pageSize = pageSize;
    this.pageNo = pageNo;
    this.totalRecords = totalRecords;
    this.data = data;
    this.firstNo = ((pageNo - 1) * pageSize + 1);

    if (pageSize > 0) {
      this.totalPages = ((totalRecords - 1) / pageSize + 1);
      if (totalRecords == 0)
        this.totalPages = 1;
    }
  }

  public PageBean(int pageSize, int pageNo, int totalRecords, List<?> data, String orderBy, String order)
  {
    this(pageSize, pageNo, totalRecords, data);
    this.orderBy = orderBy;
    this.order = order;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNo() {
    return this.pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getTotalRecords() {
    return this.totalRecords;
  }

  public void setTotalRecords(int totalRecords) {
    this.totalRecords = totalRecords;
  }

  public int getTotalPages() {
    return this.totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public List<?> getData() {
    return this.data;
  }

  public void setData(List<?> data) {
    this.data = data;
  }

  public String getOrderBy() {
    return this.orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getOrder() {
    return this.order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public int getFirstNo() {
    return this.firstNo;
  }

  public void setFirstNo(int firstNo) {
    this.firstNo = firstNo;
  }
}