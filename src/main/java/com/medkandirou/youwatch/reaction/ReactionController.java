package com.medkandirou.youwatch.reaction;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="api/react")
public class ReactionController {

    private final ReactionService reactionService;
    private ReactionController(ReactionService reactionService){
        this.reactionService=reactionService;
    }

    @GetMapping
    public ResponseEntity<List<ReactionDTOres>> getAll(){
        return new ResponseEntity<>(reactionService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReactionDTOres> save(@Valid @RequestBody ReactionDTOreq reactionDTOreq){
        return new ResponseEntity<>(reactionService.save(reactionDTOreq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ReactionDTOres> update(@Valid @RequestBody ReactionDTOreq categorieId){
        return new ResponseEntity<>(reactionService.save(categorieId), HttpStatus.OK);
    }

    @GetMapping(path = {"{channelId}/{videoId}"})
    public ResponseEntity<ReactionDTOres> findById(@PathVariable("channelId") Long channelId,@PathVariable("videoId") Long videoId){
        Video_channel_Id id = new Video_channel_Id();
        Channel ch= new Channel();
        ch.setId(channelId);
        Video video= new Video();
        video.setId(videoId);
        return new ResponseEntity<>(reactionService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{channelId}/{videoId}"})
    public ResponseEntity<ReactionDTOreq> deleteById(@PathVariable("channelId") Long channelId,@PathVariable("videoId") Long videoId){
        Video_channel_Id id= new Video_channel_Id();
        Channel ch= new Channel();
        ch.setId(channelId);
        Video video= new Video();
        video.setId(videoId);
        return new ResponseEntity<>(reactionService.deleteById(id), HttpStatus.OK);
    }
}
