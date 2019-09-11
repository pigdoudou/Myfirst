package com.sc.community.service;

import com.sc.community.dto.NoticeDTO;
import com.sc.community.dto.PaginationDTO;
import com.sc.community.dto.QuestionDTO;
import com.sc.community.mapper.NoticeMapper;
import com.sc.community.mapper.UserMapper;
import com.sc.community.model.Notice;
import com.sc.community.model.Question;
import com.sc.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 13:152019/9/11
 * @Description:
 */
@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer id,Integer page,Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = noticeMapper.myCount(id);
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1 || paginationDTO.getTotalPage() == 0) {
            page = 1;
        } else if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Notice> notice = noticeMapper.myNoticeList(id, offset, size);
        if(notice.size()==0){return paginationDTO;}
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for (Notice notices: notice) {
            NoticeDTO noticeDTO = new NoticeDTO();
            BeanUtils.copyProperties(notices, noticeDTO);
            User user = userMapper.findById(notices.getNotifier());
            noticeDTO.setUser(user);
            noticeDTOList.add(noticeDTO);
        }
        paginationDTO.setData(noticeDTOList);
        paginationDTO.setPage(page);
        return paginationDTO;
    }
}
