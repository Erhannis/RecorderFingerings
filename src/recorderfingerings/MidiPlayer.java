/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recorderfingerings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Transmitter;
import javax.swing.Timer;

/**
 *
 * @author mewer
 */
public class MidiPlayer {
    public static MidiDevice receivingDevice = null;
    
    public boolean useExternalSynth = false;
    public Sequence sequence;
    private Sequencer sequencer;
    
    private MidiEventListener listener;
    private Timer eventTimer;
    
    public static void ensureReceivingDevice() throws MidiUnavailableException {
        if (receivingDevice == null) {
            receivingDevice = getReceivingDevice();
            //TODO This should probably be closed later.
            receivingDevice.open();
        }
    }
    
    public MidiPlayer(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        long time = System.currentTimeMillis();
        this.sequence = sequence;
        System.out.println("0 time " + (System.currentTimeMillis() - time));
        ensureReceivingDevice();
        System.out.println("1 time " + (System.currentTimeMillis() - time));
        System.out.println("2 time " + (System.currentTimeMillis() - time));

        sequencer = MidiSystem.getSequencer(false);
        System.out.println("3 time " + (System.currentTimeMillis() - time));
        Transmitter tx = sequencer.getTransmitter();
        System.out.println("4 time " + (System.currentTimeMillis() - time));
        Receiver rx = receivingDevice.getReceiver();
        System.out.println("5 time " + (System.currentTimeMillis() - time));
        tx.setReceiver(rx);
        System.out.println("6 time " + (System.currentTimeMillis() - time));

        sequencer.open();
        System.out.println("7 time " + (System.currentTimeMillis() - time));
        sequencer.setSequence(sequence);
        System.out.println("8 time " + (System.currentTimeMillis() - time));
    }
    
    public void hotswapSequence(Sequence sequence) throws InvalidMidiDataException {
        this.sequence = sequence;
        long pos = sequencer.getTickPosition();
        sequencer.setSequence(sequence);
        sequencer.setTickPosition(pos);
    }
    
    public static MidiDevice getReceivingDevice() throws MidiUnavailableException {
        for (MidiDevice.Info mdi: MidiSystem.getMidiDeviceInfo()) {
            MidiDevice dev = MidiSystem.getMidiDevice(mdi);
            if (dev.getMaxReceivers() != 0) {
//                String lcName = StringUtils.defaultString(mdi.getName()).toLowerCase(Locale.ENGLISH);
                String lcName = mdi.getName().toLowerCase(Locale.ENGLISH);
//                if (lcName.contains(useExternalSynth ? "usb": "java")) {
                    return dev;
//                }
            }
        }
        return null;
    }    
    
    public boolean isRunning() {
        return sequencer.isRunning();
    }
    
    public void start() {
        if (eventTimer != null) {
            eventTimer.stop();
        }
        eventTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMidiEventListener(MidiPlayer.this.listener);
            }
        });
        eventTimer.start();
        sequencer.start();
    }

    public void stop() {
        if (eventTimer != null) {
            eventTimer.stop();
        }
        sequencer.stop();
    }

    /**
     * Currently just assumes the first track in the sequence.
     * @param listener 
     */
    public void setMidiEventListener(final MidiEventListener listener) {
        this.listener = listener;
        listener.currentIndex = MidiUtilities.findEventIndex(sequence.getTracks()[0], sequencer.getTickPosition());
    }

    public boolean released = false;
    
    private void updateMidiEventListener(MidiEventListener listener) {
        //TODO Account for pausing.
        if (released) {
            eventTimer.stop();
            return;
        }
        long tick = sequencer.getTickPosition();
        MidiEvent curEvent = sequence.getTracks()[0].get(listener.currentIndex);
        int count = 0;
        while (curEvent.getTick() < tick) {
            curEvent = sequence.getTracks()[0].get(listener.currentIndex);
            listener.onEvent(curEvent);
            listener.currentIndex++;
            count++;
        }
        if (count > 1) {
            System.out.println("Lag " + (count - 1));
        }
        eventTimer.setDelay(Math.min((int)((curEvent.getTick() - tick) * millisPerTick()), 50));
    }
    
    public long millisPerTick() {
        if (sequence.getDivisionType() == Sequence.PPQ) {
            return (long)(60000 / (sequence.getResolution() * sequencer.getTempoInBPM() * sequencer.getTempoFactor()));
        } else {
            System.err.println("Oh heck!  Weird resolution!");
        }
        return 10;
    }
    
    public static abstract class MidiEventListener {
        public int currentIndex;
        public abstract void onEvent(MidiEvent event);
    }
}
