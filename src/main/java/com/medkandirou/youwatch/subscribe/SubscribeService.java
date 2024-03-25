package com.medkandirou.youwatch.subscribe;


import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscribeService implements ISubscribe{
    private final SubscribeRepository subscribeRepository;
    private final ChannelRepository channelRepository;
    private final ModelMapper modelMapper;

    public SubscribeService(SubscribeRepository subscribeRepository, ChannelRepository channelRepository, ModelMapper modelMapper) {
        this.subscribeRepository = subscribeRepository;
        this.channelRepository = channelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public SubscribeDTOres findById(SubscribeId subscribeId) {
        Subscribe subscribe = subscribeRepository.findById(subscribeId)
                .orElseThrow(() -> new ResourceNotFoundException("subscribeId : " + subscribeId));
        return modelMapper.map(subscribe, SubscribeDTOres.class);
    }

    @Override
    public List<SubscribeDTOres> findAll() {
        List<Subscribe> subscribes = subscribeRepository.findAll();
        return subscribes.stream()
                .map(sub -> modelMapper.map(sub, SubscribeDTOres.class))
                .collect(Collectors.toList());
    }

    public SubscribeDTOres save(SubscribeDTOreq subscribeDTOReq) {
        Subscribe subscribe = modelMapper.map(subscribeDTOReq, Subscribe.class);
        Channel channel = channelRepository.findById(subscribeDTOReq.getSubscribeId().getChannelFollow().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found with ID: " + subscribeDTOReq.getSubscribeId().getChannelFollow().getId()));
        Channel channelFollowed = channelRepository.findById(subscribeDTOReq.getSubscribeId().getChannelFollowed().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Channel followed not found with ID: " + subscribeDTOReq.getSubscribeId().getChannelFollowed().getId()));
        SubscribeId subscribeId = new SubscribeId();
        subscribeId.setChannelFollow(channel);
        subscribeId.setChannelFollowed(channelFollowed);
        subscribe.setSubscribeId(subscribeId);
        subscribe.setDate(LocalDateTime.now());
        subscribeRepository.save(subscribe);
        return modelMapper.map(subscribe, SubscribeDTOres.class);
    }

    @Override
    public SubscribeDTOres update(SubscribeDTOreq entity) {
        return null;
    }

    @Override
    public SubscribeDTOreq deleteById(SubscribeId subscribeId) {
        Subscribe subscribe = subscribeRepository.findById(subscribeId)
                .orElseThrow(() -> new ResourceNotFoundException("id subscribe: " + subscribeId));
        subscribeRepository.deleteById(subscribeId);
        return modelMapper.map(subscribe, SubscribeDTOreq.class);
    }


    @Override
    public List<SubscribeDTOres> findSubscribeByChannelId(Long channelId) {
        List<Subscribe> subscribes = subscribeRepository.findSubscribeByChannelId(channelId);
        return subscribes.stream()
                .map(sub -> modelMapper.map(sub, SubscribeDTOres.class))
                .collect(Collectors.toList());
    }
}
