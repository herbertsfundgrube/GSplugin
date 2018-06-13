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
    6:Jagd
    7:PlayerXP
    */
    int[] XP =new int[8];
    int[] Level =new int[8];
    int[] MaxLevel =new int[8];
    int skillp;
    UUID id;
    public XPPlayer(UUID id){
        this.id=id;
        for(int i=0;i<XP.length;i++){
            XP[i]=0;
            Level[i]=1;
            MaxLevel[i]=1;
            skillp=1;
        }
    }
    
    public void incXP(int feld,int menge){
        XP[feld]+=menge;
        if(XP[feld]>=((Level[feld]*4000)/(Level[feld]+50))){
            if(feld==7){
                //playerLevelup
                XP[feld]=0;
                Level[feld]++;
                skillp++;
            }
            else if(MaxLevel[feld]==Level[feld]){
                //Wenn maxlevel erreicht
                incXP(7,XP[feld]-((Level[feld]*4000)/(Level[feld]+50)));
                XP[feld]=((Level[feld]*4000)/(Level[feld]+50));
            }
            else{
                //Level up
                XP[feld]=0;
                Level[feld]++;
            }
        }
    }
    
    
    public boolean incMaxLvl(int feld){
        if(skillp>0){
            MaxLevel[feld]++;
            return true;
        }
        return false;
    }
    
    
    public String getInfo(int feld){
        return ""+(((Level[feld]*4000)/(Level[feld]+50))-XP[feld])+"/"+(Level[feld]*4000)/(Level[feld]+50)+"XP\nLevel:"+Level[feld]+"/MaximalLevel:"+MaxLevel[feld];
    }
    
    
    @Override
    public String toString(){
        char c=(char)145;
        String out=""+id.getMostSignificantBits()+c+id.getLeastSignificantBits()+c;
        for(int i=0;i<XP.length;i++){
            out=out+XP[i]+c+Level[i]+c+MaxLevel[i]+c;
        }
        return out;
    }
    
    
}
