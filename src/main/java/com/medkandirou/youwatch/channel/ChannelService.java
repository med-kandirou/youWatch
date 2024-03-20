package com.medkandirou.youwatch.channel;


import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.category.CategoryRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelService implements IChannel{

    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public ChannelDTOres findById(Long id) {
        Channel question = channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id channel : " + id));
        return modelMapper.map(question, ChannelDTOres.class);
    }

    @Override
    public List<ChannelDTOres> findAll() {
        List<Channel> channels = channelRepository.findAll();
        return channels.stream()
                .map(question -> modelMapper.map(question, ChannelDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChannelDTOres save(ChannelDTOreq entity) {
        Channel channel= modelMapper.map(entity, Channel.class);
        channelRepository.save(channel);
        return modelMapper.map(channel, ChannelDTOres.class);
    }

    @Override
    public ChannelDTOres update(ChannelDTOreq entity) {
        return null;
    }

    @Override
    public ChannelDTOreq deleteById(Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id channel: " + id));
        channelRepository.deleteById(id);
        return modelMapper.map(channel, ChannelDTOreq.class);
    }



    @Override
    public List<Integer> statistiques() {
        List<Integer> statistics = new ArrayList<>();
        Integer channelCount = channelRepository.findAll().size();
        Integer videoCount = videoRepository.findAll().size();
        Integer categoryCount = categoryRepository.findAll().size();
        statistics.add(channelCount);
        statistics.add(videoCount);
        statistics.add(categoryCount);
        return statistics;
    }


}
