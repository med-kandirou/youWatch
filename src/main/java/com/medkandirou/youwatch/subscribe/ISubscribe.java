package com.medkandirou.youwatch.subscribe;

import com.medkandirou.youwatch.helpers.IData;

import java.util.List;

public interface ISubscribe extends IData<SubscribeDTOreq, SubscribeDTOres, SubscribeId> {

    List<SubscribeDTOres> findSubscribeByChannelId(Long channelId);
}
