package com.yinpei.peipeiaccountingsys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinpei.peipeiaccountingsys.entity.Notice;
import com.yinpei.peipeiaccountingsys.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    @Autowired
    NoticeMapper noticeMapper;
}
