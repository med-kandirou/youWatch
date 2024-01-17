package com.medkandirou.youwatch.video;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="api/video")
public class VideoController {

    private final VideoService videoService;

    private VideoController(VideoService videoService){
        this.videoService=videoService;
    }

    @GetMapping
    public ResponseEntity<List<VideoDTOres>> getAll(){
        return new ResponseEntity<>(videoService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VideoDTOres> save(@Valid @RequestBody VideoDTOreq videoDTOReq){
        return new ResponseEntity<>(videoService.save(videoDTOReq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VideoDTOres> update(@Valid @RequestBody VideoDTOreq videoDTOReq){
        return new ResponseEntity<>(videoService.save(videoDTOReq), HttpStatus.OK);
    }

    @GetMapping(path = {"{videoId}"})
    public ResponseEntity<VideoDTOres> findById(@PathVariable("videoId") Long videoId){
        return new ResponseEntity<>(videoService.findById(videoId), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{videoId}"})
    public ResponseEntity<VideoDTOreq> deleteById(@PathVariable("videoId") Long videoId){
        return new ResponseEntity<>(videoService.deleteById(videoId), HttpStatus.OK);
    }
}
