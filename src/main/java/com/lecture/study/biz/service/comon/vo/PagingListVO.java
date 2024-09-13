package com.lecture.study.biz.service.comon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingListVO<T> {

    // 현재 페이지
    private Integer page = 0;

    // 페이지당 보여줄 데이터 수
    private Integer record = 0;

    // 총 데이터 수
    private Integer total = 0;

    // 총 페이지 수
    private Integer totalPage = 0;

    // 화면의 페이지 끝 번호
    private Integer startPage = 0;

    // 화면의 페이지 시작 번호
    private Integer endPage = 0;

    // 이전 페이지 유무
    private boolean prev = false;

    // 다음 페이지 유무
    private boolean next = false;

    // DB 리스트 데이터
    private List<T> list = new ArrayList<>();

    /**
     * PagingVO 생성자 세팅
     * @param pageVO
     * @param total
     */
    public PagingListVO(PageVO pageVO, List<T> list, Integer total) {

        this.total = total;
        this.list = list == null ? new ArrayList<>() : list;

        if(pageVO.getPage() != null && pageVO.getRecord() != null) {
            this.page = pageVO.getPage();
            this.record = pageVO.getRecord();

            // 총 페이지 수 세팅
            this.totalPage = (int) (Math.ceil((total * 1.0) / pageVO.getRecord()));

            // 임시 끝번호, 시작번호 세팅
            this.endPage = (int) (Math.ceil(pageVO.getPage() / (pageVO.getPageSize() * 1.0))) * pageVO.getPageSize();
            this.startPage = this.endPage - (pageVO.getPageSize() - 1);

            // 끝번호 유효성 확인
            if(totalPage < this.endPage) {
                this.endPage = totalPage;
            }

            // prev, next 확인
            this.prev = this.startPage > 1;
            this.next = this.endPage < totalPage;
        }

        // request의 page 혹은 record가 null일 경우
        // -> 1 page에 전체 리스트를 세팅
        else {
            this.page = 1;
            this.record = null;

            this.totalPage = 1;
            this.startPage = 1;
            this.endPage = 1;

            this.prev = false;
            this.next = false;
        }
    }
}
