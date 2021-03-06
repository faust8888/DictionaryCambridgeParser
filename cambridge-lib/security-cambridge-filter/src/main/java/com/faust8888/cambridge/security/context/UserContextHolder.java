package com.faust8888.cambridge.security.context;

import org.springframework.util.Assert;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> USER_CONTEXT = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = USER_CONTEXT.get();
        if (context == null) {
            context = createEmptyContext();
            USER_CONTEXT.set(context);
        }
        return USER_CONTEXT.get();
    }

    private static final void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        USER_CONTEXT.set(context);
    }

    private static final UserContext createEmptyContext(){
        return new UserContext();
    }

}
