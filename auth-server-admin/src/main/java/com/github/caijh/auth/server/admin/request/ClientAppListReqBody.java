package com.github.caijh.auth.server.admin.request;

import com.github.caijh.framework.core.model.PageReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientAppListReqBody extends PageReqBody {

    private String clientName;

}
