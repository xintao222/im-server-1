package com.nd.im.service;

import com.nd.im.config.ActionGroupNames;
import com.nd.im.config.ActionNames;
import com.nd.im.entity.ServerUserEntity;
import com.nd.im.localservice.ServerUserLocalService;
import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.OutputConfig;
import com.wolf.framework.worker.context.MessageContext;
import java.util.List;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.INQUIRE_SERVER_USER,
        returnParameter = {
    @OutputConfig(name = "userId", typeEnum = TypeEnum.CHAR_32, desc = "用户id"),
    @OutputConfig(name = "userName", typeEnum = TypeEnum.CHAR_32, desc = "名称"),
    @OutputConfig(name = "type", typeEnum = TypeEnum.CHAR_32, desc = "类型")
},
        validateSession = true,
        page = true,
        response = true,
        group = ActionGroupNames.IM,
        description = "查询客服帐号")
public class InquireServerUserServiceImpl implements Service {

    @InjectLocalService()
    private ServerUserLocalService serverUserLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        long pageIndex = messageContext.getPageIndex();
        long pageSize = messageContext.getPageSize();
        List<ServerUserEntity> userEntityList = this.serverUserLocalService.inquireServerUser(pageIndex, pageSize);
        messageContext.setEntityListData(userEntityList);
        messageContext.success();
    }
}
