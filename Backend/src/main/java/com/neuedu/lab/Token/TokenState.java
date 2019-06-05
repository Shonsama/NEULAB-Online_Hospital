package com.neuedu.lab.Token;

public enum TokenState {
    /**
     * 过期
     */
    EXPIRED("EXPIRED"),
    /**
     * 无效(token不合法)
     */
    INVALID("INVALID"),
    /**
     * 有效的
     */
    VALID("VALID");

    private String state;

    TokenState (String state){
        this.state = state;
    }

    public static TokenState getTokenState(String tokenState){
        TokenState states[] = TokenState.values();
        TokenState ts = null;
        for(TokenState state : states){
            if(state.getState().equals(tokenState)){
                ts = state;
                break;
            }

        }
        return ts;
    }

    public String getState() {
        return state;
    }

    public String toString() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
