package com.medkandirou.youwatch.subscribe;

import com.medkandirou.youwatch.channel.Channel;
import jakarta.validation.Valid;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/subscribe")
@RequiredArgsConstructor
public class SubscribeController {

    private final SubscribeService subscribeService;


    @GetMapping
    public ResponseEntity<List<SubscribeDTOres>> getAll(){
        return new ResponseEntity<>(subscribeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubscribeDTOres> save(@Valid @RequestBody SubscribeDTOreq subscribeDTOReq){
        return new ResponseEntity<>(subscribeService.save(subscribeDTOReq), HttpStatus.OK);
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
        subid.setChannelFollow(channel);
        subid.setChannelFollowed(channelFollowed);
        return new ResponseEntity<>(subscribeService.findById(subid), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{channelId}/{channelFollowedId}"})
    public ResponseEntity<SubscribeDTOreq> deleteById(@PathVariable("channelId") Long channelId, @PathVariable("channelFollowedId") Long channelFollowedId){
        SubscribeId subid = new SubscribeId();
        Channel channel= new Channel();
        channel.setId(channelId);
        Channel channelFollowed= new Channel();
        channelFollowed.setId(channelFollowedId);
        subid.setChannelFollow(channel);
        subid.setChannelFollowed(channelFollowed);
        return new ResponseEntity<>(subscribeService.deleteById(subid), HttpStatus.OK);
    }



    @GetMapping(path = {"/channel/{channelId}"})
    public ResponseEntity<List<SubscribeDTOres>> findSubscribeByChannelId(@PathVariable Long channelId) {
        return new ResponseEntity<>(subscribeService.findSubscribeByChannelId(channelId), HttpStatus.OK);
    }
}
