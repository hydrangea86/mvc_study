package com.spring.mvc.reply.controller;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    /*
        - 댓글 목록 요청: /api/v1/reply       GET       실무형 ex-> /api/vi/reply GET
        - 댓글 쓰기 요청: /api/v1/reply       POST
        - 댓글 수정 요청: /api/v1/reply/3     PUT
        - 댓글 삭제 요청 /api/v1/reply/14     DELETE
     */

    //댓글 목록 요청 처리
    @GetMapping("")
    public Map<String, Object> list(Long boardNo, Page page) {
        log.info("api/v1/reply GET!");
        Map<String, Object> replies = replyService.getList(boardNo, page);
        return replies;
    }

    //댓글 등록 요청 처리 [jason 이용 클라이언트가 웹브라우저가 아닐시 (비동기 통신) api 방식 @RequestBody 붙이기]
    @PostMapping("")
    public String create(@RequestBody Reply reply) {
        log.info("/api/v1/reply POST !!");
        return replyService.write(reply) ? "insert-success" : "insert-fail";
    }

    //댓글 삭제 요청 처리
    @DeleteMapping("/{rno}")
    public String remove(@PathVariable("rno") Long replyNo) {
        log.info("/api/v1/reply DELETE!!" + replyNo);
        return replyService.remove(replyNo) ? "delete-success" : "delete-fail";
    }

    //댓글 수정 요청 [jason 이용 클라이언트가 웹브라우저가 아닐시 (비동기 통신) api 방식 @RequestBody 붙이기]
    @PutMapping("/{rno}")
    public String modify(@PathVariable("rno") Long replyNo, @RequestBody Reply reply) {
        log.info("/api/v1/reply/ PUT!! ");
        reply.setReplyNo(replyNo);
        return replyService.modify(reply) ? "modify-success" : "modify-fail";
    }

}
