package com.medkandirou.youwatch.comment;


import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTOres>> getAll(){
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDTOres> save(@Valid @RequestBody CommentDTOreq commentDTOreq){
        return new ResponseEntity<>(commentService.save(commentDTOreq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CommentDTOres> update(@Valid @RequestBody CommentDTOreq categorieId){
        return new ResponseEntity<>(commentService.save(categorieId), HttpStatus.OK);
    }


    @GetMapping(path = {"video/{videoId}"})
    public ResponseEntity<List<CommentDTOres>> getCommentsByVideo(@PathVariable("videoId") Long videoId){
        return new ResponseEntity<>(commentService.getCommentsByVideo(videoId), HttpStatus.OK);

    }
}
