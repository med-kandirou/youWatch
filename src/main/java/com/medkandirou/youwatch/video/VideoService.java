package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.category.CategoryRepository;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
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
                .map(video -> modelMapper.map(video, VideoDTOres.class))
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
        video.setDatePosting(LocalDateTime.now());
        video.setNbrLikes(0);
        video.setNbrVues(0);
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

    @Override
    public Page<VideoDTOres> paginate(Pageable pageable) {
        return videoRepository.findAll(pageable)
                .map(video -> modelMapper.map(video, VideoDTOres.class));
    }


    @Override
    public List<VideoDTOres> getvideoByChannel(Long channelId) {
        List<Video> videos = videoRepository.findVideoByChannelId(channelId);
        return videos.stream()
                .map(video -> modelMapper.map(video, VideoDTOres.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<VideoDTOres> search(String inputSearch) {
        List<Video> videos = videoRepository.search(inputSearch);
        return videos.stream()
                .map(video -> modelMapper.map(video, VideoDTOres.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<VideoDTOres> trending() {
        List<Video> videos = videoRepository.trending();
        return videos.stream()
                .map(video -> modelMapper.map(video, VideoDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoDTOres> findByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        return videoRepository.findByCategory(category).stream()
                .map(video -> modelMapper.map(video, VideoDTOres.class))
                .collect(Collectors.toList());
    }

}
