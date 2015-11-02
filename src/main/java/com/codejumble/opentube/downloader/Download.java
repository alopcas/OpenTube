/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codejumble.opentube.downloader;

import com.github.axet.vget.VGet;
import com.github.axet.wget.info.DownloadInfo;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.SwingWorker;

/**
 *
 * @author lope115
 */
public class Download extends SwingWorker {

    private final VGet v;
    private DownloadInfo downloadInfo;
    private File targetFile;
    private double progress;

    public Download(String videoURL, String path) throws MalformedURLException {
        targetFile = new File(path);
        v = new VGet(new URL(videoURL), new File(path));
        v.setTarget(targetFile);
        progress = 0;
    }

    public VGet getV() {
        return v;
    }

    @Override
    protected Object doInBackground() {
        setProgress(progress);
        v.download();
        return null;
    }

    @Override
    protected void done() {
        setProgress(100);
        System.out.println("Task completed");
    }

    public void refreshProgress() {
        DownloadInfo downloadInfo = v.getVideo().getInfo();
        if (downloadInfo != null && targetFile.exists()) {
            double progress = ((double)targetFile.length() / downloadInfo.getLength());
            setProgress(progress.intValu); //Percentage
        }
    }

}
