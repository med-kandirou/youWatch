package com.medkandirou.youwatch.channel;

import com.medkandirou.youwatch.helpers.IData;

import java.util.HashMap;
import java.util.List;

public interface IChannel extends IData<ChannelDTOreq,ChannelDTOres,Long> {
    List<Integer> statistiques();
}
