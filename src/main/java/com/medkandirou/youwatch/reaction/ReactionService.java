package com.medkandirou.youwatch.reaction;



import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.helpers.Video_channel_Id;
import com.medkandirou.youwatch.video.Video;
import com.medkandirou.youwatch.video.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionService implements IReaction{
    private final ReactionRepository reactionRepository;
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    public ReactionService(ReactionRepository reactionRepository, ChannelRepository channelRepository, VideoRepository videoRepository, ModelMapper modelMapper) {
        this.reactionRepository = reactionRepository;
        this.channelRepository = channelRepository;
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ReactionDTOres findById(Video_channel_Id videoChannelId) {
        Reaction Reaction = reactionRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id video_channel : " + videoChannelId));
        return modelMapper.map(Reaction, ReactionDTOres.class);
    }

    @Override
    public List<ReactionDTOres> findAll() {
        List<Reaction> categories = reactionRepository.findAll();
        return categories.stream()
                .map(cat -> modelMapper.map(cat, ReactionDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReactionDTOres save(ReactionDTOreq entity) {
        Reaction reaction = modelMapper.map(entity, Reaction.class);
        Channel channel = channelRepository.findById(entity.getVideo_channel_id().getChannel().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Channel: " + entity.getVideo_channel_id().getChannel().getId()));
        Video video = videoRepository.findById(entity.getVideo_channel_id().getVideo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Video: " + entity.getVideo_channel_id().getVideo().getId()));
        Video_channel_Id video_channel_id= new Video_channel_Id();
        video_channel_id.setChannel(channel);
        video_channel_id.setVideo(video);
        reaction.setVideo_channel_id(video_channel_id);
        reaction.setReaction(entity.isReaction());
        reactionRepository.save(reaction);
        return modelMapper.map(reaction, ReactionDTOres.class);
    }

    @Override
    public ReactionDTOres update(ReactionDTOreq entity) {
        return null;
    }

    @Override
    public ReactionDTOreq deleteById(Video_channel_Id videoChannelId) {
        Reaction Reaction = reactionRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id Categorie: " + videoChannelId));
        reactionRepository.deleteById(videoChannelId);
        return modelMapper.map(Reaction, ReactionDTOreq.class);
    }
}
