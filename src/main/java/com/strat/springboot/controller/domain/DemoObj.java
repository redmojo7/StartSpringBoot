package com.strat.springboot.controller.domain;

/**
 * @author : Donald
 * @date : 2017/12/5 15:57.
 * @description :
 */
public class DemoObj {
    private Long id;
    private String name;
    
    public DemoObj() { //①
        super();
    }
    public DemoObj(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
