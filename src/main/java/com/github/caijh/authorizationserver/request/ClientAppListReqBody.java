package com.github.caijh.authorizationserver.request;

import com.github.caijh.commons.base.AbstractListReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAppListReqBody extends AbstractListReqBody {

    private String clientName;

}
