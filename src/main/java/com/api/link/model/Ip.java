package com.api.link.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ip {
    @PrimaryKey
	private String ip;
    private String access;

    public Ip(){}
    public Ip(String ip){
        this.ip = ip;
        this.access = "";
    }
    public String getIp(){
        return this.ip;
    }

    public List<String> getAccess(){
        try {
            return new ArrayList<String>(Arrays.asList(this.access.split(",")));
        } catch (Exception e) {
            return new ArrayList<String>();
        }
    }
    public void add(String link){
        this.access += (link + ",");
    }
}
