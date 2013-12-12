/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recorderfingerings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiFileFormat;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author mewer
 */
public class RFMainWindow extends javax.swing.JFrame {

    /**
     * Creates new form RFMainWindow
     */
    public RFMainWindow() {
        initComponents();
        try {
            MidiPlayer.ensureReceivingDevice();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        labelFileName = new javax.swing.JLabel();
        labelTracks = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        spinTrack = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        labelSelectedTrack = new javax.swing.JLabel();
        labelChannels = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        cbChannel0 = new javax.swing.JCheckBox();
        cbChannel1 = new javax.swing.JCheckBox();
        cbChannel2 = new javax.swing.JCheckBox();
        cbChannel3 = new javax.swing.JCheckBox();
        cbChannel4 = new javax.swing.JCheckBox();
        cbChannel5 = new javax.swing.JCheckBox();
        cbChannel6 = new javax.swing.JCheckBox();
        cbChannel7 = new javax.swing.JCheckBox();
        cbChannel8 = new javax.swing.JCheckBox();
        cbChannel9 = new javax.swing.JCheckBox();
        cbChannel10 = new javax.swing.JCheckBox();
        cbChannel11 = new javax.swing.JCheckBox();
        cbChannel12 = new javax.swing.JCheckBox();
        cbChannel13 = new javax.swing.JCheckBox();
        cbChannel14 = new javax.swing.JCheckBox();
        cbChannel15 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnPlayPause = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setToolTipText("");

        labelFileName.setText("File: -");

        labelTracks.setText("Tracks: -");

        jLabel1.setText("Track:");

        spinTrack.setEnabled(false);
        spinTrack.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinTrackStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelFileName)
                    .add(labelTracks)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(spinTrack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(454, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(labelFileName)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(labelTracks)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(spinTrack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setTopComponent(jPanel1);

        labelSelectedTrack.setText("Track -");

        labelChannels.setText("Channels: -");

        cbChannel0.setText("Channel 0");
        cbChannel0.setEnabled(false);
        cbChannel0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel0ActionPerformed(evt);
            }
        });

        cbChannel1.setText("Channel 1");
        cbChannel1.setEnabled(false);
        cbChannel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel1ActionPerformed(evt);
            }
        });

        cbChannel2.setText("Channel 2");
        cbChannel2.setEnabled(false);
        cbChannel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel2ActionPerformed(evt);
            }
        });

        cbChannel3.setText("Channel 3");
        cbChannel3.setEnabled(false);
        cbChannel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel3ActionPerformed(evt);
            }
        });

        cbChannel4.setText("Channel 4");
        cbChannel4.setEnabled(false);
        cbChannel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel4ActionPerformed(evt);
            }
        });

        cbChannel5.setText("Channel 5");
        cbChannel5.setEnabled(false);
        cbChannel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel5ActionPerformed(evt);
            }
        });

        cbChannel6.setText("Channel 6");
        cbChannel6.setEnabled(false);
        cbChannel6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel6ActionPerformed(evt);
            }
        });

        cbChannel7.setText("Channel 7");
        cbChannel7.setEnabled(false);
        cbChannel7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel7ActionPerformed(evt);
            }
        });

        cbChannel8.setText("Channel 8");
        cbChannel8.setEnabled(false);
        cbChannel8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel8ActionPerformed(evt);
            }
        });

        cbChannel9.setText("Channel 9");
        cbChannel9.setEnabled(false);
        cbChannel9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel9ActionPerformed(evt);
            }
        });

        cbChannel10.setText("Channel 10");
        cbChannel10.setEnabled(false);
        cbChannel10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel10ActionPerformed(evt);
            }
        });

        cbChannel11.setText("Channel 11");
        cbChannel11.setEnabled(false);
        cbChannel11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel11ActionPerformed(evt);
            }
        });

        cbChannel12.setText("Channel 12");
        cbChannel12.setEnabled(false);
        cbChannel12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel12ActionPerformed(evt);
            }
        });

        cbChannel13.setText("Channel 13");
        cbChannel13.setEnabled(false);
        cbChannel13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel13ActionPerformed(evt);
            }
        });

        cbChannel14.setText("Channel 14");
        cbChannel14.setEnabled(false);
        cbChannel14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel14ActionPerformed(evt);
            }
        });

        cbChannel15.setText("Channel 15");
        cbChannel15.setEnabled(false);
        cbChannel15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChannel15ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbChannel0)
                    .add(cbChannel1)
                    .add(cbChannel2)
                    .add(cbChannel3)
                    .add(cbChannel4)
                    .add(cbChannel5)
                    .add(cbChannel6)
                    .add(cbChannel7)
                    .add(cbChannel8)
                    .add(cbChannel9)
                    .add(cbChannel10)
                    .add(cbChannel11)
                    .add(cbChannel12)
                    .add(cbChannel13)
                    .add(cbChannel14)
                    .add(cbChannel15))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(cbChannel0)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel8)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel12)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel14)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbChannel15)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel5);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(labelSelectedTrack)
                    .add(labelChannels))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 304, Short.MAX_VALUE)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 169, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(labelSelectedTrack)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(labelChannels)
                        .add(0, 145, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel4);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 563, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jSplitPane2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 298, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                    .add(jSplitPane2)
                    .addContainerGap()))
        );

        jSplitPane1.setLeftComponent(jPanel3);

        jToolBar1.setRollover(true);

        btnPlayPause.setText("|>");
        btnPlayPause.setFocusable(false);
        btnPlayPause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlayPause.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlayPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayPauseActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPlayPause);

        btnStop.setText("[_]");
        btnStop.setFocusable(false);
        btnStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        jToolBar1.add(btnStop);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        jMenu1.setText("File");

        jMenuItem1.setText("Open midi...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JFileChooser chooser = new JFileChooser();
    public File chosenFile;
    public Sequence sequence = null;
    public boolean[][] channels;
    public MidiPlayer midiPlayer;
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                chosenFile = file;
                MidiFileFormat mff = MidiSystem.getMidiFileFormat(file);
                sequence = MidiSystem.getSequence(file);
                
                Track[] tracks = sequence.getTracks();
                int trackCount = tracks.length;
                
                labelFileName.setText("File: " + file.getAbsolutePath());
                labelTracks.setText("Tracks: " + trackCount);
                spinTrack.setModel(new SpinnerNumberModel(0, 0, trackCount - 1, 1));
                spinTrack.setEnabled(true);
                channels = new boolean[trackCount][16];

                //System.out.println("Tracks: " + trackCount);
                for (int i = 0; i < trackCount; i++) {
                    Track t = tracks[i];
                    for (int j = 0; j < 16; j++) {
                        channels[i][j] = false;
                    }
                    //System.out.println("Track " + t + " ----------------------------------------------");
                    //System.out.println("Events: " + t.size());
                    for (int j = 0; j < t.size(); j++) {
                        MidiEvent event = t.get(j);
                        MidiMessage message = event.getMessage();
                        int status = message.getStatus();
                        int type = (status & 0xF0) >> 4;
                        int channel = status & 0x0F;
                        switch (type) {
                            case 0x8:
                                // Note off
                                break;
                            case 0x9:
                                // Note on
                                channels[i][channel] = true;
                                int pitch = (int)(message.getMessage()[1] & 0xFF);
                                //System.out.println("Event " + j + "  \tat " + event.getTick());
                                //System.out.println("Message " + Integer.toHexString(message.getStatus()) + " " + MeUtils.bytesToHex(message.getMessage()));
                                //System.out.println(event.getTick() + " \t - " + Integer.toHexString(channel) + " : " + Integer.toHexString(pitch));
                                break;
                        }
                    }
                }
                
                updateTrackPanel();
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(RecorderFingerings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RecorderFingerings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public JCheckBox getChannelCheckbox(int i) {
        switch (i) {
            case 0:
                return cbChannel0;
            case 1:
                return cbChannel1;
            case 2:
                return cbChannel2;
            case 3:
                return cbChannel3;
            case 4:
                return cbChannel4;
            case 5:
                return cbChannel5;
            case 6:
                return cbChannel6;
            case 7:
                return cbChannel7;
            case 8:
                return cbChannel8;
            case 9:
                return cbChannel9;
            case 10:
                return cbChannel10;
            case 11:
                return cbChannel11;
            case 12:
                return cbChannel12;
            case 13:
                return cbChannel13;
            case 14:
                return cbChannel14;
            case 15:
                return cbChannel15;
        }
        return null;
    }
    
    public void updateTrackPanel() {
        int selectedTrack = ((Integer)spinTrack.getValue()).intValue();
        labelSelectedTrack.setText("Track " + selectedTrack);
        int channelCount = 0;
        boolean openChannelFound = false;
        for (int i = 0; i < 16; i++) {
            if (channels[selectedTrack][i]) {
                channelCount++;
            }
            if (channels[selectedTrack][i]) {
                getChannelCheckbox(i).setEnabled(true);
                getChannelCheckbox(i).setSelected(!openChannelFound); // Just select the first.
                openChannelFound = true;
            } else {
                getChannelCheckbox(i).setEnabled(false);
                getChannelCheckbox(i).setSelected(false);
            }
        }
        labelChannels.setText("Channels: " + channelCount);
    }
    
    public void hotswapSequence() {
        if (midiPlayer != null) {
            try {
                midiPlayer.hotswapSequence(compileSequence());
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void spinTrackStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinTrackStateChanged
        updateTrackPanel();
    }//GEN-LAST:event_spinTrackStateChanged

    private void cbChannel15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel15ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel15ActionPerformed

    private void cbChannel14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel14ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel14ActionPerformed

    private void cbChannel13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel13ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel13ActionPerformed

    private void cbChannel12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel12ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel12ActionPerformed

    private void cbChannel11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel11ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel11ActionPerformed

    private void cbChannel10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel10ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel10ActionPerformed

    private void cbChannel9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel9ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel9ActionPerformed

    private void cbChannel8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel8ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel8ActionPerformed

    private void cbChannel7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel7ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel7ActionPerformed

    private void cbChannel6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel6ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel6ActionPerformed

    private void cbChannel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel5ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel5ActionPerformed

    private void cbChannel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel4ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel4ActionPerformed

    private void cbChannel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel3ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel3ActionPerformed

    private void cbChannel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel2ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel2ActionPerformed

    private void cbChannel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel1ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel1ActionPerformed

    private void cbChannel0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChannel0ActionPerformed
        hotswapSequence();
    }//GEN-LAST:event_cbChannel0ActionPerformed

    public Sequence compileSequence() {
        Sequence newSq = null;
                
        if (this.sequence != null) {
            try {
                newSq = MidiSystem.getSequence(chosenFile);
                Track[] tracks = newSq.getTracks();
                Track chosenTrack = tracks[((Integer)spinTrack.getValue()).intValue()];
                for (Track t : tracks) {
                    if (t != chosenTrack){
                        newSq.deleteTrack(t);
                    }
                }
                ArrayList<MidiEvent> eventsToDelete = new ArrayList<MidiEvent>();
                Track t = chosenTrack;
                boolean[] chosenChannels = new boolean[16];
                for (int i = 0; i < 16; i++) {
                    chosenChannels[i] = getChannelCheckbox(i).isSelected();
                }
                //TODO This could maybe be done in one pass.
                for (int i = 0; i < t.size(); i++) {
                    MidiEvent event = t.get(i);
                    MidiMessage message = event.getMessage();
                    int status = message.getStatus();
                    int type = (status & 0xF0) >> 4;
                    int channel = status & 0xF;
                    if ((type == 0x9) && (!chosenChannels[channel])) {
                        eventsToDelete.add(event);
                    }
                }
                for (MidiEvent event : eventsToDelete) {
                    t.remove(event);
                }
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return newSq;
    }
    
    private void btnPlayPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayPauseActionPerformed
        if (sequence != null) {
            try {
                if (midiPlayer == null) {
                    midiPlayer = new MidiPlayer(compileSequence());
                }
                if (midiPlayer.sequencer.isRunning()) {
                    midiPlayer.sequencer.stop();
                    btnPlayPause.setText("|>");
                } else {
                    midiPlayer.sequencer.start();
                    btnPlayPause.setText("||");
                }
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPlayPauseActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        if (sequence != null) {
            try {
                if (midiPlayer != null) {
                    midiPlayer.sequencer.stop();
                    btnPlayPause.setText("|>");
                }
                midiPlayer = new MidiPlayer(compileSequence());
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(RFMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnStopActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(RFMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RFMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RFMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RFMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RFMainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlayPause;
    private javax.swing.JButton btnStop;
    private javax.swing.JCheckBox cbChannel0;
    private javax.swing.JCheckBox cbChannel1;
    private javax.swing.JCheckBox cbChannel10;
    private javax.swing.JCheckBox cbChannel11;
    private javax.swing.JCheckBox cbChannel12;
    private javax.swing.JCheckBox cbChannel13;
    private javax.swing.JCheckBox cbChannel14;
    private javax.swing.JCheckBox cbChannel15;
    private javax.swing.JCheckBox cbChannel2;
    private javax.swing.JCheckBox cbChannel3;
    private javax.swing.JCheckBox cbChannel4;
    private javax.swing.JCheckBox cbChannel5;
    private javax.swing.JCheckBox cbChannel6;
    private javax.swing.JCheckBox cbChannel7;
    private javax.swing.JCheckBox cbChannel8;
    private javax.swing.JCheckBox cbChannel9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelChannels;
    private javax.swing.JLabel labelFileName;
    private javax.swing.JLabel labelSelectedTrack;
    private javax.swing.JLabel labelTracks;
    private javax.swing.JSpinner spinTrack;
    // End of variables declaration//GEN-END:variables
}
