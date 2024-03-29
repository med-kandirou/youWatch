package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import com.medkandirou.youwatch.video.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements IComment{

    private final CommentRepository commentRepository;
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, ChannelRepository channelRepository, VideoRepository videoRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.channelRepository = channelRepository;
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDTOres findById(Video_channel_Id id) {
        return null;
    }

    @Override
    public List<CommentDTOres> findAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(c -> modelMapper.map(c, CommentDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTOres save(CommentDTOreq entity) {
        Comment comment = modelMapper.map(entity, Comment.class);
        Channel channel = channelRepository.findById(entity.getChannelId())
                .orElseThrow(() -> new ResourceNotFoundException("id Channel: " + entity.getChannelId()));
        Video video = videoRepository.findById(entity.getVideoId())
                .orElseThrow(() -> new ResourceNotFoundException("id Video: " + entity.getVideoId()));
        Video_channel_Id video_channel_id= new Video_channel_Id();
        video_channel_id.setChannel(channel);
        video_channel_id.setVideo(video);
        comment.setVideo_channel_id(video_channel_id);
        comment.setDatePosting(LocalDateTime.now());
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDTOres.class);
    }

    @Override
    public CommentDTOres update(CommentDTOreq entity) {
        return null;
    }

    @Override
    public CommentDTOreq deleteById(Video_channel_Id id) {
        /*Comment Comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id Categorie: " + id));
        commentRepository.deleteById(id);
        return modelMapper.map(Comment, CommentDTOreq.class);*/
        return null;
    }


    @Override
    public List<CommentDTOres> getCommentsByVideo(Long videoId) {
        List<Comment> comments = commentRepository.getCommentByVideoId(videoId);
        return comments.stream()
                .map(c -> modelMapper.map(c, CommentDTOres.class))
                .collect(Collectors.toList());
    }



}
