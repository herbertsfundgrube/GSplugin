/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.herbert.gsplugin.fileInterface;
import com.github.herbert.gsplugin.datenstruktur.GSinteractorList;
import com.github.herbert.gsplugin.datenstruktur.Gslist;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
/**
 *
 * @author Leen
 */
public class fInterface {
	
	
    public Gslist GsLaden(){
        Gslist l=null;
        String rep="";
        try{
            //einlesen des die Gslist repräsentierenden Strings
            FileReader f=new FileReader("GS.txt");
            BufferedReader r=new BufferedReader(f);
            rep=r.readLine();
            r.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
        //todo: Verarbeitung des Strings
        return l;
    }
    public void GsSpeichern(Gslist l){
        try{
            //schreiben des die Gslist repräsentierenden Strings
            FileWriter f=new FileWriter("/GSplugin/GS.txt");
            BufferedWriter w=new BufferedWriter(f);
            w.write(l.toString());
            w.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
    }
    public GSinteractorList gsInteractorsLaden(){
        GSinteractorList l=null;
        String rep="";
        try{
            //einlesen des die Gruppenliste repräsentierenden Strings
            FileReader f=new FileReader("/GSplugin/GSinteractors.txt");
            BufferedReader r=new BufferedReader(f);
            rep=r.readLine();
            r.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
        //todo: Verarbeitung des Strings
        return l;
    }
    public void gsInteractorsSpeichern(GSinteractorList l){
        try{
            //schreiben des die Gruppenliste repräsentierenden Strings
            FileWriter f=new FileWriter("/GSplugin/GSinteractors.txt");
            BufferedWriter w=new BufferedWriter(f);
            w.write(l.toString());
            w.close();
            f.close();
        }
        catch(IOException i){
            //Fehler beim Dateizugriff
        }
    }
    
}
