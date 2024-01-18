package com.medkandirou.youwatch.vue;

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
public class VueService implements IVue{
    private final VueRepository vueRepository;
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    public VueService(VueRepository vueRepository, ChannelRepository channelRepository, VideoRepository videoRepository, ModelMapper modelMapper) {
        this.vueRepository = vueRepository;
        this.channelRepository = channelRepository;
        this.videoRepository = videoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VueDTOres findById(Video_channel_Id videoChannelId) {
        Vue vue = vueRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id video_channel : " + videoChannelId));
        return modelMapper.map(vue, VueDTOres.class);
    }

    @Override
    public List<VueDTOres> findAll() {
        List<Vue> categories = vueRepository.findAll();
        return categories.stream()
                .map(v -> modelMapper.map(v, VueDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public VueDTOres save(VueDTOreq entity) {
        Vue vue = modelMapper.map(entity, Vue.class);
        Channel channel = channelRepository.findById(entity.getVideo_channel_id().getChannel().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Channel: " + entity.getVideo_channel_id().getChannel().getId()));
        Video video = videoRepository.findById(entity.getVideo_channel_id().getVideo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Video: " + entity.getVideo_channel_id().getVideo().getId()));
        Video_channel_Id video_channel_id= new Video_channel_Id();
        video_channel_id.setChannel(channel);
        video_channel_id.setVideo(video);
        vue.setVideo_channel_id(video_channel_id);
        vueRepository.save(vue);
        return modelMapper.map(vue, VueDTOres.class);
    }

    @Override
    public VueDTOres update(VueDTOreq entity) {
        return null;
    }

    @Override
    public VueDTOreq deleteById(Video_channel_Id videoChannelId) {
        Vue vue = vueRepository.findById(videoChannelId)
                .orElseThrow(() -> new ResourceNotFoundException("id Categorie: " + videoChannelId));
        vueRepository.deleteById(videoChannelId);
        return modelMapper.map(vue, VueDTOreq.class);
    }
}
