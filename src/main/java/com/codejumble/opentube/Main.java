/**
 * *****************************************************************************
 * Copyright (c) 2015 CodeJumble.com. All rights reserved.
 *
 * This file is part of OpenTube - www.codejumble.com
 *
 *  Foobar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  Foobar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************************************
 */
package com.codejumble.opentube;

import com.codejumble.opentube.downloader.DownloadManager;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main frame of the program. Implements functionalities related to the view
 *
 * @author alopcas
 */
public class Main extends javax.swing.JFrame implements PropertyChangeListener {

    /**
     * Software version
     */
    public static String SOFTWARE_VERSION = "0.1";

    // Font set
    private final Font PLACEHOLDER_FONT = new Font("Verdana", Font.ITALIC, 12);
    private final Font INPUT_FONT = new Font("Verdana", Font.PLAIN, 12);

    // Configuration
    private PropertiesConfiguration configuration;
    private ImageIcon appLogo;

    // Strings where settings are stored during execution
    private String configuredFolderForDownloadedMedia;
    private String tmpFilesFolder;
    private String logsFolder;
    private String settingsFilePath = "conf" + File.separator + "settings.conf";

    // Download manager to be used by default
    private DownloadManager defaultDownloadManager;

    // Logging
    static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Initilizes the main frame of the program. Loads settings and initializes
     * the components of the view.
     */
    public Main() {
        // Load configuration
        try {
            configuration = loadConfiguration();
        } catch (ConfigurationException e) {
            createErrorDialog(this, e.getMessage(), "Fatal error");
            System.exit(1);
        } catch (Exception e) {
            createErrorDialog(this, e.getMessage(), "Fatal error");
            System.exit(1);
        }

        initParameters();

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mediaFormatButtonGroup = new javax.swing.ButtonGroup();
        videoURLDownloadButton = new javax.swing.JButton();
        downloadProgressBar = new javax.swing.JProgressBar();
        videoURLField = new javax.swing.JTextField();
        pathURLLabel = new javax.swing.JLabel();
        pathField = new javax.swing.JTextField();
        videoURLLabel1 = new javax.swing.JLabel();
        fileNameField = new javax.swing.JTextField();
        resetVideoFileName();
        fileURLLabel = new javax.swing.JLabel();
        mp4FormatOption = new javax.swing.JRadioButton();
        aacFormatOption = new javax.swing.JRadioButton();
        aviFormatOption = new javax.swing.JRadioButton();
        fileFormatLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        mp3FormatOption = new javax.swing.JRadioButton();
        flvFormatOption = new javax.swing.JRadioButton();
        oggFormatOption = new javax.swing.JRadioButton();
        downloadQueueProgress = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        settingsItem = new javax.swing.JMenuItem();
        importItem = new javax.swing.JMenuItem();
        quitItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        clearItem = new javax.swing.JMenuItem();
        queueItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpPagesItem = new javax.swing.JMenuItem();
        aboutItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenTube v"+SOFTWARE_VERSION + "- www.codejumble.com");
        setIconImage(getIconImage());
        setName("OpenTubeMainFrame"); // NOI18N
        setResizable(false);

        videoURLDownloadButton.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        videoURLDownloadButton.setText("Download!");
        videoURLDownloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videoURLDownloadButtonActionPerformed(evt);
            }
        });

        downloadProgressBar.setMaximum(100);
        downloadProgressBar.setMinimum(0);
        downloadProgressBar.setStringPainted(true);

        videoURLField.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        videoURLField.setText("Video URL");
        videoURLField.setToolTipText("");
        videoURLField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                videoURLFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                videoURLFieldFocusLost(evt);
            }
        });

        pathURLLabel.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        pathURLLabel.setText("Path");

        pathField.setEditable(false);
        pathField.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        pathField.setText(configuredFolderForDownloadedMedia);
        pathField.setToolTipText("");
        pathField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pathFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pathFieldFocusLost(evt);
            }
        });

        videoURLLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        videoURLLabel1.setText("URL");

        fileNameField.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        fileNameField.setText("Destiny file name");
        fileNameField.setToolTipText("");
        fileNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fileNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fileNameFieldFocusLost(evt);
            }
        });

        fileURLLabel.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        fileURLLabel.setText("File");

        mediaFormatButtonGroup.add(mp4FormatOption);
        mp4FormatOption.setSelected(true);
        mp4FormatOption.setText("mp4");

        mediaFormatButtonGroup.add(aacFormatOption);
        aacFormatOption.setText("aac");
        aacFormatOption.setEnabled(false);

        mediaFormatButtonGroup.add(aviFormatOption);
        aviFormatOption.setText("avi");
        aviFormatOption.setEnabled(false);

        fileFormatLabel.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        fileFormatLabel.setText("Format");

        statusLabel.setText("Status:");

        status.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        status.setText("Ready");

        mediaFormatButtonGroup.add(mp3FormatOption);
        mp3FormatOption.setText("mp3");
        mp3FormatOption.setEnabled(false);

        mediaFormatButtonGroup.add(flvFormatOption);
        flvFormatOption.setText("flv");
        flvFormatOption.setEnabled(false);

        mediaFormatButtonGroup.add(oggFormatOption);
        oggFormatOption.setText("ogg");
        oggFormatOption.setEnabled(false);

        downloadQueueProgress.setText("0 of 0");

        fileMenu.setText("File");

        settingsItem.setText("Settings");
        settingsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsItemActionPerformed(evt);
            }
        });
        fileMenu.add(settingsItem);

        importItem.setText("Import list...");
        importItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importItemActionPerformed(evt);
            }
        });
        fileMenu.add(importItem);

        quitItem.setText("Quit");
        quitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitItem);

        menuBar.add(fileMenu);

        toolsMenu.setText("Tools");

        clearItem.setText("Clear cache");
        clearItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearItemActionPerformed(evt);
            }
        });
        toolsMenu.add(clearItem);

        queueItem.setText("Download queue");
        queueItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queueItemActionPerformed(evt);
            }
        });
        toolsMenu.add(queueItem);

        menuBar.add(toolsMenu);

        helpMenu.setText("Help");

        helpPagesItem.setText("Help pages");
        helpPagesItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpPagesItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpPagesItem);

        aboutItem.setText("About");
        aboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(downloadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(52, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(videoURLLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pathURLLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(fileURLLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pathField)
                            .addComponent(videoURLField)
                            .addComponent(fileNameField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(videoURLDownloadButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileFormatLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(mp4FormatOption)
                                    .addComponent(mp3FormatOption))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aacFormatOption)
                                    .addComponent(flvFormatOption))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aviFormatOption)
                                    .addComponent(oggFormatOption))))
                        .addGap(36, 36, 36))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(downloadQueueProgress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(fileFormatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(videoURLDownloadButton)
                                .addComponent(videoURLField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(videoURLLabel1))
                            .addComponent(mp4FormatOption))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pathURLLabel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mp3FormatOption)
                                .addComponent(aacFormatOption)
                                .addComponent(oggFormatOption))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(flvFormatOption)
                        .addComponent(aviFormatOption)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fileURLLabel)
                    .addComponent(fileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(downloadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(status)
                    .addComponent(downloadQueueProgress)))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Puts or removes placeholder text in the video URL field to make it more
     * user friendly. Also resets the file name in case of no input.
     *
     * @param evt Swing event
     */
    private void videoURLFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_videoURLFieldFocusGained
        if (videoURLField.getText().length() <= 0 || videoURLField.getText().equals("Video URL")) {
            videoURLField.setFont(INPUT_FONT);
            videoURLField.setText("");
            if (fileNameField.getText().length() <= 0 || fileNameField.getText().equals("Destiny file name")) {
                resetVideoFileName();
            }
        }
    }//GEN-LAST:event_videoURLFieldFocusGained
    /**
     * Puts a placeholder in case that the field is still empty after losing the
     * focus
     *
     * @param evt Swing event
     */
    private void videoURLFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_videoURLFieldFocusLost
        if (videoURLField.getText().length() <= 0 || videoURLField.getText().equals("Video URL")) {
            videoURLField.setFont(PLACEHOLDER_FONT);
            videoURLField.setText("Video URL");
        }
    }//GEN-LAST:event_videoURLFieldFocusLost
    /**
     * Puts or removes placeholder text in the file path field to make it more
     * user friendly. Also resets the file name in case of no input.
     *
     * @param evt Swing event
     */
    private void pathFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pathFieldFocusGained
        if (pathField.getText().length() <= 0 || pathField.getText().equals("Destiny file path")) {
            pathField.setFont(INPUT_FONT);
            pathField.setText("");
        }
    }//GEN-LAST:event_pathFieldFocusGained

    /**
     * Puts a placeholder in case that the field is still empty after losing the
     * focus
     *
     * @param evt Swing event
     */
    private void pathFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pathFieldFocusLost
        if (pathField.getText().length() <= 0 || pathField.getText().equals("Destiny file path")) {
            pathField.setFont(PLACEHOLDER_FONT);
            pathField.setText("Destiny file path");
        }
    }//GEN-LAST:event_pathFieldFocusLost
    /**
     * Adds the video to the queue and, in case there are not active downloads
     * currently, starts them.
     *
     * @param evt Swing event
     */
    private void videoURLDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videoURLDownloadButtonActionPerformed
        videoURLDownloadButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (isWaitingForTasks() && defaultDownloadManager.getState().equals(SwingWorker.StateValue.DONE)) {
                defaultDownloadManager = new DownloadManager(this, tmpFilesFolder, configuredFolderForDownloadedMedia);
            }
            defaultDownloadManager.addDownloadToQueue(videoURLField.getText(), fileNameField.getText(), getSelectedFormatOption());
            defaultDownloadManager.execute();

        } catch (MalformedURLException ex) {
            createErrorDialog(this, "The inputted URL doesn't match a valid format", "Wrong URL format");
        } catch (RuntimeException ex) {
            createErrorDialog(this, "The provided website is not supported", "Unsupported website");
        } catch (Exception e) {
            createErrorDialog(this, e.getMessage(), "Unexpected error");
        }
        videoURLDownloadButton.setEnabled(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        resetVideoFileName();
    }//GEN-LAST:event_videoURLDownloadButtonActionPerformed
    /**
     * Puts or removes placeholder text in the file name field to make it more
     * user friendly. Also resets the file name in case of no input.
     *
     * @param evt Swing event
     */
    private void fileNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fileNameFieldFocusGained
        if (fileNameField.getText().length() <= 0 || fileNameField.getText().equals("Destiny file name")) {
            fileNameField.setFont(INPUT_FONT);
            fileNameField.setText("");
        }
    }//GEN-LAST:event_fileNameFieldFocusGained
    /**
     * Puts a placeholder in case that the field is still empty after losing the
     * focus
     *
     * @param evt Swing event
     */
    private void fileNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fileNameFieldFocusLost
        if (fileNameField.getText().length() <= 0 || fileNameField.getText().equals("Destiny file name")) {
            fileNameField.setFont(PLACEHOLDER_FONT);
            fileNameField.setText("Destiny file name");
        }
    }//GEN-LAST:event_fileNameFieldFocusLost
    /**
     * Opens http://www.codejumble.com at System's default browser
     *
     * @param evt Swing event
     */
    private void helpPagesItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpPagesItemActionPerformed
        try {
            URI uri = new URL("http://www.codejumble.com").toURI();
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(uri);
                } catch (Exception e) {
                    createErrorDialog(this, e.getMessage(), "Error!");
                }
            }
        } catch (Exception ex) {
            createErrorDialog(this, ex.getMessage(), "Unexpected error");
        }
    }//GEN-LAST:event_helpPagesItemActionPerformed
    /**
     * Opens a small dialog showing some info about OpenTube
     *
     * @param evt Swing event
     */
    private void aboutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutItemActionPerformed
        JOptionPane.showMessageDialog(this, "OpenTube v" + SOFTWARE_VERSION + "\nVisit www.codejumble.com", "About OpenTube", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_aboutItemActionPerformed
    /**
     * Exits the program
     *
     * @param evt Swing event
     */
    private void quitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitItemActionPerformed
    /**
     * Opens the settings dialog and processes the changes performed (if
     * necessary)
     *
     * @param evt Swing event
     */
    private void settingsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsItemActionPerformed
        JTextField setting = new JTextField(configuredFolderForDownloadedMedia);
        Object[] settings = {
            "Downloads folder", setting
        };

        int option = JOptionPane.showConfirmDialog(null, settings, "Settings", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            configuration.setProperty("downloadFolder", setting.getText());
            try {
                logger.info("Saving settings");
                configuration.save();
            } catch (ConfigurationException ex) {
                createErrorDialog(this, ex.getMessage(), "Fatal error");
            }
        }
    }//GEN-LAST:event_settingsItemActionPerformed
    /**
     * Imports a list of videos contained in a csv file or plaintext format to
     * the download queue directly and starts downloading.
     *
     * @param evt Swing event
     */
    private void importItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importItemActionPerformed
        createErrorDialog(this, "This operation is not supported in this version", "Fatal error");
        throw new UnsupportedOperationException();
    }//GEN-LAST:event_importItemActionPerformed
    /**
     * Deletes all the files contained in temporal files folder (Default: 'tmp')
     *
     * @param evt Swing event
     */
    private void clearItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearItemActionPerformed
        try {
            //Clear tmp folder
            FileUtils.cleanDirectory(new File(tmpFilesFolder));
        } catch (IOException ex) {
            createErrorDialog(this, ex.getMessage(), "Fatal error");
        }
    }//GEN-LAST:event_clearItemActionPerformed
    /**
     * Displays the download queue, with certain information
     *
     * @param evt Swing event
     */
    private void queueItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queueItemActionPerformed
        createErrorDialog(this, "This operation is not supported in this version", "Fatal error");
        throw new UnsupportedOperationException();
    }//GEN-LAST:event_queueItemActionPerformed

    /**
     * Starts the program off.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            logger.error("Java error with text {}", ex.getMessage());
        } catch (InstantiationException ex) {
            logger.error("Java error with text {}", ex.getMessage());
        } catch (IllegalAccessException ex) {
            logger.error("Java error with text {}", ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logger.error("Java error with text {}", ex.getMessage());
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                logger.info("Starting the main frame");
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton aacFormatOption;
    private javax.swing.JMenuItem aboutItem;
    private javax.swing.JRadioButton aviFormatOption;
    private javax.swing.JMenuItem clearItem;
    private javax.swing.JProgressBar downloadProgressBar;
    private javax.swing.JLabel downloadQueueProgress;
    private javax.swing.JLabel fileFormatLabel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JLabel fileURLLabel;
    private javax.swing.JRadioButton flvFormatOption;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpPagesItem;
    private javax.swing.JMenuItem importItem;
    private javax.swing.ButtonGroup mediaFormatButtonGroup;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JRadioButton mp3FormatOption;
    private javax.swing.JRadioButton mp4FormatOption;
    private javax.swing.JRadioButton oggFormatOption;
    private javax.swing.JTextField pathField;
    private javax.swing.JLabel pathURLLabel;
    private javax.swing.JMenuItem queueItem;
    private javax.swing.JMenuItem quitItem;
    private javax.swing.JMenuItem settingsItem;
    private javax.swing.JLabel status;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JButton videoURLDownloadButton;
    private javax.swing.JTextField videoURLField;
    private javax.swing.JLabel videoURLLabel1;
    // End of variables declaration//GEN-END:variables

    /**
     * Checks if progress has changed and updates the download progress bar
     * value.
     *
     * @param pce
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if ("progress".equals(pce.getPropertyName())) {
            int progress = (Integer) pce.getNewValue();
            downloadProgressBar.setValue(progress);
        }
    }

    /**
     * Returns the version selected by the user for the output file
     *
     * @return desired format
     */
    private String getSelectedFormatOption() {
        String format = "mp4";
        for (Enumeration<AbstractButton> buttons = mediaFormatButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                format = button.getText();
            }
        }

        return format;
    }

    /**
     * Returns the current status of the program ('Ready', 'Downloading',
     * 'Converting')
     *
     * @return status of the program
     */
    public boolean isWaitingForTasks() {
        return status.getText().equals("Ready");
    }

    /**
     * Changes the current status to a new one
     *
     * @param newStatus
     */
    public void changeStatus(String newStatus) {
        logger.info("Status changed to {}", newStatus);
        status.setText(newStatus);
    }

    /**
     * Creates an error dialog with the desired content and dialog name
     *
     * @param frame target frame
     * @param content content of the dialog
     * @param name name of the dialog
     */
    public void createErrorDialog(JFrame frame, String content, String name) {
        logger.error("Error! with message {}", content);
        JOptionPane.showMessageDialog(frame,
                content,
                name,
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Initializes the parameters of the program such as folder paths, program
     * icons, etc.
     */
    private void initParameters() {
        logger.info("Initilizing parameters");
        String relativePathToDownloadFolder = (String) configuration.getProperty("downloadFolder");
        configuredFolderForDownloadedMedia = new File(relativePathToDownloadFolder).getAbsolutePath() + File.separator;
        String relativePathToTmpFolder = (String) configuration.getProperty("tmpFolder");
        tmpFilesFolder = new File(relativePathToTmpFolder).getAbsolutePath() + File.separator;
        String relativePathToLogsFolder = (String) configuration.getProperty("logsFolder");
        logsFolder = new File(relativePathToLogsFolder).getAbsolutePath() + File.separator;
        defaultDownloadManager = new DownloadManager(this, tmpFilesFolder, configuredFolderForDownloadedMedia);
//        ImageIcon img = new ImageIcon("C:\\Users\\lope115\\Documents\\NetBeansProjects\\tmpot\\src\\main\\resources\\media" + File.separator + "icon.png");
//        this.appLogo = img;
    }

    /**
     * Returns the configuration from the settings file.
     *
     * @return user settings
     * @throws ConfigurationException in case the file is corrupted or
     * unavailable/missing.
     */
    private PropertiesConfiguration loadConfiguration() throws ConfigurationException {
        logger.info("Loading user settings...");
        PropertiesConfiguration prop = new PropertiesConfiguration(settingsFilePath);
        return prop;
    }

    /**
     * Resets the file name field to a new value following the patern
     * 'video-yearMonthDay'
     */
    private void resetVideoFileName() {
        fileNameField.setText("video-" + new SimpleDateFormat("yyyyMMdd HHmmssSSS").format(new Date()));
    }

    /**
     * Updates the download queue status on the bottom. This method is called
     * every time the download manager enques a new download or a download is
     * done
     * @param currentlyDownloadingIndex index of the current download in the queue
     * @param downloadQueueSize total queue size
     */
    public void updateDownloadQueueStatus(int currentlyDownloadingIndex, int downloadQueueSize){
        downloadQueueProgress.setText(currentlyDownloadingIndex + " of " + downloadQueueSize);
    }
}
