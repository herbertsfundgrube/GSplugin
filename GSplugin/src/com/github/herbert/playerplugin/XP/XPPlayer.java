/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.playerplugin.XP;

import java.util.UUID;

/**
 *
 * @author Lumberjack
 */
public class XPPlayer {
    /*Felder:
    0:Minen
    1:Holzfällen
    2:Ackerbau
    3:Bauen
    4:Handwerk
    5:Handel
    6:PlayerXP
    */
    int[] XP =new int[7];
    int[] Level =new int[7];
    int[] MaxLevel =new int[7];
    UUID id;
    public XPPlayer(UUID id){
        this.id=id;
    }
    
    public void incXP(int feld,int menge){
        XP[feld]+=menge;
        if(XP[feld]>=((Level[feld]*4000)/(Level[feld]+50))){
            if(feld==6){
                //playerLevelup
                XP[feld]=0;
                Level[feld]++;
            }
            else if(MaxLevel[feld]==Level[feld]){
                //Wenn maxlevel erreicht
                XP[6]+=(XP[feld]-((Level[feld]*4000)/(Level[feld]+50)));
                XP[feld]=((Level[feld]*4000)/(Level[feld]+50));
            }
            else{
                //Level up
                XP[feld]=0;
                Level[feld]++;
            }
        }
    }
    public String getRemainingXP(int feld){
        return ""+(((Level[feld]*4000)/(Level[feld]+50))-XP[feld])+"/"+(Level[feld]*4000)/(Level[feld]+50);
    }
    
    
    
}
