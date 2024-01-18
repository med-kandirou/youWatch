package com.medkandirou.youwatch.subscribe;

import com.medkandirou.youwatch.channel.Channel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SubscribeController {

    private final SubscribeService subscribeService;

    private SubscribeController(SubscribeService subscribeService){
        this.subscribeService=subscribeService;
    }

    @GetMapping
    public ResponseEntity<List<SubscribeDTOres>> getAll(){
        return new ResponseEntity<>(subscribeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubscribeDTOres> save(@Valid @RequestBody SubscribeDTOreq SubscribeDTOReq){
        return new ResponseEntity<>(subscribeService.save(SubscribeDTOReq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubscribeDTOres> update(@Valid @RequestBody SubscribeDTOreq SubscribeDTOReq){
        return new ResponseEntity<>(subscribeService.save(SubscribeDTOReq), HttpStatus.OK);
    }

    @GetMapping(path = {"{channelId}/{channelFollowedId}"})
    public ResponseEntity<SubscribeDTOres> findById(@PathVariable("channelId") Long channelId, @PathVariable("channelFollowedId") Long channelFollowedId){
        SubscribeId subid = new SubscribeId();
        Channel channel= new Channel();
        channel.setId(channelId);
        Channel channelFollowed= new Channel();
        channelFollowed.setId(channelFollowedId);
        subid.setChannel_follow(channel);
        subid.setChannel_followed(channelFollowed);
        return new ResponseEntity<>(subscribeService.findById(subid), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{channelId}/{channelFollowedId}"})
    public ResponseEntity<SubscribeDTOreq> deleteById(@PathVariable("channelId") Long channelId, @PathVariable("channelFollowedId") Long channelFollowedId){
        SubscribeId subid = new SubscribeId();
        Channel channel= new Channel();
        channel.setId(channelId);
        Channel channelFollowed= new Channel();
        channelFollowed.setId(channelFollowedId);
        subid.setChannel_follow(channel);
        subid.setChannel_followed(channelFollowed);
        return new ResponseEntity<>(subscribeService.deleteById(subid), HttpStatus.OK);
    }
}
