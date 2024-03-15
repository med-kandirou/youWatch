package com.medkandirou.youwatch.vue;


import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "api/vue")
@RequiredArgsConstructor
public class VueController {
    private final VueService vueService;

    /*private VueController(VueService vueService){
        this.vueService=vueService;
    }*/

    @GetMapping
    public ResponseEntity<List<VueDTOres>> getAll(){
        return new ResponseEntity<>(vueService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VueDTOres> save(@Valid @RequestBody VueDTOreq VueDTOReq){
        return new ResponseEntity<>(vueService.save(VueDTOReq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VueDTOres> update(@Valid @RequestBody VueDTOreq VueDTOReq){
        return new ResponseEntity<>(vueService.save(VueDTOReq), HttpStatus.OK);
    }

    @GetMapping(path = {"{channelId}/{videoId}"})
    public ResponseEntity<VueDTOres> findById(@PathVariable("channelId") Long channelId,@PathVariable("videoId") Long videoId){
        Video_channel_Id id = new Video_channel_Id();
        Channel ch= new Channel();
        ch.setId(channelId);
        Video video= new Video();
        video.setId(videoId);
        return new ResponseEntity<>(vueService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{channelId}/{videoId}"})
    public ResponseEntity<VueDTOreq> deleteById(@PathVariable("channelId") Long channelId,@PathVariable("videoId") Long videoId){
        Video_channel_Id id = new Video_channel_Id();
        Channel ch= new Channel();
        ch.setId(channelId);
        Video video= new Video();
        video.setId(videoId);
        return new ResponseEntity<>(vueService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"channel/{channelId}"})
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<VueDTOres>> findVueByChannelId(@PathVariable("channelId") Long channelId){
        return new ResponseEntity<>(vueService.findVueByChannelId(channelId), HttpStatus.OK);
    }
}
