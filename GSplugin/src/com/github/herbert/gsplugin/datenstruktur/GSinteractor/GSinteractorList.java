/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.datenstruktur.GSinteractor;

import com.github.herbert.gsplugin.datenstruktur.GSinteractor.GSinteractor;

/**
 *
 * @author Leen
 */
public class GSinteractorList {
    GSinteractor g;
    GSinteractorList n;
    public GSinteractorList(GSinteractor g,GSinteractorList l){
        this.g=g;
        this.n=l;
    }
    public String getIdent(){
        return g.getIdent();
    }
    
    public void add(GSinteractor u){
    	if(n==null){
        	
    		n=new GSinteractorList(u,null);
    	}
    	else{
    		n.add(u);
    	}
        

    }
    
    public GSinteractor getByIdent(String ident){
        if(getIdent().equals(ident)){
            return g;
        }
        else if(n==null){
            return null;
        }
        else{
            return n.getByIdent(ident);
        }
    }
    @Override
    public String toString(){
        //Trennzeichen
        char c=(char)146;
        if(n==null){
            return (g.toString()+c);
        }
        else{
            return (g.toString()+c+n.toString()+c);
        }
    
    }
}
