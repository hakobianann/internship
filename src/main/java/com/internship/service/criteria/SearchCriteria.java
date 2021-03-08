package com.internship.service.criteria;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer size;

    private String sortField;
    private String sortDirection;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Pageable composePageRequest() {
        if (size != null && size == Integer.MAX_VALUE) {
            return null;
        }

        int page = this.page == null ? 0 : this.page;
        int size = this.size == null ? 20 : this.size;

        return PageRequest.of(page, size);
    }
}
