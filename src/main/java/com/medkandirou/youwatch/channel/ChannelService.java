package com.medkandirou.youwatch.channel;


import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.category.CategoryRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public ChannelDTOres findById(Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id channel : " + id));
        return modelMapper.map(channel, ChannelDTOres.class);
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
    public ChannelDTOres update(ChannelDTOreq channelDTOReq) {
        Channel channel = channelRepository.findById(channelDTOReq.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with id: " + channelDTOReq.getId()));

        // Update the fields that are provided in the DTO
        if (channelDTOReq.getFirstname() != null) {
            channel.setFirstname(channelDTOReq.getFirstname());
        }

        if (channelDTOReq.getLastname() != null) {
            channel.setLastname(channelDTOReq.getLastname());
        }

        if (!channelDTOReq.getProfilImg().isEmpty()) {
            channel.setProfilImg(channelDTOReq.getProfilImg());
        }

        if (!channelDTOReq.getCoverImg().isEmpty()) {
            channel.setCoverImg(channelDTOReq.getCoverImg());
        }

        if (channelDTOReq.getEmail() != null) {
            channel.setEmail(channelDTOReq.getEmail());
        }

        if (!channelDTOReq.getPassword().isEmpty()) {
            channel.setPassword(passwordEncoder.encode(channelDTOReq.getPassword()));
        }

        return modelMapper.map(channelRepository.save(channel), ChannelDTOres.class);
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
