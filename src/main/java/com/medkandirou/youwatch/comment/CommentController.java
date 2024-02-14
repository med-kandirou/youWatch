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

public class CommentController {

    private final CommentService commentService;

    private CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTOres>> getAll(){
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDTOres> save(@Valid @RequestBody CommentDTOreq categorieId){
        return new ResponseEntity<>(commentService.save(categorieId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CommentDTOres> update(@Valid @RequestBody CommentDTOreq categorieId){
        return new ResponseEntity<>(commentService.save(categorieId), HttpStatus.OK);
    }

    @GetMapping(path = {"{channelId}/{videoId}"})
    public ResponseEntity<CommentDTOres> findById(@PathVariable("channelId") Long channelId,@PathVariable("videoId") Long videoId){
        Video_channel_Id id= new Video_channel_Id();
        Channel ch= new Channel();
        ch.setId(channelId);
        Video video= new Video();
        video.setId(videoId);
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{channelId}/{videoId}"})
    public ResponseEntity<CommentDTOreq> deleteById(@PathVariable("channelId") Long channelId,@PathVariable("videoId") Long videoId){
        Video_channel_Id id= new Video_channel_Id();
        Channel ch= new Channel();
        ch.setId(channelId);
        Video video= new Video();
        video.setId(videoId);
        return new ResponseEntity<>(commentService.deleteById(id), HttpStatus.OK);
    }
}
