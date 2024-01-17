package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.category.CategoryRepository;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService implements IVideo{

    private final VideoRepository videoRepository;
    private final ChannelRepository channelRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public VideoService(VideoRepository videoRepository, ChannelRepository channelRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.videoRepository = videoRepository;
        this.channelRepository = channelRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VideoDTOres findById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id Video : " + id));
        return modelMapper.map(video, VideoDTOres.class);
    }

    @Override
    public List<VideoDTOres> findAll() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .map(question -> modelMapper.map(question, VideoDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public VideoDTOres save(VideoDTOreq entity) {
        Video video= modelMapper.map(entity, Video.class);
        Channel channel= channelRepository.findById(entity.getChannelId())
                .orElseThrow(() -> new ResourceNotFoundException("id video : " + entity.getChannelId()));
        Category category = categoryRepository.findById(entity.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("id categorie : " + entity.getCategoryId()));
        video.setCategory(category);
        video.setChannel(channel);
        videoRepository.save(video);
        return modelMapper.map(video, VideoDTOres.class);
    }

    @Override
    public VideoDTOres update(VideoDTOreq entity) {
        return null;
    }

    @Override
    public VideoDTOreq deleteById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id Video: " + id));
        videoRepository.deleteById(id);
        return modelMapper.map(video, VideoDTOreq.class);
    }
}
