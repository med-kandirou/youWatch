package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements IComment{

    private final CommentRepository CommentRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository CommentRepository, ModelMapper modelMapper) {
        this.CommentRepository = CommentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDTOres findById(Video_channel_Id videoChannelId) {
        Comment Comment = CommentRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id video_channel : " + videoChannelId));
        return modelMapper.map(Comment, CommentDTOres.class);
    }

    @Override
    public List<CommentDTOres> findAll() {
        List<Comment> categories = CommentRepository.findAll();
        return categories.stream()
                .map(cat -> modelMapper.map(cat, CommentDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTOres save(CommentDTOreq entity) {
        Comment Comment = modelMapper.map(entity, Comment.class);
        CommentRepository.save(Comment);
        return modelMapper.map(Comment, CommentDTOres.class);
    }

    @Override
    public CommentDTOres update(CommentDTOreq entity) {
        return null;
    }

    @Override
    public CommentDTOreq deleteById(Video_channel_Id videoChannelId) {
        Comment Comment = CommentRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id Categorie: " + videoChannelId));
        CommentRepository.deleteById(videoChannelId);
        return modelMapper.map(Comment, CommentDTOreq.class);
    }

}
