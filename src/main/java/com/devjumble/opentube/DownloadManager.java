/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devjumble.opentube;

import com.github.axet.vget.VGet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.SwingWorker;

/**
 *
 * @author lope115
 */
public class DownloadManager extends SwingWorker {

    private String path;
    private VGet v;

    public DownloadManager() {
        this.path = "C:"+File.separator+"Users"+File.separator+"lope115"+File.separator+"Desktop"+File.separator+""; //This must be retrieved from conf
    }

    public void addDownloadToQueue(String videoURL, String videoFileName) throws MalformedURLException {
        v = new VGet(new URL(videoURL), new File(path));
        v.setTarget(new File(path + "adasdssad.mp4")); //aqui va el videoFuileName
    }
    
    public void cancel(){
        this.cancel(true);
    }

    @Override
    protected Object doInBackground() throws Exception {
        v.download();
        v.getTarget();
        v.getVideo();
        return null;
    }
}
