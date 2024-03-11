package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import com.medkandirou.youwatch.video.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public CommentDTOres findById(Video_channel_Id videoChannelId) {
        Comment Comment = commentRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id video_channel : " + videoChannelId));
        return modelMapper.map(Comment, CommentDTOres.class);
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
        Channel channel = channelRepository.findById(entity.getVideo_channel_id().getChannel().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Channel: " + entity.getVideo_channel_id().getChannel().getId()));
        Video video = videoRepository.findById(entity.getVideo_channel_id().getVideo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Video: " + entity.getVideo_channel_id().getVideo().getId()));
        Video_channel_Id video_channel_id= new Video_channel_Id();
        video_channel_id.setChannel(channel);
        video_channel_id.setVideo(video);
        comment.setVideo_channel_id(video_channel_id);
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDTOres.class);
    }

    @Override
    public CommentDTOres update(CommentDTOreq entity) {
        return null;
    }

    @Override
    public CommentDTOreq deleteById(Video_channel_Id videoChannelId) {
        Comment Comment = commentRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id Categorie: " + videoChannelId));
        commentRepository.deleteById(videoChannelId);
        return modelMapper.map(Comment, CommentDTOreq.class);
    }


    @Override
    public List<CommentDTOres> getCommentsByVideo(Long videoId) {
        List<Comment> comments = commentRepository.getCommentByVideoId(videoId);
        return comments.stream()
                .map(c -> modelMapper.map(c, CommentDTOres.class))
                .collect(Collectors.toList());
    }



}
