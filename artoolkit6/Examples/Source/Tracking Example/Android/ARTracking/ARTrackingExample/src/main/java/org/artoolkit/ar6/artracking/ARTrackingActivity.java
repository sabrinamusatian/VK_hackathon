package org.artoolkit.ar6.artracking;

import android.os.Bundle;
import android.widget.FrameLayout;

import org.artoolkit.ar6.base.ARActivity;
import org.artoolkit.ar6.base.rendering.ARRenderer;

/**
 * A very simple example of extending ARActivity to create a new AR application.
 */
public class ARTrackingActivity extends ARActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Calls ARActivity's ctor, abstract class of AR6J
        setContentView(R.layout.main);
    }

    /**
     * Provide our own ARTrackingRenderer.
     */
    @Override
    protected ARRenderer supplyRenderer() {
        return new ARTrackingRenderer();
    }

    /**
     * Use the FrameLayout in this Activity's UI.
     */
    @Override
    protected FrameLayout supplyFrameLayout() {
        return (FrameLayout) this.findViewById(R.id.mainFrameLayout);
    }
}