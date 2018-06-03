/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.GS;

/**
 *
 * @author Leen
 */
public class CCoords {
    int x;
    int y;
    public CCoords(int x,int y){
        this.x=x;
        this.y=y;
    }
    public boolean equals(CCoords c){
        if(c.x==x&&c.y==y){
            return true;
        }
        return false;
    }
}
