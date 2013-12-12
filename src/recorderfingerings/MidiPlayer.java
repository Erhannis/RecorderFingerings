/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recorderfingerings;

import java.net.URL;
import java.util.Locale;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Transmitter;
import javax.swing.JOptionPane;

/**
 *
 * @author mewer
 */
public class MidiPlayer {
    public static MidiDevice receivingDevice = null;
    
    public boolean useExternalSynth = false;
    public Sequence sequence;
    public Sequencer sequencer;
    
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
}
