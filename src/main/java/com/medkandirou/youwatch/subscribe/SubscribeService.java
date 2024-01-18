package com.medkandirou.youwatch.subscribe;


import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    @Override
    public SubscribeDTOres save(SubscribeDTOreq entity) {
        Subscribe subscribe = modelMapper.map(entity, Subscribe.class);
        Channel channel = channelRepository.findById(entity.getSubscribeId().getChannel_follow().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Channel: " + entity.getSubscribeId().getChannel_follow().getId()));
        Channel channelFollowed = channelRepository.findById(entity.getSubscribeId().getChannel_follow().getId())
                .orElseThrow(() -> new ResourceNotFoundException("id Channel followed: " + entity.getSubscribeId().getChannel_follow().getId()));
        SubscribeId sId= new SubscribeId();
        sId.setChannel_follow(channel);
        sId.setChannel_followed(channelFollowed);
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
}
