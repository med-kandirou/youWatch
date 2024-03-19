package com.medkandirou.youwatch.channel;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/channel")
@RequiredArgsConstructor
public class ChannelController {
    
    private final ChannelService channelService;

    @GetMapping
    public ResponseEntity<List<ChannelDTOres>> getAll(){
        return new ResponseEntity<>(channelService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChannelDTOres> save(@Valid @RequestBody ChannelDTOreq channelDTOReq){
        return new ResponseEntity<>(channelService.save(channelDTOReq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ChannelDTOres> update(@Valid @RequestBody ChannelDTOreq channelDTOReq){
        return new ResponseEntity<>(channelService.save(channelDTOReq), HttpStatus.OK);
    }

    @GetMapping(path = {"{channelId}"})
    public ResponseEntity<ChannelDTOres> findById(@PathVariable("channelId") Long channelId){
        return new ResponseEntity<>(channelService.findById(channelId), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{channelId}"})
    public ResponseEntity<ChannelDTOreq> deleteById(@PathVariable("channelId") Long channelId){
        return new ResponseEntity<>(channelService.deleteById(channelId), HttpStatus.OK);
    }

}
