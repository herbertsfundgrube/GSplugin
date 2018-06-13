/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.playerplugin.XP;

/**
 *
 * @author Lumberjack
 */
public class XPPlayerlist {
    XPPlayer p;
    XPPlayerlist n;
    public XPPlayerlist(XPPlayer p,XPPlayerlist n){
        this.p=p;
        this.n=n;
    }
}
