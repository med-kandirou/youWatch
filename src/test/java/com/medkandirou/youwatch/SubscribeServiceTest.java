package com.medkandirou.youwatch;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.subscribe.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscribeServiceTest {

    @Mock
    private SubscribeRepository subscribeRepository;

    @Mock
    private ChannelRepository channelRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SubscribeService subscribeService;

    @Test
    void findAll_existingSubscriptions_returnListOfSubscriptions() {
        // Arrange
        List<Subscribe> subscriptions = Arrays.asList(new Subscribe(), new Subscribe());
        when(subscribeRepository.findAll()).thenReturn(subscriptions);
        when(modelMapper.map(any(), eq(SubscribeDTOres.class))).thenReturn(new SubscribeDTOres());

        // Act
        List<SubscribeDTOres> result = subscribeService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(subscriptions.size(), result.size());
        verify(subscribeRepository).findAll();
        verify(modelMapper, times(subscriptions.size())).map(any(), eq(SubscribeDTOres.class));
    }

    @Test
    void save_validSubscriptionDTO_entitySavedAndMappedToDTO() {
        SubscribeDTOreq subscribeDTOreq = new SubscribeDTOreq();
        Channel channelFollow = new Channel();
        Channel channelFollowed = new Channel();
        SubscribeId id= new SubscribeId();
        id.setChannelFollow(channelFollow);
        id.setChannelFollowed(channelFollowed);
        subscribeDTOreq.setSubscribeId(id);
        Subscribe subscribe = new Subscribe();
        when(modelMapper.map(subscribeDTOreq, Subscribe.class)).thenReturn(subscribe);
        when(channelRepository.findById(channelFollow.getId())).thenReturn(Optional.of(channelFollow));
        when(channelRepository.findById(channelFollowed.getId())).thenReturn(Optional.of(channelFollowed));
        when(subscribeRepository.save(subscribe)).thenReturn(subscribe);
        when(modelMapper.map(subscribe, SubscribeDTOres.class)).thenReturn(new SubscribeDTOres());

        SubscribeDTOres result = subscribeService.save(subscribeDTOreq);

        assertNotNull(result);
        verify(modelMapper).map(subscribeDTOreq, Subscribe.class);
        verify(channelRepository).findById(channelFollow.getId());
        verify(channelRepository).findById(channelFollowed.getId());
        verify(subscribeRepository).save(subscribe);
        verify(modelMapper).map(subscribe, SubscribeDTOres.class);
    }


    @Test
    void deleteById_existingSubscriptionId_subscriptionDeletedAndMappedToDTO() {
        SubscribeId subscribeId = new SubscribeId();
        Subscribe subscribe = new Subscribe();
        when(subscribeRepository.findById(subscribeId)).thenReturn(Optional.of(subscribe));
        when(modelMapper.map(subscribe, SubscribeDTOreq.class)).thenReturn(new SubscribeDTOreq());

        SubscribeDTOreq result = subscribeService.deleteById(subscribeId);

        assertNotNull(result);
        verify(subscribeRepository).findById(subscribeId);
        verify(subscribeRepository).deleteById(subscribeId);
        verify(modelMapper).map(subscribe, SubscribeDTOreq.class);
    }

}
