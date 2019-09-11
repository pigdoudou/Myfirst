package com.sc.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 11:362019/8/30
 * @Description:
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private Boolean showNextPage;
    private Boolean showPrePage;
    private Boolean showFirstPage;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage=0;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        this.page=page;

        if (totalPage <= 7) {
            for (int i = 1; i <= totalPage; i++) {
                pages.add(i);
            }
        } else {
            if (page <= 4) {
                int count = 0;
                for (int i = 1; i <= page; i++) {
                    pages.add(i);
                    count++;
                }
                for (int i = page + 1; i <= page + 7 - count; i++) {
                    pages.add(i);
                }

            } else {
                if (page + 3 <= totalPage) {
                    for (int i = page - 3; i <= page + 3; i++) {
                        pages.add(i);
                    }
                } else {
                    for (int i = totalPage-6; i <= totalPage; i++) {
                        pages.add(i);
                    }
                }
            }
        }


        if (page == 1) {
            showPrePage = false;
        } else {
            showPrePage = true;
        }

        if (page == totalPage) {
            showNextPage = false;
        } else {
            showNextPage = true;
        }

        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
