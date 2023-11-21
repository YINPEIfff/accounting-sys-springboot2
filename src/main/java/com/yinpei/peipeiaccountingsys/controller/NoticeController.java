package com.yinpei.peipeiaccountingsys.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinpei.peipeiaccountingsys.common.Result;
import com.yinpei.peipeiaccountingsys.entity.Notice;
import com.yinpei.peipeiaccountingsys.entity.User;
import com.yinpei.peipeiaccountingsys.service.NoticeService;
import com.yinpei.peipeiaccountingsys.service.UserService;
import com.yinpei.peipeiaccountingsys.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result addNotice(@RequestBody Notice notice) {
        User currentUser = TokenUtils.getCurrentUser();
        notice.setUserId(currentUser.getId());
        notice.setTime(DateUtil.now());
        noticeService.save(notice);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteNotice(@PathVariable Integer id) {
        noticeService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/all")
    public Result deleteNotices(@RequestBody List<Integer> ids) {
        noticeService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAllNotices() {
        List<Notice> noticeList = noticeService.list(new QueryWrapper<Notice>().orderByDesc("id"));
        for (Notice notice : noticeList)
            notice.setUser(userService.getById(notice.getUserId()));
        return Result.success(noticeList);
    }

    @GetMapping("/select/{id}")
    public Result selectNoticeById(@PathVariable Integer id) {
        Notice dbNotice = noticeService.getById(id);
        User dbUserByNotice = userService.getById(dbNotice.getUserId());
        dbNotice.setUser(dbUserByNotice);
        return Result.success(dbNotice);
    }

    @PutMapping("/update")
    public Result updateNoticeById(@RequestBody Notice notice){
        noticeService.updateById(notice);
        return Result.success();
    }

    @GetMapping("/selectByPage")
    public Result selectNoticeByPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam String title) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<Notice>().orderByDesc("id"); //倒序
        queryWrapper.like(StrUtil.isNotBlank(title), "title", title);
        Page<Notice> page = noticeService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Notice> records = page.getRecords();
        for (Notice record : records) {
            Integer userId = record.getUserId();
            User dbUser = userService.getById(userId);
            if (dbUser!=null){
                record.setUser(dbUser);
            }
        }
        return Result.success(page);

    }

}
