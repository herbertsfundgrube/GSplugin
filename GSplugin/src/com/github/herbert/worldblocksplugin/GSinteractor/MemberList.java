/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.worldblocksplugin.GSinteractor;

import java.util.UUID;

/**
 *
 * @author Leen
 */
public class MemberList {
    Member g;
    MemberList n;
    public MemberList(Member g,MemberList l){
        this.g=g;
        this.n=l;
    }
    
    public MemberList getNext(){
        return n;
    }
    
    public Member getByUUID(UUID ident){
        if(g.getUUID().equals(ident)){
            return g;
        }
        else if(n==null){
            return null;
        }
        else{
            return n.getByUUID(ident);
        }
    }
    public void add(Member u){
    	if(n==null){
        	
    		n=new MemberList(u,null);
    	}
    	else{
    		n.add(u);
    	}
    }
    
    public void del(UUID ident){
        if(g.getUUID().equals(ident)){
            g=n.g;
            n=n.n;
        }
        else{
            MemberList next=n;
            MemberList p=this;
            while(next!=null){
                if(next.g.getUUID().equals(ident)){
                    p.n=next.n;
                    next=null;
                }
                else{
                    p=next;
                    next=next.n;
                }
            }
        }
    }
    
    @Override
    public String toString(){
        char c=(char)146;
        if(n==null){
            return (g.toString()+c);
        }
        else{
            return (g.toString()+c+n.toString());
        }
    }
}
