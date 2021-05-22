package com.github.caijh.auth.server.admin.request;

import com.github.caijh.framework.core.model.AbstractListReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientAppListReqBody extends AbstractListReqBody {

    private String clientName;

}
