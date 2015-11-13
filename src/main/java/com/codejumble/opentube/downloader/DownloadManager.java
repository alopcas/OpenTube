/**
 * *****************************************************************************
 * Copyright (c) 2015 CodeJumble.com. All rights reserved.
 *
 * This file is part of OpenTube - www.codejumble.com
 *
 * OpenTube is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * OpenTube is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * OpenTube. If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************************************
 */
package com.codejumble.opentube.downloader;

import com.codejumble.opentube.Main;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lope115
 */
public class DownloadManager extends SwingWorker {

    private File downloadFolder;
    private File tmpFolder;
    private List<Download> downloads;
    private final Main mainFrame;

    //Logging 
    Logger logger = LoggerFactory.getLogger(Download.class);

    public DownloadManager(Main frame, String tmpFolderPath, String downloadFolderPath) {
        logger.info("Initilizing the download manager");
        this.downloadFolder = new File(downloadFolderPath);
        this.tmpFolder = new File(tmpFolderPath);
        this.downloads = new ArrayList<Download>();
        this.mainFrame = frame;
    }

    public void addDownloadToQueue(String mediaURL, String mediaFileName, String endFormat) throws MalformedURLException {
        logger.info("Adding download to queue with values URL={}, output file={}", mediaURL, downloadFolder.getAbsolutePath() + mediaFileName + endFormat);
        Download download = new Download(mediaURL, tmpFolder.getAbsolutePath(), mediaFileName, endFormat);
        downloads.add(download);
    }

    @Override
    protected Object doInBackground() throws Exception {
        try {
            logger.info("Starting the download queue...");
            for (int i = 0; i < downloads.size(); i++) {
                logger.info("Starting download number {}", i);
                Download e = downloads.get(i);
                mainFrame.changeStatus("Downloading");
                e.addPropertyChangeListener(mainFrame);
                e.execute();
                while (e.getProgress() < 100) {
                    e.refreshProgress();
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException ex) {
                        //TODO
                    }
                }
                //TODO format converting
//            if (!e.getEndFormat().equals("mp4")) {
//                //if conversion needs to be performed
//                mainFrame.changeStatus("Converting");
//                String tmp = e.getTargetFile().getAbsolutePath();
//                String tmp2 = tmpFolder.getAbsolutePath() + File.separator + e.getTargetFile().getName().replace("mp4", e.getEndFormat());
//
//                IMediaReader reader = ToolFactory.makeReader(tmp);
//                reader.addListener(ToolFactory.makeWriter(tmp2, reader));
//                while (reader.readPacket() == null)
//   ;
//            }
                //Move downloaded file to final destination
                logger.info("Moving file to downloads folder...");
                mainFrame.changeStatus("Locating");
                File currentDownload = e.getTargetFile();
                FileUtils.copyFile(currentDownload, new File(downloadFolder + File.separator + currentDownload.getName()));

                //Clear tmp folder
                logger.info("Cleaning cached elements");
                FileUtils.cleanDirectory(tmpFolder);
            }
        } catch (Exception e) {

        }
        downloads.clear();
        return null;
    }

    @Override
    protected void done() {
        //When the job is done, switch the status
        logger.info("Downloads are complete");
        mainFrame.changeStatus("Ready");
    }

}
