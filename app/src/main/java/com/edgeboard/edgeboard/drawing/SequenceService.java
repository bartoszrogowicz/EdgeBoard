package com.edgeboard.edgeboard.drawing;

/**
 * Created by Krystian on 2018-02-16.
 */

public interface SequenceService {

    void handleEndOfSequence();

    void updateSequence(final float x, final float y);

}
