package com.github.caijh.auth.server.admin.request;

import java.util.Set;

import com.github.caijh.auth.server.entity.MenuItem;
import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuReqBody extends MenuItem implements ReqBody {

    private static final long serialVersionUID = -6738607587957121011L;
    private Set<MenuItem> items;

}
