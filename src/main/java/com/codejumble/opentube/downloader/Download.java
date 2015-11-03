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
    private int progress;
    private String endFormat;

    public Download(String videoURL, String path, String endFormat) throws MalformedURLException {
        targetFile = new File(path + "." + endFormat);
        this.endFormat = endFormat;
        v = new VGet(new URL(videoURL), new File(path));
        v.setTarget(targetFile);
        progress = 0;
        downloadInfo = v.getVideo().getInfo();
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
        // If there is no need of conversion
        setProgress(100);
        //Start conversion using Xuggle
            /*
         Conversion performed here
         */
    }

    public void refreshProgress() {
        downloadInfo = v.getVideo().getInfo();
        if (downloadInfo != null && targetFile.exists()) {
            double progressWithDecimals = ((double) targetFile.length() / downloadInfo.getLength());
            progress = (int) Math.round(progressWithDecimals * 100); //Percentage
            setProgress(progress);
        }
    }

}
