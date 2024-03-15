package com.medkandirou.youwatch.vue;

import com.medkandirou.youwatch.helpers.IData;
import com.medkandirou.youwatch.helpers.Video_channel_Id;

import java.util.List;


public interface IVue extends IData<VueDTOreq,VueDTOres, Video_channel_Id> {
    List<VueDTOres> findVueByChannelId(Long channelId);
}
