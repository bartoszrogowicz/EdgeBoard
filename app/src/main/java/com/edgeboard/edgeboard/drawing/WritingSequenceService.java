package com.edgeboard.edgeboard.drawing;

import android.os.Vibrator;

import com.edgeboard.edgeboard.drawing.domain.Alphabet;
import com.edgeboard.edgeboard.drawing.domain.CornerTriangle;
import com.edgeboard.edgeboard.drawing.domain.CornerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krystian on 16.02.18.
 */

public class WritingSequenceService {

    private TrianglesService trianglesService;
    private Vibrator vibrator;

    public List<CornerType> sequence = new ArrayList<>();
    private CornerType prevSequenceElement = CornerType.NONE;


    WritingSequenceService(TrianglesService trianglesService, Vibrator vibrator) {
        this.trianglesService = trianglesService;
        this.vibrator = vibrator;
    }

    List<CornerType>  updateSequence(float touchX, float touchY) {
        setPreviousSequenceElement();
        for (CornerTriangle cT : trianglesService.triangles) {
            if(cT.getRegion().contains((int) touchX, (int) touchY) && prevSequenceElement != cT.getCornerType()) {
                if(sequence.size() <= 5) {
                    sequence.add(cT.getCornerType());
                    vibrator.vibrate(200);
                }
            }
        }
        return sequence;
    }

    private void setPreviousSequenceElement() {
        if(sequence.size() < 1) {
            prevSequenceElement = CornerType.NONE;
        } else {
            prevSequenceElement = sequence.get(sequence.size() - 1);  // if number = 1 then would get current elem
        }
    }


    public Alphabet getEnumFromSequence() {
        return Alphabet.fromValue(sequence);
    }

    public void clearSequence() {
        sequence.clear();
    }

    public boolean isSequenceStopped() {
        if(!sequence.isEmpty()){
            return prevSequenceElement == sequence.get(sequence.size() - 1);
        }
        return true;

    }
}
