package com.medkandirou.youwatch.video;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Controller
@RequestMapping(path="api/video")
@RequiredArgsConstructor
public class VideoController {

    private final IVideo videoService;

    /*@GetMapping
    public ResponseEntity<List<VideoDTOres>> getAll(){
        return new ResponseEntity<>(videoService.findAll(), HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<Page<VideoDTOres>> paginate(Pageable pageable) {
        return new ResponseEntity<>(videoService.paginate(pageable), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
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

    @GetMapping(path = {"channel/{channelId}"})
    public ResponseEntity<List<VideoDTOres>> getvideoByChannel(@PathVariable("channelId") Long channelId){
        return new ResponseEntity<>(videoService.getvideoByChannel(channelId), HttpStatus.OK);
    }


    @GetMapping(path = {"search/{inputSearch}"})
    public ResponseEntity<List<VideoDTOres>> search(@PathVariable("inputSearch") String inputSearch){
        return new ResponseEntity<>(videoService.search(inputSearch), HttpStatus.OK);
    }

}
